package hiatus.hiatusapp.menu;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.MenuItem;

import hiatus.hiatusapp.R;


public class MenuActivity extends FragmentActivity {

    private final static String TAG = "MenuActivity";
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
                selectFragment(item, true);
                return true;
            }
        });

        // initialize with first fragment
        MenuItem homeItem = mBottomNav.getMenu().getItem(0);
        Log.d(TAG, homeItem.getTitle().toString());
        mSelectedItem = homeItem.getItemId();
        selectFragment(homeItem, false);
    }

    // Replaces the fragment in the fragment container by a new one
    private void selectFragment(MenuItem item, boolean addToBackStack) {
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
            menuItem.setChecked(menuItem.getItemId() == mSelectedItem);
        }

        if (frag != null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, frag);
            if (addToBackStack) {
                // allows to access the precedent fragment through a back press
                transaction.addToBackStack(null);
            }
            transaction.commit();
        }
    }

    @Override
    public void onBackPressed() {
        // when pressing back, we want to go to home fragment if it is not already the case
        MenuItem homeItem = mBottomNav.getMenu().getItem(0);
        if (mSelectedItem != homeItem.getItemId()) {
            // select home item
            selectFragment(homeItem, true);
        }
        // or go to previous activity otherwise
        else {
            super.onBackPressed();
        }
    }
}
