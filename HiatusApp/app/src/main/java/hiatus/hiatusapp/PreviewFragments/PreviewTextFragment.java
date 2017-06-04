package hiatus.hiatusapp.PreviewFragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import hiatus.hiatusapp.ContributionText.TextContent;
import hiatus.hiatusapp.R;

/**
 * A fragment that shows a preview of a text content.
 */
public class PreviewTextFragment extends Fragment {

    private String mTitle;
    private String mText;

    public PreviewTextFragment() {
        // Required empty public constructor
    }

    public static Fragment newInstance(TextContent content) {
        Fragment fragment = new PreviewTextFragment();
        Bundle args = new Bundle();
        args.putString("title", content.getTitle());
        args.putString("text", content.getText());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if(args != null) {
            mTitle = args.getString("title");
            mText = args.getString("text");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_preview_text, container, false);
        ((TextView) view.findViewById(R.id.title)).setText(mTitle);
        ((TextView) view.findViewById(R.id.text)).setText(mText);
        return view;
    }

}
