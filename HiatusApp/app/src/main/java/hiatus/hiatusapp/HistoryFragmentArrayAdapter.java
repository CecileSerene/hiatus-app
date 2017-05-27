package hiatus.hiatusapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import hiatus.hiatusapp.ContributionContext.ContributionContext;

/**
 * Created by Florimond on 27/05/2017.
 * Defines the array adapter for contribution contexts. Used to display contribution contexts in a ListView.
 */

public class HistoryFragmentArrayAdapter extends ArrayAdapter<ContributionContext> {

    public HistoryFragmentArrayAdapter(Context context, ArrayList<ContributionContext> contexts) {
        super(context, 0, contexts);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        ContributionContext context = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_history_item, parent, false);
        }
        // Lookup view for data population
        TextView tvTheme = (TextView) convertView.findViewById(R.id.contribution_theme);
        TextView tvTitle = (TextView) convertView.findViewById(R.id.contribution_title);
        // Populate the data into the template view using the data object
        tvTheme.setText(context.getTheme());
        tvTitle.setText(context.getTitle());
        // Return the completed view to render on screen
        return convertView;
    }
}
