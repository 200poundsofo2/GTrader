package com.example.gtraderprototype.entity;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {

    private Player expectedPlayer = new Player("althea", 4, 5, 4, 4, Difficulty.Easy);
    private Player newPlayer;
    @Before
    public void setUp() throws Exception {
        newPlayer = new Player("althea", 4, 5, 4, 4, Difficulty.Hard);
    }

    @Test
    public void getPlayer() {
        Player pl = new Player("althea", 4, 5, 4, 4, Difficulty.Easy);
        assertEquals(expectedPlayer, pl);
    }

    @Test
    public void setDifficulty() {
        newPlayer.setDifficulty(Difficulty.Easy);
        assertEquals(expectedPlayer, newPlayer);
    }

    @Test
    public void getDifficultyLevel() {
        assertEquals(expectedPlayer.getDifficultyLevel(), Difficulty.Easy);
    }

    @Test
    public void playerIsPirate() {
        newPlayer.setPirate(true);
        assertEquals(newPlayer.getIsPirate(), true);
    }
    public void playerIsNotPirate() {
        assertEquals(newPlayer.getIsPirate(), false);
    }
}