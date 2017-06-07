package hiatus.hiatusapp.menu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;

import hiatus.hiatusapp.R;
import hiatus.hiatusapp.contribution.text.TextContent;
import hiatus.hiatusapp.previews.PreviewTextFragment;

public class UserTextPreviewActivity extends FragmentActivity {

    TextContent content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preview_basic);

        Intent i = getIntent();
        content = i.getParcelableExtra("content");

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, PreviewTextFragment.newInstance(content))
                .commit();
    }
}
