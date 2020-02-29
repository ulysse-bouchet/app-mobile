package fr.ulyssebouchet.gevu;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(0, 0);
        setContentView(R.layout.activity_search);

        BottomNavigationView bottomNavigationMenu = findViewById(R.id.bottom_navigation);
        bottomNavigationMenu.setSelectedItemId(R.id.menu_item_search);

        bottomNavigationMenu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent i;
                switch (item.getItemId()) {
                    case R.id.menu_item_home:
                        i = new Intent(SearchActivity.this, HomeActivity.class);
                        break;
                    case R.id.menu_item_search:
                        i = new Intent(SearchActivity.this, SearchActivity.class);
                        break;
                    case R.id.menu_item_matches:
                        i = new Intent(SearchActivity.this, MatchesActivity.class);
                        break;
                    default:
                        i = new Intent(SearchActivity.this, SearchActivity.class);
                        break;
                }
                startActivity(i);
                return true;
            }
        });
    }
}