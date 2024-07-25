package edu.huflit.demo_yoga;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import edu.huflit.demo_yoga.MenuFragment.HistoriesFragment;
import edu.huflit.demo_yoga.MenuFragment.HomeFragment;
import edu.huflit.demo_yoga.MenuFragment.FavoriteFragment;
import edu.huflit.demo_yoga.MenuFragment.SearchFragment;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bnv;
    HomeFragment homeFragment = new HomeFragment();
    FavoriteFragment favoriteFragment = new FavoriteFragment();
    HistoriesFragment historiesFragment= new HistoriesFragment();

    SearchFragment searchFragment = new SearchFragment();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bnv = findViewById(R.id.bottom_menu);
        getSupportFragmentManager().beginTransaction().replace(R.id.container,homeFragment).commit();
        bnv.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected( MenuItem item) {
                switch (item.getItemId()){
                    case R.id.mnhome:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,homeFragment).commit();
                        return true;
                    case R.id.mnliked:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, favoriteFragment).commit();
                        return true;
                    case R.id.mnsearch:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,searchFragment).commit();
                        return true;
                    case R.id.mhistory:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,historiesFragment).commit();
                        return true;
                }
                return false;
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}