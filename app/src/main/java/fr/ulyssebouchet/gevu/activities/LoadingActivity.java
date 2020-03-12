package fr.ulyssebouchet.gevu.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;

import java.util.List;

import fr.ulyssebouchet.gevu.R;
import fr.ulyssebouchet.gevu.data.AppDatabase;
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
                final Intent intent = new Intent(LoadingActivity.this, MainActivity.class);

                Thread ligue1 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        List<Match> matches = null;
                        try {
                            matches = FootballDataAPI.getMatches(FootballDataAPI.ID_LIGUE1);
                        } catch (Exception ignored) { }
                        if(matches != null) {
                            intent.putExtra("L1_NB", matches.size());
                            for (int i = 0; i < matches.size(); ++i)
                                intent.putExtra("L1_MATCH_" + i, matches.get(i).toString());
                        }
                    }
                });

                Thread bundesliga = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        List<Match> matches = null;
                        try {
                            matches = FootballDataAPI.getMatches(FootballDataAPI.ID_BUNDESLIGA);
                        } catch (Exception ignored) { }
                        if(matches != null) {
                            intent.putExtra("BUNDES_NB", matches.size());
                            for (int i = 0; i < matches.size(); ++i)
                                intent.putExtra("BUNDES_MATCH_" + i, matches.get(i).toString());
                        }
                    }
                });

                Thread liga = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        List<Match> matches = null;
                        try {
                            matches = FootballDataAPI.getMatches(FootballDataAPI.ID_LIGA);
                        } catch (Exception ignored) { }
                        if(matches != null) {
                            intent.putExtra("LIGA_NB", matches.size());
                            for (int i = 0; i < matches.size(); ++i)
                                intent.putExtra("LIGA_MATCH_" + i, matches.get(i).toString());
                        }
                    }
                });

                Thread premierLeague = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        List<Match> matches = null;
                        try {
                            matches = FootballDataAPI.getMatches(FootballDataAPI.ID_PL);
                        } catch (Exception ignored) { }
                        if(matches != null) {
                            intent.putExtra("PL_NB", matches.size());
                            for (int i = 0; i < matches.size(); ++i)
                                intent.putExtra("PL_MATCH_" + i, matches.get(i).toString());
                        }
                    }
                });

                Thread serieA = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        List<Match> matches = null;
                        try {
                            matches = FootballDataAPI.getMatches(FootballDataAPI.ID_SERIE_A);
                        } catch (Exception ignored) { }
                        if(matches != null) {
                            intent.putExtra("SERIA_NB", matches.size());
                            for (int i = 0; i < matches.size(); ++i)
                                intent.putExtra("SERIA_MATCH_" + i, matches.get(i).toString());
                        }
                    }
                });

                ligue1.start();
                bundesliga.start();
                liga.start();
                premierLeague.start();
                serieA.start();

                try {
                    ligue1.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    bundesliga.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    liga.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    premierLeague.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    serieA.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                startActivity(intent);
                finish();
            }
        }).start();
    }
}