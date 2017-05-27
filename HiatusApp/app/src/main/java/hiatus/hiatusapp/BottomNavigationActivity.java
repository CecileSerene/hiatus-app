package hiatus.hiatusapp;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import hiatus.hiatusapp.dummy.DummyContent;

public class BottomNavigationActivity extends FragmentActivity implements ContributionFragment.onContributionSelectedListener{

    private TextView mTextMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation);

        mTextMessage = (TextView) findViewById(R.id.title);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);

        // set the navigation listeners to change fragments when selecting menu items
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    // TODO set the change of view here
                    case R.id.navigation_home:
                        mTextMessage.setText(R.string.title_home);
                        setContributionFragment();
                        return true;
                    case R.id.navigation_history:
                        mTextMessage.setText(R.string.title_history);
                        return true;
                    case R.id.navigation_profile:
                        mTextMessage.setText(R.string.title_profile);
                        return true;
                }
                return false;
            }
        });

        // We're now going to create the first fragment and put it inside the FrameLayout.

        // If we're restoring from a previous state, don't do anything
        if (savedInstanceState != null) {
            return;
        }

        // Create the first fragment
        ContributionFragment firstFragment = new ContributionFragment();

        // In case this activity was started with special instructions from an
        // Intent, pass the Intent's extras to the fragment as arguments
        firstFragment.setArguments(getIntent().getExtras());

        // Add the fragment to the 'fragment_container' FrameLayout
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, firstFragment).commit();
    }

    private void setContributionFragment() {
        ContributionFragment fragment = new ContributionFragment();
        // Add the fragment to the 'fragment_container' FrameLayout
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, fragment).commit();
    }

    @Override
    public void onContributionSelected(DummyContent.DummyItem item) {
        Log.i("DEBUG", "In onContributionSelected!");
    }
}
