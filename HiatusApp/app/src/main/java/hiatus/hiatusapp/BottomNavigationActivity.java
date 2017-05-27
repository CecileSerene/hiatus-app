package hiatus.hiatusapp;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import hiatus.hiatusapp.dummy.DummyContent;

public class BottomNavigationActivity extends FragmentActivity {

    private static final String SELECTED_ITEM = "arg_selected_item";
    private BottomNavigationView mBottomNav;
    private TextView mTextMessage;
    private int mSelectedItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation);

        mTextMessage = (TextView) findViewById(R.id.title);

        mBottomNav = (BottomNavigationView) findViewById(R.id.navigation);

        // set the navigation listeners to change fragments when selecting menu items
        mBottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectFragment(item);
                return true;
            }
        });

        // Set the first fragment

        Fragment frag = ContributionFragment.newInstance();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, frag)
                .commit();
    }

    // Replaces the fragment in the fragment container by a new one
    private void selectFragment(MenuItem item) {
        Fragment frag = null;

        switch (item.getItemId()) {
            case R.id.navigation_home:
                frag = ContributionFragment.newInstance();
                break;
            case R.id.navigation_history:
                frag = HistoryFragment.newInstance();
                break;
            default:
                break;
        }

        mSelectedItem = item.getItemId();

        // uncheck the other items.
        for (int i = 0; i< mBottomNav.getMenu().size(); i++) {
            MenuItem menuItem = mBottomNav.getMenu().getItem(i);
            menuItem.setChecked(menuItem.getItemId() == item.getItemId());
        }

        mTextMessage.setText(item.getTitle());

        if (frag != null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, frag)
                    .addToBackStack(null)
                    .commit();
        }
    }

    @Override
    public void onBackPressed() {
        // when pressing back,
        // go to home fragment if it is not already the case
        System.out.println("in backpressed");
        MenuItem homeItem = mBottomNav.getMenu().getItem(0);
        if (mSelectedItem != homeItem.getItemId()) {
            // select home item
            selectFragment(homeItem);
        } else {
            super.onBackPressed();
        }
    }
}
