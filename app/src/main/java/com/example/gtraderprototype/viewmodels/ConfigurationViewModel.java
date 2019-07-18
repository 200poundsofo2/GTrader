package com.example.gtraderprototype.viewmodels;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import android.content.Context;
import androidx.annotation.NonNull;

import androidx.annotation.NonNull;

import com.example.gtraderprototype.entity.Difficulty;
import com.example.gtraderprototype.entity.GameInstance;
import com.example.gtraderprototype.entity.Item;
import com.example.gtraderprototype.model.GameInstanceInteractor;
import com.example.gtraderprototype.model.Model;
import com.example.gtraderprototype.model.PlayerInteractor;
import com.example.gtraderprototype.model.UniverseInteractor;
import com.example.gtraderprototype.views.MainActivity;
import com.example.gtraderprototype.views.SpacePortActivity;

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
        for(int i = 0; i< playerInteractor.getPlayer().getSpaceShip().getNumberOfAvailableCargoBays(); i++){
            playerInteractor.getPlayer()
                    .getSpaceShip().addCargo(listOfItems[(int)(Math.random() *listOfItems.length)]);
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
