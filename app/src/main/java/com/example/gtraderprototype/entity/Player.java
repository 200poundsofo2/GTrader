package com.example.gtraderprototype.entity;


import android.util.Log;

public class Player extends Character {
    public static volatile Player player = null;
    public Player(String name, int pilotPoints, int engineerPoints, int fighterPoints, int traderPoints){
        super(name, pilotPoints, engineerPoints, fighterPoints, traderPoints, Ship.Gnatt);
        player = this;

        Log.d("bitch", this.toString());
    }
    public static Player getPlayer(){
        if (player == null){
            synchronized (Player.class){
                if(player == null){
                    player = new Player("Null", 0, 0, 0, 0);
                }
            }
        }
        return player;
    }

    public void setPirate(boolean pirate) {
        getPlayer().isPirate = pirate;
    }
    public String toString(){return "Player Name: " + name + ", Pilot Skill Points: " + pilotSkillPoints +
            ", Engineer Skill Points: " + engineerSkillPoints + ", Fighter Skill Points: " + fighterSkillPoints +
            ", Trader Skill Points: " + traderSkillPoints +
            ", Money: " + money + ", SpaceShip: "+ spaceship.getName();}
}
