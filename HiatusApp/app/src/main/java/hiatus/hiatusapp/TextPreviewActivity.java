package hiatus.hiatusapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import hiatus.hiatusapp.ContributionBundle.ContributionBundle;
import hiatus.hiatusapp.ContributionContent.TextContent;
import hiatus.hiatusapp.ContributionContext.TextContext;

public class TextPreviewActivity extends AppCompatActivity {

    private static final String TAG = "TextPreviewActivity";

    TextView text_preview;
    TextView title_preview;
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


        text_preview = (TextView) findViewById(R.id.text_preview);
        title_preview = (TextView) findViewById(R.id.title_preview);
        modifyButton = (Button) findViewById(R.id.modify);
        sendButton = (Button) findViewById(R.id.send);

        content.display(title_preview, text_preview);

        //if modifications of the contributions are allowed, then the button is enabled
        if (context.isModificationsAllowed()){
            modifyButton.setEnabled(true);
        }

        modifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TextPreviewActivity.this, TextActivity.class);
                i.putExtra("content", content);
                startActivity(i);
            }
        });

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // create a bundle
                String id = DatabaseHelper.newContributionBundleId(context.getId());
                ContributionBundle bundle = new ContributionBundle(id, context.getId(), content);

                // save the bundle to db
                DatabaseHelper.saveContributionBundle(bundle);

                Toast.makeText(TextPreviewActivity.this, "Contribution successfully sent.", Toast.LENGTH_SHORT).show();

                /*Intent i = new Intent(TextPreviewActivity.this, SendActivity.class);
                i.putExtra("content", content);
                startActivity(i);*/
            }
        });


    }
}
