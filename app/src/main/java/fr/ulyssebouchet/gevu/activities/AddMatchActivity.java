package fr.ulyssebouchet.gevu.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import fr.ulyssebouchet.gevu.R;
import fr.ulyssebouchet.gevu.data.Match;

public class AddMatchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_match);

        Match match = Match.Companion.getMatch(getIntent().getStringExtra("match"));

        ViewGroup insertPoint = findViewById(R.id.match_form);

        ConstraintLayout layout  = (ConstraintLayout) getLayoutInflater().inflate(R.layout.match, null);

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
    }
}