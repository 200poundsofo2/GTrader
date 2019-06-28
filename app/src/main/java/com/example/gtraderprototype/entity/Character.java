package com.example.gtraderprototype.entity;

import java.util.List;

public class Character { //singleton design pattern: Player.getPlayer()
    protected String name;
    protected int pilotSkillPoints;
    protected int engineerSkillPoints;
    protected int fighterSkillPoints;
    protected int traderSkillPoints;
    protected List<String> Inventory;
    protected boolean isPirate;
    protected int money;
    protected Ship spaceship;
    protected String currentLocation;

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
}

