package hiatus.hiatusapp.Menu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import hiatus.hiatusapp.ContributionContext.ContributionContext;
import hiatus.hiatusapp.ContributionContext.PhotoContext;
import hiatus.hiatusapp.ContributionContext.TextContext;
import hiatus.hiatusapp.ContributionHistoryDetail;
import hiatus.hiatusapp.DatabaseHelper;
import hiatus.hiatusapp.R;

/**
 * A fragment representing the list of contributions on the history page.
 */
public class MenuHistoryFragment extends ListFragment {

    private FirebaseListAdapter<ContributionContext> mAdapter;

    public static Fragment newInstance() {
        return new MenuHistoryFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu_history, container, false);

        Query myContextsQuery = DatabaseHelper
                // take bundles whose userId is current logged-in user's id
                .getContributionBundleReference()
                .equalTo(FirebaseAuth.getInstance().getCurrentUser().getUid(), "userUid");

        mAdapter = new FirebaseListAdapter<ContributionContext>(
                getActivity(), ContributionContext.class, R.layout.fragment_menu_list_item, myContextsQuery) {
            @Override
            protected void populateView(View v, ContributionContext model, int position) {
                TextView title = (TextView) v.findViewById(R.id.contribution_title);
                TextView theme = (TextView) v.findViewById(R.id.contribution_theme);
                title.setText(model.getTitle());
                theme.setText(model.getTheme());
            }
        };
        setListAdapter(mAdapter);


        return view;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        ContributionContext context = mAdapter.getItem(position);

        // populate an intent with the contribution context
        Intent i = new Intent(getActivity(), ContributionHistoryDetail.class);
        i.putExtra("context",context);
        startActivity(i);
        // TODO replace these with the contribution-specific data
        i.putExtra("date", "01/01/2001");
        i.putExtra("state", "Accepté");

        startActivity(i);
    }
}
