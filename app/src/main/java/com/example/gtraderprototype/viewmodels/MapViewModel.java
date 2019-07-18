package com.example.gtraderprototype.viewmodels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.gtraderprototype.entity.Player;
import com.example.gtraderprototype.entity.Region;
import com.example.gtraderprototype.entity.Universe;
import com.example.gtraderprototype.model.GameInstanceInteractor;
import com.example.gtraderprototype.model.Model;
import com.example.gtraderprototype.model.PlayerInteractor;
import com.example.gtraderprototype.model.UniverseInteractor;

/**
 * a class that creates map where the player can travel between markers
 */
public class MapViewModel extends AndroidViewModel {

    private final UniverseInteractor universeInteractor;
    private final PlayerInteractor playerInteractor;

    /**
     * constructor for the map model
     * @param application android stuff
     */
    public MapViewModel(@NonNull Application application) {
        super(application);
        GameInstanceInteractor gameInteractor = Model.getInstance().getGameInstanceInteractor();
        universeInteractor = Model.getInstance().getUniverseInteractor();
        playerInteractor = Model.getInstance().getPlayerInteractor();
    }

    /**
     * gets the markers from the universe
     */
    public void getMarkers() {
        Universe universe = universeInteractor.getUniverse();
    }

    /**
     * gets the universe interactor
     * @return the universe interactor
     */
    public Universe getUniverse() {
        return universeInteractor.getUniverse();
    }

    /**
     * gets the player's fuel
     * @return the player's fuel
     */
    public int getPlayerFuel() {
        return playerInteractor.getPlayer().getSpaceShip().getFuel();
    }

    /**
     * gets the player's ship range
     * @return the player's ship range
     */
    public int getPlayerShipRange() {
        return playerInteractor.getPlayer().getSpaceShip().getFuelCapacity();
    }

    /**
     * gets the player's ship's name
     * @return the name of the player's ship
     */
    public String getPlayerShipName() {
        return playerInteractor.getPlayer().getSpaceShip().getName();
    }

    /**
     * gets the player's location
     * @return the player location
     */
    public String getPlayerLocationName() {
        return playerInteractor.getLocation().regionName;
    }

    /**
     * gets the player money
     * @return the amount of money that the player has
     */
    public int getPlayerMoney() {
        return playerInteractor.getPlayer().getMoney();
    }

    /**
     * a function that allows the player to travel between region
     * @param regionname name of the region
     * @param fuelCost teh cost of fuel
     */
    public void travelToRegion(String regionname, int fuelCost) {
        Region region = universeInteractor.getRegionByName(regionname);
        if (region != null) {
            playerInteractor.setLocation(region);
            playerInteractor.deductFuel(fuelCost);
            Log.d("GTrader", "Entered region: " + region.toString());
        }
    }

    /**
     * gets the player
     * @return the player
     */
    public Player getPlayer() {
        return playerInteractor.getPlayer();
    }


}
