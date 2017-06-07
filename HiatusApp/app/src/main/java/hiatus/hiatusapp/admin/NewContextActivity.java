package hiatus.hiatusapp.admin;

import android.app.Activity;
import android.app.LauncherActivity;
import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import hiatus.hiatusapp.contribution.base.ContributionContext;
import hiatus.hiatusapp.contribution.photo.PhotoContext;
import hiatus.hiatusapp.contribution.text.TextContext;
import hiatus.hiatusapp.DatabaseHelper;
import hiatus.hiatusapp.R;

public class NewContextActivity extends Activity{

    EditText mTitle;
    EditText mNumber;
    EditText mTheme;
    EditText mInstructions;
    Spinner typeSpinner;
    Button mSendButton;
    String instructions;
    String theme;
    String title;
    int nb_of_character;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_context);

        mTitle = (EditText) findViewById(R.id.add_title);
        mNumber = (EditText) findViewById(R.id.add_number);
        mTheme = (EditText) findViewById(R.id.add_theme);
        mInstructions = (EditText) findViewById(R.id.add_instructions);
        typeSpinner = (Spinner) findViewById(R.id.type_spinner);
        mSendButton = (Button) findViewById(R.id.button_send);

        // setup spinner adapter
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spinner_types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(adapter);
        typeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                findViewById(R.id.form_text).setVisibility(position == 0 ? View.VISIBLE : View.GONE);
                findViewById(R.id.form_photo).setVisibility(position == 1 ? View.VISIBLE : View.GONE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private boolean validateForm() {
        title = mTitle.getText().toString();
        theme = mTheme.getText().toString();
        instructions = mInstructions.getText().toString();

        boolean valid = true;
        // reset errors
        mNumber.setError(null);
        mTitle.setError(null);
        mTheme.setError(null);
        mInstructions.setError(null);

        if (TextUtils.isEmpty(title)) {
            mTitle.setError(getString(R.string.error_field_required));
            valid = false;
        }
        if (TextUtils.isEmpty(theme)) {
            mTheme.setError(getString(R.string.error_field_required));
            valid = false;
        }
        if(TextUtils.isEmpty(instructions)) {
            mInstructions.setError(getString(R.string.error_field_required));
            valid = false;
        }
        return valid;
    }

    public void create_context(View view){
        if (!validateForm()) {
            return;
        }
        ContributionContext context = null;
        title = mTitle.getText().toString();
        theme = mTheme.getText().toString();
        instructions = mInstructions.getText().toString();

        // build the context corresponding to which type is chosen

        switch (typeSpinner.getSelectedItemPosition()) {
            case 0:
                String nbCharacters = mNumber.getText().toString();
                int charLimit = TextUtils.isEmpty(nbCharacters) ? -1 : Integer.parseInt(nbCharacters);
                context = new TextContext(
                        DatabaseHelper.newContributionContextId(),
                        title, theme, instructions,
                        charLimit);
                break;
            case 1:
                context = new PhotoContext(
                        DatabaseHelper.newContributionContextId(),
                        title, theme, instructions);
                break;
            default:
                break;
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
