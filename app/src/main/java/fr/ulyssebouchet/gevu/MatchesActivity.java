package fr.ulyssebouchet.gevu;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MatchesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(0, 0);
        setContentView(R.layout.activity_matches);

        BottomNavigationView bottomNavigationMenu = findViewById(R.id.bottom_navigation);
        bottomNavigationMenu.setSelectedItemId(R.id.menu_item_matches);

        bottomNavigationMenu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent i;
                switch (item.getItemId()) {
                    case R.id.menu_item_home:
                        i = new Intent(MatchesActivity.this, HomeActivity.class);
                        break;
                    case R.id.menu_item_search:
                        i = new Intent(MatchesActivity.this, SearchActivity.class);
                        break;
                    case R.id.menu_item_matches:
                        i = new Intent(MatchesActivity.this, MatchesActivity.class);
                        break;
                    default:
                        i = new Intent(MatchesActivity.this, MatchesActivity.class);
                        break;
                }
                startActivity(i);
                return true;
            }
        });
    }
}