package hiatus.hiatusapp;

import android.support.constraint.solver.widgets.Snapshot;
import android.util.Log;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import hiatus.hiatusapp.ContributionBundle.ContributionBundle;
import hiatus.hiatusapp.ContributionContent.ContributionContent;
import hiatus.hiatusapp.ContributionContent.PhotoContent;
import hiatus.hiatusapp.ContributionContent.TextContent;
import hiatus.hiatusapp.ContributionContext.ContributionContext;
import hiatus.hiatusapp.ContributionContext.PhotoContext;
import hiatus.hiatusapp.ContributionContext.TextContext;

/**
 * Database helper class that provides static methods to manipulate the DB or get references to its primary nodes.
 * Created by Florimond on 31/05/2017.
 */

public class DatabaseHelper {

    private static String TAG = "DatabaseHelper";

    // v CAUTION: do not change unless you know what you're doing !
    // (reason: these ref names must follow the Firebase database structure.)
    private static String USER_REF = "users";
    private static String CONTEXT_REF = "contribution_contexts";
    private static String BUNDLE_REF = "contribution_bundles";
    private static String BUNDLE_CONTENT_REF = "bundle_contents";
    // ^

    private static DatabaseReference db = FirebaseDatabase.getInstance().getReference();


    /*
    User database interface
     */

    public static DatabaseReference getUsersReference() {
        return db.child(USER_REF);
    }

    public static void saveUser(FirebaseUser user) {
        getUsersReference()
                .child(user.getUid())
                .setValue(new User(user.getUid(), user.getDisplayName(), user.getEmail()));
        Log.d(TAG, "save_user:" + user.getUid());
    }

    /*
    Contribution context database interface
     */

    /**
     * @return a reference to the node of contribution contexts
     */
    public static DatabaseReference getContributionContextReference() {
        return db.child(CONTEXT_REF);
    }

    public static String newContributionContextId() {
        String id = db.child(CONTEXT_REF).push().getKey();
        Log.d(TAG, "new_context_id:" + id);
        return id;
    }

    /**
     * Saves a new contribution context to the database
     * @param context ContributionContext
     */
    public static void saveContributionContext(ContributionContext context) {
        getContributionContextReference()
                .child(context.getId())
                .setValue(context);
        Log.d(TAG, "save_text_context:" + context.getId());
    }

    /**
     * Retrieves a context from a data snapshot. To use in Firebase read listeners.
     * @param snapshot data snapshot of a contribution context node
     * @return the Contribution Context built from the snapshot
     */
    public static ContributionContext retrieveContext(DataSnapshot snapshot) {
        ContributionContext context = null;
        int type = snapshot.child("type").getValue(Integer.class);
        if (type == ContributionContext.TYPE_TEXT) {
            context = snapshot.getValue(TextContext.class);
        } else if (type == ContributionContext.TYPE_PHOTO) {
            context = snapshot.getValue(PhotoContext.class);
        }
        return context;
    }

    /*
    Contribution bundle database interface
     */

    /**
     * @return a reference to the node of contribution bundles.
     */
    public static DatabaseReference getContributionBundleReference() {
        return db.child(BUNDLE_REF);
    }

    /**
     * Creates a node for a new contribution bundle in the database.
     * @param userId id of the user that owns the bundle.
     * @return the node id. Use it to instantiate a new ContributionBundle.
     */
    public static String newContributionBundleId(String userId) {
        String id = db.child(BUNDLE_REF).child(userId).push().getKey();
        Log.d(TAG, "new_bundle_id:" + id);
        return id;
    }

    /**
     * Saves a contribution bundle to the database.
     * @param bundle ContributionBundle
     */
    public static void saveContributionBundle(ContributionBundle bundle) {
        getContributionBundleReference()
                .child(bundle.getUserUid())
                .child(bundle.getId())
                .setValue(bundle);
        Log.d(TAG, "save_bundle:" + bundle.getId());
    }

    /*
    Contribution content database interface
     */

    public static ContributionContent retrieveContent(ContributionBundle bundle) {
        ContributionContent content = null;
        ContributionContent.Model model = bundle.getContentModel();
        if (model.getType() == ContributionContent.Model.TYPE_TEXT) {
            content = new TextContent(model);
        } else if (model.getType() == ContributionContent.Model.TYPE_PHOTO) {
            content = new PhotoContent(model);
        }
        return content;
    }
}
