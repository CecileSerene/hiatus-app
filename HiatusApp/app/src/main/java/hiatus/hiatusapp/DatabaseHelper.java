package hiatus.hiatusapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import hiatus.hiatusapp.account_management.User;
import hiatus.hiatusapp.contribution.base.ContributionBundle;
import hiatus.hiatusapp.contribution.base.ContributionContent;
import hiatus.hiatusapp.contribution.photo.PhotoContent;
import hiatus.hiatusapp.contribution.text.TextContent;
import hiatus.hiatusapp.contribution.base.ContributionContext;
import hiatus.hiatusapp.contribution.photo.PhotoContext;
import hiatus.hiatusapp.contribution.text.TextContext;

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
    private static String CURRENT_CONTEXT_REF = "current_contexts";
    private static String BUNDLE_CONTENT_REF = "bundle_contents";
    // ^

    private static DatabaseReference db = FirebaseDatabase.getInstance().getReference();
    private static StorageReference sto = FirebaseStorage.getInstance().getReference();


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
    public static DatabaseReference getOpenContributionContextReference() {
        return db.child(CONTEXT_REF).child("open");
    }

    public static DatabaseReference getContributionContextReference() {
        return db.child(CONTEXT_REF);
    }

    public static String newContributionContextId() {
        String id = db.child(CONTEXT_REF).child("open").push().getKey();
        Log.d(TAG, "new_context_id:" + id);
        return id;
    }





    /**
     * Saves a new contribution context to the database
     * @param context ContributionContext
     */
    public static void saveContributionContext(ContributionContext context) {
        getOpenContributionContextReference()
                .child(context.getId())
                .setValue(context);
        Log.d(TAG, "save_text_context:" + context.getId());
    }

    public static void setClosed(ContributionContext context){
        getOpenContributionContextReference()
                .child(context.getId())
                .setValue(null);
        getContributionContextReference()
                .child("closed")
                .child(context.getId())
                .setValue(context);;
        Log.d(TAG, "close_text_context:" + context.getId());
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

    private static DatabaseReference getBundlesForUser(String userId) {
        return getContributionBundleReference().child(userId);
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

    public static void removeContributionBundle(String userUid, String bundleId) {
        getBundlesForUser(userUid)
                .child(bundleId)
                .setValue(null);
        Log.d(TAG, "remove_bundle:" + bundleId);

    }

    public static void changeStateContibutionBundle(String userUid, String bundleId, int state){
        getBundlesForUser(userUid)
                .child(bundleId)
                .child("state")
                .setValue(state);
        Log.d(TAG, "change_state_bundle:" + bundleId);
        }

    /*
    Storage interface
     */

    public static StorageReference getStorageReference() {
        return sto;
    }

    /*
    Contribution content database interface
     */

    public static ContributionContent retrieveContent(ContributionBundle bundle) {
        ContributionContent.Model model = bundle.getContentModel();

        if (model.getType() == ContributionContent.Model.TYPE_TEXT) {
            return new TextContent(model);
        } else if (model.getType() == ContributionContent.Model.TYPE_PHOTO) {
            return new PhotoContent(model);
        }
        return null;
    }

    /*
    Contribution photo content storage interface
     */

    public static String newPhotoStoragePath(String bundleId) {
        return "images/photo_contributions/" + bundleId;
    }
    public static StorageReference getPhotoContentStorageReference() {
        return sto.child("images").child("photo_contributions");
    }
}
