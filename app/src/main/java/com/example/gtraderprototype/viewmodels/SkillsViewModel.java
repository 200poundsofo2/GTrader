package com.example.gtraderprototype.viewmodels;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import android.app.Application;

import com.example.gtraderprototype.model.Model;
import com.example.gtraderprototype.model.PlayerInteractor;

/**
 * the activity that display the player's stats
 */
public class SkillsViewModel extends AndroidViewModel {
    private final PlayerInteractor playerInteractor;

    /**
     * gets the player and his or her stats
     * @param application android stuff
     */
    public SkillsViewModel(@NonNull Application application) {
        super(application);
        playerInteractor = Model.getInstance().getPlayerInteractor();
    }

    /**
     * gets the name of the player
     * @return the name of the player
     */
    public String getName() {
        return playerInteractor.getPlayer().getName();
    }
}
