package com.example.gtraderprototype.entity;

public  class Weapon extends Equipment{
    @Override
    public void powerUp(){
        System.out.println("Weapon powerup");
    }

    public Weapon(String name, String owner, int credit, int strength, int health){
        super(name,owner,credit,strength,health);
    }
}
