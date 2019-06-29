package com.example.gtraderprototype.views;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.example.gtraderprototype.entity.Player;

import com.example.gtraderprototype.R;

public class SpacePortActivity extends AppCompatActivity {

    private Fragment fragment;
    private FragmentManager fragmentManager;
    private Player player = Player.getPlayer();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spaceport);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        fragmentManager = getSupportFragmentManager();

        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.skills:
                        fragment = new SkillsFragment();
                        ArrayAdapter adapter = new ArrayAdapter<String>(SpacePortActivity.this, R.layout.fragment_market, player.getInventory());
                        ListView listView = (ListView) findViewById(R.id.inventoryList);
                        listView.setAdapter(adapter);
                        break;
                    case R.id.ship:
                        //fragment = new ShipFragment();
                        break;
                    case R.id.travel:
                        //TODO: travel map
                        break;
                    case R.id.market:
                        fragment = new MarketFragment();
                        break;
                    case R.id.save:
                        //TODO: save game implementation
                        break;
                }
                final FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.main, fragment).commit();
                return true;
            }
        });
    }
}
