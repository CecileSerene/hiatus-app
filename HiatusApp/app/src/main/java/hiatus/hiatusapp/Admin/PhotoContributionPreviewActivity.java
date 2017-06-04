package hiatus.hiatusapp.Admin;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import hiatus.hiatusapp.ContributionPhoto.PhotoContent;
import hiatus.hiatusapp.PreviewFragments.PreviewPhotoFragment;
import hiatus.hiatusapp.R;

public class PhotoContributionPreviewActivity extends FragmentActivity {

    PreviewPhotoFragment mImageFrag;
    PhotoContent content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_contribution_preview);

        Intent i = getIntent();
        content = i.getParcelableExtra("content");

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
}
