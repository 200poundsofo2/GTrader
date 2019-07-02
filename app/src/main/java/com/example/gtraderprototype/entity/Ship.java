package com.example.gtraderprototype.entity;

public enum Ship {
    Gnatt("Gnatt", 50, 1, 3, 3, 3, 1, 50);
    private String name;
    private int hullStrength;
    private int numberOfUsedCargoBays;
    private Item[] cargoBays;
    private int numberOfUsedWeaponSlots;
    private Equipment[] weaponSlots;
    private int numberOfUsedShieldSlots;
    private Equipment[] shieldSlots;
    private int numberOfUsedGadgetSlots;
    private Equipment[] gadgetSlots;
    private int numberOfUsedCrewQuarters;
    private NPC[] crewQuarters;
    private int travelRange;
    private int fuel;

    Ship(String name, int hullStrength, int numberOfAvailableCargoBays, int numberOfAvailableWeaponSlots, int numberOfAvailableShieldSlots, int numberOfAvailableGadgetSlots, int numberOfAvailableCrewQuarters, int travelRange) {
        this.name = name;
        this.hullStrength = hullStrength;
        this.cargoBays = new Item[numberOfAvailableCargoBays];
        this.weaponSlots = new Equipment[numberOfAvailableWeaponSlots];
        this.shieldSlots = new Equipment[numberOfAvailableShieldSlots];
        this.gadgetSlots = new Equipment[numberOfAvailableGadgetSlots];
        this.crewQuarters = new NPC[numberOfAvailableCrewQuarters];
        this.travelRange = travelRange;
        this.fuel = travelRange;
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
        return cargoBays;
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
            }
        }
    }

    public void dropCargo(Item soldItem) {
        for (int i = 0; i < cargoBays.length; i++) {
            if (soldItem.equals(cargoBays[i])) {
                cargoBays[i] = null;
                numberOfUsedCargoBays--;
            }
        }
    }

    public Equipment[] getWeapons() {
        return this.weaponSlots;
    }

    public boolean canAddWeapon() {
        return numberOfUsedWeaponSlots != weaponSlots.length;
    }

    public void addWeapon(Equipment weapon) {
        this.weaponSlots[numberOfUsedWeaponSlots++] = weapon;
    }

    public Equipment[] getShields() {
        return shieldSlots;
    }

    public boolean canAddShield() {
        return numberOfUsedShieldSlots != shieldSlots.length;
    }

    public void addShield(Equipment shield) {
        this.shieldSlots[numberOfUsedShieldSlots++] = shield;
    }

    public Equipment[] getGadgets() {
        return gadgetSlots;
    }

    public boolean canAddGadget() {
        return numberOfUsedGadgetSlots != gadgetSlots.length;
    }

    public void addGadget(Equipment gadget) {
        this.gadgetSlots[numberOfUsedGadgetSlots++] = gadget;
    }

    public NPC[] getCrewMembers() {
        return crewQuarters;
    }

    public boolean canAddCrewMember() {
        return numberOfUsedCrewQuarters != crewQuarters.length;
    }

    public void addCrewMember(NPC crewMember) {
        this.crewQuarters[numberOfUsedCrewQuarters++] = crewMember;
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

    public void setFuel(int fuel) {
        this.fuel = fuel;
    }

}