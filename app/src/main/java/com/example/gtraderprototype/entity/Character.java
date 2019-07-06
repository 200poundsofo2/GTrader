package com.example.gtraderprototype.entity;

import android.util.Log;

import java.util.List;

public class Character { //singleton design pattern: Player.getPlayer()
    protected String name;
    protected int pilotSkillPoints;
    protected int engineerSkillPoints;
    protected int fighterSkillPoints;
    protected int traderSkillPoints;
    protected boolean isPirate;
    protected int money;
    protected Ship spaceship;
    protected Region currentLocation;


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
    public void setPilotSkillPoints(int points){this.pilotSkillPoints = points;}
    public void setEngineerSkillPoints(int points){this.engineerSkillPoints = points;}
    public void setFighterSkillPoints(int points){this.fighterSkillPoints = points;}
    public void setTraderSkillPoints(int points){this.traderSkillPoints = points;}
    public Ship getShip(){ return spaceship ; }
    public Region getRegion() { return currentLocation;}
    public void setRegion(Region location) {
        currentLocation = location;
        Log.d("GTrader", "Updated location for "+name+" to "+currentLocation.regionName);
    }
    public int getMoney(){ return money;}
    public void pay(int cost){
        money-=cost;
    }
    public void getPaid(int credit){
        money+=credit;
    }
    public void setPirate(boolean pirate){
        this.isPirate = pirate;
    }
    public boolean getIsPirate(){
        return isPirate;
    }
}

