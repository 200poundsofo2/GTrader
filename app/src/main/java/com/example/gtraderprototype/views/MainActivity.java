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


public class MainActivity extends AppCompatActivity {
    private Button createNewGame;
    private Button exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createNewGame = findViewById(R.id.button_new_game);
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
    public void closeApp(View view){
        finish();
        moveTaskToBack(true);
    }

}
