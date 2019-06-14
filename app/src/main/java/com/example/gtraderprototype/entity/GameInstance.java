package com.example.gtraderprototype.entity;

import com.example.gtraderprototype.model.Database;

public class GameInstance {
    //Game Instance Reference
    private static String gameID;

    //Player's player class
    private static Player userPlayer;

    //Difficulty 0-4
    private int difficulty;


    public static Player getUsersPlayer(){
        return userPlayer;
    }
    public static String getGameID(){
        return gameID;
    }
    public int getDifficulty(){
        return difficulty;
    }
    public void setDifficulty(byte newDifficulty){
        this.difficulty = newDifficulty;
    }

    public GameInstance(){
        this.gameID = Database.getNewGameID();
        this.difficulty = 2;

    }
}


