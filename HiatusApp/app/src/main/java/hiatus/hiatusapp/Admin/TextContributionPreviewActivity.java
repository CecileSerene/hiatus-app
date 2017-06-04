package hiatus.hiatusapp.Admin;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import hiatus.hiatusapp.ContributionText.TextContent;
import hiatus.hiatusapp.PreviewFragments.PreviewTextFragment;
import hiatus.hiatusapp.R;

public class TextContributionPreviewActivity extends FragmentActivity {

    TextContent content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_contribution_preview);

        Intent i = getIntent();
        content = i.getParcelableExtra("content");


        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, PreviewTextFragment.newInstance(content))
                .commit();


        Button buttonAccept = (Button) findViewById(R.id.accept);
        buttonAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO here the state must change
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
