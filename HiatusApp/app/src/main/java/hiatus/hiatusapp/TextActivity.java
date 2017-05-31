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
import hiatus.hiatusapp.ContributionContext.TextContext;


public class TextActivity extends Activity {

    Button mPreviewButton;
    EditText mEdittext;
    TextContext context;
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
        context = i.getParcelableExtra("context");
        content = i.getParcelableExtra("content");
        if (content == null) {
            content = new TextContent(context.getId());
        }

        mEdittext.setFilters(new InputFilter[] {new InputFilter.LengthFilter(context.getNumberOfCharacters())});
    }

    public void preview(View view){
        // set the content text
        content.setText(mEdittext.getText().toString());

        // build the intent
        Intent i = new Intent(this, TextPreviewActivity.class);
        i.putExtra("context", context);
        i.putExtra("content", content);

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
