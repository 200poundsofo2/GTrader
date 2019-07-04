package com.example.gtraderprototype.views;

import android.os.Bundle;
import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;
import com.example.gtraderprototype.entity.Player;

import com.example.gtraderprototype.R;

public class SpacePortActivity extends AppCompatActivity {

    private Fragment fragment;
    private FragmentManager fragmentManager;
    private Player player = Player.getPlayer();
    private TextView region;
    private TextView shipName;
    private TextView fuelAmount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spaceport);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        fragmentManager = getSupportFragmentManager();

        region = findViewById(R.id.nameofRegion);
        shipName = findViewById(R.id.nameofShip);
        fuelAmount = findViewById(R.id.fuelAmount);

        region.setText(player.getRegion().getRegionName());
        shipName.setText(player.getShip().getName());
        fuelAmount.setText("Fuel: " + player.getShip().getFuel() + "/" + player.getShip().getTravelRange());

        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.skills:
                        fragment = new SkillsFragment();
                        break;
                    case R.id.ship:
                        //fragment = new ShipFragment();
                        break;
                    case R.id.travel:
                        fragment = new fragment_map();
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
