package com.example.intenttest;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void onClick(View view){
        switch (view.getId()) {
            case R.id.button:
                Intent i = new Intent(this, Main2Activity.class);
                startActivity(i);
                break;
            case R.id.buttonHiatus:
                Intent iatus = new Intent(Intent.ACTION_VIEW);
                iatus.setData(Uri.parse("http://bda.campus.ecp.fr/hiatus/"));
                startActivity(iatus);
                break;

        }

    }
}
