package com.example.gtraderprototype.views;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gtraderprototype.entity.Item;
import com.example.gtraderprototype.entity.Marketplace;
import com.example.gtraderprototype.entity.Player;
import com.example.gtraderprototype.R;
import com.example.gtraderprototype.entity.Ship;

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
    private ArrayList<Item> buyable;
    private  ArrayList<Item> sellable;
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
        Marketplace marketplace = new Marketplace(player);
        buyable = marketplace.getPlayerBuyableItems();
        buyAdapter = new MarketplaceBuyAdapter(buyable, new RecyclerViewClickListener() {
            @Override
            public void recyclerViewListClicked(View v, int position) {
                player = Player.getPlayer();
                Ship playerShip = player.getShip();
                Item item = buyAdapter.getItemAt(position);
                if (player.getMoney() >= item.getRegionPrice()) {
                    if (playerShip.canAddCargo()) {
                        Log.d("GTrader", "Player Contents: Money:" + player.getMoney()+ " Ship: " + playerShip.getNumberOfUsedCargoBays());
                        playerShip.addCargo(item);
                        player.pay(item.getRegionPrice());
                        moneyView.setText("Money: $" + player.getMoney());
                        Marketplace marketplace = new Marketplace(player);
                        sellable.clear();
                        sellable.addAll(marketplace.getPlayerSellableItems());
                        sellAdapter.notifyDataSetChanged();
                        Log.d("GTrader", "Player Contents: Money:" + player.getMoney()+ " Ship: " + playerShip.getNumberOfUsedCargoBays());
                    } else {
                        Toast.makeText(getActivity(),
                                "You have no space", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getActivity(),
                            "You do not have enough money", Toast.LENGTH_LONG).show();
                }
            }
        });
        buyRecyclerView.setAdapter(buyAdapter);
        sellable = marketplace.getPlayerSellableItems();
        sellAdapter = new MarketplaceSellAdapter(sellable, new RecyclerViewClickListener() {
            @Override
            public void recyclerViewListClicked(View v, int position) {
                player = Player.getPlayer();
                Ship playerShip = player.getShip();
                Item item = sellAdapter.getItemAt(position);
                if (playerShip.hasCargo()) {
                    Log.d("GTrader", "Player Contents: Money:" + player.getMoney()+ " Ship: " + playerShip.getNumberOfUsedCargoBays());
                    player.getPaid(item.getRegionPrice());
                    playerShip.dropCargo(item);
                    moneyView.setText("Money: $" + player.getMoney());
                    Marketplace marketplace = new Marketplace(player);
                    sellable.clear();
                    sellable.addAll(marketplace.getPlayerSellableItems());
                    sellAdapter.notifyDataSetChanged();
                    Log.d("GTrader", "Player Contents: Money:" + player.getMoney()+ " Ship: " + playerShip.getNumberOfUsedCargoBays());
                } else {
                    Toast.makeText(getActivity(),
                            "You have no cargo", Toast.LENGTH_LONG).show();
                }
            }
        });
        sellRecyclerView.setAdapter(sellAdapter);
        return rootView;
    }
}
