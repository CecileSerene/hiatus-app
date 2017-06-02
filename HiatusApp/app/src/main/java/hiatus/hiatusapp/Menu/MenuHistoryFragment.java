package hiatus.hiatusapp.Menu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.firebase.database.DatabaseReference;

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

    private ArrayList<ContributionContext> contexts;

    public static Fragment newInstance() {
        return new MenuHistoryFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu_history, container, false);

        // DatabaseReference ref = DatabaseHelper.getContributionContextReference();

        // v TODO replace with a call to the database to the user's history contributions
        contexts = new ArrayList<>();
        contexts.add(new TextContext("0", "instructions", "L'Amour en cage", "Passion", 50));
        contexts.add(new PhotoContext("0", "instructions", "Concours de dessin","Jeu"));
        // ^

        setListAdapter(new ContributionContextArrayAdapter(getActivity(), contexts));

        return view;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        ContributionContext context = contexts.get(position);

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
