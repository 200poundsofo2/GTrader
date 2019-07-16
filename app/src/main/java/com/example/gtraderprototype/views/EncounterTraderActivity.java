package com.example.gtraderprototype.views;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gtraderprototype.R;

public class EncounterTraderActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.encounter_trader_trade);
        Button trade = findViewById(R.id.button_trade);
        Button attack = findViewById(R.id.button_attack);
        Button flee = findViewById(R.id.button_flee);
        Button skip = findViewById(R.id.button_skip);

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
