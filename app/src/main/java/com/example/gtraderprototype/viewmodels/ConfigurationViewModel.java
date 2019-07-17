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

/**
 * the configuration view model
 */
public class ConfigurationViewModel extends AndroidViewModel {

    private final GameInstanceInteractor gameInteractor;
    private final UniverseInteractor universeInteractor;
    private final PlayerInteractor playerInteractor;

    /**
     * the constructor for the configuration view model
     * @param application android stuff
     */
    public ConfigurationViewModel(@NonNull Application application) {
        super(application);
        gameInteractor = Model.getInstance().getGameInstanceInteractor();
        universeInteractor = Model.getInstance().getUniverseInteractor();
        playerInteractor = Model.getInstance().getPlayerInteractor();
    }

    /**
     * creates a new game
     * @param difficulty how hard the game is
     * @param context android stuff
     */
    public void newGame(Difficulty difficulty, Context context){
        gameInteractor.newGame(difficulty, context);
        playerInteractor.getPlayer()
                .setRegion(universeInteractor.getRandomSystem().getRandomRegion());
        Item[] listOfItems = Item.values();
        for(int i = 0; i< 10; i++){
            playerInteractor.getPlayer()
                    .getShip().addCargo(listOfItems[(int)(Math.random() *listOfItems.length)]);
        }

    }
}
