package hiatus.hiatusapp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import hiatus.hiatusapp.dummy.DummyContent.DummyItem;

/**
 * A fragment representing a list of history contributions.
 */
public class HistoryFragment extends ListFragment {

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static Fragment newInstance() {
        Fragment fragment = new HistoryFragment();
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // TODO replace with a call to the database to the user's history contributions
        ArrayList<ContributionContext> exampleContexts = new ArrayList<>();
        exampleContexts.add(new ContributionText("instructions", "L'Amour en cage", "Passion", 50));
        exampleContexts.add(new ContributionDrawing("instructions", "Concours de dessin", 50, "Jeu"));

        setListAdapter(new HistoryFragmentArrayAdapter(getActivity(), exampleContexts));
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        // TODO implement some logic...
    }
}
