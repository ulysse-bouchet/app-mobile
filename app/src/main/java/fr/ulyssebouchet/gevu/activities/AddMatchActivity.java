package fr.ulyssebouchet.gevu.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.room.Room;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import fr.ulyssebouchet.gevu.R;
import fr.ulyssebouchet.gevu.data.AppDatabase;
import fr.ulyssebouchet.gevu.data.Match;

public class AddMatchActivity extends AppCompatActivity {
    private static final int GALLERY_REQUEST_CODE = 1;
    private ImageView selectedImageView;
    private Match match;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_match);

        match = Match.Companion.getMatch(getIntent().getStringExtra("match"));

        ViewGroup insertPoint = findViewById(R.id.match_form);

        ConstraintLayout layout = (ConstraintLayout) getLayoutInflater().inflate(R.layout.match, null);

        String score = match.getHomeTeamGoals() + " - " + match.getAwayTeamGoals();

        ((TextView) layout.findViewById(R.id.home_team)).setText(match.getHomeTeam());
        ((TextView) layout.findViewById(R.id.score)).setText(score);
        ((TextView) layout.findViewById(R.id.away_team)).setText(match.getAwayTeam());
        ((TextView) layout.findViewById(R.id.date)).setText(match.getDate());

        insertPoint.addView(layout, 1);

        findViewById(R.id.btn_return_icon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        findViewById(R.id.btn_return).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        selectedImageView = (ImageView) findViewById(R.id.selected_image);
        selectedImageView.setVisibility(View.GONE);

        findViewById(R.id.btn_select_image).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickFromGallery();
            }
        });

        findViewById(R.id.btn_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveMatch();
            }
        });
    }

    private void saveMatch() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                EditText anecdote = findViewById(R.id.input_anecdote);
                match.setAnecdote(anecdote.getText().toString());
                AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                        AppDatabase.class, "database-name").build();
                try {
                    db.MatchDao().insert(match);
                } catch (Exception ignored) { }
            }
        }).start();
        finish();
    }

    private void pickFromGallery() {
        //Create an Intent with action as ACTION_PICK
        Intent intent = new Intent(Intent.ACTION_PICK);
        // Sets the type as image/*. This ensures only components of type image are selected
        intent.setType("image/*");
        //We pass an extra array with the accepted mime types. This will ensure only components with these MIME types as targeted.
        String[] mimeTypes = {"image/jpeg", "image/png"};
        intent.putExtra(Intent.EXTRA_MIME_TYPES,mimeTypes);
        // Launching the Intent
        startActivityForResult(intent, GALLERY_REQUEST_CODE);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Result code is RESULT_OK only if the user selects an Image
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == GALLERY_REQUEST_CODE) {//data.getData returns the content URI for the selected Image
                Uri selectedImage = data.getData();
                selectedImageView.setImageURI(selectedImage);
                selectedImageView.setVisibility(View.VISIBLE);
                match.setImage(selectedImage.toString());
            }
        } else super.onActivityResult(requestCode, resultCode, data);
    }
}