package com.example.gtraderprototype.entity;

public class Shield {
    private String owner;
    private int price;
    private int protection;

    public void setProtection(int protection) {
        this.protection = protection;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public int getProtection() {
        return protection;
    }

    public int getPrice() {
        return price;
    }

    public String getOwner() {
        return owner;
    }
}
