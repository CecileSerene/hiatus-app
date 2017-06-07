package hiatus.hiatusapp.admin;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import hiatus.hiatusapp.DatabaseHelper;
import hiatus.hiatusapp.contribution.base.ContributionBundle;
import hiatus.hiatusapp.contribution.text.TextContent;
import hiatus.hiatusapp.previews.PreviewTextFragment;
import hiatus.hiatusapp.R;

public class TextContributionPreviewActivity extends FragmentActivity {

    TextContent content;
    String bundleId;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_contribution_preview);

        Intent i = getIntent();
        content = i.getParcelableExtra("content");
        bundleId = i.getStringExtra("bundleId");
        userId = i.getStringExtra("userId");

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, PreviewTextFragment.newInstance(content))
                .commit();


        Button buttonAccept = (Button) findViewById(R.id.accept);
        buttonAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper.changeStateContibutionBundle(userId, bundleId, ContributionBundle.ACCEPTED);
                Toast.makeText(TextContributionPreviewActivity.this, "The contribution has been accepted", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        Button buttonDeny = (Button) findViewById(R.id.deny);
        buttonDeny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper.changeStateContibutionBundle(userId, bundleId, ContributionBundle.DENIED);
                Toast.makeText(TextContributionPreviewActivity.this, "The contribution has been denied", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
