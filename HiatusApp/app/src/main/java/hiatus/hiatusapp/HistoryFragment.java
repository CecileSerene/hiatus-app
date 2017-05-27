package hiatus.hiatusapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
 * Activities containing this fragment MUST implement the {@link onHistoryContributionSelectedListener}
 * interface.
 */
public class HistoryFragment extends Fragment {

    // TODO: Customize parameter argument names
    // private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private onHistoryContributionSelectedListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public HistoryFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static Fragment newInstance() {
        Fragment fragment = new HistoryFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_history_list, container, false);

        // get the list view
        ListView lv = (ListView) view.findViewById(R.id.list);

        // Set the adapter
        Context context = view.getContext();
        // TODO replace with a call to the database to the user's history contributions
        ArrayList<ContributionContext> exampleContexts = new ArrayList<>();
        exampleContexts.add(new ContributionText("instructions", "L'Amour en cage", "Passion", 50));
        exampleContexts.add(new ContributionDrawing("instructions", "Concours de dessin", 50, "Jeu"));

        lv.setAdapter(new HistoryFragmentArrayAdapter(context, exampleContexts));

        return view;

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof onHistoryContributionSelectedListener) {
            mListener = (onHistoryContributionSelectedListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface onHistoryContributionSelectedListener {
        // TODO: Update argument type and name
        void onHistoryContributionSelected(DummyItem item);
    }
}
