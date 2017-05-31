package hiatus.hiatusapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import hiatus.hiatusapp.ContributionContext.ContributionContext;

public class ContributionHistoryDetail extends Activity {

    ContributionContext context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contribution_history_detail);

        // Receive the intent

        Intent i = getIntent();
        context = i.getParcelableExtra("context");

        // Populate the view with contribution context
        ((TextView) findViewById(R.id.contribution_title)).setText(context.getTitle());
        ((TextView) findViewById(R.id.contribution_theme)).setText(context.getTheme());
        ((TextView) findViewById(R.id.contribution_date)).setText(i.getStringExtra("date"));
        ((TextView) findViewById(R.id.contribution_state)).setText(i.getStringExtra("state"));
    }

    public void quitDetail(View view) {
        finish();
    }
}
