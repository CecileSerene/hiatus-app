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
        mPreviewButton = (Button) findViewById(R.id.preview);
        mTitleButton = (Button) findViewById(R.id.ajouter_titre);
        mEdittext = (EditText) findViewById(R.id.mytext);

        Intent i = getIntent();

        try { //here it is the content that is sent via the intent, whene we come from the preview to modify
            content = (TextContent) i.getParcelableExtra("content");
            context = (ContributionText) content.getContext();
            mEdittext.setText(content.getText());
        } catch (Exception e) { // here it is the context that is sent
            context = (ContributionText) i.getParcelableExtra("context");
            content = new TextContent("",context);

        }
        Log.i("heeeeer", "nombre" + context.getNb_of_characters());

        mEdittext.setFilters(new InputFilter[] {new InputFilter.LengthFilter(context.getNb_of_characters())});






    }

    public void preview(View view){
        String contribution = mEdittext.getText().toString();
        Intent i = new Intent(this, TextPreviewActivity.class);
        content.setText(contribution);

        i.putExtra("content",content);
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
