package com.example.gtraderprototype.entity;


import android.util.Log;

public final class Player extends Character {
    private static volatile Player player;
    private Difficulty difficulty;
    private Player(){
        super("No Name", 0, 0, 0, 0, Ship.Gnatt);
        difficulty = Difficulty.Beginner;
        player = this;

        Log.d("Woah", this.toString());
    }

    public static Player getPlayer(){
        if (player == null){
            synchronized (Player.class){
                if(player == null){
                    player = new Player();
                }
            }
        }
        return player;
    }

    public void setDifficulty(Difficulty d){
        difficulty = d;
    }

    public int getDifficultyLevel(){
        return difficulty.difficultyIndex;
    }

    public void setPirate(boolean pirate) {
        getPlayer().isPirate = pirate;
    }
    private String toString(){return "Player Name: "
            + name + ", Pilot Skill Points: " + pilotSkillPoints +
            ", Engineer Skill Points: " + engineerSkillPoints +
            ", Fighter Skill Points: " + fighterSkillPoints +
            ", Trader Skill Points: " + traderSkillPoints +
            ", Money: " + money + ", SpaceShip: "+ spaceship.getName();}
}
