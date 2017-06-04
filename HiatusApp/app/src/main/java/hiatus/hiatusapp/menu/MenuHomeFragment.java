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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import hiatus.hiatusapp.contribution.base.ContributionContext;
import hiatus.hiatusapp.DatabaseHelper;
import hiatus.hiatusapp.R;

/**
 * A fragment representing the list of contributions on the home page
 */
public class MenuHomeFragment extends ListFragment {

    private static final String TAG = "MenuHome";

    private DatabaseReference mRef;
    private ContributionContextArrayAdapter mAdapter;
    private ArrayList<ContributionContext> mContexts;

    public static Fragment newInstance() {
        return new MenuHomeFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu_home, container, false);

        mContexts = new ArrayList<>();
        mAdapter = new ContributionContextArrayAdapter(getActivity(), mContexts);
        setListAdapter(mAdapter);

        mRef = DatabaseHelper.getContributionContextReference();
        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot contextSnapshot : dataSnapshot.getChildren()) {
                    ContributionContext context = DatabaseHelper.retrieveContext(contextSnapshot);
                    mContexts.add(context);
                    Log.d(TAG, "load_context:" + context.getId() + ":" + context.getTitle());
                }
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "loadContexts:onCancelled", databaseError.toException());
            }
        });

        return view;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        ContributionContext context = mAdapter.getItem(position);

        // populate an intent with the contribution context
        Intent i = new Intent(getActivity(), ContributionHomeDetail.class);
        i.putExtra("context", context);
        startActivity(i);

    }
}
