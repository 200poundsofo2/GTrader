package com.example.gtraderprototype;

import com.example.gtraderprototype.entity.Difficulty;
import com.example.gtraderprototype.entity.Player;
import com.example.gtraderprototype.views.EncounterPirateActivity;
import com.example.gtraderprototype.views.EncounterPoliceActivity;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import com.example.gtraderprototype.entity.Ship;
import com.example.gtraderprototype.entity.Item;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

/** JUnit for random events: encounter police and encounter pirate
 *
 */

public class EcounterUnitTest {

    private static EncounterPirateActivity encounterPirateActivity;
    private static EncounterPoliceActivity encounterPoliceActivity;
    private static Player player;
    private static Ship ship;
    private static List<Item> illegalItems;

    @Before
    public void setUp() {
        player = Player.getPlayer();
        ship = Ship.Gnatt;
        illegalItems=new LinkedList<>();
        encounterPirateActivity = new EncounterPirateActivity();
        encounterPoliceActivity = new EncounterPoliceActivity();
        encounterPoliceActivity.setPlayer(player);
        encounterPirateActivity.setPlayer(player);
        encounterPoliceActivity.setShip(ship);
        encounterPoliceActivity.setIllegalItems(illegalItems);
    }

    @After
    public void tearDown(){
        player=null;
        ship=null;
        illegalItems=null;
        encounterPoliceActivity=null;
        encounterPirateActivity=null;
    }


    @Test
    public void testPoliceSearchIllegal(){
        addIllegalCargo();
        encounterPoliceActivity.setPenalty(0);
        encounterPoliceActivity.testSearch();
        assertEquals(4100,encounterPoliceActivity.getPenalty());
        assertEquals(8,checkNumItems(ship.getCargo()));

        List<Item> expected=new LinkedList<>();
        expected.add(Item.Firearms);
        expected.add(Item.Narcotics);
        assertEquals(expected,encounterPoliceActivity.getIllegalItems());
    }

    @Test
    public void testPoliceSearch(){
        addCargo();
        encounterPoliceActivity.testSearch();
        assertEquals(0,encounterPoliceActivity.getPenalty());
        assertEquals(10,checkNumItems(ship.getCargo()));
    }


    @Test
    public void testPlayerBeginner(){
        //pirate
        player.setDifficulty(Difficulty.Beginner);//Beginner: 0
        assertEquals(0, player.getDifficultyLevel());
        player.setMoney(1000);
        addCargo();
        assertEquals(10,checkNumItems(ship.getCargo()));

        encounterPirateActivity.testRobPlayer();
        assertEquals(900,player.getMoney());

        assertEquals(9,checkNumItems(player.getShip().getCargo()));


    }

    @Test
    public void testPlayerEasy(){
        //pirate
        player.setDifficulty(Difficulty.Easy);//Easy:1
        assertEquals(1, player.getDifficultyLevel());
        player.setMoney(1000);
        addCargo();
        assertEquals(10,checkNumItems(ship.getCargo()));

        encounterPirateActivity.testRobPlayer();
        assertEquals(800,player.getMoney());
        assertEquals(8,checkNumItems(ship.getCargo()));


    }


    @Test
    public void testPlayerHard(){
        //pirate
        player.setDifficulty(Difficulty.Hard);//Hard: 3
        player.setMoney(1000);
        addCargo();
        encounterPirateActivity.testRobPlayer();
        assertEquals(600,player.getMoney());
        assertEquals(6,checkNumItems(ship.getCargo()));

    }

    @Test
    public void testPlayerImpossible(){
        //pirate
        player.setDifficulty(Difficulty.Impossible);//Impossible: 4
        player.setMoney(1000);
        addCargo();

        encounterPirateActivity.testRobPlayer();
        assertEquals(500,player.getMoney());
        assertEquals(5,checkNumItems(ship.getCargo()));
    }

    private int checkNumItems(Item[] cargo){
        int num=0;
        for(Item i:cargo){
            if(i!=null){
                num++;
            }
        }
        return num;
    }
    private static void addCargo(){
        ship.addCargo(Item.Water);
        ship.addCargo(Item.Furs);
        ship.addCargo(Item.Food);
        ship.addCargo(Item.Ore);
        ship.addCargo(Item.Water);
        ship.addCargo(Item.Ore);
        ship.addCargo(Item.Water);
        ship.addCargo(Item.Medicine);
        ship.addCargo(Item.Water);
        ship.addCargo(Item.Medicine);
    }
    private static void addIllegalCargo(){
        ship.addCargo(Item.Water);
        ship.addCargo(Item.Furs);
        ship.addCargo(Item.Firearms);
        ship.addCargo(Item.Narcotics);
        ship.addCargo(Item.Water);
        ship.addCargo(Item.Ore);
        ship.addCargo(Item.Water);
        ship.addCargo(Item.Water);
        ship.addCargo(Item.Water);
        ship.addCargo(Item.Medicine);
    }

}
