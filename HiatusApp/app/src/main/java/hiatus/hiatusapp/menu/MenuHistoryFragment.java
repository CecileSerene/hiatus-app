package hiatus.hiatusapp.menu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import hiatus.hiatusapp.admin.ContributionBundleArrayAdapter;
import hiatus.hiatusapp.admin.PhotoContributionPreviewActivity;
import hiatus.hiatusapp.admin.TextContributionPreviewActivity;
import hiatus.hiatusapp.contribution.base.ContributionBundle;
import hiatus.hiatusapp.contribution.base.ContributionContent;
import hiatus.hiatusapp.contribution.base.ContributionContext;
import hiatus.hiatusapp.DatabaseHelper;
import hiatus.hiatusapp.R;

/**
 * A fragment representing the list of contributions on the history page.
 */
public class MenuHistoryFragment extends ListFragment {

    private static final String TAG = "MenuHistoryFragment";

    private ContributionBundleArrayAdapter mAdapter;
    private ArrayList<ContributionBundle> mBundles;
    private ArrayList<ContributionContent> mContents;

    public static Fragment newInstance() {
        return new MenuHistoryFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu_history, container, false);

        mBundles = new ArrayList<>();
        mContents = new ArrayList<>();

        mAdapter = new ContributionBundleArrayAdapter(getActivity(), mBundles);

        final String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        Query myContextsQuery = DatabaseHelper
                // take bundles whose userId is current logged-in user's id
                .getContributionBundleReference()
                .orderByKey()
                .equalTo(userId);

        myContextsQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                DataSnapshot userSnapshot = dataSnapshot.child(userId);
                for (DataSnapshot bundleSnapshot : userSnapshot.getChildren()) {
                    ContributionBundle bundle = bundleSnapshot.getValue(ContributionBundle.class);
                    mBundles.add(bundle);
                    mContents.add(DatabaseHelper.retrieveContent(bundle));
                    Log.d(TAG, "loadBundle:" + bundle.getId() + bundle.getContentModel().getTitle());
                }
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "loadBundles:onCancelled", databaseError.toException());
            }
        });
        setListAdapter(mAdapter);


        return view;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        ContributionBundle bundle = mBundles.get(position);
        ContributionContent content = mContents.get(position);

        Intent i = new Intent(
                getActivity(),
                bundle.getContentModel().getType() == ContributionContent.Model.TYPE_TEXT ?
                        TextContributionPreviewActivity.class : PhotoContributionPreviewActivity.class
        );
        i.putExtra("content", content);
        i.putExtra("date", bundle.getDate());
        i.putExtra("state", bundle.getState());
        startActivity(i);
    }
}
