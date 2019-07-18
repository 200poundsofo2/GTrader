package com.example.gtraderprototype.views;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.gtraderprototype.entity.Difficulty;
import com.example.gtraderprototype.entity.Item;
import com.example.gtraderprototype.entity.Ship;
import com.example.gtraderprototype.entity.Player;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.example.gtraderprototype.R;
import com.example.gtraderprototype.model.Model;
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
    private Difficulty gameDifficulty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.encounter_pirate);

        EncounterViewModel viewModel = ViewModelProviders.of(this).get(EncounterViewModel.class);
        player=viewModel.getPlayer();
        gameDifficulty = Model.getInstance().getGameInstanceInteractor().getGameDifficulty();

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
        double fleeProbability = 1.0 - (((gameDifficulty.difficultyIndex()
                + 1) * 2) / (double) 10); //0.8 for beginner, 0 for impossible
        if(Math.random() < fleeProbability){
            //flee
            surrender.setEnabled(false);
            attack.setEnabled(false);
            flee.setText(getString(R.string.back));
            encounterText.setText(getString(R.string.flee_succeeded));
            pirateText.setText(getString(R.string.see_you_nexttime));
            flee.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        } else{ //unable to flee, game over
            startActivity(new Intent(EncounterPirateActivity.this,
             GameOverActivity.class));
        }
    }

    private void attack(){
        //I am still thinking about a good game :)
        pirateText.setText(getString(R.string.lets_play));
        surrender.setEnabled(false);
        flee.setEnabled(false);
        attack.setText("start game");
        attack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pirateGame=new Intent(EncounterPirateActivity.this,
                 PirateGameActivity.class);
                startActivity(pirateGame);
            }
        });
    }

    private void surrender(){

        attack.setEnabled(false);
        flee.setEnabled(false);
        surrender.setText(getString(R.string.back));
        pirateText.setText(getString(R.string.i_tooketh));
        encounterText.setText(getString(R.string.you_lost_cargo));

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
        int moneyRobbed= (int) (((gameDifficulty.difficultyIndex() + 1.0) / 10) * player.getMoney());
        player.setMoney(player.getMoney()-moneyRobbed);

        Ship ship=player.getSpaceShip();
        int numItem=(int) (((gameDifficulty.difficultyIndex()+1.0)/10)
                *ship.getCargo().size());
        //10% for beginner 50% for impossible

        for(int i=0; i<numItem;i++){
            int j=random.nextInt(ship.getCargo().size());
            while (ship.getCargo().get(j)== null){
                j=random.nextInt(ship.getCargo().size());
            }
            ship.dropCargo(ship.getCargo().get(j));
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


}
