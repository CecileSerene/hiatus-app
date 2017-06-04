package hiatus.hiatusapp.contribution.text;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import hiatus.hiatusapp.contribution.base.ContributionBundle;
import hiatus.hiatusapp.contribution.base.ContributionContent;
import hiatus.hiatusapp.DatabaseHelper;
import hiatus.hiatusapp.menu.MenuActivity;
import hiatus.hiatusapp.previews.PreviewTextFragment;
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
                // get a new bundle id
                String id = DatabaseHelper.newContributionBundleId(context.getId());

                // create the database-adapted content model
                ContributionContent.Model model = content.toModel();

                // build the bundle
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                ContributionBundle bundle = new ContributionBundle(
                        id, user.getUid(), user.getDisplayName(), context.getId(), model);

                // save the bundle to db
                DatabaseHelper.saveContributionBundle(bundle);
                Toast.makeText(TextPreviewActivity.this, "Contribution successfully sent.", Toast.LENGTH_SHORT).show();

                //go back to menu
                startActivity(new Intent(v.getContext(), MenuActivity.class));
            }
        });


    }
}
