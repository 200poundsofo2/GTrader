package com.example.gtraderprototype.entity;

public enum Ship {
    Gnatt("Gnatt", 50, 3, 3, 3, 3, 1, 50 );
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
    Ship(String name, int hullStrength, int numberOfAvailableCargoBays, int numberOfAvailableWeaponSlots, int numberOfAvailableShieldSlots, int numberOfAvailableGadgetSlots, int numberOfAvailableCrewQuarters, int travelRange){
        this.name = name;
        this.hullStrength = hullStrength;
        this.cargoBays = new Item[numberOfAvailableCargoBays];
        this.weaponSlots = new Equipment[numberOfAvailableWeaponSlots];
        this.shieldSlots = new Equipment[numberOfAvailableShieldSlots];
        this.gadgetSlots = new Equipment[numberOfAvailableGadgetSlots];
        this.crewQuarters = new NPC[numberOfAvailableCrewQuarters];
        this.travelRange = travelRange;
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
    public Item[] getCargo() {
        return cargoBays;
    }

    public boolean addCargo(Item cargo) {
        if( numberOfUsedCargoBays != cargoBays.length ) {
            this.cargoBays[++numberOfUsedCargoBays] = cargo;
            return true;
        }
        return false;
    }

    public Equipment[] getWeapons() {
        return this.weaponSlots;
    }

    public boolean addWeapon(Equipment weapon) {
        if( numberOfUsedWeaponSlots != weaponSlots.length ) {
            this.weaponSlots[++numberOfUsedWeaponSlots] = weapon;
            return true;
        }
        return false;
    }

    public Equipment[] getShields() {
        return shieldSlots;
    }

    public boolean addShield(Equipment shield) {
        if( numberOfUsedShieldSlots != shieldSlots.length) {
            this.shieldSlots[++numberOfUsedShieldSlots] = shield;
            return true;
        }
        return false;
    }

    public Equipment[] getGadgets() {
        return gadgetSlots;
    }

    public boolean addGadget(Equipment gadget) {
        if( numberOfUsedGadgetSlots != gadgetSlots.length) {
            this.gadgetSlots[++numberOfUsedGadgetSlots] = gadget;
            return true;
        }
        return false;
    }

    public NPC[] getCrewMembers() {
        return crewQuarters;
    }

    public boolean addCrewMember(NPC crewMember) {
        if( numberOfUsedCrewQuarters != crewQuarters.length ) {
            this.crewQuarters[++numberOfUsedCrewQuarters] = crewMember;
            return true;
        }
        return false;
    }

    public int getTravelRange() {
        return travelRange;
    }

    public void setTravelRange(int travelRange) {
        this.travelRange = travelRange;
    }

}