package hiatus.hiatusapp.Menu;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;

import hiatus.hiatusapp.R;

/**
 * Fragment for profile settings.
 */
public class MenuProfileFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener {

    public static final String KEY_PREF_FULLNAME = "pref_fullname";
    public static final String KEY_PREF_EMAIL = "pref_email";


    public static Fragment newInstance() {
        return new MenuProfileFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // fill the preferences with current values
        SharedPreferences sp = getPreferenceScreen().getSharedPreferences();
        findPreference(KEY_PREF_FULLNAME)
                .setSummary(sp.getString(KEY_PREF_FULLNAME,
                            getString(R.string.menu_profile_section_fullname_summary)));
        findPreference(KEY_PREF_EMAIL)
                .setSummary(sp.getString(KEY_PREF_EMAIL,
                            getString(R.string.menu_profile_section_email_summary)));
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.preferences);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        Preference pref;
        switch (key) {
            case KEY_PREF_FULLNAME:
                pref = findPreference(key);
                pref.setSummary(sharedPreferences.getString(key, ""));
                break;
            case KEY_PREF_EMAIL:
                pref = findPreference(key);
                pref.setSummary(sharedPreferences.getString(key, ""));
                break;
            default:
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);

    }

    @Override
    public void onPause() {
        getPreferenceManager().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
        super.onPause();
    }
}
