package hiatus.hiatusapp.Menu;

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
import hiatus.hiatusapp.R;

/**
 * A fragment representing the list of contributions on the history page.
 */
public class MenuHistoryFragment extends ListFragment {

    // TODO: Customize parameter initialization
    public static Fragment newInstance() {
        return new MenuHistoryFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu_history, container, false);

        // v TODO replace with a call to the database to the user's history contributions
        ArrayList<ContributionContext> exampleContexts = new ArrayList<>();
        exampleContexts.add(new ContributionText("instructions", "L'Amour en cage", "Passion", 50));
        exampleContexts.add(new ContributionDrawing("instructions", "Concours de dessin", 50, "Jeu"));
        // ^

        setListAdapter(new ContributionContextArrayAdapter(getActivity(), exampleContexts));

        return view;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        /*
        TODO implement logic to go to detail view of the selected contribution, showing:
        - the contribution title
        - the contribution theme
        - the contribution specificities in a separate box (consider making a fragment for it)
        - the state of the user's contribution (approved/denied/waiting for approval)
        - a preview of the user's contribution
        - a link to the contribution detail page
          */
    }
}
