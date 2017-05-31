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
import hiatus.hiatusapp.ContributionContext.PhotoContext;
import hiatus.hiatusapp.ContributionContext.TextContext;
import hiatus.hiatusapp.ContributionHomeDetail;
import hiatus.hiatusapp.R;

/**
 * A fragment representing the list of contributions on the home page
 */
public class MenuHomeFragment extends ListFragment {

    private ArrayList<ContributionContext> contexts;
    // TODO: Customize parameter initialization
    public static Fragment newInstance() {
        return new MenuHomeFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu_home, container, false);

        // v TODO replace with a call to the database to get most recent contributions
        contexts = new ArrayList<>();
        contexts.add(new TextContext("Dessine-moi un smiley!", "Obsession", "Dessine un smiley le plus rapidement possible !", 50));
        contexts.add(new PhotoContext("Photographie ludique", "Jeu", "instructions"));
        // ^

        setListAdapter(new ContributionContextArrayAdapter(getActivity(), contexts));

        return view;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        ContributionContext context = contexts.get(position);

        // populate an intent with the contribution context
        Intent i = new Intent(getActivity(), ContributionHomeDetail.class);
        i.putExtra("context",context);
        startActivity(i);

    }
}
