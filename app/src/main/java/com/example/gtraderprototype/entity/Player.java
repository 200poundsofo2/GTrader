package com.example.gtraderprototype.entity;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class Player { //singleton design pattern: Player.getPlayer()
    public static  volatile Player player = null;

    private String name;
    private Difficulty difficulty;
    private int pioltSkillPoints;
    private int engineerSkiilPoints;
    private int fighterSkillPoints;
    private int traderSkillPoints;
    private List<String> Inventory;
    private boolean isPirate;
    private int money;
    private String spaceship;
    private String currentLocation;


    private Player(){}
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

    public Player (String name, int pilotPoints, int engineerPoints, int fighterPoints, int traderPoints, Difficulty difficulty){
        this.name = name;
        pioltSkillPoints = pilotPoints;
        engineerSkiilPoints = engineerPoints;
        fighterSkillPoints = fighterPoints;
        traderSkillPoints = traderPoints;
        this.difficulty = difficulty;
        money = 1000;
        spaceship = "Gnat";
    }

    public void setName(String name) {
        getPlayer().name = name;
    }
    public String getName(){
        return getPlayer().name;
    }
    public int getPioltSkillPoints(){return pioltSkillPoints;};
    public int getEngineerSkillPoints(){return  engineerSkiilPoints;};
    public int getFighterSkillPoints(){return fighterSkillPoints;};
    public int getTraderSkillPoints(){return traderSkillPoints;};
    public void setDifficulty(Difficulty d) {  difficulty = d;}
    public Difficulty getDifficulty(){
        return difficulty;
    }
    public void setPirate(boolean pirate){
        getPlayer().isPirate=pirate;
    }
    public boolean checkIsPirate(){
        return getPlayer().isPirate;
    }
    public String toString(){return "Player Name: " + name + ", Pilot Skill Points: " + pioltSkillPoints +
            ", Engineer Skill Points: " + engineerSkiilPoints + ", Fighter Skill Points: " +
            ", Trader Skill Points: " + traderSkillPoints + ", Difficulty:" + difficulty +
            ", Money: " + money + ", SpaceShip: "+ spaceship;}
}

