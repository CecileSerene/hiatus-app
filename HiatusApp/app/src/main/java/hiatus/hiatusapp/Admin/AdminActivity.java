package hiatus.hiatusapp.Admin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DatabaseReference;


import hiatus.hiatusapp.ContributionContext.ContributionContext;
import hiatus.hiatusapp.DatabaseHelper;
import hiatus.hiatusapp.R;

public class AdminActivity extends Activity {

    /*
    The main Activity af the admin. He can add a new context and see all the current contexts
     */

    private FirebaseListAdapter<ContributionContext> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);


        // setup list view

        ListView lvAdmin = (ListView) findViewById(R.id.admin_context);

        DatabaseReference mRef = DatabaseHelper.getContributionContextReference();

        // create the adapter
        mAdapter = new FirebaseListAdapter<ContributionContext>(this, ContributionContext.class, R.layout.fragment_menu_list_item, mRef) {
            @Override
            protected void populateView(View v, ContributionContext model, int position) {
                TextView title = (TextView) v.findViewById(R.id.contribution_title);
                TextView theme = (TextView) v.findViewById(R.id.contribution_theme);
                title.setText(model.getTitle());
                theme.setText(model.getTheme());
            }
        };
        lvAdmin.setAdapter(mAdapter);

        lvAdmin.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                ContributionContext context = mAdapter.getItem(position);
                // populate an intent with the contribution context
                Intent i = new Intent(view.getContext(), UserContributionActivity.class);
                i.putExtra("context",context);
                startActivity(i);
            }
        });


        // set new context button behavior

        Button newContextButton = (Button) findViewById((R.id.new_context));

        newContextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), NewContextActivity.class);
                startActivity(i);
            }
        });



    }

}
