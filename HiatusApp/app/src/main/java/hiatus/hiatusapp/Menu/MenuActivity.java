package hiatus.hiatusapp.Menu;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentActivity;
import android.view.MenuItem;

import hiatus.hiatusapp.R;


public class MenuActivity extends FragmentActivity {

    private BottomNavigationView mBottomNav;
    private int mSelectedItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        mBottomNav = (BottomNavigationView) findViewById(R.id.navigation);

        mBottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectFragment(item);
                return true;
            }
        });

        // initialize with first fragment
        MenuItem homeItem = mBottomNav.getMenu().getItem(0);
        selectFragment(homeItem);
        mSelectedItem = homeItem.getItemId();
    }

    // Replaces the fragment in the fragment container by a new one
    private void selectFragment(MenuItem item) {
        Fragment frag = null;

        switch (item.getItemId()) {
            case R.id.navigation_home:
                frag = MenuHomeFragment.newInstance();
                break;
            case R.id.navigation_history:
                frag = MenuHistoryFragment.newInstance();
                break;
            case R.id.navigation_profile:
                frag = MenuProfileFragment.newInstance();
                break;
            default:
                break;
        }

        mSelectedItem = item.getItemId();

        // uncheck the other items.
        for (int i = 0; i < mBottomNav.getMenu().size(); i++) {
            MenuItem menuItem = mBottomNav.getMenu().getItem(i);
            menuItem.setChecked(menuItem.getItemId() == item.getItemId());
        }

        if (frag != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, frag)
                    .addToBackStack(null)  // allows going back without changing activity (see onBackPressed() below)
                    .commit();
        }
    }

    @Override
    public void onBackPressed() {
        // when pressing back, we want to go to home fragment if it is not already the case
        MenuItem homeItem = mBottomNav.getMenu().getItem(0);
        if (mSelectedItem != homeItem.getItemId()) {
            // select home item
            selectFragment(homeItem);
        }
        // or go to previous activity otherwise
        else {
            super.onBackPressed();
        }
    }
}
