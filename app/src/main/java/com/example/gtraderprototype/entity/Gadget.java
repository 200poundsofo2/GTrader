package com.example.gtraderprototype.entity;

import android.util.Log;

public class Gadget extends Equipment {
    @Override
    public void powerUp(){
        Log.d("Gtrader", "Gadget powerup");
    }

    public Gadget(String name, String owner, int credit, int strength, int health){
        super(name,owner,credit,strength,health);
    }
}
