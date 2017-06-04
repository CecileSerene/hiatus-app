package hiatus.hiatusapp.menu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import hiatus.hiatusapp.contribution.base.ContributionContext;

import hiatus.hiatusapp.contribution.photo.PhotoActivity;
import hiatus.hiatusapp.contribution.text.TextActivity;
import hiatus.hiatusapp.R;

public class ContributionHomeDetail extends Activity {

    private static String TAG = "ContributionHomeDetail";
    ContributionContext context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contribution_home_detail);

        // Receive the intent
        Intent i = getIntent();
        context = i.getParcelableExtra("context");

        // Populate the view with contribution context
        ((TextView) findViewById(R.id.contribution_title)).setText(context.getTitle());
        ((TextView) findViewById(R.id.contribution_theme)).setText(context.getTheme());
        ((TextView) findViewById(R.id.contribution_instructions)).setText(context.getInstructions());
    }

    public void quitDetail(View view) {
        finish();
    }

    public void contribuer(View view) {

        if (context.getType() == ContributionContext.TYPE_TEXT) {
            Intent i = new Intent(this, TextActivity.class);
            i.putExtra("context", context);
            startActivity(i);
        } else if (context.getType() == ContributionContext.TYPE_PHOTO) {
            Intent i = new Intent(this, PhotoActivity.class);
            i.putExtra("context", context);
            startActivity(i);
        }
        else {
            Log.e(TAG, "Not anything at all");
        }
    }
}
