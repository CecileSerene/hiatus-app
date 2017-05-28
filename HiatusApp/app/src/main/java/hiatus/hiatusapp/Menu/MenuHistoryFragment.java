package hiatus.hiatusapp.Menu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import hiatus.hiatusapp.ContributionContext.ContributionContext;
import hiatus.hiatusapp.ContributionContext.ContributionDrawing;
import hiatus.hiatusapp.ContributionContext.ContributionText;
import hiatus.hiatusapp.ContributionHistoryDetail;
import hiatus.hiatusapp.ContributionHomeDetail;
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

        // v TODO replace with a call to the database to the user's history contributions
        contexts = new ArrayList<>();
        contexts.add(new ContributionText("instructions", "L'Amour en cage", "Passion", 50));
        contexts.add(new ContributionDrawing("instructions", "Concours de dessin", 50, "Jeu"));
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
        i.putExtra("title", context.getTitle());
        i.putExtra("theme", context.getTheme());
        // TODO replace these with the contribution-specific data
        i.putExtra("date", "01/01/2001");
        i.putExtra("state", "Accepté");

        startActivity(i);
    }
}
