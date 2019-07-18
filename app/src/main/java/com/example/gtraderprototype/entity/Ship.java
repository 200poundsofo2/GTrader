package com.example.gtraderprototype.entity;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;


public class Ship {
    public enum ShipType{
        GNATT("Gnatt", 50, 3, 3, 3, 3, 3, 50);

        String shipName;
        int hullStrength, numCargoBays, numWep, numShield, numGadget, numCrew, fuelCapacity;
        ShipType(String shipName, int hullStrength, int numCargoBays, int numWep, int numShield, int numGadget, int numCrew, int fuelCapacity){
            this.shipName = shipName;
            this.hullStrength = hullStrength;
            this.numCargoBays = numCargoBays;
            this.numWep = numWep;
            this.numShield = numShield;
            this.numCrew = numCrew;
            this.numGadget = numGadget;
            this.fuelCapacity = fuelCapacity;
        }

/**
 * an enum that contain all possible ship model and the behavior of the ship itself
 */
public enum Ship {
    Gnatt();
    private String name;
    private int hullStrength;
    private int numberOfAvailableCargoBays;
    private int numberOfUsedCargoBays;
    private final Item[] cargoBays;
    private int numberOfUsedWeaponSlots;
    private final Equipment[] weaponSlots;
    private int numberOfUsedShieldSlots;
    private final Equipment[] shieldSlots;
    private int numberOfUsedGadgetSlots;
    private final Equipment[] gadgetSlots;
    private int numberOfUsedCrewQuarters;
    private final NPC[] crewQuarters;
    private int travelRange;
    private int fuel;
    private int fuelCapacity;

    Ship() {
        this.name = "Gnatt";
        this.hullStrength = 50;
        this.cargoBays = new Item[3];
        this.weaponSlots = new Equipment[3];
        this.shieldSlots = new Equipment[3];
        this.gadgetSlots = new Equipment[3];
        this.crewQuarters = new NPC[1];
        this.travelRange = 50;
        this.fuel = 50;
        this.fuelCapacity = 50;
    }

    /**
     * gets the name of the ship
     * @return the name of the ship
     */
    public String getName() {
        return name;
    }

    /**
     * sets the name of the ship
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * get the remaining hp of the ship
     * @return the health point that the ship has
     */
    public int getHullStrength() {
        return hullStrength;
    }

    /**
     * sets the health points of the ship
     * @param hullStrength health desired for the ship
     */
    public void setHullStrength(int hullStrength) {
        this.hullStrength = hullStrength;
    }

    /**
     * gets the number of cargo bays available to be loaded
     * @return the number of available cargo bays
     */
    public int getNumberOfUsedCargoBays() {
        return numberOfUsedCargoBays;
    }

    /**
     * gets the list of loaded cargo
     * @return list of cargo on the ship
     */
    public Item[] getCargo() {
        return cargoBays.clone();
    }

    /**
     * checks if there is space in the cargo bays to add more cargo
     * @return true if cargo can be added false otherwise
     */
    public boolean canAddCargo() {
        Log.d("GTraderinv", "Avail:"+this.numberOfAvailableCargoBays);
        return (this.numberOfAvailableCargoBays>0);
    }

    /**
     * checks if the player has any cargo
     * @return true if cargo exists in the cargo hold false otherwise
     */
    public boolean hasCargo() {
        return (numberOfUsedCargoBays > 0);
    }

    /**
     * adds cargo
     * @param cargo cargo that will be added to the cargo bay
     */
    public void addCargo(Item cargo) {
        this.cargoBays.add(cargo);
        this.numberOfUsedCargoBays++;
        this.numberOfAvailableCargoBays--;
        Log.d("INV", "added "+numberOfAvailableCargoBays);

    }

    /**
     * cargo that is unloaded after a selling or is taken on inspection
     * @param soldItem item that will be taken
     */
    public void dropCargo(Item soldItem) {
        this.cargoBays.remove(soldItem);
        this.numberOfUsedCargoBays--;
        this.numberOfAvailableCargoBays++;
        Log.d("INV", "removed "+numberOfAvailableCargoBays);
    }

