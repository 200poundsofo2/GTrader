package com.example.gtraderprototype.entity;

import android.util.Log;


import com.example.gtraderprototype.model.Database;

/**
 * game instance entity
 */
public class GameInstance {
    //Game Instance Reference
    private final String gameID;


    private Difficulty difficulty;
    private System[] system;

    /**
     * get the game id
     * @return a serialized value that represents the game
     */
    public String getGameID(){
        return gameID;
    }

    /**
     * gets the game's set difficulty
     * @return teh game's difficulty
     */
    public Difficulty getDifficulty(){ return difficulty;}

    /**
     * sets the game's set difficulty
     * @param difficulty the desired game difficulty
     */
    public void setDifficulty(Difficulty difficulty){
        this.difficulty = difficulty;
    }

    /**
     * a constructor for the current game instance
     * @param difficulty the difficulty of the game
     */
    public GameInstance( Difficulty difficulty){
        this.gameID = Database.getNewGameID();
        this.difficulty = difficulty;
        Log.d("GTrader", "Created Local Game Instance: " + this.toString());


    }

    public String toString(){
        return "INSTANCE: " + "Game ID: " + gameID + "Difficulty: " + difficulty;
    }
}


