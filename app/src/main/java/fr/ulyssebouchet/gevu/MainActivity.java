package fr.ulyssebouchet.gevu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Timer;
import java.util.TimerTask;

import fr.ulyssebouchet.gevu.fragments.HomeFragment;
import fr.ulyssebouchet.gevu.fragments.MatchesFragment;
import fr.ulyssebouchet.gevu.fragments.SearchFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationMenu = findViewById(R.id.bottom_navigation);
        openFragment(new HomeFragment());

        bottomNavigationMenu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_item_home:
                        openFragment(new HomeFragment());
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