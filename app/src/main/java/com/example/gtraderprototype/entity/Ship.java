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

    }

    ShipType shipType;
    private String name;
    private int hullStrength;
    private int numberOfAvailableCargoBays;
    private int numberOfUsedCargoBays;
    private List<Item> cargoBays = new ArrayList<>();
    private int numberOfUsedWeaponSlots;
    private int numberOfAvailableWeaponSlots;
    private List<Equipment> weaponSlots= new ArrayList<>();
    private int numberOfUsedShieldSlots;
    private int numberOfAvailableShieldSlots;
    private List<Equipment> shieldSlots= new ArrayList<>();
    private int numberOfUsedGadgetSlots;
    private int numberOfAvailableGadgetSlots;
    private List<Equipment> gadgetSlots= new ArrayList<>();
    private int numberOfUsedCrewQuarters;
    private int numberOfAvailableCrewQuarters;
    private List<NPC> crewQuarters= new ArrayList<>();
    private int fuel;
    private int fuelCapacity;

    Ship(ShipType shipType) {
        this.name = shipType.shipName;
        this.hullStrength = shipType.hullStrength;
        this.fuel = shipType.fuelCapacity;
        this.fuelCapacity = shipType.fuelCapacity;
        this.numberOfAvailableCargoBays = shipType.numCargoBays;
        this.numberOfAvailableCrewQuarters = shipType.numCrew;
        this.numberOfAvailableGadgetSlots = shipType.numGadget;
        this.numberOfAvailableShieldSlots = shipType.numShield;
        this.numberOfAvailableWeaponSlots = shipType.numWep;
        this.shipType = shipType;
    }

    Ship(){

    }
    public ShipType getShipType(){
        return this.shipType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHullStrength() {
        return hullStrength;
    }

    public void setHullStrength(int hullStrength) {
        this.hullStrength = hullStrength;
    }

    public int getNumberOfUsedCargoBays() {
        return numberOfUsedCargoBays;
    }

    public int getNumberOfAvailableCargoBays(){
        return this.numberOfAvailableCargoBays;
    }

    public List<Item> getCargo() {
        Log.d("innerinv", cargoBays.toString());
        return this.cargoBays;
    }

    public void setCargo(ArrayList<Item> cargo){
        this.cargoBays.addAll(cargo);
    }

    public boolean canAddCargo() {
        Log.d("GTraderinv", "Avail:"+this.numberOfAvailableCargoBays);
        return (this.numberOfAvailableCargoBays>0);
    }

    public boolean hasCargo() {
        return (numberOfUsedCargoBays > 0);
    }

    public void addCargo(Item cargo) {
        this.cargoBays.add(cargo);
        this.numberOfUsedCargoBays++;
        this.numberOfAvailableCargoBays--;
        Log.d("INV", "added "+numberOfAvailableCargoBays);

    }

    public void dropCargo(Item soldItem) {
        this.cargoBays.remove(soldItem);
        this.numberOfUsedCargoBays--;
        this.numberOfAvailableCargoBays++;
        Log.d("INV", "removed "+numberOfAvailableCargoBays);
    }

    public List<Equipment> getWeapons() {
        return this.weaponSlots;
    }

    public boolean canAddWeapon() {
        return numberOfUsedWeaponSlots != numberOfAvailableWeaponSlots;
    }

    public void addWeapon(Equipment weapon) {
        this.weaponSlots.set(numberOfUsedWeaponSlots++, weapon);
    }

    public List<Equipment> getShields() {
        return shieldSlots;
    }

    public boolean canAddShield() {
        return numberOfUsedShieldSlots != numberOfAvailableShieldSlots;
    }

    public void addShield(Equipment shield) {
        this.shieldSlots.set(numberOfUsedShieldSlots++, shield);
    }

    public List<Equipment> getGadgets() {
        return gadgetSlots;
    }

    public boolean canAddGadget() {
        return numberOfUsedGadgetSlots != numberOfAvailableGadgetSlots;
    }

    public void addGadget(Equipment gadget) {
        this.gadgetSlots.set(numberOfUsedGadgetSlots++, gadget);
    }

    public List<NPC> getCrewMembers() {
        return crewQuarters;
    }

    public boolean canAddCrewMember() {
        return numberOfUsedCrewQuarters != numberOfAvailableCrewQuarters;
    }

    public void addCrewMember(NPC crewMember) {
        this.crewQuarters.set(numberOfUsedCrewQuarters++, crewMember);
    }


    public int getFuel() {
        return this.fuel;
    }
    public int getFuelCapacity() {
        return this.fuelCapacity;
    }
    public void setFuelCapacity(int fuelCapacity) {
        this.fuelCapacity = fuelCapacity;
    }

    public void setFuel(int fuel) {
        this.fuel = fuel;
    }

    public void deductFuel(int fuel){
        this.fuel -= fuel;
    }

}