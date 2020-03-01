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

        //final Match m = new Match("OM", "PSG");
        //final Match m2 = new Match("OM", "OL");

        final

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Intent intent = new Intent(LoadingActivity.this, MainActivity.class);

                    List<Match> matches = FootballDataAPI.getMatches(FootballDataAPI.ID_LIGUE1);

                    intent.putExtra("nbMatches", matches.size());
                    for (int i = 0; i < matches.size(); ++i){
                        intent.putExtra("Match " + i, matches.get(i).toString());
                    }

                    startActivity(intent);
                    finish();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();


        /*
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Intent i = new Intent(LoadingActivity.this, MainActivity.class);
                i.putExtra("nbMatches", 2);
                i.putExtra("Match 1", m.toString());
                i.putExtra("Match 2", m2.toString());
                startActivity(i);
                finish();
            }
        }, 1500);
        */
    }
}