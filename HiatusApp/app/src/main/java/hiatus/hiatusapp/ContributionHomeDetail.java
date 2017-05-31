package hiatus.hiatusapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import hiatus.hiatusapp.ContributionContext.ContributionContext;

import hiatus.hiatusapp.ContributionContext.PhotoContext;
import hiatus.hiatusapp.ContributionContext.TextContext;

public class ContributionHomeDetail extends Activity {

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

        if (context instanceof TextContext) {
            Intent i = new Intent(this, TextActivity.class);
            i.putExtra("context",context);
            startActivity(i);
        } else if (context instanceof PhotoContext) {
            Intent i = new Intent(this, PhotoActivity.class);
            i.putExtra("context",context);
            startActivity(i);
        }
        else {
            Log.i("here", "Not anything at all");
        }
    }

}
