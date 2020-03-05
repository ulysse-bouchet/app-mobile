package fr.ulyssebouchet.gevu.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import fr.ulyssebouchet.gevu.R;
import fr.ulyssebouchet.gevu.data.Match;
import fr.ulyssebouchet.gevu.fragments.HomeFragment;
import fr.ulyssebouchet.gevu.fragments.MatchesFragment;
import fr.ulyssebouchet.gevu.fragments.SearchFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final Map<String, List<Match>> leagues = new HashMap<>();

        leagues.put("Ligue 1", getMatches("L1"));
        leagues.put("Bundesliga", getMatches("BUNDES"));
        leagues.put("Liga", getMatches("LIGA"));
        leagues.put("Premier League", getMatches("PL"));
        leagues.put("Serie A", getMatches("SERIA"));

        BottomNavigationView bottomNavigationMenu = findViewById(R.id.bottom_navigation);
        openFragment(new HomeFragment(leagues));

        bottomNavigationMenu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_item_home:
                        openFragment(new HomeFragment(leagues));
                        break;
                    case R.id.menu_item_search:
                        openFragment(new SearchFragment());
                        break;
                    case R.id.menu_item_matches:
                        openFragment(new MatchesFragment());
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }

    private List<Match> getMatches(String league) {
        Intent intent = getIntent();

        int nbMatches = intent.getIntExtra(league + "_NB", -1);
        final List<Match> matches = new LinkedList<>();
        for (int i = 0; i < nbMatches; ++i) {
            matches.add(Match.Companion.getMatch(intent.getStringExtra(league + "_MATCH_" + i)));
        }
        return matches;
    }

    private void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    boolean exit = false;
    Timer t = new Timer();
    @Override
    public void onBackPressed() {
        if (!exit) {
            Toast.makeText(MainActivity.this,
                    "Appuyez de nouveau pour quitter", Toast.LENGTH_SHORT).show();
            exit = true;
            t.schedule(new TimerTask() {
                @Override
                public void run() {
                    exit = false;
                }
            }, 2000);
        } else
            finish();
        //super.onBackPressed();
    }
}