package com.example.gtraderprototype.views;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.gtraderprototype.R;
//import com.example.gtraderprototype.model.GameInstanceInteractor;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void loadConfigurationPage(View view) {
        startActivity(new Intent(MainActivity.this, ConfigurationActivity.class));
    }

    public void loadSavedGamesPage(View view) {
        startActivity(new Intent(MainActivity.this, SavedGamesActivity.class));
    }

    public void closeApp(View view) {
        finish();
        moveTaskToBack(true);
    }
}
