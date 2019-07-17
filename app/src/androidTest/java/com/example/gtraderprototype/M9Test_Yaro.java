package com.example.gtraderprototype;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.assertEquals;

import com.example.gtraderprototype.entity.Ship;
import com.example.gtraderprototype.entity.Item;

public class M9Test_Yaro {
    private Ship expectedShip;
    private Ship secondExpectedShip;
    private Ship newShip;

    /* Ship */
    @Before
    public void buildShip() {
        expectedShip.addCargo(Item.Water);
        expectedShip.addCargo(Item.Furs);
        expectedShip.addCargo(Item.Firearms);
        secondExpectedShip.addCargo(Item.Water);
        secondExpectedShip.addCargo(Item.Furs);
        secondExpectedShip.addCargo(Item.Firearms);
        newShip.addCargo(Item.Water);
        newShip.addCargo(Item.Furs);
    }

    @Test
    public void addCargoNotMoreThanInventory() {
        expectedShip.addCargo(Item.Games);
        assertEquals(expectedShip, secondExpectedShip);
    }

    @Test
    public void addCargo() {
        newShip.addCargo(Item.Firearms);
        assertEquals(expectedShip, newShip);
    }

    @Test
    public void removeCargoDoesNotExist() {
        expectedShip.dropCargo(Item.Games);
        assertEquals(expectedShip, secondExpectedShip);
    }

    @Test
    public void removeCargoExists() {
        expectedShip.dropCargo(Item.Firearms);
        assertEquals(expectedShip, newShip);
    }

}
