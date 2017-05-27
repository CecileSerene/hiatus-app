package hiatus.hiatusapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

import hiatus.hiatusapp.ContributionContext.ContributionContext;
import hiatus.hiatusapp.ContributionContext.ContributionPhoto;
import hiatus.hiatusapp.ContributionContext.ContributionText;

/**
 * A fragment representing a list of contributions
 */
public class ContributionFragment extends ListFragment {

    public static Fragment newInstance() {
        Fragment fragment = new ContributionFragment();
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // TODO replace with a call to the database to get most recent contributions
        ArrayList<ContributionContext> exampleContexts = new ArrayList<>();
        exampleContexts.add(new ContributionText("instructions", "Dessine-moi un smiley!", "Obsession", 50));
        exampleContexts.add(new ContributionPhoto("instructions", "Photographie ludique", "Jeu", 50));

        setListAdapter(new ContributionFragmentArrayAdapter(getActivity(), exampleContexts));

    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        // TODO implement some logic...
    }
}
