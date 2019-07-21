package com.example.gtraderprototype.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import com.example.gtraderprototype.model.Model;
import com.example.gtraderprototype.model.PlayerInteractor;
import com.example.gtraderprototype.entity.Player;

/** view model class for encounter activities
 *
 */
public class EncounterViewModel extends AndroidViewModel {
    private final PlayerInteractor playerInteractor;

    /**
     * constructor
     * @param application application
     */
    public EncounterViewModel(@NonNull Application application) {
        super(application);
        Model model=Model.getInstance();
        playerInteractor = model.getPlayerInteractor();
    }

    /**
     * getter of the player
     * @return player of the game
     */
    public Player getPlayer(){
        return playerInteractor.getPlayer();
    }


}

