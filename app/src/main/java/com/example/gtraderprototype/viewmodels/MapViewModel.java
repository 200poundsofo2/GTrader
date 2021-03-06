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

public class MapViewModel extends AndroidViewModel {

    private UniverseInteractor universeInteractor;
    private PlayerInteractor playerInteractor;

    public MapViewModel(@NonNull Application application) {
        super(application);
        universeInteractor = Model.getInstance().getUniverseInteractor();
        playerInteractor = Model.getInstance().getPlayerInteractor();
    }

    public void getMarkers() {
        Universe universe = universeInteractor.getUniverse();
    }

    public Universe getUniverse() {
        return universeInteractor.getUniverse();
    }

    public int getPlayerFuel() {
        return playerInteractor.getPlayer().getSpaceShip().getFuel();
    }

    public int getPlayerShipRange() {
        return playerInteractor.getPlayer().getSpaceShip().getFuelCapacity();
    }

    public String getPlayerShipName() {
        return playerInteractor.getPlayer().getSpaceShip().getName();
    }

    public String getPlayerLocationName() {
        return playerInteractor.getLocation().regionName;
    }


    public void travelToRegion(String regionname, int fuelCost) {
        Region region = universeInteractor.getRegionByName(regionname);
        if (region != null) {
            playerInteractor.setLocation(region);
            playerInteractor.deductFuel(fuelCost);
            Log.d("GTrader", "Entered region: " + region.toString());
        }
    }
    public Player getPlayer() {
        return playerInteractor.getPlayer();
    }


}
