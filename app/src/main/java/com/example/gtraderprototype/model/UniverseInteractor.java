package com.example.gtraderprototype.model;

import com.example.gtraderprototype.entity.Region;
import com.example.gtraderprototype.entity.Universe;
import com.example.gtraderprototype.entity.System;

/**
 * A class that retrieves the universe associated with the player's save
 */
public class UniverseInteractor extends Interactor {
    /**
     * A constructor for the universe interactor
     * @param db database that contains the stored information for the universe
     */
    public UniverseInteractor(Database db){
        super(db);
    }

    private Universe universe;

    /**
     * getter for the universe
     * @return the universe stored
     */
    public Universe getUniverse(){
        return universe;
    }

    /**
     * adds a system to the universe
     * @param system a system contained in the universe
     */
    public void addSystem(System system){

        universe.addSystem(system);

    }

    /**
     * sets the universe
     * @param universe new universe
     */
    public void setUniverse(Universe universe){
        this.universe = universe;
        //addSystems(10);
        Universe.universe = universe;
    }

    /**
     * adds random systems to the universe
     * @param numSystems number of systems wanted
     */
    public void addSystems(int numSystems){
        for(int s =0; s<numSystems; s++){
            universe.addRandomSystem();
        }
    }

    /**
     * gets a random system
     * @return a system
     */
    public System getRandomSystem(){
        return universe.systems.get((int)(Math.random()*universe.systems.size()));
    }

    /**
     * gets region by name with in a system
     * @param regionname region name
     * @return a region
     */
    public Region getRegionByName(String regionname){
        for(System system: universe.systems){
            for(Region region: system.getRegions()){
                if(region.regionName.equals(regionname)){
                    return region;
                }
            }
        }
        return null;
    }


}
