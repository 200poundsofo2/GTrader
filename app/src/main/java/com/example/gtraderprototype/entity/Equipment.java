package com.example.gtraderprototype.entity;

public abstract class Equipment {
    private String name;
    private String owner;
    private int credit;
    private int strength;
    private int health;

    //setters
    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setName(String name){
        this.name=name;
    }

    public void setHealth(int health){
        this.health=health;
    }

    //getters
    public int getStrength() {
        return strength;
    }

    public int getCredit(){
        return credit;
    }

    public String getOwner(){
        return owner;
    }

    public String getName(){
        return name;
    }

    public int getHealth(){
        return this.health;
    }

    //abstract method: use the equipment, the effect depends on the type of equipment
    public abstract void powerUp();

    //constructor
    public Equipment(String name, String owner, int credit, int strength, int health){
        this.name=name;
        this.owner=owner;
        this.credit=credit;
        this.strength=strength;
        this.health=health;
    }

}
