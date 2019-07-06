package com.example.gtraderprototype.viewmodels;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.gtraderprototype.entity.Difficulty;
import com.example.gtraderprototype.entity.Player;
import com.example.gtraderprototype.model.GameInstanceInteractor;
import com.example.gtraderprototype.model.Model;
import com.example.gtraderprototype.model.PlayerInteractor;
import com.example.gtraderprototype.model.UniverseInteractor;

public class MarketViewModel extends AndroidViewModel {

    private GameInstanceInteractor gameInteractor;
    private UniverseInteractor universeInteractor;
    private PlayerInteractor playerInteractor;

    public MarketViewModel(@NonNull Application application) {
        super(application);
        gameInteractor = Model.getInstance().getGameInstanceInteractor();
        universeInteractor = Model.getInstance().getUniverseInteractor();
        playerInteractor = Model.getInstance().getPlayerInteractor();
    }

}
