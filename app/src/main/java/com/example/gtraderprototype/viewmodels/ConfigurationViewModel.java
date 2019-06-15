package com.example.gtraderprototype.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.example.gtraderprototype.entity.Player;

public class ConfigurationViewModel extends AndroidViewModel {
    protected Player player;
    public ConfigurationViewModel(@NonNull Application application) {
        super(application);

    }

}
