package hiatus.hiatusapp.menu;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import hiatus.hiatusapp.account_management.LoginActivity;
import hiatus.hiatusapp.R;

/**
 * Fragment for profile settings.
 */
public class MenuProfileFragment extends Fragment {

    public static final String KEY_PREF_FULLNAME = "pref_fullname";
    public static final String KEY_PREF_EMAIL = "pref_email";

    // ui references
    private EditText mDisplayName;
    private EditText mEmail;
    private TextView mPassword;
    private TextView mLogout;


    public static Fragment newInstance() {
        return new MenuProfileFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu_profile, container, false);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        // get ui references
        mDisplayName = (EditText) view.findViewById(R.id.profile_name_field);
        mEmail = (EditText) view.findViewById(R.id.profile_email_field);
        mPassword = (TextView) view.findViewById(R.id.profile_password_link);
        mLogout = (TextView) view.findViewById(R.id.profile_logout_link);

        // set ui values
        mDisplayName.setText(user.getDisplayName());
        mEmail.setText(user.getEmail());

        // Attach signOut action to logout link
        mLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Activity currentActivity = getActivity();

                // confirm sign off through alert dialog
                new AlertDialog.Builder(currentActivity)
                        .setTitle(R.string.confirm_signout_title)
                        .setMessage(R.string.confirm_signout_message)
                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                FirebaseAuth mAuth = FirebaseAuth.getInstance();
                                mAuth.signOut();
                                startActivity(new Intent(getActivity(), LoginActivity.class));
                                currentActivity.finish();
                            }
                        })
                        .setNegativeButton(R.string.no, null)
                        .show();
            }
        });

        mDisplayName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == R.id.modify_name || actionId == EditorInfo.IME_NULL) {
                    // TODO save display name changes to Firebase
                    return false;
                }
                return false;
            }
        });
        mEmail.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == R.id.modify_name || actionId == EditorInfo.IME_NULL) {
                    // TODO save email changes to Firebase
                    return false;
                }
                return false;
            }
        });

        return view;
    }
}
