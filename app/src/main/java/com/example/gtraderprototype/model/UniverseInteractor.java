package com.example.gtraderprototype.model;

import com.example.gtraderprototype.entity.Player;
import com.example.gtraderprototype.entity.Universe;

public class UniverseInteractor extends Interactor {
    public UniverseInteractor(Database db){
        super(db);
        db.getNames();

    }

    private Universe universe;


    public Universe getUniverse(){
        return universe;
    }
    public void setUniverse(Universe universe){
        this.universe = universe;
        addSystems(10);
        Universe.universe = universe;
    }

    public void addSystems(int numSystems){
        for(int s =0; s<numSystems; s++){
            universe.addRandomSystem();
        }
    }


}
