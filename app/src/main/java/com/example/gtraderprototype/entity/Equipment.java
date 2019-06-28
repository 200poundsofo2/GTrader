package com.example.gtraderprototype.entity;

public class Equipment {
    private int strength;
    private int health;
    private TypeOfEquipment type;
    private Item item;

    public Equipment(int strength, int health, TypeOfEquipment type, Item item){
        this.strength=strength;
        this.health=health;
        this.type = type;
        this.item = item;
    }

    //getters
    public int getStrength() {
        return strength;
    }
    public int getHealth(){
        return this.health;
    }
    public TypeOfEquipment getType() { return this.type;}
    public Item getItem() { return this.item; }
}
