package com.example.gtraderprototype.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gtraderprototype.R;
import com.example.gtraderprototype.entity.Player;

public class GameOverActivity extends AppCompatActivity {
    private Button restart;
    private Button quit;
    private TextView gameover;
//    String reason = getIntent().getStringExtra("reason");

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameover);
        gameover=findViewById(R.id.gameover);
       // gameover.setText(reason+"\n"+"Game Over :(");
        restart=findViewById(R.id.restart);
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GameOverActivity.this, ConfigurationActivity.class));
            }
        });
        quit=findViewById(R.id.quit);
        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                moveTaskToBack(true);
            }
        });
    }
}
