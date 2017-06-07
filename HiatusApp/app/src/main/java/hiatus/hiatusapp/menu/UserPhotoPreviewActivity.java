package hiatus.hiatusapp.menu;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.StorageReference;

import hiatus.hiatusapp.DatabaseHelper;
import hiatus.hiatusapp.R;
import hiatus.hiatusapp.contribution.photo.PhotoContent;
import hiatus.hiatusapp.previews.PreviewPhotoFragment;

public class UserPhotoPreviewActivity extends FragmentActivity {

    private static final String TAG = "PhotoPreviewActivity";

    PreviewPhotoFragment mImageFrag;
    PhotoContent content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preview_basic);

        Intent i = getIntent();
        content = i.getParcelableExtra("content");
        Log.d(TAG, content.getUrl());

        downloadPhoto();

        // add image preview fragment
        mImageFrag = PreviewPhotoFragment.newInstance(content);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, mImageFrag)
                .commit();
    }

    private void downloadPhoto() {
        // get reference to the photo file in the database
        StorageReference ref = DatabaseHelper.getStorageReference().child(content.getUrl());

        // download the photo
        ref.getBytes(Long.MAX_VALUE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                content.setPhoto(BitmapFactory.decodeByteArray(bytes, 0, bytes.length));
                mImageFrag.updateImage(content.getPhoto());
                Log.d(TAG, "download_photo:SUCCESS:" + content.getUrl());
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(TAG, "download_photo:FAIL:" + content.getUrl());
            }
        });
    }
}
