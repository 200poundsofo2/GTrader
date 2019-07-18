package com.example.gtraderprototype.views;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.gtraderprototype.R;
//import com.example.gtraderprototype.model.GameInstanceInteractor;

/**
 * the title menu of the application
 */
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * a function that navigates to the configuration activity via button press
     * @param view android stuff
     */
    public void loadConfigurationPage(View view) {
        startActivity(new Intent(MainActivity.this, ConfigurationActivity.class));
    }

    /**
     * a function that navigates to the configuration activity via button press
     * @param view android stuff
     */
    public void loadSavedGamesPage(View view) {
        startActivity(new Intent(MainActivity.this, SavedGamesActivity.class));
    }

    /**
     * a function that closes the application
     * @param view android stuff
     */
    public void closeApp(View view) {
        finish();
        moveTaskToBack(true);
    }
}
