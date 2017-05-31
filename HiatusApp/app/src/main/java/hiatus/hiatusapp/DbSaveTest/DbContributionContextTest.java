package hiatus.hiatusapp.DbSaveTest;

import android.os.Bundle;
import android.app.Activity;
import android.support.v7.preference.EditTextPreference;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import hiatus.hiatusapp.ContributionContext.TextContext;
import hiatus.hiatusapp.R;

public class DbContributionContextTest extends Activity {

    private EditText mTitle;
    private EditText mTheme;
    private EditText mInstructions;
    private DatabaseReference databaseContexts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db_contribution_context_test);

        databaseContexts = FirebaseDatabase.getInstance().getReference("mycontexts");

        mTitle = (EditText) findViewById(R.id.title);
        mTheme = (EditText) findViewById(R.id.theme);
        mInstructions = (EditText) findViewById(R.id.instructions);
        Button buttonSave = (Button) findViewById(R.id.button_save);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addContext();
            }
        });
    }

    private void addContext() {
        String title = mTitle.getText().toString();
        String theme = mTheme.getText().toString();
        String instructions = mInstructions.getText().toString();

        mTitle.setError(null);
        mTheme.setError(null);
        mInstructions.setError(null);

        boolean valid = true;

        if (TextUtils.isEmpty(title)) {
            mTitle.setError(getString(R.string.error_field_required));
            valid = false;
        }
        if (TextUtils.isEmpty(theme)) {
            mTheme.setError(getString(R.string.error_field_required));
            valid = false;
        }
        if (TextUtils.isEmpty(instructions)) {
            mInstructions.setError(getString(R.string.error_field_required));
            valid = false;
        }

        if (valid) {
            String id = databaseContexts.push().getKey();
            MyContext context = new MyContext(title, theme, instructions);
            databaseContexts.child(id).setValue(context);
            Toast.makeText(this, "Context added", Toast.LENGTH_SHORT).show();
        }
    }

}
