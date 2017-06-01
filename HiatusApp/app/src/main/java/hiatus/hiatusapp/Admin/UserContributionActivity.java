package hiatus.hiatusapp.Admin;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import hiatus.hiatusapp.ContributionBundle.ContributionBundle;
import hiatus.hiatusapp.ContributionContent.PhotoContent;
import hiatus.hiatusapp.ContributionContent.TextContent;
import hiatus.hiatusapp.ContributionContext.ContributionContext;
import hiatus.hiatusapp.R;

public class UserContributionActivity extends Activity {

    /*
    Here the admin can see the different contributions on one context. He can also stop this context.
     */

    private ArrayList<ContributionBundle> bundles;
    ListView lvBundle;
    ContributionContext context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_contribution);

        Intent intent = getIntent();
        context = intent.getParcelableExtra("context");

        //TODO ici il faut faire la query de récupérer tous les bundle ayant ce context
        
        //exemple
        ArrayList<ContributionBundle> textBundles = new ArrayList<ContributionBundle>();
        textBundles.add(new ContributionBundle("0", "johndoeid", "John Doe", "1", new TextContent("1")));
        textBundles.add(new ContributionBundle("1", "johndoeid", "John Doe", "2", new TextContent("2")));
        //

        //exemple
        ArrayList<ContributionBundle> photoBundles = new ArrayList<ContributionBundle>();
        photoBundles.add(new ContributionBundle("0", "johndoeid", "John Doe", "1", new PhotoContent("1")));
        photoBundles.add(new ContributionBundle("1", "johndoeid", "John Doe", "2", new PhotoContent("2")));
        //

        //TODO remove after proper query made !
        if (context.getType() == ContributionContext.TYPE_TEXT) {
            bundles = textBundles;
        }
        else {
            bundles = photoBundles;
        }

        lvBundle = (ListView) findViewById(R.id.bundle_list);

        lvBundle.setAdapter(new ContributionBundleArrayAdapter(this, bundles));

        lvBundle.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                ContributionBundle bundle = bundles.get(position);

                if(context.getType() == ContributionContext.TYPE_TEXT){
                    // populate an intent with the contribution context
                    Intent i = new Intent(view.getContext(), TextContributionPreviewActivity.class);
                    i.putExtra("content",bundle.getContent());
                    startActivity(i);

                }
                else if(context.getType() == ContributionContext.TYPE_PHOTO){
                    // populate an intent with the contribution context
                    Intent i = new Intent(view.getContext(), PhotoContributionPreviewActivity.class);
                    i.putExtra("content",bundle.getContent());
                    startActivity(i);

                }


            }
        });

        Button buttonStop = (Button) findViewById(R.id.stop_context);
        buttonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO here we want to remove the context, to stop the contributions
            }
        });

    }
}
