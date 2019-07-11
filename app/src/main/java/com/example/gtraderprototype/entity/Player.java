package com.example.gtraderprototype.entity;


import android.util.Log;

public class Player extends Character {
    public static volatile Player player = null;
    Difficulty difficulty;
    public Player(String name, int pilotPoints, int engineerPoints, int fighterPoints, int traderPoints, Difficulty d){
        super(name, pilotPoints, engineerPoints, fighterPoints, traderPoints, Ship.Gnatt);
        difficulty = d;
        player = this;

        Log.d("Woah", this.toString());
    }

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

    public void setDifficulty(Difficulty d){
        difficulty = d;
    }

    public Difficulty getDifficulty(){
        return difficulty;
    }

    public void setPirate(boolean pirate) {
        getPlayer().isPirate = pirate;
    }
    public String toString(){return "Player Name: " + name + ", Pilot Skill Points: " + pilotSkillPoints +
            ", Engineer Skill Points: " + engineerSkillPoints + ", Fighter Skill Points: " + fighterSkillPoints +
            ", Trader Skill Points: " + traderSkillPoints +
            ", Money: " + money + ", SpaceShip: "+ spaceship.getName();}
}
