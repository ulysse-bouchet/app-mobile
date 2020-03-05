package fr.ulyssebouchet.gevu.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import fr.ulyssebouchet.gevu.R;

public class AddMatchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_match);
        TextView t = findViewById(R.id.text_test);
        t.setText(getIntent().getStringExtra("match"));
    }
}