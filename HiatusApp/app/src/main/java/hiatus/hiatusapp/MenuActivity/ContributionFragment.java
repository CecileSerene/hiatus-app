package hiatus.hiatusapp.MenuActivity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import hiatus.hiatusapp.ContributionContext.ContributionContext;
import hiatus.hiatusapp.ContributionContext.ContributionPhoto;
import hiatus.hiatusapp.ContributionContext.ContributionText;
import hiatus.hiatusapp.R;

/**
 * A fragment representing the list of contributions on the home page
 */
public class ContributionFragment extends ListFragment {

    // TODO: Customize parameter initialization
    public static Fragment newInstance() {
        Fragment fragment = new ContributionFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contribution_list, container, false);

        TextView tv = (TextView) view.findViewById(R.id.list_title);
        tv.setText("Appels à contribution en cours"); // TODO make @strings

        // v TODO replace with a call to the database to get most recent contributions
        ArrayList<ContributionContext> exampleContexts = new ArrayList<>();
        exampleContexts.add(new ContributionText("instructions", "Dessine-moi un smiley!", "Obsession", 50));
        exampleContexts.add(new ContributionPhoto("instructions", "Photographie ludique", "Jeu", 50));
        // ^

        setListAdapter(new ContributionContextArrayAdapter(getActivity(), exampleContexts));

        return view;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        // TODO implement some logic...
    }
}
