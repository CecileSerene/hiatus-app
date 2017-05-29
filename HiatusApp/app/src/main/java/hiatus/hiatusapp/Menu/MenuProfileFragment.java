package hiatus.hiatusapp.Menu;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import hiatus.hiatusapp.LoginActivity;
import hiatus.hiatusapp.R;

/**
 * Fragment for profile settings.
 */
public class MenuProfileFragment extends Fragment {

    public static final String KEY_PREF_FULLNAME = "pref_fullname";
    public static final String KEY_PREF_EMAIL = "pref_email";


    public static Fragment newInstance() {
        return new MenuProfileFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu_profile, container, false);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        ((TextView) view.findViewById(R.id.profile_name_field)).setText(user.getDisplayName());
        ((TextView) view.findViewById(R.id.profile_email_field)).setText(user.getEmail());

        // Attach signOut action to logout link
        view.findViewById(R.id.logout_link).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth mAuth = FirebaseAuth.getInstance();
                mAuth.signOut();
                startActivity(new Intent(getActivity(), LoginActivity.class));
            }
        });

        return view;
    }
}
