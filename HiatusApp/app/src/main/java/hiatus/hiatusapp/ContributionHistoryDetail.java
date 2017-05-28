package hiatus.hiatusapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ContributionHistoryDetail extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contribution_history_detail);

        // Receive the intent
        Intent i = getIntent();

        // Populate the view with contribution context
        ((TextView) findViewById(R.id.contribution_title)).setText(i.getStringExtra("title"));
        ((TextView) findViewById(R.id.contribution_theme)).setText(i.getStringExtra("theme"));
        ((TextView) findViewById(R.id.contribution_date)).setText(i.getStringExtra("date"));
        ((TextView) findViewById(R.id.contribution_state)).setText(i.getStringExtra("state"));
    }

    public void quitDetail(View view) {
        finish();
    }
}
