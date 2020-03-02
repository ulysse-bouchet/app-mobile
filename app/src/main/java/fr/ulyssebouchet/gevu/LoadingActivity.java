package fr.ulyssebouchet.gevu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import fr.ulyssebouchet.gevu.data.FootballDataAPI;
import fr.ulyssebouchet.gevu.data.Match;

public class LoadingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        new Thread(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(LoadingActivity.this, MainActivity.class);
                try {
                    List<Match> matches = FootballDataAPI.getMatches(FootballDataAPI.ID_LIGUE1);

                    intent.putExtra("nbMatches", matches.size());
                    for (int i = 0; i < matches.size(); ++i){
                        intent.putExtra("Match " + i, matches.get(i).toString());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    startActivity(intent);
                    finish();
                }
            }
        }).start();
    }
}