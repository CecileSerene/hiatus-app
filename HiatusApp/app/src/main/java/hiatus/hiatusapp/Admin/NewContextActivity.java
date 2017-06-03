package hiatus.hiatusapp.Admin;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import org.w3c.dom.Text;

import hiatus.hiatusapp.ContributionContext.ContributionContext;
import hiatus.hiatusapp.ContributionContext.PhotoContext;
import hiatus.hiatusapp.ContributionContext.TextContext;
import hiatus.hiatusapp.DatabaseHelper;
import hiatus.hiatusapp.Menu.MenuActivity;
import hiatus.hiatusapp.R;

public class NewContextActivity extends Activity {

    EditText et_title;
    EditText et_number;
    EditText et_theme;
    EditText et_instructions;
    RadioButton rd_text;
    RadioButton rd_photo;
    Button b_send;
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

    private boolean validateForm() {
        title = et_title.getText().toString();
        theme = et_theme.getText().toString();
        instructions = et_instructions.getText().toString();

        boolean valid = true;
        // reset errors
        et_number.setError(null);
        et_title.setError(null);
        et_theme.setError(null);
        et_instructions.setError(null);

        if (TextUtils.isEmpty(title)) {
            et_title.setError(getString(R.string.error_field_required));
            valid = false;
        }
        if (TextUtils.isEmpty(theme)) {
            et_theme.setError(getString(R.string.error_field_required));
            valid = false;
        }
        if(TextUtils.isEmpty(instructions)) {
            et_instructions.setError(getString(R.string.error_field_required));
            valid = false;
        }
        if (rd_text.isChecked()) {
            if (TextUtils.isEmpty(et_number.getText().toString())) {
                et_number.setError(getString(R.string.error_field_required));
                valid = false;
            }
        }
        return valid;
    }

    public void create_context(View view){
        if (!validateForm()) {
            return;
        }
        ContributionContext context = null;
        title = et_title.getText().toString();
        theme = et_theme.getText().toString();
        instructions = et_instructions.getText().toString();

        // build the context corresponding to which type is chosen

        if (rd_text.isChecked()) {
            int nb_of_characters = Integer.parseInt(et_number.getText().toString());
            String id = DatabaseHelper.newContributionContextId();
            context = new TextContext(id, title, theme, instructions, nb_of_characters);
        }
        else if (rd_photo.isChecked()){
            // TODO save image to DB and get link into the contribution context
            String id = DatabaseHelper.newContributionContextId();
            context = new PhotoContext(id, instructions, title, theme);
        }

        // save the context to the database

        if (context != null) {
            DatabaseHelper.saveContributionContext(context);
            Toast.makeText(this, "Contribution context successfully saved.", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, AdminActivity.class));
            finish();
        }


    }
}
