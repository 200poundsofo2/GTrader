package com.example.gtraderprototype.entity;

import android.util.Log;

import com.example.gtraderprototype.model.Database;
import com.example.gtraderprototype.model.UniverseInteractor;

public class GameInstance {
    //Game Instance Reference
    private String gameID;

    //Player's player class
    private Player userPlayer;

    private Difficulty difficulty;
    private System[] system;

    public Player getUserPlayer(){
        return userPlayer;
    }
    public String getGameID(){
        return gameID;
    }
    public Difficulty getDifficulty(){ return difficulty;}
    public void setDifficulty(Difficulty difficulty){
        this.difficulty = difficulty;
    }

    public GameInstance(Player player, Difficulty difficulty){
        this.gameID = Database.getNewGameID();
        this.userPlayer = player;
        this.difficulty = difficulty;
        Log.d("GTrader", "Created Local Game Instance: "+this.toString());

    }

    public String toString(){
        return "INSTANCE: "+"Game ID: "+gameID+"Difficulty: "+ difficulty+"| PLAYER INFO: "+userPlayer.toString();
    }
}


