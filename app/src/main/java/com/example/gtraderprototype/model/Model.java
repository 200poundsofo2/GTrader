package com.example.gtraderprototype.model;

import java.util.HashMap;
import java.util.Map;

/**
 * a model that determines what to pull from the database
 */
public final class Model {
    private final Database gtDB;


    private final Map<String, Object> interactorMap;

    private static final Model instance = new Model();

    /**
     * gets the current instance of model;
     * @return an instance of one of three interactors
     */
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

    /**
     * if the instance is a game then we return this interactor
     * @return if the instance is a game then we return this interactor
     */
    public GameInstanceInteractor getGameInstanceInteractor(){
        return (GameInstanceInteractor) interactorMap.get("GameInstance");
    }
    /**
     * if the instance is a Universe then we return this interactor
     * @return if the instance is a Universe then we return this interactor
     */
    public UniverseInteractor getUniverseInteractor(){
        return (UniverseInteractor) interactorMap.get("Universe");
    }
    /**
     * if the instance is a player then we return this interactor
     * @return if the instance is a player then we return this interactor
     */
    public PlayerInteractor getPlayerInteractor(){
        return (PlayerInteractor) interactorMap.get("Player");
    }
}
