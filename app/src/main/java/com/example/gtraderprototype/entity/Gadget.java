package com.example.gtraderprototype.entity;

public class Gadget extends Equipment {
    @Override
    public void powerUp(){
        System.out.println("Gadget powerup");
    }

    public Gadget(String name, String owner, int credit, int strength, int health){
        super(name,owner,credit,strength,health);
    }
}
