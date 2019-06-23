package com.example.gtraderprototype.entity;

public class Shield extends Equipment{
    @Override
    public void powerUp(){
        //("Shield powerup");
    }

    public Shield(String name, String owner, int credit, int strength, int health){
        super(name,owner,credit,strength,health);
    }

}
