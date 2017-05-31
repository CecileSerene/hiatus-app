package hiatus.hiatusapp.Admin;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import hiatus.hiatusapp.ContributionBundle.ContributionBundle;
import hiatus.hiatusapp.R;

/**
 * Created by Cecile on 31/05/2017.
 */

public class ContributionBundleArrayAdapter extends ArrayAdapter<ContributionBundle> {

    public ContributionBundleArrayAdapter(Context context, ArrayList<ContributionBundle> bundles){
        super(context,0,bundles);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Get the data item for this position
        ContributionBundle contributionBundle = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.bundle_list_item, parent, false);
        }

        setContent(contributionBundle, convertView);
        setState(contributionBundle, convertView);
        return convertView;

    }

    private void setState(ContributionBundle bundle, View convertView) {
        ImageView ivState = (ImageView) convertView.findViewById(R.id.state);

        if (bundle.getState() == ContributionBundle.ACCEPTED){
            ivState.setImageResource(R.drawable.ic_action_name);
            ivState.setColorFilter(Color.GREEN);
        }
        else{
            ivState.setImageResource(R.drawable.ic_close);
            ivState.setColorFilter(Color.RED);
        }
    }

    protected void setContent(ContributionBundle bundle, View view){
        // Lookup view for data population
        TextView tvTitle = (TextView) view.findViewById(R.id.content_title);
        TextView tvAuthor = (TextView) view.findViewById(R.id.contribution_author);
        // Populate the data into the template view using the data object
        if (bundle.getContent().getTitle().length() > 0){
            tvTitle.setText(bundle.getContent().getTitle());
        }
        else {
            tvTitle.setText("(Sans titre)");
        }
        // tvAuthor.setText(bundle.getUser());

    }
}
