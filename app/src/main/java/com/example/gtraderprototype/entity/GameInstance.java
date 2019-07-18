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
    private Player player;

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

      /**
     * setting the instance player
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
     * initialize a game instance
     */
    public GameInstance(Difficulty difficulty, Player player){
        this.gameID = Database.getNewGameID();
        this.difficulty = difficulty;
        this.player = player;
        Log.d("GTrader", "Created local game instance: "+ this.toString());
    }
    /**
     * printing out game Id and difficulty of the game of the user
     * @return
     */
    public String toString(){
        return "INSTANCE: " + "Game ID: " + gameID + "Difficulty: " + difficulty + ",Player: "+player.toString();
    }
}


