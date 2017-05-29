package hiatus.hiatusapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import hiatus.hiatusapp.Menu.MenuActivity;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
