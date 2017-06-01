package hiatus.hiatusapp.Admin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import hiatus.hiatusapp.ContributionContext.ContributionContext;
import hiatus.hiatusapp.ContributionContext.PhotoContext;
import hiatus.hiatusapp.ContributionContext.TextContext;
import hiatus.hiatusapp.Menu.ContributionContextArrayAdapter;
import hiatus.hiatusapp.R;

public class AdminActivity extends Activity {

    /*
    The main Activity af the admin. He can add a new context and see all the current contexts
     */

    private ArrayList<ContributionContext> contexts;

    private ListView lvAdmin;
    private Button newContextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        lvAdmin = (ListView) findViewById(R.id.admin_context);
        newContextButton = (Button) findViewById((R.id.new_context));


        // v TODO replace with a call to the database to all current contexts
        contexts = new ArrayList<>();

        contexts.add(new TextContext("0", "L'Amour en cage", "Passion","instructions", 50));
        contexts.add(new PhotoContext("0", "Concours de dessin","voici les instructiions","Jeu"));

        // ^

        lvAdmin.setAdapter(new ContributionContextArrayAdapter(this, contexts));

        lvAdmin.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                ContributionContext context = contexts.get(position);

                // populate an intent with the contribution context
                Intent i = new Intent(view.getContext(), UserContributionActivity.class);
                i.putExtra("context",context);
                startActivity(i);
            }
        });

        newContextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), NewContextActivity.class);
                startActivity(i);
            }
        });



    }

}
