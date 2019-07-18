package com.example.gtraderprototype.model;

import com.example.gtraderprototype.entity.Region;
import com.example.gtraderprototype.entity.Universe;
import com.example.gtraderprototype.entity.System;

public class UniverseInteractor extends Interactor {
    public UniverseInteractor(Database db){
        super(db);
    }

    private Universe universe;


    public Universe getUniverse(){
        return universe;
    }


    public void addSystem(System system){
        universe.addSystem(system);

    }
    public void setUniverse(Universe universe){
        this.universe = universe;
        Universe.universe = universe;
    }

    public void addSystems(int numSystems){
        for(int s =0; s<numSystems; s++){
            universe.addRandomSystem();
        }
    }

    public System getRandomSystem(){
        return universe.systems.get((int)(Math.random()*universe.systems.size()));
    }
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
