package com.example.gtraderprototype.viewmodels;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import android.app.Application;

import com.example.gtraderprototype.model.Model;
import com.example.gtraderprototype.model.PlayerInteractor;

public class SkillsViewModel extends AndroidViewModel {
    private PlayerInteractor playerInteractor;

    public SkillsViewModel(@NonNull Application application) {
        super(application);
        playerInteractor = Model.getInstance().getPlayerInteractor();
    }

    public String getName() {
        return playerInteractor.getPlayer().getName();
    }
}
