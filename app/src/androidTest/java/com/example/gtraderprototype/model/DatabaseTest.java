package com.example.gtraderprototype.model;

import com.example.gtraderprototype.entity.GameInstance;

import org.junit.Test;

import static org.junit.Assert.*;

public class DatabaseTest {

    @Test
    public void getNewGameID() {
        String key1 = Database.getNewGameID();
        String key2 = Database.getNewGameID();
        assertNotEquals("Keys are unique.",key1, key2);

    }

    @Test
    public void saveGame() {
        GameInstance instance = new GameInstance();
        Database.saveState(instance);
    }


}