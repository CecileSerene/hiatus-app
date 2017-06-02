package hiatus.hiatusapp.MakeAContribution;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import hiatus.hiatusapp.ContributionBundle.ContributionBundle;
import hiatus.hiatusapp.ContributionContent.TextContent;
import hiatus.hiatusapp.ContributionContext.TextContext;
import hiatus.hiatusapp.DatabaseHelper;
import hiatus.hiatusapp.PreviewFragments.PreviewTextFragment;
import hiatus.hiatusapp.R;

public class TextPreviewActivity extends FragmentActivity {

    private static final String TAG = "TextPreviewActivity";

    Button modifyButton;
    Button sendButton;
    TextContext context;
    TextContent content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);

        Intent i = getIntent();
        content = i.getParcelableExtra("content");
        context = i.getParcelableExtra("context");

        modifyButton = (Button) findViewById(R.id.modify);
        sendButton = (Button) findViewById(R.id.send);

        // add the preview fragment
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, PreviewTextFragment.newInstance(content))
                .commit();

        //if modifications of the contributions are allowed, then the button is enabled
        if (context.isModificationsAllowed()){
            modifyButton.setEnabled(true);
        }

        modifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // create a bundle
                String id = DatabaseHelper.newContributionBundleId(context.getId());
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
                ContributionBundle bundle = new ContributionBundle(
                        id, user.getUid(), user.getDisplayName(), context.getId(), content
                );

                // save the bundle to db
                DatabaseHelper.saveContributionBundle(bundle);
                Toast.makeText(TextPreviewActivity.this, "Contribution successfully sent.", Toast.LENGTH_SHORT).show();
            }
        });


    }
}