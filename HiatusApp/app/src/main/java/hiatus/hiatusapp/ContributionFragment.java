package hiatus.hiatusapp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
    // TODO: Customize parameters
    private onContributionSelectedListener mListener;

    private ListView listView;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ContributionFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static Fragment newInstance() {
        Fragment fragment = new ContributionFragment();
        // add parameters below
        // Bundle args = new Bundle();
        // fragment.setArguments(args);
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
        return inflater.inflate(R.layout.fragment_contribution_list, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // get the list view
        listView = (ListView) view.findViewById(R.id.list);

        // Set the adapter
        Context context = view.getContext();
        // TODO replace with a call to the database to get most recent contributions
        ArrayList<ContributionContext> exampleContexts = new ArrayList<>();
        exampleContexts.add(new ContributionText("instructions", "Dessine-moi un smiley!", "Obsession", 50));
        exampleContexts.add(new ContributionPhoto("instructions", "Photographie ludique", "Jeu", 50));

        listView.setAdapter(new ContributionFragmentArrayAdapter(context, exampleContexts));


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
