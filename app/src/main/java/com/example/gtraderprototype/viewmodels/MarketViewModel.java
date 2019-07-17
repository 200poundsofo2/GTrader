package com.example.gtraderprototype.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.gtraderprototype.entity.Marketplace;
import com.example.gtraderprototype.model.GameInstanceInteractor;
import com.example.gtraderprototype.model.Model;
import com.example.gtraderprototype.model.PlayerInteractor;
import com.example.gtraderprototype.model.UniverseInteractor;

/**
 * the connection between the market place activity and the database
 */
public class MarketViewModel extends AndroidViewModel {

    private final PlayerInteractor playerInteractor;

    /**
     * gets the player to create an instance of the market place
     * @param application android stuff
     */
    public MarketViewModel(@NonNull Application application) {
        super(application);
        GameInstanceInteractor gameInteractor = Model.getInstance().getGameInstanceInteractor();
        UniverseInteractor universeInteractor = Model.getInstance().getUniverseInteractor();
        playerInteractor = Model.getInstance().getPlayerInteractor();
    }

    /**
     * returns an instance of the market place
     * @return a market place that contains buyable goods from a region and sellable good from the player
     */
    public Marketplace getMarketplace(){
        return playerInteractor.getMarketplace();
    }

}
