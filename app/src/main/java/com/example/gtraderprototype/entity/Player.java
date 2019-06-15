package com.example.gtraderprototype.entity;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class Player { //singleton design pattern: Player.getPlayer()
    public static volatile Player player = null;

    private String name;
    private int pilotSkillPoints;
    private int engineerSkillPoints;
    private int fighterSkillPoints;
    private int traderSkillPoints;
    private List<String> Inventory;
    private boolean isPirate;
    private int money;
    private String spaceship;
    private String currentLocation;


    public Player(){}
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

    public Player (String name, int pilotPoints, int engineerPoints, int fighterPoints, int traderPoints){
        this.name = name;
        this.pilotSkillPoints = pilotPoints;
        this.engineerSkillPoints = engineerPoints;
        this.fighterSkillPoints = fighterPoints;
        this.traderSkillPoints = traderPoints;
        this.money = 1000;
        this.spaceship = "Gnat";
    }

    public void setName(String name) {
        getPlayer().name = name;
    }
    public String getName(){
        return name;
    }
    public int getPilotSkillPoints(){return pilotSkillPoints;}
    public int getEngineerSkillPoints(){return  engineerSkillPoints;}
    public int getFighterSkillPoints(){return fighterSkillPoints;}
    public int getTraderSkillPoints(){return traderSkillPoints;}

    public void setPirate(boolean pirate){
        getPlayer().isPirate=pirate;
    }
    public boolean getIsPirate(){
        return isPirate;
    }
    public String toString(){return "Player Name: " + name + ", Pilot Skill Points: " + pilotSkillPoints +
            ", Engineer Skill Points: " + engineerSkillPoints + ", Fighter Skill Points: " + fighterSkillPoints +
            ", Trader Skill Points: " + traderSkillPoints +
            ", Money: " + money + ", SpaceShip: "+ spaceship;}
}

