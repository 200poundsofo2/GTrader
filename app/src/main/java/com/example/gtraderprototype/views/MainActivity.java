package com.example.gtraderprototype.views;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.graphics.drawable.AnimationDrawable;

import com.example.gtraderprototype.R;
//import com.example.gtraderprototype.model.GameInstanceInteractor;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView imgView = findViewById(R.id.backgroundImage);

        if (imgView != null) {
            AnimationDrawable progressAnimation = (AnimationDrawable) imgView.getBackground();
            progressAnimation.start();
        } else {
            System.out.println("Img view is null");
        }
    }

    /**
     * Loads the configuration page
     * @param view of page
     */
    public void loadConfigurationPage(View view) {
        startActivity(new Intent(MainActivity.this, ConfigurationActivity.class));
    }

    /**
     * Loads the saved games page
     * @param view of page
     */
    public void loadSavedGamesPage(View view) {
        startActivity(new Intent(MainActivity.this, SavedGamesActivity.class));
    }

    /**
     * Closes up when presses exit
     * @param view of page
     */
    public void closeApp(View view) {
        finish();
        moveTaskToBack(true);
    }
}
