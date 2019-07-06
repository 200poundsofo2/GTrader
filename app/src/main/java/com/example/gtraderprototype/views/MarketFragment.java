package com.example.gtraderprototype.views;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
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
import com.example.gtraderprototype.model.Model;
import com.example.gtraderprototype.viewmodels.MapViewModel;

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
    private ArrayList<Item> buyable;
    private ArrayList<Item> sellable;
    private MapViewModel mapviewmodel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_market, container, false);
        super.onCreate(savedInstanceState);
        mapviewmodel = ViewModelProviders.of(this).get(MapViewModel.class);

        //Views
        moneyView = rootView.findViewById(R.id.money);
        buyRecyclerView = rootView.findViewById(R.id.buying_recycler_view);
        sellRecyclerView = rootView.findViewById(R.id.selling_recycler_view);
        buyRecyclerView.setLayoutManager(layoutManagerBuy);
        sellRecyclerView.setLayoutManager(layoutManagerSell);

        layoutManagerBuy = new LinearLayoutManager(getActivity());
        layoutManagerSell = new LinearLayoutManager(getActivity());

        moneyView.setText("Money: $"+mapviewmodel.getPlayerMoney());



        Random rand = new Random();
        int randomCargo1 = rand.nextInt(10);
        int randomCargo2 = rand.nextInt(10);
        Item[] listOfItems = Item.values();
        Model.getInstance().getPlayerInteractor().getPlayer().getShip().addCargo(listOfItems[0]);
        Marketplace marketplace = new Marketplace();
        buyable = marketplace.getPlayerBuyableItems();
        buyAdapter = new MarketplaceBuyAdapter(buyable, new RecyclerViewClickListener() {
            @Override
            public void recyclerViewListClicked(View v, int position) {
                Ship playerShip =  Model.getInstance().getPlayerInteractor().getPlayer().getShip();
                Item item = buyAdapter.getItemAt(position);
                if ( Model.getInstance().getPlayerInteractor().getPlayer().getMoney() >= item.getRegionPrice()) {
                    if (playerShip.canAddCargo()) {
                        Log.d("GTrader", "Player Contents: Money:" +  Model.getInstance().getPlayerInteractor().getPlayer().getMoney()+ " Ship: " + playerShip.getNumberOfUsedCargoBays());
                        playerShip.addCargo(item);
                        Model.getInstance().getPlayerInteractor().getPlayer().pay(item.getRegionPrice());
                        moneyView.setText("Money: $" +  Model.getInstance().getPlayerInteractor().getPlayer().getMoney());
                        Marketplace marketplace = new Marketplace();
                        sellable.clear();
                        sellable.addAll(marketplace.getPlayerSellableItems());
                        sellAdapter.notifyDataSetChanged();
                        Log.d("GTrader", "Player Contents: Money:" +  Model.getInstance().getPlayerInteractor().getPlayer().getMoney()+ " Ship: " + playerShip.getNumberOfUsedCargoBays());
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
                Ship playerShip =  Model.getInstance().getPlayerInteractor().getPlayer().getShip();
                Item item = sellAdapter.getItemAt(position);
                if (playerShip.hasCargo()) {
                    Log.d("GTrader", "Player Contents: Money:" +  Model.getInstance().getPlayerInteractor().getPlayer().getMoney()+ " Ship: " + playerShip.getNumberOfUsedCargoBays());
                    Model.getInstance().getPlayerInteractor().getPlayer().getPaid(item.getRegionPrice());
                    playerShip.dropCargo(item);
                    moneyView.setText("Money: $" +  Model.getInstance().getPlayerInteractor().getPlayer().getMoney());
                    Marketplace marketplace = new Marketplace();
                    sellable.clear();
                    sellable.addAll(marketplace.getPlayerSellableItems());
                    sellAdapter.notifyDataSetChanged();
                    Log.d("GTrader", "Player Contents: Money:" +  Model.getInstance().getPlayerInteractor().getPlayer().getMoney()+ " Ship: " + playerShip.getNumberOfUsedCargoBays());
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
