package com.example.gtraderprototype.views;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import com.example.gtraderprototype.R;
import com.example.gtraderprototype.viewmodels.MapViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SpacePortActivity extends AppCompatActivity {

    private Fragment fragment;
    private FragmentManager fragmentManager;
    private TextView region;
    private TextView shipName;
    private TextView fuelAmount;
    private MapViewModel mapviewmodel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mapviewmodel = ViewModelProviders.of(this).get(MapViewModel.class);

        setContentView(R.layout.activity_spaceport);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        fragmentManager = getSupportFragmentManager();

        TextView region = findViewById(R.id.name_of_region);
        TextView shipName = findViewById(R.id.nameofShip);
        TextView fuelAmount = findViewById(R.id.fuel_amount);

        //Set text
        region.setText(mapviewmodel.getPlayerLocationName());
        shipName.setText(mapviewmodel.getPlayerShipName());
        fuelAmount.setText("Fuel: " + mapviewmodel.getPlayerFuel() + "/" + mapviewmodel.getPlayerShipRange());

        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.skills:
                        fragment = new SkillsFragment();
                        break;
                    case R.id.ship:
                        fragment = new ShipFragment();
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
