package com.example.gtraderprototype.entity;
import android.util.Log;

/**
 * player class that initializes the user
 */
public final class Player extends Character {
    private static volatile Player player;
    private Difficulty difficulty;
    public Player(){
        super("No Name", 0, 0, 0, 0, Ship.Gnatt);
        difficulty = Difficulty.Beginner;
        player = this;

    }
    public Player(){

    }

    /**
     * getting the statistics of the player
     * @return player's information
     */
    public static Player getPlayer(){
        if (player == null){
            synchronized (Player.class){
                if(player == null){
                    player = new Player("NoName", 0, 0, 0, 0);
                }
            }
        }
        return player;
    }
    
    /**
     * getting information of whether this character is a pirate or not
     * @return boolean indicating this character is a pirate or not
     */
    public void setPirate(boolean pirate) {
        getPlayer().isPirate = pirate;
    public String toString(){return "Player Name: "
            + name + ", Pilot Skill Points: " + pilotSkillPoints +
            ", Engineer Skill Points: " + engineerSkillPoints +
            ", Fighter Skill Points: " + fighterSkillPoints +
            ", Trader Skill Points: " + traderSkillPoints +
            ", Money: " + money + ", SpaceShip: "+ spaceShip.getName();}
}
