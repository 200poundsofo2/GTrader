package com.example.gtraderprototype.viewmodels;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.gtraderprototype.entity.Difficulty;
import com.example.gtraderprototype.entity.Marketplace;
import com.example.gtraderprototype.entity.Player;
import com.example.gtraderprototype.model.GameInstanceInteractor;
import com.example.gtraderprototype.model.Model;
import com.example.gtraderprototype.model.PlayerInteractor;
import com.example.gtraderprototype.model.UniverseInteractor;
import com.example.gtraderprototype.views.MarketplaceBuyAdapter;

public class MarketViewModel extends AndroidViewModel {

    private PlayerInteractor playerInteractor;


    public MarketViewModel(@NonNull Application application) {
        super(application);
        playerInteractor = Model.getInstance().getPlayerInteractor();
    }

    public Marketplace getMarketplace(){
        return playerInteractor.getMarketplace();
    }

}
