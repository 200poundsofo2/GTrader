package com.example.gtraderprototype.entity;

public  class Weapon {
    private String owner;
    private int price;
    private int power;

    public void setPower(int power) {
        this.power = power;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public int getPower() {
        return power;
    }

    public int getPrice() {
        return price;
    }

    public String getOwner() {
        return owner;
    }
}
