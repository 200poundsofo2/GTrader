package com.example.gtraderprototype.entity;

/**
 * NPC for the game which can be hired by user
 */
public class NPC extends Character {
      /**
     * initializing the skill points of a NPC
     * @param name name of NPC
     * @param name name of the player or NPC
     * @param pilotPoints skill points of piloting
     * @param engineerPoints skill points for engineering
     * @param fighterPoints skill points for fighting
     * @param traderPoints skill points for trading
     * @param spaceship assigned spaceship
     */
    public NPC(String name, int pilotPoints, int engineerPoints, int fighterPoints, int traderPoints, Ship.ShipType spaceship){
        super(name, pilotPoints, engineerPoints, fighterPoints, traderPoints, spaceship);
    }
}
