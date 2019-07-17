package com.example.gtraderprototype.views;

import android.os.Bundle;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

import com.example.gtraderprototype.R;

/**
 * displays all of the players current games
 */
public class SavedGamesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_games);
    }

    /**
     *  returns player to the main menu
     * @param view android stuff
     */
    public void backToWelcome(View view){
        startActivity(new Intent(SavedGamesActivity.this, MainActivity.class));
    }
}
