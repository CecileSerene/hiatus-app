package hiatus.hiatusapp;

import android.util.Log;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import hiatus.hiatusapp.ContributionBundle.ContributionBundle;
import hiatus.hiatusapp.ContributionContext.ContributionContext;

/**
 * Created by Florimond on 31/05/2017.
 */

public class DatabaseHelper {

    private static String TAG = "DatabaseHelper";

    // v CAUTION: do not change unless you know what you're doing !!!
    private static String USER_REF_NAME = "user";
    private static String CONTEXT_REF_NAME = "contribution_context";
    private static String BUNDLE_REF_NAME = "contribution_bundle";
    // ^

    private static DatabaseReference db = FirebaseDatabase.getInstance().getReference();

    public static DatabaseReference getUsersReference() {
        return db.child(USER_REF_NAME);
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
    static String newContributionBundleId(String contextId) {
        return db.child(BUNDLE_REF_NAME).child(contextId).push().getKey();
    }

    /**
     * Saves a contribution bundle to the database.
     * @param bundle contribution bundle to save to the database.
     */
    static void saveContributionBundle(ContributionBundle bundle) {
        db.child(BUNDLE_REF_NAME)
                .child(bundle.getContextId())
                .child(bundle.getId())
                .setValue(bundle);
    }
}
