package hiatus.hiatusapp.admin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import hiatus.hiatusapp.contribution.base.ContributionBundle;
import hiatus.hiatusapp.contribution.base.ContributionContent;
import hiatus.hiatusapp.contribution.base.ContributionContext;
import hiatus.hiatusapp.DatabaseHelper;
import hiatus.hiatusapp.R;

import static hiatus.hiatusapp.DatabaseHelper.setClosed;

public class UserContributionActivity extends Activity {

    private static final String TAG = "UserContribution";
    /*
    Here the admin can see the different contributions on one context. He can also stop this context.
     */

    private ArrayList<ContributionBundle> bundles;
    private ArrayList<ContributionContent> contents;
    ListView lvBundle;
    ContributionContext context;
    private ContributionBundleArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_contribution);

        Intent intent = getIntent();
        context = intent.getParcelableExtra("context");

        bundles = new ArrayList<>();
        contents = new ArrayList<>();

        // find all bundles that have this context by filtering the list of bundles in the database
        DatabaseReference mRef = DatabaseHelper.getContributionBundleReference();
        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // the bundle node contains nodes arranged by userId, iterate through them
                for (DataSnapshot userBundlesSnapshot : dataSnapshot.getChildren()) {
                    // there may be many bundles associated to one userId, iterate through them
                    for (DataSnapshot bundleSnapshot : userBundlesSnapshot.getChildren()) {
                        if (bundleSnapshot.child("contextId").getValue(String.class).equals(context.getId())) {
                            ContributionBundle bundle = bundleSnapshot.getValue(ContributionBundle.class);
                            bundles.add(bundle);
                            contents.add(DatabaseHelper.retrieveContent(bundle));
                            Log.d(TAG, "loadBundle:" + bundle.getId() + bundle.getContentModel().getTitle());
                        }
                    }
                    adapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "loadBundles:onCancelled", databaseError.toException());
            }
        });

        lvBundle = (ListView) findViewById(R.id.bundle_list);

        adapter = new ContributionBundleArrayAdapter(this, bundles);
        lvBundle.setAdapter(adapter);

        lvBundle.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                ContributionBundle bundle = bundles.get(position);

                if(context.getType() == ContributionContext.TYPE_TEXT){
                    // populate an intent with the contribution context
                    Intent i = new Intent(view.getContext(), TextContributionPreviewActivity.class);
                    i.putExtra("content", contents.get(position));
                    i.putExtra("bundleId", bundle.getId());
                    i.putExtra("userId", bundle.getUserUid());
                    startActivity(i);

                }
                else if(context.getType() == ContributionContext.TYPE_PHOTO){
                    // populate an intent with the contribution context
                    Intent i = new Intent(view.getContext(), PhotoContributionPreviewActivity.class);
                    i.putExtra("content", contents.get(position));
                    i.putExtra("bundleId", bundle.getId());
                    i.putExtra("userId", bundle.getUserUid());
                    startActivity(i);

                }


            }
        });

        Button buttonStop = (Button) findViewById(R.id.stop_context);
        buttonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.setCurrent(ContributionContext.CLOSED);
                setClosed(context);
                Toast.makeText(view.getContext(),"Context sucessfully closed",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(view.getContext(), AdminActivity.class);
                startActivity(i);
            }
        });

    }
}
