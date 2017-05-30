package hiatus.hiatusapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import hiatus.hiatusapp.ContributionContent.TextContent;
import hiatus.hiatusapp.ContributionContext.TextContext;

public class TextPreviewActivity extends AppCompatActivity {

    TextView text_preview;
    TextView title_preview;
    Button modify;
    Button send;
    TextContext context;
    TextContent content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);

        Intent i = getIntent();
        content = (TextContent) i.getParcelableExtra("content");


        text_preview = (TextView) findViewById(R.id.text_preview);
        title_preview = (TextView) findViewById(R.id.title_preview);
        modify = (Button) findViewById(R.id.modify);


        content.display(title_preview, text_preview);


        //if modifications of the contributions are allowed, then the button is enabled
        if (content.getContext().isModifications_allowed()){
            modify.setEnabled(true);
        }


    }

    public void modify(View view){
        Intent i = new Intent(this, TextActivity.class);
        i.putExtra("content",content);
        startActivity(i);
    }

    public  void send(View view){
        Intent i = new Intent(this, SendActivity.class);
        i.putExtra("content",content);
        startActivity(i);
    }
}
