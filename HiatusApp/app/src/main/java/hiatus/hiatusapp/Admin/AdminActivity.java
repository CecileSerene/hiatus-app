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

import hiatus.hiatusapp.ContributionContext.ContributionContext;
import hiatus.hiatusapp.ContributionContext.PhotoContext;
import hiatus.hiatusapp.ContributionContext.TextContext;
import hiatus.hiatusapp.ContributionHomeDetail;
import hiatus.hiatusapp.Menu.ContributionContextArrayAdapter;
import hiatus.hiatusapp.NewContextActivity;
import hiatus.hiatusapp.R;

import static hiatus.hiatusapp.R.id.container;

public class AdminActivity extends Activity {

    private ArrayList<ContributionContext> contexts;

    private ListView lvAdmin;
    private Button newContextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        lvAdmin = (ListView) findViewById(R.id.admin_context);
        newContextButton = (Button) findViewById((R.id.new_context));


        // v TODO replace with a call to the database to the user's history contributions
        contexts = new ArrayList<>();
        contexts.add(new TextContext("0","L'Amour en cage", "Passion","instructions", 50));
        contexts.add(new PhotoContext( "1","Concours de dessin","voici les instructiions","Jeu"));
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
