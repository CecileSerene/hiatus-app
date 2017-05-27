package hiatus.hiatusapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import hiatus.hiatusapp.ContributionContent.TextContent;
import hiatus.hiatusapp.ContributionContext.ContributionText;


public class TextActivity extends Activity {

    Button mPreviewButton;
    EditText mEdittext;
    ContributionText context;
    Button mTitleButton;
    TextContent content;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);

        //____ Exemple _____
        String instructions_ex = "Ecrivez les premiers mots qui vous viennent sur le thème du jeu";
        ContributionText text = new ContributionText(instructions_ex,"Ecriture automatique","Jeu",50);
        context = text;


        //___ fin exemple ____



        mPreviewButton = (Button) findViewById(R.id.preview);
        mTitleButton = (Button) findViewById(R.id.ajouter_titre);
        mEdittext = (EditText) findViewById(R.id.mytext);
        mEdittext.setFilters(new InputFilter[] {new InputFilter.LengthFilter(context.getNb_of_characters())});
        content = new TextContent("",context);

        Intent intent = getIntent();
        try {
            String contribution = intent.getExtras().getString("contribution");
            String title = intent.getExtras().getString("title");
            mEdittext.setText(contribution);
            content.setTitle(title);
        } catch (Exception e) {

        }



    }

    public void preview(View view){
        String contribution = mEdittext.getText().toString();
        Intent i = new Intent(this, TextPreviewActivity.class);
        content.setText(contribution);
        Log.i("the content : ",content.toString());
        //TODO here I want to pass the content object and not just the string,
        // but I cant resolve how to do it, so I just send the title and the text,
        // and suppose we canc get the context in another way

        i.putExtra("title",content.getTitle());
        i.putExtra("contribution", content.getText());
        startActivity(i);


    }

    public void setTitle(View view){
        LayoutInflater inflater = getLayoutInflater();
        View popUpLayout = inflater.inflate(R.layout.popup_title, null);

        final EditText mTitle = (EditText) popUpLayout.findViewById(R.id.title);
        mTitle.setText(content.getTitle());

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Titre");
        alert.setView(popUpLayout);
        alert.setCancelable(true);

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {}
        });
        alert.setPositiveButton("Ajouter", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                String title = mTitle.getText().toString();
                content.setTitle(title);

                Toast.makeText(getBaseContext(), "Tu as ajouté le titre : " + title, Toast.LENGTH_SHORT).show();
            }
        });

        AlertDialog dialog = alert.create();
        dialog.show();
    }


}
