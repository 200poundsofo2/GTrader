package com.example.gtraderprototype.model;

import java.util.HashMap;
import java.util.Map;

public class Model {
    private Database gtDB;


    private Map<String, Object> interactorMap;

    private static Model instance = new Model();

    public static Model getInstance(){ return instance;}

    private Model(){
        gtDB = new Database();
        interactorMap = new HashMap<>();
        registerInteractors();
        gtDB.getGlobalUniverse();
    }
    private void registerInteractors(){
        interactorMap.put("GameInstance", new GameInstanceInteractor(gtDB));
        interactorMap.put("Universe", new UniverseInteractor(gtDB));
        interactorMap.put("Player", new PlayerInteractor(gtDB));
    }

    public GameInstanceInteractor getGameInstanceInteractor(){
        return (GameInstanceInteractor) interactorMap.get("GameInstance");
    }
    public UniverseInteractor getUniverseInteractor(){
        return (UniverseInteractor) interactorMap.get("Universe");
    }
    public PlayerInteractor getPlayerInteractor(){
        return (PlayerInteractor) interactorMap.get("Player");
    }
}
