package hiatus.hiatusapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class TextActivity extends Activity {

    Button mButton;
    EditText mEdittext;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);

        mButton = (Button) findViewById(R.id.preview);
        mEdittext = (EditText) findViewById(R.id.mytext);



    }

    public void preview(View view){
        String contribution = mEdittext.getText().toString();
        Intent i = new Intent(this, PreviewActivity.class);
        i.putExtra("contribution",contribution);
        startActivity(i);


    }


}
