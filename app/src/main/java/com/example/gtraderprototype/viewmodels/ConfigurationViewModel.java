package com.example.gtraderprototype.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.gtraderprototype.entity.Difficulty;
import com.example.gtraderprototype.entity.Player;
import com.example.gtraderprototype.model.GameInstanceInteractor;
import com.example.gtraderprototype.model.Model;

public class ConfigurationViewModel extends AndroidViewModel {

    private GameInstanceInteractor interactor;

    public ConfigurationViewModel(@NonNull Application application) {
        super(application);
        interactor = Model.getInstance().getGameInstanceInteractor();
    }
    public void newGame(Player player, Difficulty difficulty, Context context){
        Log.d("GTrader", "New player created: "+player.toString());
        interactor.newGame(player, difficulty, context);
    }
}
