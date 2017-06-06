package hiatus.hiatusapp.admin;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.StorageReference;

import hiatus.hiatusapp.DatabaseHelper;
import hiatus.hiatusapp.contribution.photo.PhotoContent;
import hiatus.hiatusapp.previews.PreviewPhotoFragment;
import hiatus.hiatusapp.R;

public class PhotoContributionPreviewActivity extends FragmentActivity {

    private static final String TAG = "PhotoPreviewActivity";

    PreviewPhotoFragment mImageFrag;
    PhotoContent content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_contribution_preview);

        Intent i = getIntent();
        content = i.getParcelableExtra("content");
        Log.d(TAG, content.getUrl());

        downloadPhoto();

        // add image preview fragment
        mImageFrag = PreviewPhotoFragment.newInstance(content);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, mImageFrag)
                .commit();

        Button buttonAccept = (Button) findViewById(R.id.accept);
        buttonAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO here the state must change
            }
        });

        Button buttonDeny = (Button) findViewById(R.id.deny);
        buttonDeny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO here the state must change
            }
        });
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
