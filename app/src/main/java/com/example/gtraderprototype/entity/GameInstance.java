package com.example.gtraderprototype.entity;

import android.util.Log;

import com.example.gtraderprototype.model.Database;

public class GameInstance {
    //Game Instance Reference
    private String gameID;


    private Difficulty difficulty;
    private Player player;

    public String getGameID(){
        return gameID;
    }
    public Difficulty getDifficulty(){ return difficulty;}
    public void setDifficulty(Difficulty difficulty){
        this.difficulty = difficulty;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }


    public GameInstance(){
        this.gameID = Database.getNewGameID();
        this.difficulty = Difficulty.Beginner;
    }
    public GameInstance(Difficulty difficulty, Player player){
        this.gameID = Database.getNewGameID();
        this.difficulty = difficulty;
        this.player = player;
        Log.d("GTrader", "Created Local Game Instance: " + this.toString());
    }

    public String toString(){
        return "INSTANCE: " + "Game ID: " + gameID + "Difficulty: " + difficulty + ",Player: "+player.toString();
    }
}


