package com.example.gtraderprototype.model;

import android.view.View;
import android.widget.TextView;

import com.example.gtraderprototype.R;
import com.example.gtraderprototype.entity.Player;
import com.example.gtraderprototype.entity.Region;

public class PlayerInteractor extends Interactor {
    public PlayerInteractor(Database db){
        super(db);
    }

    private Player player = Player.getPlayer();

    public Player getPlayer(){
        return player;
    }
    public void setLocation(Region region){
        player.setRegion(region);


    }

    public void deductFuel(int fuelAmt){
        player.getShip().deductFuel(fuelAmt);
    }
    public Region getLocation(){
        return player.getRegion();
    }

}
