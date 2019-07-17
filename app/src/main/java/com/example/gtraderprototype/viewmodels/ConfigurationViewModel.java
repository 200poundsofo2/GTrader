package com.example.gtraderprototype.viewmodels;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.gtraderprototype.entity.Difficulty;
import com.example.gtraderprototype.entity.GameInstance;
import com.example.gtraderprototype.entity.Item;
import com.example.gtraderprototype.entity.Player;
import com.example.gtraderprototype.model.GameInstanceInteractor;
import com.example.gtraderprototype.model.Model;
import com.example.gtraderprototype.model.PlayerInteractor;
import com.example.gtraderprototype.model.UniverseInteractor;
import com.example.gtraderprototype.views.MainActivity;
import com.example.gtraderprototype.views.SpacePortActivity;


public class ConfigurationViewModel extends AndroidViewModel {

    private GameInstanceInteractor gameInteractor;
    private UniverseInteractor universeInteractor;
    private PlayerInteractor playerInteractor;

    public ConfigurationViewModel(@NonNull Application application) {
        super(application);
        gameInteractor = Model.getInstance().getGameInstanceInteractor();
        universeInteractor = Model.getInstance().getUniverseInteractor();
        playerInteractor = Model.getInstance().getPlayerInteractor();
    }
    public void newGame(Difficulty difficulty, Player player, Context context){
        playerInteractor.getPlayer().setRegionName(universeInteractor.getRandomSystem().getRandomRegion().regionName);
        Item[] listOfItems = Item.values();
        for(int i = 0; i< playerInteractor.getPlayer().getSpaceship().getNumberOfAvailableCargoBays(); i++){
            playerInteractor.getPlayer().getSpaceship().addCargo(listOfItems[(int)(Math.random()*listOfItems.length)]);
        }
        gameInteractor.newGame(difficulty, player, context);
    }
    public void removeGame(String gameID, Context context){
        gameInteractor.removeGame(gameID, context);
    }

    public void exitGame(Context context){
        Intent myIntent = new Intent(context, MainActivity.class);
        context.startActivity(myIntent);
    }

}
