package com.example.gtraderprototype.views;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import com.example.gtraderprototype.R;
import android.view.KeyEvent;
import android.widget.Toast;
import com.example.gtraderprototype.entity.PirateGame.Game;

public class PirateGameActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pirate_game);
        Game game = new Game(PirateGameActivity.this);
        setContentView(game);
    }

}
