package hiatus.hiatusapp.Admin;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

import hiatus.hiatusapp.ContributionBundle.ContributionBundle;
import hiatus.hiatusapp.ContributionContent.TextContent;
import hiatus.hiatusapp.R;

public class UserContributionActivity extends Activity {

    private ArrayList<ContributionBundle> bundles;
    ListView lvBundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_contribution);
        
        //exemple
        bundles = new ArrayList<ContributionBundle>();
        bundles.add(new ContributionBundle("0", "1", new TextContent("1")));
        bundles.add(new ContributionBundle("1", "2", new TextContent("2")));
        //

        lvBundle = (ListView) findViewById(R.id.bundle_list);

        lvBundle.setAdapter(new ContributionBundleArrayAdapter(this, bundles));

        //TODO set an onclickitemlistener which redirects to different activities textpreview or photopreview

    }
}
