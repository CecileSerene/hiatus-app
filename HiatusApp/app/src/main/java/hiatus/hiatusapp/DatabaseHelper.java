package hiatus.hiatusapp;

import android.util.Log;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
    private static DatabaseReference usersRef = db.child(USER_REF_NAME);
    private static DatabaseReference contextsRef = db.child(CONTEXT_REF_NAME);
    private static DatabaseReference bundlesRef = db.child(BUNDLE_REF_NAME);

    public static void addContextToDabase(ContributionContext contributionContext) {
        String id = contextsRef.push().getKey();
        contextsRef.child(id).setValue(contributionContext);
        Log.i(TAG, "add_context_to_database:" + contributionContext);
    }

    public static void addBundleToDatabase(FirebaseUser user, ContributionBundle contributionBundle) {
        // add bundle to BUNDLE_REF_NAME node
        String id = bundlesRef.push().getKey();
        bundlesRef.child(id).setValue(contributionBundle);

        Log.i(TAG, "add_bundle_to_database:" + contributionBundle);
    }

    /**
     * @return a reference to the node of contribution contexts
     */
    public static DatabaseReference getContributionContextReference() {
        return contextsRef;
    }

    /**
     * @return a reference to the node of contribution bundles
     */
    public static DatabaseReference getContributionBundleReference() {
        return bundlesRef;
    }
}
