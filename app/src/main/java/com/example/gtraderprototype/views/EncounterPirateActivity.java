package com.example.gtraderprototype.views;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.gtraderprototype.entity.Ship;
import com.example.gtraderprototype.entity.Player;
import com.example.gtraderprototype.entity.Item;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.example.gtraderprototype.R;
import com.example.gtraderprototype.viewmodels.EncounterViewModel;
import android.content.Intent;
import android.widget.Toast;

import java.util.Random;

public class EncounterPirateActivity extends AppCompatActivity {
    Random random;
    private Button attack;

    private Button flee;
    private Button surrender;
    private TextView pirateText;
    private TextView encounterText;
    Player player;
    Ship ship;
    private EncounterViewModel viewModel;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.encounter_pirate);
        random= new Random();
        viewModel = ViewModelProviders.of(this).get(EncounterViewModel.class);
        player=viewModel.getPlayerInteractor().getPlayer();
        ship=player.getShip();

        pirateText=findViewById(R.id.pirateText);
        encounterText=findViewById(R.id.encounterText);
        attack=findViewById(R.id.attack);
        flee=findViewById(R.id.flee);
        surrender=findViewById(R.id.surrender);

        attack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attack();
            }
        });

        flee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flee();
            }
        });

        surrender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                surrender();
            }
        });

    }
    private void flee(){
        double fleeProbability = 1.0 - (player.getDifficulty().difficultyIndex()+1)*2/10.0; //0.8 for beginner, 0 for impossible
        if(Math.random() < fleeProbability){
            //flee
            surrender.setEnabled(false);
            attack.setEnabled(false);
            flee.setText("Back");
            encounterText.setText("Flee Succeeded!");
            pirateText.setText("This isn't the end! Let's wait and see.");
            flee.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    finish();
                }
            });
        } else{ //unable to flee, game over
            startActivity(new Intent(EncounterPirateActivity.this, GameOverActivity.class).putExtra("reason","unsuccessful flee"));
        }



    }

    private void attack(){
        //I am still thinking about a good game :)

    }

    private void surrender(){
        attack.setEnabled(false);
        flee.setEnabled(false);
        surrender.setText("Back");
        pirateText.setText("Ahahah I took some precious from you.");
        encounterText.setText("You will lose part of your cargo");

        surrender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int numItem=((player.getDifficulty().difficultyIndex()+1)/10)*ship.getCargo().length; //10% for beginner 50% for impossible
                for(int i=0; i<numItem;i++){
                    int j=random.nextInt(ship.getCargo().length);
                    ship.getCargo()[j]=null;
                }
                int moneyRobbed=(player.getDifficulty().difficultyIndex()+1)/10*player.getMoney();
                player.setMoney(player.getMoney()-moneyRobbed);

                finish();
            }
        });

    }
}
