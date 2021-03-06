package com.example.gtraderprototype.entity;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {

    private Player expectedPlayer = new Player("althea", 4, 5, 4, 4);
    private Player newPlayer;
    @Before
    public void setUp() throws Exception {
        newPlayer = new Player("althea", 4, 5, 4, 8);
    }

    @Test
    public void getPlayer() {
        Player pl = new Player("althea", 4, 5, 4, 4);
        assertEquals(expectedPlayer, pl);
    }

    @Test
    public void setPoints() {
        newPlayer.setTraderSkillPoints(4);
        assertEquals(expectedPlayer, newPlayer);
    }

    @Test
    public void getPoints() {
        assertEquals(expectedPlayer.getTraderSkillPoints(), 4);
    }

    @Test
    public void playerIsPirate() {
        newPlayer.setPirate(true);
        assertEquals(newPlayer.getIsPirate(), true);
    }
    @Test
    public void playerIsNotPirate() {
        assertEquals(newPlayer.getIsPirate(), false);
    }
}