package com.example.gtraderprototype.model;

public abstract class Interactor {
    private Database gtDB;
    protected Interactor(Database db){
        gtDB = db;
    }
    protected  Database getDatabase(){
        return gtDB;
    }
}
