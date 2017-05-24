package hiatus.hiatusapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ContributionActivity extends Activity {

    private CurrentIssue issue;
    private Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contribution);

        //____ Exemple _____
        String[] themes = {"jeu","sur le vif"};
        issue = new CurrentIssue(themes, new CurrentIssue.date(30,4));
        issue.setParticipative(new CurrentIssue.Participative( Type.TEXTE, 20.0, "sur le vif"));
        issue.getParticipative().setInstructions("Ecrit en quelques mots ce que t'inspire le thème");

        //___ fin exemple ____

        String theme = issue.getParticipative().getTheme();;
        Type type = issue.getParticipative().getType();
        String instruction = issue.getParticipative().getInstructions();
        double time = issue.getParticipative().getTime();

        TextView explanation = (TextView) findViewById(R.id.textView);
        explanation.setText("La contribution est sur le thème " + theme + "." +
                " Il s'agit de faire un " + type.toString() + " en suivant les instructions suivantes : " + instruction + "." +
                "Tu as un temps limité de " + time + "secondes.\n"
                + "Laisse ton imagination déborder !");

        Button button = (Button) findViewById(R.id.contribuer);


    }

    public void contribuer(View view){

        switch(issue.getParticipative().getType()){
            case TEXTE :
                Intent i = new Intent(this, TextActivity.class);
                startActivity(i);
                break;
            case DESSIN:
                Toast.makeText(getApplicationContext(), "Drawing not implemented yet", Toast.LENGTH_SHORT);
                break;
            case PHOTO:
                Toast.makeText(getApplicationContext(), "Photo not implemented yet", Toast.LENGTH_SHORT);
                break;

        }

    }
}
