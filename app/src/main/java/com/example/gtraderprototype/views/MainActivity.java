package com.example.gtraderprototype.views;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.gtraderprototype.R;
import com.example.gtraderprototype.model.GameInstanceInteractor;
import com.example.gtraderprototype.viewmodels.ConfigurationViewModel;


public class MainActivity extends AppCompatActivity {
    private Button createNewGame;
    private Button savedGames;
    private Button exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createNewGame = findViewById(R.id.button_new_game);
        savedGames = findViewById(R.id.button_saved_games);
        exit = findViewById(R.id.button_exit);
    }
    public void loadConfigurationPage(View view){
        createNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ConfigurationActivity.class);
                startActivity(intent);
            }
        });
    }

    public void loadSavedGamesPage(View view) {
        savedGames.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SavedGamesActivity.class);
                startActivity(intent);
            }
        });
    }

    public void closeApp(View view){
        finish();
        moveTaskToBack(true);
    }

}
