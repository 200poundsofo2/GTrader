package com.example.gtraderprototype.viewmodels;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import android.content.Context;
import androidx.annotation.NonNull;

import com.example.gtraderprototype.entity.Difficulty;
import com.example.gtraderprototype.entity.Item;
import com.example.gtraderprototype.model.GameInstanceInteractor;
import com.example.gtraderprototype.model.Model;
import com.example.gtraderprototype.model.PlayerInteractor;
import com.example.gtraderprototype.model.UniverseInteractor;

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
    public void newGame(Difficulty difficulty, Context context){
        gameInteractor.newGame(difficulty, context);
        playerInteractor.getPlayer().setRegion(universeInteractor.getRandomSystem().getRandomRegion());
        Item[] listOfItems = Item.values();
        for(int i = 0; i< 10; i++){
            playerInteractor.getPlayer().getShip().addCargo(listOfItems[(int)(Math.random() *listOfItems.length)]);
        }

    }
}
