package com.example.gtraderprototype.views;

import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.gtraderprototype.R;

public class SavedGamesActivity extends AppCompatActivity {

    private Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_games);
        back = findViewById(R.id.back);
    }

    public void backToWelcome(View view){
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SavedGamesActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
