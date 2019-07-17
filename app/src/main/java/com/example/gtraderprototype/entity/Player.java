package com.example.gtraderprototype.entity;


import android.util.Log;

/**
 * player class that initializes the user
 */
public class Player extends Character {
    public static volatile Player player = null;
    Difficulty difficulty;

    /**
     * initializing a player that has a default Gnatt ship
     * @param name name of the player
     * @param pilotPoints skill points of piloting
     * @param engineerPoints skill points of engineering
     * @param fighterPoints skill points of fighting
     * @param traderPoints skill points of trading
     * @param d difficulty level
     */
    public Player(String name, int pilotPoints, int engineerPoints, int fighterPoints, int traderPoints, Difficulty d){
        super(name, pilotPoints, engineerPoints, fighterPoints, traderPoints, Ship.Gnatt);
        difficulty = d;
        player = this;

        Log.d("Woah", this.toString());
    }

    /**
     * getting the statistics of the player
     * @return player's information
     */
    public static Player getPlayer(){
        if (player == null){
            synchronized (Player.class){
                if(player == null){
                    player = new Player("No Name", 0, 0, 0, 0, Difficulty.Beginner);
                }
            }
        }
        return player;
    }

    /**
     * setting difficulty of the game which play choose to play
     * @param d difficulty level
     */
    public void setDifficulty(Difficulty d){
        difficulty = d;
    }

    /**
     * getting the difficulty level the player choose to play
     * @return difficulty level
     */
    public int getDifficultyLevel(){
        return difficulty.difficultyIndex;
    }

    /**
     * getting information of whether this character is a pirate or not
     * @return boolean indicating this character is a pirate or not
     */
    public void setPirate(boolean pirate) {
        getPlayer().isPirate = pirate;
    }

    public String toString(){return "Player Name: " + name + ", Pilot Skill Points: " + pilotSkillPoints +
            ", Engineer Skill Points: " + engineerSkillPoints + ", Fighter Skill Points: " + fighterSkillPoints +
            ", Trader Skill Points: " + traderSkillPoints +
            ", Money: " + money + ", SpaceShip: "+ spaceship.getName();}
}
