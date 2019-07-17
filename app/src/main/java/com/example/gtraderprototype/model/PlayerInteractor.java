package com.example.gtraderprototype.model;

import com.example.gtraderprototype.entity.Marketplace;
import com.example.gtraderprototype.entity.Player;
import com.example.gtraderprototype.entity.Region;

public class PlayerInteractor extends Interactor {
    public PlayerInteractor(Database db){
        super(db);
    }

    private final Player player = Player.getPlayer();

    private Marketplace marketplace = new Marketplace(player);

    public Player getPlayer(){
        return player;
    }
    public void setLocation(Region region){
        player.setRegion(region);
        marketplace = new Marketplace(Player.getPlayer());
    }

    public void deductFuel(int fuelAmt){
        player.getShip().deductFuel(fuelAmt);
    }
    public Region getLocation(){
        return player.getRegion();
    }

    public Marketplace getMarketplace(){
        return marketplace;
    }

}
