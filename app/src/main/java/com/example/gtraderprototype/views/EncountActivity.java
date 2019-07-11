package com.example.gtraderprototype.views;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.example.gtraderprototype.R;
import com.example.gtraderprototype.entity.Player;
import com.example.gtraderprototype.entity.Ship;
import com.example.gtraderprototype.viewmodels.EncounterViewModel;
import com.example.gtraderprototype.viewmodels.MapViewModel;
import com.example.gtraderprototype.entity.Item;

public class EncountActivity extends AppCompatActivity {

    private TextView words;
    private Button b1;
    private Button b2;
    private EncounterViewModel viewModel;
    Player player;
    Item[] cargo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_police);
        words=findViewById(R.id.policeWord);
        b1=findViewById(R.id.b1);
        b2=findViewById(R.id.b2);
        viewModel = ViewModelProviders.of(this).get(EncounterViewModel.class);
        player=viewModel.getPlayerInteractor().getPlayer();
        cargo=player.getShip().getCargo();

        b1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startSearching();
            }
        });

        b2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                underArrest("You didn't obey. ");
            }
        });


        //finish();

    }
    private void startSearching(){
        boolean arrest = false;
        words.setText("Searching");
        b1.setEnabled(false);
        b2.setEnabled(false);
        for(Item i:cargo){
            if(i.equals(Item.Firearms)||i.equals(Item.Narcotics)||player.getIsPirate()){
                arrest=true;
                break;
            }
        }
        if(arrest){
            underArrest("You are a bad trader. ");
        }else{
            fine();
        }

    }
    private void underArrest(String msg){
        words.setText(msg+"You are under arrest.");
    }

    private void fine(){
        words.setText("You are a good man");
    }


}
