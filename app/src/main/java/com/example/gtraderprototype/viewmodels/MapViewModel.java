package com.example.gtraderprototype.viewmodels;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.gtraderprototype.R;
import com.example.gtraderprototype.entity.Difficulty;
import com.example.gtraderprototype.entity.Player;
import com.example.gtraderprototype.entity.Region;
import com.example.gtraderprototype.entity.Universe;
import com.example.gtraderprototype.model.GameInstanceInteractor;
import com.example.gtraderprototype.model.Model;
import com.example.gtraderprototype.model.PlayerInteractor;
import com.example.gtraderprototype.model.UniverseInteractor;
import com.example.gtraderprototype.views.SpacePortActivity;

public class MapViewModel extends AndroidViewModel {

    private GameInstanceInteractor gameInteractor;
    private UniverseInteractor universeInteractor;
    private PlayerInteractor playerInteractor;

    public MapViewModel(@NonNull Application application) {
        super(application);
        gameInteractor = Model.getInstance().getGameInstanceInteractor();
        universeInteractor = Model.getInstance().getUniverseInteractor();
        playerInteractor = Model.getInstance().getPlayerInteractor();
    }
    public void getMarkers(){
        Universe universe = universeInteractor.getUniverse();
    }
    public Universe getUniverse(){
        return universeInteractor.getUniverse();
    }

    public int getPlayerFuel(){
        return playerInteractor.getPlayer().getShip().getFuel();
    }
    public int getPlayerShipRange(){
        return playerInteractor.getPlayer().getShip().getTravelRange();
    }
    public String getPlayerShipName(){
        return playerInteractor.getPlayer().getShip().getName();
    }
    public String getPlayerLocationName(){
        return playerInteractor.getLocation().regionName;
    }
    public int getPlayerMoney(){
        return playerInteractor.getPlayer().getMoney();
    }
    public void travelToRegion(String regionname, int fuelCost){
        Region region = universeInteractor.getRegionByName(regionname);
        if(region!=null){
            playerInteractor.setLocation(region);
            playerInteractor.deductFuel(fuelCost);
        }
    }


}
