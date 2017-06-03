package hiatus.hiatusapp.Menu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

import hiatus.hiatusapp.ContributionContext.ContributionContext;
import hiatus.hiatusapp.ContributionContext.PhotoContext;
import hiatus.hiatusapp.ContributionContext.TextContext;
import hiatus.hiatusapp.ContributionHomeDetail;
import hiatus.hiatusapp.DatabaseHelper;
import hiatus.hiatusapp.R;

/**
 * A fragment representing the list of contributions on the home page
 */
public class MenuHomeFragment extends ListFragment {

    private FirebaseListAdapter<ContributionContext> mAdapter;

    public static Fragment newInstance() {
        return new MenuHomeFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu_home, container, false);


        // create adapter linked to the contribution contexts stored in database

        DatabaseReference mRef = DatabaseHelper.getContributionContextReference();

        mAdapter = new FirebaseListAdapter<ContributionContext>(getActivity(), ContributionContext.class, R.layout.fragment_menu_list_item, mRef) {
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
        Intent i = new Intent(getActivity(), ContributionHomeDetail.class);
        i.putExtra("context",context);
        startActivity(i);

    }
}
