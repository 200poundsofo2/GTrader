package com.example.gtraderprototype.entity;

public class Gadget {
    private String owner;
    private int price;
    private int strength;

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public int getStrength() {
        return strength;
    }

    public int getPrice() {
        return price;
    }

    public String getOwner() {
        return owner;
    }
}
