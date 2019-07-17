package com.example.gtraderprototype.entity;

import android.util.Log;

import java.util.List;

/**
 * Character class that initialize attributes and behaviors for both the player
 * and the NPCs
 */
public class Character { //singleton design pattern: Player.getPlayer()
    protected String name;
    protected int pilotSkillPoints;
    protected int engineerSkillPoints;
    protected int fighterSkillPoints;
    protected int traderSkillPoints;
    protected boolean isPirate;
    protected int money;
    protected Ship spaceship;
    protected String currentLocationName;

    /**
     * Constructor that distribute skill points of the character
     *
     * @param name name of the player or NPC
     * @param pilotPoints skill points of piloting
     * @param engineerPoints skill points for engineering
     * @param fighterPoints skill points for fighting
     * @param traderPoints skill points for trading
     * @param spaceshiptype assigned spaceship
     */
    public Character (String name, int pilotPoints, int engineerPoints, int fighterPoints, int traderPoints, Ship.ShipType spaceshiptype){
        this.name = name;
        this.pilotSkillPoints = pilotPoints;
        this.engineerSkillPoints = engineerPoints;
        this.fighterSkillPoints = fighterPoints;
        this.traderSkillPoints = traderPoints;
        this.money = 1000;
        this.spaceship = new Ship(Ship.ShipType.GNATT);
        this.spaceship.shipType = spaceshiptype;
    }

    /**
     * character
     */
    public Character (){}

    /**
     * setting name of a character
     * @param name name of the character
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * getting name of a character
     * @return name of character
     */
    public String getName(){
        return name;
    }

    /**
     * getting piloting skill point
     * @return piloting skill point
     */
    public int getPilotSkillPoints(){return pilotSkillPoints;}
    /**
     * getting engineering skill point
     * @return engineering skill point
     */
    public int getEngineerSkillPoints(){return  engineerSkillPoints;}
    /**
     * getting fighting skill point
     * @return fighting skill point
     */
    public int getFighterSkillPoints(){return fighterSkillPoints;}
    /**
     * getting trading skill point
     * @return trading skill point
     */
    public int getTraderSkillPoints(){return traderSkillPoints;}

    /**
     * setting piloting skill point
     * @param points pilot skill point
     */
    public void setPilotSkillPoints(int points){this.pilotSkillPoints = points;}
    /**
     * setting engineering skill point
     * @param points engineering skill point
     */
    public void setEngineerSkillPoints(int points){this.engineerSkillPoints = points;}
    /**
     * setting fighting skill point
     * @param points fighting skill point
     */
    public void setFighterSkillPoints(int points){this.fighterSkillPoints = points;}
    /**
     * setting trading skill point
     * @param points trading skill point
     */
    public void setTraderSkillPoints(int points){this.traderSkillPoints = points;}

    /**
     * getting ship that this character owns
     * @return ship
     */
    public Ship getSpaceShip(){ return spaceship ; }
      
    /**
     * setting ship for the player
     * @param spaceship the spaceship to set the player to
     */
    public void setShapeship(Ship spaceship){ this.spaceship = spaceship;}
      
    /**
     * getting region that this character is at
     * @return region
     */
    public String getRegionName() { return currentLocationName;}

    /**
     * setting region that this character will be at
     * @param location the location the character is going to
     */
    public void setRegionName(String location) {
        currentLocation = location;
        Log.d("GTrader", "Updated location for "+name+" to "+location);
    }

    /**
     * getting the amount of money thic character owns
     * @return money
     */
    public int getMoney(){ return money;}

    /**
     * paying money for an item
     * @param cost the cost of the item that will be deducted in money
     */
    public void pay(int cost){
        money-=cost;
    }

    /**
     * earning money
     * @param credit the amount of money being paid to character
     */
    public void getPaid(int credit){
        money+=credit;
    }

    /**
     * character choose to become a pirate
     * @param pirate boolean indicating this character is a pirate
     */
    public void setPirate(boolean pirate){
        this.isPirate = pirate;
    }

    /**
     * getting information of whether this character is a pirate or not
     * @return boolean indicating this character is a pirate or not
     */
    public boolean getIsPirate(){
        return isPirate;
    }

    /**
     * setting money of the character has
     * @param m money
     */
    public void setMoney(int m){
        money=m;
    }
}

