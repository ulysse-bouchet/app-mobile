package fr.ulyssebouchet.gevu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(0, 0);
        setContentView(R.layout.activity_home);

        BottomNavigationView bottomNavigationMenu = findViewById(R.id.bottom_navigation);
        bottomNavigationMenu.setSelectedItemId(R.id.menu_item_home);

        bottomNavigationMenu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent i;
                switch (item.getItemId()) {
                    case R.id.menu_item_home:
                        i = new Intent(HomeActivity.this, HomeActivity.class);
                        break;
                    case R.id.menu_item_search:
                        i = new Intent(HomeActivity.this, SearchActivity.class);
                        break;
                    case R.id.menu_item_matches:
                        i = new Intent(HomeActivity.this, MatchesActivity.class);
                        break;
                    default:
                        i = new Intent(HomeActivity.this, HomeActivity.class);
                        break;
                }
                startActivity(i);
                return true;
            }
        });
    }
}