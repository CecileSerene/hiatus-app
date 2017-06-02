package hiatus.hiatusapp;

import android.provider.ContactsContract;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import hiatus.hiatusapp.ContributionBundle.ContributionBundle;

/**
 * Database helper class that provides static methods to manipulate the DB or get references to its primary nodes.
 * Created by Florimond on 31/05/2017.
 */

public class DatabaseHelper {

    private static String TAG = "DatabaseHelper";

    // v CAUTION: do not change unless you know what you're doing !
    // (reason: these ref names must follow the Firebase database structure.)
    private static String ADMIN_REF_NAME = "admins";
    private static String USER_REF_NAME = "users";
    private static String CONTEXT_REF_NAME = "contribution_contexts";
    private static String BUNDLE_REF_NAME = "contribution_bundles";
    // ^

    private static DatabaseReference db = FirebaseDatabase.getInstance().getReference();


    /*
    Admin users database interface
     */

    public static DatabaseReference getAdminsReference() {
        return db.child(ADMIN_REF_NAME);
    }

    public static boolean isAdmin(String userId) {
        return db.child(ADMIN_REF_NAME).child(userId).getRoot() == null;
    }

    /*
    User database interface
     */

    public static DatabaseReference getUsersReference() {
        return db.child(USER_REF_NAME);
    }

    public static void saveUser(FirebaseUser user) {
        getUsersReference()
                .child(user.getUid())
                .setValue(new User(user.getUid(), user.getDisplayName(), user.getEmail()));
    }

    /**
     * @return a reference to the node of contribution contexts
     */
    public static DatabaseReference getContributionContextReference() {
        return db.child(CONTEXT_REF_NAME);
    }

    public static String newContributionContextId() {
        return db.child(CONTEXT_REF_NAME).push().getKey();
    }

    /*
    Contribution bundle database interface
     */

    /**
     * @return a reference to the node of contribution bundles.
     */
    public static DatabaseReference getContributionBundleReference() {
        return db.child(BUNDLE_REF_NAME);
    }

    /**
     * Creates a node for a new contribution bundle in the database.
     * @param contextId id of the contribution context associated with the contribution bundle.
     * @return the node id. Use it to instantiate a new ContributionBundle.
     */
    public static String newContributionBundleId(String contextId) {
        return db.child(BUNDLE_REF_NAME).child(contextId).push().getKey();
    }

    /**
     * Saves a contribution bundle to the database.
     * @param bundle contribution bundle to save to the database.
     */
    public static void saveContributionBundle(ContributionBundle bundle) {
        getContributionBundleReference()
                .child(bundle.getContextId())
                .child(bundle.getId())
                .setValue(bundle);
    }
}
