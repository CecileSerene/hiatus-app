package hiatus.hiatusapp.Admin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;

import hiatus.hiatusapp.ContributionContext.ContributionContext;
import hiatus.hiatusapp.ContributionContext.PhotoContext;
import hiatus.hiatusapp.ContributionContext.TextContext;
import hiatus.hiatusapp.DatabaseHelper;
import hiatus.hiatusapp.Menu.ContributionContextArrayAdapter;
import hiatus.hiatusapp.R;

public class AdminActivity extends Activity {

    private static final String TAG = "AdminActivity";

    /*
    The main Activity af the admin. He can add a new context and see all the current contexts
     */

    private DatabaseReference mRef;
    private ContributionContextArrayAdapter mAdapter;
    private ArrayList<ContributionContext> mContexts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        ListView lvAdmin = (ListView) findViewById(R.id.admin_context);
        mContexts = new ArrayList<>();


        // setup list view adapter

        mAdapter = new ContributionContextArrayAdapter(this, mContexts);

        mRef = DatabaseHelper.getContributionContextReference();
        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot contextSnapshot : dataSnapshot.getChildren()) {
                    ContributionContext context = DatabaseHelper.retrieveContext(contextSnapshot);
                    mContexts.add(context);
                    Log.d(TAG, "load_context:" + context.getId() + ":" + context.getTitle());
                }
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "loadContexts:onCancelled", databaseError.toException());
            }
        });

        lvAdmin.setAdapter(mAdapter);


        // setup listview click behavior

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
