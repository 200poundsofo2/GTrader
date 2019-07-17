package com.example.gtraderprototype.entity;

import android.util.Log;

import com.example.gtraderprototype.model.Database;

/**
 * game instance reference
 */
public class GameInstance {
    private String gameID;


    private Difficulty difficulty;
    private Player player;

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
     * getting the Player
     * @return Player
     */
    public Player getPlayer() {
        return player;
    }

      /**
     * setting the difficulty level
     * @param player the game's player
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * initialize a game instance
     */
    public GameInstance(){
        this.gameID = Database.getNewGameID();
        this.difficulty = Difficulty.Beginner;
    }
    /**
     * printing out game Id and difficulty of the game of the user
     * @return
     */
    public String toString(){
        return "INSTANCE: " + "Game ID: " + gameID + "Difficulty: " + difficulty + ",Player: "+player.toString();
    }
}


