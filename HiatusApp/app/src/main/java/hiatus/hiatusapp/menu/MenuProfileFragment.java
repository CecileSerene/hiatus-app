package hiatus.hiatusapp.menu;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import hiatus.hiatusapp.DatabaseHelper;
import hiatus.hiatusapp.account_management.LoginActivity;
import hiatus.hiatusapp.R;
import hiatus.hiatusapp.admin.AdminActivity;

/**
 * Fragment for profile settings.
 */
public class MenuProfileFragment extends Fragment {

    private static final String TAG = "MenuProfile";
    public static final String KEY_PREF_FULLNAME = "pref_fullname";
    public static final String KEY_PREF_EMAIL = "pref_email";

    // ui references
    private EditText mDisplayName;
    private EditText mEmail;
    private TextView mGoToAdmin;
    private TextView mPassword;
    private TextView mLogout;


    public static Fragment newInstance() {
        return new MenuProfileFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu_profile, container, false);

        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        // get ui references
        mDisplayName = (EditText) view.findViewById(R.id.profile_name_field);
        mEmail = (EditText) view.findViewById(R.id.profile_email_field);
        mPassword = (TextView) view.findViewById(R.id.profile_password_link);
        mGoToAdmin = (TextView) view.findViewById(R.id.go_to_admin_link);
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

        // update user data on saving name or email

        mDisplayName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == R.id.modify_name || actionId == EditorInfo.IME_NULL) {
                    final String name = mDisplayName.getText().toString();
                    // update the auth profile
                    UserProfileChangeRequest request = new UserProfileChangeRequest.Builder()
                            .setDisplayName(name).build();
                    user.updateProfile(request).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            // update the profile in DB
                            DatabaseHelper.getUsersReference()
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .child("name")
                                    .setValue(name);
                            Log.d(TAG, "update_display_name:SUCCESS");
                        }
                    });
                    return true;
                }
                return false;
            }
        });
        mEmail.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == R.id.modify_email || actionId == EditorInfo.IME_NULL) {
                    final String email = mEmail.getText().toString();
                    // update the auth profile
                    user.updateEmail(email)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Log.d(TAG, "update_email_adress:SUCCESS");
                                        // update the profile in DB
                                        DatabaseHelper.getUsersReference()
                                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                .child("email")
                                                .setValue(email);
                                    }
                                }
                            });
                    return true;
                }
                return false;
            }
        });

        // set admin link visible if user is admin

        mGoToAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), AdminActivity.class));
            }
        });

        DatabaseHelper.getUsersReference()
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mGoToAdmin.setVisibility(dataSnapshot.child("isAdmin").getValue(Boolean.class) ? View.VISIBLE : View.GONE);
                Log.d(TAG, "admin_link:visible");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, "admin_link:onCancelled", databaseError.toException());
            }
        });

        return view;
    }
}
