package com.example.gtraderprototype.views;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.example.gtraderprototype.R;
import com.example.gtraderprototype.entity.Difficulty;
import com.example.gtraderprototype.entity.Item;
import com.example.gtraderprototype.entity.Player;
import com.example.gtraderprototype.entity.Ship;
import com.example.gtraderprototype.model.Model;
import com.example.gtraderprototype.viewmodels.EncounterViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EncounterTraderActivity extends AppCompatActivity {
    private Button trade;
    private Button attack;
    private Button flee;
    private TextView text;

    private Player player;
    private Ship ship;
    private Difficulty difficulty;

    private List<Item> cargo;
    private List<Item> offeredItems;
    double total;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.encounter_trader_trade);
        trade=findViewById(R.id.button_trade);
        attack=findViewById(R.id.button_attack);
        flee=findViewById(R.id.button_flee);
        text=findViewById(R.id.text_trader);

        EncounterViewModel viewModel = ViewModelProviders.of(this).get(EncounterViewModel.class);
        player=viewModel.getPlayer();
        ship=player.getSpaceShip();
        difficulty= Model.getInstance().getGameInstanceInteractor().getGameDifficulty();

        cargo=ship.getCargo();
        offeredItems=new ArrayList<>();
        total=0;

        trade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                trade();
            }
        });

        attack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attack();
            }
        });

        flee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void trade() {
        trade.setText("Deal");
        attack.setText("Attack");
        flee.setText("Refuse and end Trade");

        trade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initializeTradeList();
                text.setText("The trader offers " + offeredItems.toString()
                             + " for " + (int)total);
                trade.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        deal();
                        finish();
                    }
                });
            }
        });
    }

    private void  initializeTradeList() {
        offeredItems.clear();
        total = 0;

        Random rand = new Random();
        int numOfferedItems = rand.nextInt(4) + 1;

        for (int i = 0; i < numOfferedItems; i++) {
            int randomIndex = rand.nextInt((ship.getNumberOfUsedCargoBays()) );
            Item offered = cargo.get(randomIndex);
            total += offered.getBasePrice();
            offeredItems.add(offered);
        }
        double variance = rand.nextDouble() * (2) - 1;
        total = total*(1 + variance);
    }

    private void deal() {
        if (player.getMoney() >= total) {
            for (Item i: offeredItems) {
                ship.addCargo(i);
            }
            player.setMoney(player.getMoney() - (int)total);
        } else {
            text.setText("Not enough money");
        }
    }

    private void attack() {
        attack.setText("Attack Again");
        trade.setText("Deal abd end Trade");

        attack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double winChance = 1.0 - ((difficulty.difficultyIndex() + 1) / (double) 10);
                if (Math.random() < winChance) {
                    initializeTradeList();
                    total = total * 0.8;
                    text.setText("The trader offers " + offeredItems.toString()
                                 + " for " + (int)total);
                } else {
                    text.setText("you failed the attack");
                    flee();
                }
                trade.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        deal();
                        finish();
                    }
                });
            }
        });
    }

    private void flee() {
        trade.setEnabled(false);
        trade.setVisibility(View.GONE);
        attack.setEnabled(false);
        attack.setVisibility(View.GONE);

        flee.setText("Back");
        flee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                double percent = (difficulty.difficultyIndex() + 1) * 2 / (double) 50;
                int numDroppedItems = (int)(ship.getNumberOfUsedCargoBays() * percent);
                List<Item> droppedItems = new ArrayList<>();
                for (int i = 0; i < numDroppedItems; i++) {
                    int randomIndex = (int)Math.random() * (ship.getNumberOfUsedCargoBays() + 1);
                    Item dropped = cargo.remove(randomIndex);
                    droppedItems.add(dropped);
                    ship.dropCargo(dropped);
                }
                text.setText("You dropped " + droppedItems.toString() + " during fleeing");
                finish();
            }
        });
    }
}
