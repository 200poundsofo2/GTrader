package com.example.gtraderprototype.entity;

public class NPC extends Character {
    public NPC(String name, int pilotPoints, int engineerPoints,
               int fighterPoints, int traderPoints, Ship spaceship){
        super(name, pilotPoints, engineerPoints, fighterPoints, traderPoints, spaceship);
    }
}
