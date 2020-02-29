package fr.ulyssebouchet.gevu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.SurfaceControl;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationMenu = findViewById(R.id.bottom_navigation);

        bottomNavigationMenu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent i;
                switch (item.getItemId()) {
                    case R.id.menu_item_home:
                        openFragment(HomeFragment.Companion.newInstance());
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

    private void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}