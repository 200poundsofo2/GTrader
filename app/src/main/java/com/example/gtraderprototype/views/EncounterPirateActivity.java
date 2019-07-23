package com.example.gtraderprototype.views;

import android.app.Activity;
import android.media.MediaPlayer;
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
    MediaPlayer mapmusic;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mapmusic= MediaPlayer.create(getApplicationContext() ,R.raw.map);
        mapmusic.setLooping(true);
        mapmusic.start();
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
            surrender.setVisibility(View.GONE);
            attack.setEnabled(false);
            attack.setVisibility(View.GONE);
            flee.setText("Back");
            encounterText.setText("Flee Succeeded!");
            pirateText.setText("This isn't the end! Let's wait and see.");
            flee.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mapmusic.stop();
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
        surrender.setVisibility(View.GONE);
        flee.setEnabled(false);
        flee.setVisibility(View.GONE);
        attack.setText("start game");
        encounterText.setText("kill 50 pirates without colliding them!");
        attack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pirateGame=new Intent(EncounterPirateActivity.this, PirateGameActivity.class);
                startActivity(pirateGame);
                //back
                pirateText.setText("OK.You win. Lets wait and see");
                encounterText.setText("You beat the pirates!");
                attack.setText("Go Back");
                attack.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mapmusic.stop();
                        finish();
                    }
                });
            }
        });
    }

    private void surrender(){

        attack.setEnabled(false);
        attack.setVisibility(View.GONE);
        flee.setEnabled(false);
        flee.setVisibility(View.GONE);
        surrender.setText("Back");
        pirateText.setText("HA! I took some precious from you.");
        encounterText.setText("You will lose part of your cargo");

        surrender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mapmusic.stop();
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
