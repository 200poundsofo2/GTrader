package com.example.gtraderprototype.model;

abstract class Interactor {
    private final Database gtDB;
    Interactor(Database db){
        gtDB = db;
    }
    protected  Database getDatabase(){
        return gtDB;
    }
}
