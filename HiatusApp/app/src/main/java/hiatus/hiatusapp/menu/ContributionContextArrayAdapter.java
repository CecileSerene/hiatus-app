package hiatus.hiatusapp.menu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import hiatus.hiatusapp.contribution.base.ContributionContext;
import hiatus.hiatusapp.R;

/**
 * Created by Florimond on 27/05/2017.
 * Defines the array adapter for contribution contexts. Used to display contribution contexts in a ListView.
 */

public class ContributionContextArrayAdapter extends ArrayAdapter<ContributionContext> {

    public ContributionContextArrayAdapter(Context context, ArrayList<ContributionContext> contexts) {
        super(context, 0, contexts);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        ContributionContext contributionContext = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_menu_list_item, parent, false);
        }
        setContent(contributionContext, convertView);
        return convertView;
    }

    // Override in subclasses to define your own contents... ?
    // (not sure as it depends on the underlying layout)
    protected void setContent(ContributionContext contributionContext, View view) {
        // Lookup view for data population
        TextView tvTheme = (TextView) view.findViewById(R.id.contribution_theme);
        TextView tvTitle = (TextView) view.findViewById(R.id.contribution_title);
        // Populate the data into the template view using the data object
        tvTheme.setText(contributionContext.getTheme());
        tvTitle.setText(contributionContext.getTitle());

    }
}
