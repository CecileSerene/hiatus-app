package hiatus.hiatusapp.admin;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;


import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

import hiatus.hiatusapp.contribution.base.ContributionContext;
import hiatus.hiatusapp.account_management.LoginActivity;
import hiatus.hiatusapp.DatabaseHelper;
import hiatus.hiatusapp.menu.ContributionContextArrayAdapter;
import hiatus.hiatusapp.R;

public class AdminActivity extends Activity {

    private static final String TAG = "AdminActivity";

    /*
    The main Activity af the admin. He can add a new context and see all the current contexts
     */

    private DatabaseReference mRef;
    private ContributionContextArrayAdapter mOpenAdapter;
    private ContributionContextArrayAdapter mClosedAdapter;
    private ArrayList<ContributionContext> mOpenContexts;
    private ArrayList<ContributionContext> mClosedContexts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        Button newContextButton = (Button) findViewById((R.id.new_context));
        Button exitButton = (Button) findViewById(R.id.admin_exit);
        ListView lvOpen = (ListView) findViewById(R.id.admin_open_context);
        ListView lvClosed = (ListView) findViewById(R.id.admin_closed_context);
        mOpenContexts = new ArrayList<>();
        mClosedContexts = new ArrayList<>();

        // setup list view adapter

        mOpenAdapter = new ContributionContextArrayAdapter(this, mOpenContexts);
        mClosedAdapter = new ContributionContextArrayAdapter(this, mClosedContexts);

        mRef = DatabaseHelper.getOpenContributionContextReference();
        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot contextSnapshot : dataSnapshot.getChildren()) {
                    ContributionContext context = DatabaseHelper.retrieveContext(contextSnapshot);
                    mOpenContexts.add(context);
                    Log.d(TAG, "load_context:" + context.getId() + ":open");
                }
                mOpenAdapter.notifyDataSetChanged();
                //mClosedAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "loadContexts:onCancelled", databaseError.toException());
            }
        });

        DatabaseHelper.getClosedContributionContextReference()
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot contextSnapshot : dataSnapshot.getChildren()) {
                            ContributionContext context = DatabaseHelper.retrieveContext(contextSnapshot);
                            mClosedContexts.add(context);
                            Log.d(TAG, "load_context:" + context.getId() + ":closed");
                        }
                        mClosedAdapter.notifyDataSetChanged();
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.w(TAG, "loadContexts:onCancelled", databaseError.toException());
                    }
                });

        lvOpen.setAdapter(mOpenAdapter);
        lvClosed.setAdapter(mClosedAdapter);

        // setup listview click behavior

        lvOpen.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                ContributionContext context = mOpenAdapter.getItem(position);
                // populate an intent with the contribution context
                Intent i = new Intent(view.getContext(), UserContributionActivity.class);
                i.putExtra("context",context);
                startActivity(i);
            }
        });

        // set new context button behavior
        newContextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), NewContextActivity.class);
                startActivity(i);
                finish();
            }
        });

        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



    }

}
