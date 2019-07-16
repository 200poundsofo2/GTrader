package com.example.gtraderprototype.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.gtraderprototype.entity.Marketplace;
import com.example.gtraderprototype.model.GameInstanceInteractor;
import com.example.gtraderprototype.model.Model;
import com.example.gtraderprototype.model.PlayerInteractor;
import com.example.gtraderprototype.model.UniverseInteractor;

public class MarketViewModel extends AndroidViewModel {

    private final PlayerInteractor playerInteractor;


    public MarketViewModel(@NonNull Application application) {
        super(application);
        GameInstanceInteractor gameInteractor = Model.getInstance().getGameInstanceInteractor();
        UniverseInteractor universeInteractor = Model.getInstance().getUniverseInteractor();
        playerInteractor = Model.getInstance().getPlayerInteractor();
    }

    public Marketplace getMarketplace(){
        return playerInteractor.getMarketplace();
    }

}
