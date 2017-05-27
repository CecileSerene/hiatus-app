package hiatus.hiatusapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import hiatus.hiatusapp.ContributionContent.ContributionContent;
import hiatus.hiatusapp.ContributionContent.TextContent;
import hiatus.hiatusapp.ContributionContext.ContributionContext;
import hiatus.hiatusapp.ContributionContext.ContributionText;

public class TextPreviewActivity extends AppCompatActivity {

    TextView text_preview;
    TextView title_preview;
    Button modify;
    Button send;
    ContributionText context;
    TextContent content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);

        //____ Exemple _____
        String instructions_ex = "Ecrivez les premiers mots qui vous viennent sur le thème du jeu";
        ContributionText text = new ContributionText(instructions_ex,"Ecriture automatique","Jeu",50);
        context = text;


        //___ fin exemple ____


        text_preview = (TextView) findViewById(R.id.text_preview);
        title_preview = (TextView) findViewById(R.id.title_preview);
        modify = (Button) findViewById(R.id.modify);

        Intent intent = getIntent();
        String contribution = intent.getExtras().getString("contribution");
        String title = intent.getExtras().getString("title");
        content = new TextContent(title, contribution, context);
        content.display(title_preview, text_preview);


        //if modifications of the contributions are allowed, then the button is enabled
        if (content.getContext().isModifications_allowed()){
            modify.setEnabled(true);
        }


    }

    public void modify(View view){
        Intent i = new Intent(this, TextActivity.class);
        i.putExtra("title",content.getTitle());
        i.putExtra("contribution", content.getText());
        startActivity(i);
    }

    public  void send(View view){
        Intent i = new Intent(this, SendActivity.class);
        startActivity(i);
    }
}
