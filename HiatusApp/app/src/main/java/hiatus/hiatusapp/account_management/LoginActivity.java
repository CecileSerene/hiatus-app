package hiatus.hiatusapp.account_management;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.app.Activity;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import hiatus.hiatusapp.admin.AdminActivity;
import hiatus.hiatusapp.DatabaseHelper;
import hiatus.hiatusapp.menu.MenuActivity;
import hiatus.hiatusapp.R;


/**
 * A login screen that offers login via email/password.
 * Useful example: https://github.com/firebase/quickstart-android/blob/master/auth/app/src/main/java/com/google/firebase/quickstart/auth/EmailPasswordActivity.java
 */
public class LoginActivity extends Activity {

    private static final String TAG = "Login";

    /**
     * Firebase references.
     */
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    /**
     * UI references.
     */
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //startActivity(new Intent(LoginActivity.this, AdminActivity.class)); // toggle to skip login and go to admin

        // Setup firebase authentication
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    if (user.isEmailVerified()) {
                        Log.d(TAG, "onAuthStateChanged:sign_in:" + user.getUid());
                        // if user is admin, go to admin activity, else go to menu activity
                        DatabaseReference userRef = DatabaseHelper.getUsersReference().child(user.getUid());
                        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                boolean isAdmin = dataSnapshot.child("isAdmin").exists();
                                Log.d(TAG, "onAuthStateChanged:sign_in_role:" + (isAdmin ? "admin" : "user"));
                                if (isAdmin) {
                                    afterLoginAsAdmin();
                                } else {
                                    afterLoginAsUser();
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                    } else {
                        Toast.makeText(
                                LoginActivity.this,
                                "Please check your emails at " +
                                        user.getEmail() +
                                        " and verify your email address.",
                                Toast.LENGTH_LONG).show();
                        Log.d(TAG, "onAuthStateChanged:email_not_verified:" + user.getEmail());
                    }
                } else {
                    showProgress(false);
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
            }
        };

        // Get UI references
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);
        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);

        // Set up actions

        // Action on the editor to login directly through the keyboard's login button
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    if (validateForm()) {
                        signIn(mEmailView.getText().toString(), mPasswordView.getText().toString());
                        return true;
                    } else {
                        return false;
                    }
                }
                return false;
            }
        });

        // Attach signIn() to sign-in button
        findViewById(R.id.email_sign_in_button).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn(mEmailView.getText().toString(), mPasswordView.getText().toString());
            }
        });

        // Go to register form with register link
        findViewById(R.id.register_link).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                goToRegister();
            }
        });
    }

    private void afterLoginAsUser() {
        Intent i = new Intent(LoginActivity.this, MenuActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
        finish();
    }

    private void afterLoginAsAdmin() {
        // ask if admin wants to login as admin or simple user
        new AlertDialog.Builder(this)
                .setTitle(R.string.prompt_which_role_title)
                .setMessage(R.string.prompt_which_role_message)
                .setPositiveButton(R.string.prompt_which_role_admin, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i = new Intent(LoginActivity.this, AdminActivity.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i);
                        finish();
                    }
                })
                .setNegativeButton(R.string.prompt_which_role_user, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        afterLoginAsUser();
                    }
                })
                .show();
    }

    private void goToRegister() {
        startActivity(new Intent(this, RegisterActivity.class));
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    /*
    Firebase login-register functions
     */

    private void signIn(final String email, final String password) {
        Log.d(TAG, "signIn:" + email);

        showProgress(true);

        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Log.d(TAG, "signInWithEmail:success");
                    } else {
                        Log.w(TAG, "signInWithEmail:failure", task.getException());
                        Toast.makeText(LoginActivity.this, R.string.bad_credentials_message, Toast.LENGTH_SHORT).show();
                        showProgress(false);
                    }
                }
            });
    }

    /*
    Login form validation
     */

    private boolean isEmailValid(String email) {
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }

    private boolean validateForm() {
        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        boolean valid = true;
        View focusView = null;

        // verify email
        String email = mEmailView.getText().toString();
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            valid = false;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            valid = false;
        }

        // verify password
        String password = mPasswordView.getText().toString();
        if (TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            valid = false;
        }

        // reset focus on invalid field if not valid
        if (!valid) {
            focusView.requestFocus();
        }

        return valid;

    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations.
        int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

        mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            }
        });

        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
        mProgressView.animate().setDuration(shortAnimTime).alpha(
                show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            }
        });
    }
}

