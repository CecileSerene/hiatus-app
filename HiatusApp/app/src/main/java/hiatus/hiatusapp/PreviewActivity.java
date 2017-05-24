package hiatus.hiatusapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class PreviewActivity extends AppCompatActivity {

    TextView preview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);

        preview = (TextView) findViewById(R.id.text_preview);

        Intent intent = getIntent();
        String contribution = intent.getExtras().getString("contribution");
        preview.setText(contribution);


    }
}
