package hiatus.hiatusapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import hiatus.hiatusapp.ContributionContext.ContributionContext;
import hiatus.hiatusapp.ContributionContext.PhotoContext;
import hiatus.hiatusapp.ContributionContext.TextContext;
import hiatus.hiatusapp.Menu.MenuActivity;

public class NewContextActivity extends AppCompatActivity {

    EditText et_title;
    EditText et_number;
    EditText et_theme;
    EditText et_instructions;
    RadioButton rd_text;
    RadioButton rd_photo;
    Button b_send;
    ContributionContext context;
    String instructions;
    String theme;
    String title;
    int nb_of_character;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_context);

        et_title = (EditText) findViewById(R.id.add_title);
        et_number = (EditText) findViewById(R.id.add_number);
        et_theme = (EditText) findViewById(R.id.add_theme);
        et_instructions = (EditText) findViewById(R.id.add_instructions);
        rd_text = (RadioButton) findViewById(R.id.radio_text);
        rd_photo = (RadioButton) findViewById(R.id.radio_photo);
        b_send = (Button) findViewById(R.id.button_send);
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_text:
                if (checked)
                    et_number.setEnabled(true);
                    break;
            case R.id.radio_photo:
                if (checked)
                    et_number.setEnabled(false);
                    break;
        }
    }

    public void create_context(View view){
        title = et_title.getText().toString();
        theme = et_theme.getText().toString();
        instructions = et_instructions.getText().toString();
        Intent i = new Intent(this, MenuActivity.class);
        if (title.length()==0 | theme.length() == 0 | instructions.length() == 0){
            Toast.makeText(this, "Tu n'as pas rempli tous les champs", Toast.LENGTH_SHORT).show();
            //ici certains champs sont encore vides
        }
        if (rd_text.isChecked()){
            try {
                nb_of_character = Integer.parseInt(et_number.getText().toString());
                context = new TextContext(instructions, title, theme, nb_of_character);
                //TODO here we need to send the new context to the database
                startActivity(i);
            } catch ( Exception e){
                Toast.makeText(this, "Tu n'as pas mis de limite de caractères", Toast.LENGTH_SHORT).show();
            }

        }
        else if (rd_photo.isChecked()){
            context = new PhotoContext(instructions, title, theme);
            //TODO here we need to send the new context to the database
            startActivity(i);
        }
        else {
            Toast.makeText(this, "Tu n'as pas choisi le type", Toast.LENGTH_SHORT).show();
        }


    }
}
