package com.example.gtraderprototype.viewmodels;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import android.content.Context;
import androidx.annotation.NonNull;
import android.util.Log;

import com.example.gtraderprototype.entity.Difficulty;
import com.example.gtraderprototype.entity.Player;
import com.example.gtraderprototype.model.GameInstanceInteractor;
import com.example.gtraderprototype.model.Model;
import com.example.gtraderprototype.model.UniverseInteractor;

public class ConfigurationViewModel extends AndroidViewModel {

    private GameInstanceInteractor gameInteractor;
    private UniverseInteractor universeInteractor;

    public ConfigurationViewModel(@NonNull Application application) {
        super(application);
        gameInteractor = Model.getInstance().getGameInstanceInteractor();
        universeInteractor = Model.getInstance().getUniverseInteractor();
    }
    public void newGame(Player player, Difficulty difficulty, Context context){
        Log.d("GTrader", "New player created: " + player.toString());
        gameInteractor.newGame(player, difficulty, context);
    }
}