    /**
     * gets a list of equipped weapons
     * @return a list of equipped weapons
     */
    public Equipment[] getWeapons() {
        return this.weaponSlots.clone();
    }

    /**
     * checks if there is space for a weapon to be added
     * @return true if there is space for an additional weapon false otherwise
     */
    public boolean canAddWeapon() {
        return numberOfUsedWeaponSlots != numberOfAvailableWeaponSlots;
    }

    /**
     * adds a weapon to the list of weapons
     * @param weapon the weapon that will be added to the weapon list
     */
    public void addWeapon(Equipment weapon) {
        this.weaponSlots[numberOfUsedWeaponSlots] = weapon;
        numberOfUsedWeaponSlots++;
    }

    /**
     * gets of list of shield related items
     * @return a list of shield related items
     */
    public Equipment[] getShields() {
        return shieldSlots.clone();
    }

    /**
     * checks if their eis space for another shield to be added to the shield list
     * @return true if there is space false otherwise
     */
    public boolean canAddShield() {
        return numberOfUsedShieldSlots != numberOfAvailableShieldSlots;
    }

    /**
     * adds shield equipment ot the list
     * @param shield shield equipment
     */
    public void addShield(Equipment shield) {
        this.shieldSlots[numberOfUsedShieldSlots] = shield;
        numberOfUsedShieldSlots++;
    }

    /**
     * get the a list of equipped gadgets
     * @return a list of equipped gadgets
     */
    public Equipment[] getGadgets() {
        return gadgetSlots.clone();
    }

    /**
     * checks if another gadget can be added to the list
     * @return true if there is space false otherwise
     */
    public boolean canAddGadget() {
        return numberOfUsedGadgetSlots != numberOfAvailableGadgetSlots;
    }

    /**
     * adds gadget to the list og equipped gadgets
     * @param gadget the gadget that will be added
     */
    public void addGadget(Equipment gadget) {
        this.gadgetSlots[numberOfUsedGadgetSlots] = gadget;
        numberOfUsedGadgetSlots++;
    }

    /**
     * a list of crew members on the ship
     * @return list of crew members on the ship
     */
    public NPC[] getCrewMembers() {
        return crewQuarters.clone();
    }

    /**
     * checks if there is space for another crew member
     * @return true if their is enough space false otherwise
     */
    public boolean canAddCrewMember() {
        return numberOfUsedCrewQuarters != numberOfAvailableCrewQuarters;
    }

    /**
     * adds a crew member to the list of cre members
     * @param crewMember the crew member you wish to be added
     */
    public void addCrewMember(NPC crewMember) {
        this.crewQuarters[numberOfUsedCrewQuarters] = crewMember;
        numberOfUsedCrewQuarters++;
    }

    /**
     * gets the maximum range a player can travel
     * @return maximum range a player can travel
     */
    public int getTravelRange() {
        return travelRange;
    }

    /**
     * gets the maximum range a player can travel
     * @param travelRange the desired travel range
     */
    public void setTravelRange(int travelRange) {
        this.travelRange = travelRange;
    }

    /**
     * the fuel that the ship currently contains
     * @return the fuel that the ship currently contains
     */
    public int getFuel() {
        return this.fuel;
    }

    /**
     * gets the maximum capacity for fuel
     * @return capacity for fuel
     */
    public int getFuelCapacity() {
        return this.fuelCapacity;
    }

    /**
     * sets the maximum capacity for fuel
     * @param fuelCapacity the desired capacity for fuel
     */
    public void setFuelCapacity(int fuelCapacity) {
        this.fuelCapacity = fuelCapacity;
    }

    /**
     * sets the amount of fuel that the ship currently contains
     * @param fuel the desired amount of fuel
     */
    public void setFuel(int fuel) {
        this.fuel = fuel;
    }

    /**
     * the fuel cost due to travel
     * @param fuel cost of fuel
     */
    public void deductFuel(int fuel){
        this.fuel -= fuel;
    }

}