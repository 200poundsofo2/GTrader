package com.example.gtraderprototype.entity;

import java.util.List;

public class Character { //singleton design pattern: Player.getPlayer()
    protected String name;
    private int pilotSkillPoints;
    private int engineerSkillPoints;
    private int fighterSkillPoints;
    private int traderSkillPoints;
    private List<String> Inventory;
    protected boolean isPirate;
    private int money;
    private Ship spaceship;
    private String currentLocation;

    public Character (String name, int pilotPoints, int engineerPoints, int fighterPoints, int traderPoints, Ship spaceship){
        this.name = name;
        this.pilotSkillPoints = pilotPoints;
        this.engineerSkillPoints = engineerPoints;
        this.fighterSkillPoints = fighterPoints;
        this.traderSkillPoints = traderPoints;
        this.money = 1000;
        this.spaceship = spaceship;
    }
    public Character (){}

    public void setName(String name) {
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public int getPilotSkillPoints(){return pilotSkillPoints;}
    public int getEngineerSkillPoints(){return  engineerSkillPoints;}
    public int getFighterSkillPoints(){return fighterSkillPoints;}
    public int getTraderSkillPoints(){return traderSkillPoints;}
    public Ship getShip(){ return spaceship ; }

    public void setPirate(boolean pirate){
        this.isPirate = pirate;
    }
    public boolean getIsPirate(){
        return isPirate;
    }
    public String toString(){return "Player Name: " + name + ", Pilot Skill Points: " + pilotSkillPoints +
            ", Engineer Skill Points: " + engineerSkillPoints + ", Fighter Skill Points: " + fighterSkillPoints +
            ", Trader Skill Points: " + traderSkillPoints +
            ", Money: " + money + ", SpaceShip: "+ spaceship.getName();}
}

