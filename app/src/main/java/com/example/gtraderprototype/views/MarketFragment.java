package com.example.gtraderprototype.views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.gtraderprototype.entity.Item;
import com.example.gtraderprototype.entity.Marketplace;
import com.example.gtraderprototype.entity.Player;
import com.example.gtraderprototype.R;
import com.example.gtraderprototype.entity.Ship;
import com.example.gtraderprototype.entity.Universe;

import java.util.ArrayList;
import java.util.Random;

import static com.example.gtraderprototype.entity.Universe.universe;

public class MarketFragment extends Fragment {
    private TextView moneyView;
    private RecyclerView buyRecyclerView;
    private RecyclerView sellRecyclerView;
    private MarketplaceBuyAdapter buyAdapter;
    private MarketplaceSellAdapter sellAdapter;
    private RecyclerView.LayoutManager layoutManagerBuy;
    private RecyclerView.LayoutManager layoutManagerSell;
    private Player player = Player.getPlayer();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_market, container, false);
        super.onCreate(savedInstanceState);
        moneyView = rootView.findViewById(R.id.money);
        buyRecyclerView = rootView.findViewById(R.id.buying_recycler_view);
        sellRecyclerView = rootView.findViewById(R.id.selling_recycler_view);
        layoutManagerBuy = new LinearLayoutManager(getActivity());
        buyRecyclerView.setLayoutManager(layoutManagerBuy);
        layoutManagerSell = new LinearLayoutManager(getActivity());
        sellRecyclerView.setLayoutManager(layoutManagerSell);
        moneyView.setText("Money: $"+player.getMoney());
        player.setRegion(universe.systems.get(0).getRegions().get(0));
        Random rand = new Random();
        int randomCargo1 = rand.nextInt(10);
        int randomCargo2 = rand.nextInt(10);
        Item[] listOfItems = Item.values();
        player.getShip().addCargo(listOfItems[0]);
        player.getShip().addCargo(listOfItems[randomCargo1]);
        player.getShip().addCargo(listOfItems[randomCargo2]);
        Marketplace marketplace = new Marketplace(player);
        buyAdapter = new MarketplaceBuyAdapter(marketplace.getPlayerBuyableItems());
        buyRecyclerView.setAdapter(buyAdapter);
        sellAdapter = new MarketplaceSellAdapter(marketplace.getPlayerSellableItems());
        sellRecyclerView.setAdapter(sellAdapter);
        return rootView;
    }

   /* public void buy_item(View view) {
        buyAdapter.getItemAt()
        Ship playerShip = player.getShip();
        if(player.getMoney()< item.getRegionPrice()){
            if(playerShip.canAddCargo()) {
                playerShip.addCargo(item);
                player.pay(item.getRegionPrice());
                moneyView.setText("Money: $"+player.getMoney());
            }
        }
    }*/

    public void sell_item(View view) {
    }
}
