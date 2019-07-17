package com.example.gtraderprototype.model;


import android.util.Log;

import com.example.gtraderprototype.entity.Marketplace;
import com.example.gtraderprototype.entity.Player;
import com.example.gtraderprototype.entity.Region;

public class PlayerInteractor extends Interactor {
    public PlayerInteractor(Database db){
        super(db);
    }

    private Player player = Player.getPlayer();

    Marketplace marketplace = new Marketplace(player);

    public Player getPlayer(){
        return player;
    }
    public void setPlayer(Player player){
        this.player = player;
        marketplace = new Marketplace(player);
        Log.d("GTrader", "new" + player.toString());
    }
    public void setLocation(Region region){
        player.setRegionName(region.regionName);
        marketplace = new Marketplace(Player.getPlayer());
        Database.saveState(Model.getInstance().getGameInstanceInteractor().getGameInstance());
    }

    public void deductFuel(int fuelAmt){
        player.getSpaceship().deductFuel(fuelAmt);
    }
    public Region getLocation(){
        return Model.getInstance().getUniverseInteractor().getRegionByName(player.getRegionName());
    }

    public Marketplace getMarketplace(){
        return marketplace;
    }

}
