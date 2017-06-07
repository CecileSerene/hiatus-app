package hiatus.hiatusapp.admin;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import hiatus.hiatusapp.DatabaseHelper;
import hiatus.hiatusapp.contribution.base.ContributionBundle;
import hiatus.hiatusapp.contribution.text.TextContent;
import hiatus.hiatusapp.previews.PreviewTextFragment;
import hiatus.hiatusapp.R;

import static hiatus.hiatusapp.DatabaseHelper;

public class TextContributionPreviewActivity extends FragmentActivity {

    TextContent content;
    int bundleId;
    int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_contribution_preview);

        Intent i = getIntent();
        content = i.getParcelableExtra("content");
        bundleId = i.getIntExtra("bundleId",0);
        userId = i.getIntExtra("userId",0);



        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, PreviewTextFragment.newInstance(content))
                .commit();


        Button buttonAccept = (Button) findViewById(R.id.accept);
        buttonAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper.changeStateContibutionBundle(bundleId, userId, ContributionBundle.ACCEPTED);
            }
        });

        Button buttonDeny = (Button) findViewById(R.id.deny);
        buttonAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO here the state must change
            }
        });
    }
}
