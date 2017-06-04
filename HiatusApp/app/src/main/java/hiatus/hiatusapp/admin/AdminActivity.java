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
    private ContributionContextArrayAdapter mAdapter;
    private ArrayList<ContributionContext> mContexts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        Button newContextButton = (Button) findViewById((R.id.new_context));
        Button logoutButton = (Button) findViewById(R.id.admin_logout);
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

        newContextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), NewContextActivity.class);
                startActivity(i);
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Context currentContext = view.getContext();


                // confirm sign off through alert dialog
                new AlertDialog.Builder(currentContext)
                        .setTitle(R.string.confirm_signout_title)
                        .setMessage(R.string.confirm_signout_message)
                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                FirebaseAuth mAuth = FirebaseAuth.getInstance();
                                mAuth.signOut();
                                startActivity(new Intent(currentContext, LoginActivity.class));
                                //AdminActivity.class.finish();
                            }
                        })
                        .setNegativeButton(R.string.no, null)
                        .show();
            }
        });



    }

}
