package com.example.gtraderprototype.views;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.gtraderprototype.entity.Item;
import com.example.gtraderprototype.entity.Ship;
import com.example.gtraderprototype.entity.Player;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.example.gtraderprototype.R;
import com.example.gtraderprototype.viewmodels.EncounterViewModel;
import android.content.Intent;

import java.util.Random;

/** Random event: encounter pirate
 *  if Player surrender, they will lose part of their items in the ship
 *  (how many items will be robbed depends on the level of difficulty)
 *  if Player attack, they will play a game
 *  if Player flee, they will either flee away successfully or killed by the pirate
 *  (the chance of successful flee depends on the level of difficulty)
 */

public class EncounterPirateActivity extends AppCompatActivity {


    private  Button attack;
    private  Button flee;
    private  Button surrender;
    private  TextView pirateText;
    private  TextView encounterText;

    private  Player player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.encounter_pirate);

        EncounterViewModel viewModel = ViewModelProviders.of(this).get(EncounterViewModel.class);
        player=viewModel.getPlayer();


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
        double fleeProbability = 1.0 - (((player.getDifficultyLevel()
                + 1) * 2) / (double) 10); //0.8 for beginner, 0 for impossible
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
            startActivity(new Intent(EncounterPirateActivity.this, GameOverActivity.class));
        }
    }

    private void attack(){
        pirateText.setText("Lets play game");
        surrender.setEnabled(false);
        flee.setEnabled(false);
        attack.setText("start game");
        attack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pirateGame=new Intent(EncounterPirateActivity.this, PirateGameActivity.class);
                startActivity(pirateGame);
            }
        });
    }

    private void surrender(){

        attack.setEnabled(false);
        flee.setEnabled(false);
        surrender.setText("Back");
        pirateText.setText("HA! I took some precious from you.");
        encounterText.setText("You will lose part of your cargo");

        surrender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                robPlayer();
                finish();
            }
        });
    }

    private void robPlayer(){
        Random random=new Random();
        int moneyRobbed= (int) (((player.getDifficultyLevel() + 1.0) / 10) * player.getMoney());
        player.setMoney(player.getMoney()-moneyRobbed);

        Ship ship=player.getShip();
        int numItem=(int) (((player.getDifficultyLevel()+1.0)/10)
                *checkNumItems(ship.getCargo()));
        //10% for beginner 50% for impossible

        for(int i=0; i<numItem;i++){
            int j=random.nextInt(ship.getCargo().length);
            while (ship.getCargo()[j]== null){
                j=random.nextInt(ship.getCargo().length);
            }
            ship.dropCargo(ship.getCargo()[j]);
        }
    }

    /** used for testing
     *  test robPlayer()
     */
    public void testRobPlayer(){
        robPlayer();
    }

    /** setter for the player
     *
     * @param player Player of this Game
     */
    public void setPlayer(Player player){
        this.player=player;
    }
    private int checkNumItems(Item[] cargo){
        int num=0;
        for(Item i:cargo){
            if(i!=null){
                num++;
            }
        }
        return num;
    }


}
