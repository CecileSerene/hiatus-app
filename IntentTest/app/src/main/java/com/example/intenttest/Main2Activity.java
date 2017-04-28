package com.example.intenttest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Main2Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                Intent i = new Intent(this, MainActivity.class);
                startActivity(i);

        }
    }
}
