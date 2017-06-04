package hiatus.hiatusapp.ContributionPhoto;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;

import hiatus.hiatusapp.ContributionBase.ContributionBundle;
import hiatus.hiatusapp.ContributionBase.ContributionContent;
import hiatus.hiatusapp.ContributionBase.ContributionContext;
import hiatus.hiatusapp.ContributionText.TextPreviewActivity;
import hiatus.hiatusapp.DatabaseHelper;
import hiatus.hiatusapp.Menu.MenuActivity;
import hiatus.hiatusapp.PreviewFragments.PreviewPhotoFragment;
import hiatus.hiatusapp.R;

public class PhotoActivity extends FragmentActivity {

    PreviewPhotoFragment mImageFrag;
    PhotoContext context;
    PhotoContent content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        Intent i = getIntent();
        context = i.getParcelableExtra("context");

        content = new PhotoContent(context.getId());

        // add image preview fragment
        mImageFrag = PreviewPhotoFragment.newInstance(content);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, mImageFrag)
                .commit();

        Button saveButton = (Button) findViewById(R.id.send);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // upload photo then go back to menu
                uploadPhoto();
                startActivity(new Intent(PhotoActivity.this, MenuActivity.class));
            }
        });

    }

    static final int REQUEST_IMAGE_CAPTURE = 1;

    public void takePicture(View view) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");

            mImageFrag.updateImage(imageBitmap);
            content.setPhoto(imageBitmap);
        }
    }

    public void setTitle(View view){
        LayoutInflater inflater = getLayoutInflater();
        View popUpLayout = inflater.inflate(R.layout.popup_title, null);

        final EditText mTitle = (EditText) popUpLayout.findViewById(R.id.title);
        mTitle.setText(content.getTitle());

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Titre");
        alert.setView(popUpLayout);
        alert.setCancelable(true);

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {}
        });
        alert.setPositiveButton("Ajouter", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                String title = mTitle.getText().toString();
                content.setTitle(title);

                Toast.makeText(getBaseContext(), "Tu as ajouté le titre : " + title, Toast.LENGTH_SHORT).show();
            }
        });

        AlertDialog dialog = alert.create();
        dialog.show();
    }

    private void uploadPhoto(){
        StorageReference imagesRef = DatabaseHelper.getPhotoContentStorageReference();

        // transform image bitmap to input stream
        int byteSize = content.getPhoto().getRowBytes() * content.getPhoto().getHeight();
        ByteBuffer buffer = ByteBuffer.allocate(byteSize);
        content.getPhoto().copyPixelsToBuffer(buffer);
        ByteArrayInputStream bs = new ByteArrayInputStream(buffer.array());


        // upload file to Firebase Storage using an async task
        UploadTask uploadTask = imagesRef.putStream(bs);

        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // TODO handle unsuccessful uploads
            }
        }).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {

                // gets the URL of the newly uploaded file
                @SuppressWarnings("VisibleForTests")
                Uri downloadUrl = task.getResult().getDownloadUrl();
                content.setUrl(downloadUrl);

                saveBundleToDb();
            }
        });
    }

    private void saveBundleToDb() {
        // get a new bundle id
        String id = DatabaseHelper.newContributionBundleId(context.getId());

        // build the bundle
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        ContributionBundle bundle = new ContributionBundle(
                id, user.getUid(), user.getDisplayName(), context.getId(), content.toModel());

        // save bundle to db
        DatabaseHelper.saveContributionBundle(bundle);
        Toast.makeText(PhotoActivity.this, "Contribution successfully sent.", Toast.LENGTH_SHORT).show();
    }
}
