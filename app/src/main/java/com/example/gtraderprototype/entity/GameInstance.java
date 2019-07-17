package com.example.gtraderprototype.entity;

import android.util.Log;


import com.example.gtraderprototype.model.Database;

public class GameInstance {
    //Game Instance Reference
    private final String gameID;


    private Difficulty difficulty;
    private System[] system;

    public String getGameID(){
        return gameID;
    }
    public Difficulty getDifficulty(){ return difficulty;}
    public void setDifficulty(Difficulty difficulty){
        this.difficulty = difficulty;
    }

    public GameInstance( Difficulty difficulty){
        this.gameID = Database.getNewGameID();
        this.difficulty = difficulty;
        Log.d("GTrader", "Created Local Game Instance: " + this.toString());


    }

    public String toString(){
        return "INSTANCE: " + "Game ID: " + gameID + "Difficulty: " + difficulty;
    }
}


