package com.example.gtraderprototype.entity;

import android.util.Log;

import com.example.gtraderprototype.model.Database;

/**
 * game instance reference
 */
public class GameInstance {
    private String gameID;


    private Difficulty difficulty;
    private System[] system;

    /**
     * getting the Game ID number
     * @return Game ID
     */
    public String getGameID(){
        return gameID;
    }
    /**
     * getting the difficulty level
     * @return difficulty level
     */
    public Difficulty getDifficulty(){ return difficulty;}

    /**
     * setting the difficulty level
     * @param difficulty the difficulty level the user chose
     */
    public void setDifficulty(Difficulty difficulty){
        this.difficulty = difficulty;
    }

    /**
     * initialize a game instance
     * @param difficulty difficult the user chooses for the game
     */
    public GameInstance( Difficulty difficulty){
        this.gameID = Database.getNewGameID();
        this.difficulty = difficulty;
        Log.d("GTrader", "Created Local Game Instance: " + this.toString());


    }

    /**
     * printing out game Id and difficulty of the game of the user
     * @return
     */
    public String toString(){
        return "INSTANCE: " + "Game ID: " + gameID + "Difficulty: " + difficulty;
    }
}


