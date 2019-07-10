package com.example.gtraderprototype.views;

import android.os.Bundle;
import android.util.Log;
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

    private Fragment newFragment;
    private int oldFragment = R.id.main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("GTrader", "creating activity fragment");
        super.onCreate(savedInstanceState);
        MapViewModel mapviewmodel = ViewModelProviders.of(this).get(MapViewModel.class);

        setContentView(R.layout.activity_spaceport);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        FragmentManager fragmentManager = getSupportFragmentManager();

        TextView region = findViewById(R.id.name_of_region);
        TextView shipName = findViewById(R.id.nameofShip);
        TextView fuelAmount = findViewById(R.id.fuel_amount);

        //Set text
        region.setText(mapviewmodel.getPlayerLocationName());
        shipName.setText(mapviewmodel.getPlayerShipName());
        fuelAmount.setText("Fuel: " + mapviewmodel.getPlayerFuel()
                + "/" + mapviewmodel.getPlayerShipRange());

        newFragment = new MarketFragment();
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(oldFragment, newFragment);
        transaction.addToBackStack(null);
        transaction.commit();

        navigation.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                        int id = item.getItemId();
                        if (newFragment != null) {
                            oldFragment = newFragment.getId();
                        }
                        switch (id) {
                            case R.id.skills:
                                newFragment = new SkillsFragment();
                                break;
                            case R.id.ship:
                                newFragment = new ShipFragment();
                                break;
                            case R.id.travel:
                                newFragment = new fragment_map();
                                break;
                            case R.id.market:
                                newFragment = new MarketFragment();
                                break;
                            case R.id.save:
                                //TODO: save game implementation
                                break;
                        }
                        final FragmentTransaction transaction =
                                getSupportFragmentManager().beginTransaction();
                        transaction.replace(oldFragment, newFragment);
                        transaction.addToBackStack(null);
                        transaction.commit();
                        return true;
                    }
                });
    }

}
