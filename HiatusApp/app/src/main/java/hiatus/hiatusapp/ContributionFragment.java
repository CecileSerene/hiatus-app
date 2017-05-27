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
import hiatus.hiatusapp.ContributionContext.ContributionPhoto;
import hiatus.hiatusapp.ContributionContext.ContributionText;
import hiatus.hiatusapp.dummy.DummyContent.DummyItem;

/**
 * A fragment representing a list of contributions
 * Activities containing this fragment MUST implement the {@link onContributionSelectedListener}
 * interface.
 */
public class ContributionFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private onContributionSelectedListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ContributionFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ContributionFragment newInstance(int columnCount) {
        ContributionFragment fragment = new ContributionFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
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
        View view = inflater.inflate(R.layout.fragment_contribution_list, container, false);

        // Set the adapter
        if (view instanceof ListView) {
            Context context = view.getContext();
            ListView listView = (ListView) view;
            // TODO replace with a call to the database to get most recent contributions
            ArrayList<ContributionContext> exampleContexts = new ArrayList<>();
            exampleContexts.add(new ContributionText("instructions", "Dessine-moi un smiley!", "Obsession", 50));
            exampleContexts.add(new ContributionPhoto("instructions", "Photographie ludique", "Jeu", 50));

            listView.setAdapter(new ContributionContextAdapter(context, exampleContexts));
        }
        return view;

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof onContributionSelectedListener) {
            mListener = (onContributionSelectedListener) context;
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
    public interface onContributionSelectedListener {
        // TODO: Update argument type and name
        void onContributionSelected(DummyItem item);
    }
}
