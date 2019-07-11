package com.example.gtraderprototype.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.gtraderprototype.model.GameInstanceInteractor;
import com.example.gtraderprototype.model.Model;
import com.example.gtraderprototype.model.PlayerInteractor;
import com.example.gtraderprototype.model.UniverseInteractor;

public class EncounterViewModel extends AndroidViewModel {
    private GameInstanceInteractor gameInteractor;
    private PlayerInteractor playerInteractor;

    public EncounterViewModel(@NonNull Application application) {
        super(application);
        gameInteractor = Model.getInstance().getGameInstanceInteractor();
        playerInteractor = Model.getInstance().getPlayerInteractor();
    }

    public GameInstanceInteractor getGameInteractor(){
        return gameInteractor;
    }

    public PlayerInteractor getPlayerInteractor(){
        return playerInteractor;
    }
}

