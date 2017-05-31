/*
package hiatus.hiatusapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import hiatus.hiatusapp.ContributionContext.ContributionContext;
import hiatus.hiatusapp.ContributionContext.ContributionDrawing;
import hiatus.hiatusapp.ContributionContext.ContributionPhoto;
import hiatus.hiatusapp.ContributionContext.ContributionText;
import hiatus.hiatusapp.ContributionContext.Type;

public class ContributionActivity extends Activity {

    private ContributionContext context;
    private Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contribution);

*/
/*        //____ Exemple text context _____
        String instructions_ex = "Ecrivez les premiers mots qui vous viennent sur le thème du jeu";
        TextContext text = new TextContext(instructions_ex,"Ecriture automatique","Jeu",50);
        context = text;
        //___ fin exemple ____*//*


        //____ Exemple Photo context_____
        String instructions_ex = "Prenez une photo sur le vif";
        PhotoContext photo = new PhotoContext(instructions_ex,"Photo sur le vif","Sur le vif",50);
        context = photo;

        //___ fin exemple ____

        String theme = context.getTheme();
        String instructions = context.getInstructions();

        TextView title = (TextView) findViewById(R.id.title);
        title.setText(context.getTitle());

        TextView explanation = (TextView) findViewById(R.id.instructions);
        String displayText = "Le thème est " +  theme + " et les instructions sont "+instructions+".";
        if (context.getLimited_time() < Double.POSITIVE_INFINITY){
            displayText += "\nTu as un temps limité de " + context.getLimited_time() + " secondes.";
        }
        if (context instanceof TextContext){
            displayText += "\nTu es limité à " + ((TextContext) context).getNb_of_characters() + " caractères";
        }
        explanation.setText(displayText);

        Button button = (Button) findViewById(R.id.contribuer);


    }

    public void contribuer(View view){

        if (context instanceof TextContext) {
            Intent i = new Intent(this, TextActivity.class);
            startActivity(i);
        }
        else if(context instanceof ContributionDrawing) {
            Toast.makeText(getApplicationContext(), "Drawing not implemented yet", Toast.LENGTH_SHORT);
        }
        else if(context instanceof PhotoContext) {
            Intent i = new Intent(this, PhotoActivity.class);
            startActivity(i);
        }



    }
}
*/
