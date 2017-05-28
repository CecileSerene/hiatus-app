package hiatus.hiatusapp.Menu;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.preference.PreferenceFragmentCompat;

import hiatus.hiatusapp.R;

/**
 * Fragment for profile settings.
 */
public class MenuProfileFragment extends PreferenceFragmentCompat {


    public static Fragment newInstance() {
        return new MenuProfileFragment();
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.preferences);
    }
}
