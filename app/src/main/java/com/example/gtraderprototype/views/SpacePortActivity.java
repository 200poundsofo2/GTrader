package com.example.gtraderprototype.views;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.gtraderprototype.R;

public class SpacePortActivity extends AppCompatActivity {
    private static final SpacePortActivity ourInstance = new SpacePortActivity();

    public static SpacePortActivity getInstance() {
        return ourInstance;
    }

    private BottomNavigationView navigation;
    private Fragment fragment;
    private FragmentManager fragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spaceport);
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        fragmentManager = getSupportFragmentManager();

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
                        //TODO: travel alert implementation
                        break;
                    case R.id.save:
                        //TODO: save game implementation
                        break;
                }
                final FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.skills, fragment).commit();
                return true;
            }
        });
    }
}
