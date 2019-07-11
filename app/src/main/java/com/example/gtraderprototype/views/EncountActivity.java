package com.example.gtraderprototype.views;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.example.gtraderprototype.R;
import com.example.gtraderprototype.entity.Player;
import com.example.gtraderprototype.viewmodels.EncounterViewModel;
import com.example.gtraderprototype.entity.Item;

public class EncountActivity extends AppCompatActivity {

    private TextView words;
    private Button b1;
    private Button b2;
    private EncounterViewModel viewModel;
    Player player;
    Item[] cargo;
    int penalty;
    int bribe;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.encounter_police);
        words=findViewById(R.id.policeWord);
        b1=findViewById(R.id.b1);
        b2=findViewById(R.id.b2);
        viewModel = ViewModelProviders.of(this).get(EncounterViewModel.class);
        player=viewModel.getPlayerInteractor().getPlayer();
        cargo=player.getShip().getCargo();
        penalty=100;
        bribe=player.getMoney()*(player.getDifficulty().difficultyIndex()+1)/10;

        b1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startSearching();
            }
        });

        b2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                underArrest("You didn't obey. ");
            }
        });


    }
    private void startSearching(){
        for(Item i:cargo){
            if(i.equals(Item.Firearms)||i.equals(Item.Narcotics)||player.getIsPirate()){
                penalty+=i.getMaximumPrice();
            }
        }
        if(penalty!=0){
            underArrest("You are a bad trader. ");
        }else{
            fine();
        }

    }
    private void underArrest(String msg){
        words.setText(msg+"You are under arrest.");
        b1.setText("Pay the penalty: $"+penalty);
        b1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                player.setMoney(player.getMoney()-penalty);
                finish();
            }
        });


        b2.setText("Bribe: $"+bribe);
        b2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                player.setMoney(player.getMoney()-bribe);
                finish();
            }
        });

    }

    private void fine(){
        words.setText("You are a good man");
        b1.setText("Goodbye");
        b2.setEnabled(false);
        b1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(EncountActivity.this, GameOverActivity.class));
            }
        });
    }


}
