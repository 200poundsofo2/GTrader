package com.example.gtraderprototype.entity;



public enum Ship {
    Gnatt();
    private String name;
    private int hullStrength;
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

    public Item[] getCargo() {
        return cargoBays.clone();
    }

    public boolean canAddCargo() {
        return numberOfUsedCargoBays != cargoBays.length;
    }

    public boolean hasCargo() {
        return numberOfUsedCargoBays > 0;
    }

    public void addCargo(Item cargo) {
        for (int i = 0; i < cargoBays.length; i++) {
            if (cargoBays[i] == null) {
                cargoBays[i] = cargo;
                numberOfUsedCargoBays++;
                break;
            }
        }
    }

    public void dropCargo(Item soldItem) {
        for (int i = 0; i < cargoBays.length; i++) {
            if (soldItem.equals(cargoBays[i])) {
                cargoBays[i] = null;
                numberOfUsedCargoBays--;
                break;
            }
        }
    }

    public Equipment[] getWeapons() {
        return this.weaponSlots.clone();
    }

    public boolean canAddWeapon() {
        return numberOfUsedWeaponSlots != weaponSlots.length;
    }

    public void addWeapon(Equipment weapon) {
        this.weaponSlots[numberOfUsedWeaponSlots] = weapon;
        numberOfUsedWeaponSlots++;
    }

    public Equipment[] getShields() {
        return shieldSlots.clone();
    }

    public boolean canAddShield() {
        return numberOfUsedShieldSlots != shieldSlots.length;
    }

    public void addShield(Equipment shield) {
        this.shieldSlots[numberOfUsedShieldSlots] = shield;
        numberOfUsedShieldSlots++;
    }

    public Equipment[] getGadgets() {
        return gadgetSlots.clone();
    }

    public boolean canAddGadget() {
        return numberOfUsedGadgetSlots != gadgetSlots.length;
    }

    public void addGadget(Equipment gadget) {
        this.gadgetSlots[numberOfUsedGadgetSlots] = gadget;
        numberOfUsedGadgetSlots++;
    }

    public NPC[] getCrewMembers() {
        return crewQuarters.clone();
    }

    public boolean canAddCrewMember() {
        return numberOfUsedCrewQuarters != crewQuarters.length;
    }

    public void addCrewMember(NPC crewMember) {
        this.crewQuarters[numberOfUsedCrewQuarters] = crewMember;
        numberOfUsedCrewQuarters++;
    }

    public int getTravelRange() {
        return travelRange;
    }

    public void setTravelRange(int travelRange) {
        this.travelRange = travelRange;
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