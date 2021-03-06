package com.example.gtraderprototype.views;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gtraderprototype.R;
import com.example.gtraderprototype.entity.Item;
import com.example.gtraderprototype.entity.Marketplace;
import com.example.gtraderprototype.entity.Player;
import com.example.gtraderprototype.entity.Ship;
import com.example.gtraderprototype.model.Model;
import com.example.gtraderprototype.viewmodels.MarketViewModel;

import java.util.ArrayList;

public class MarketFragment extends Fragment {
    private TextView moneyView;
    private MarketplaceBuyAdapter buyAdapter;
    private MarketplaceSellAdapter sellAdapter;
    private Player player = Player.getPlayer();
    private Ship playerShip = player.getSpaceShip();
    private ArrayList<Item> sellable = new ArrayList<>();
    private Marketplace marketplace = new Marketplace(player);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("GTrader", "view creating");
        View rootView = inflater.inflate(R.layout.fragment_market, container, false);
        player = Model.getInstance().getPlayerInteractor().getPlayer();
        Log.d("invv", player.toString());
        super.onCreate(savedInstanceState);

        RecyclerView buyRecyclerView;
        RecyclerView sellRecyclerView;
        RecyclerView.LayoutManager layoutManagerBuy;
        RecyclerView.LayoutManager layoutManagerSell;
        ArrayList<Item> buyable;
        //Set views/fleeText
        moneyView = rootView.findViewById(R.id.money);
        buyRecyclerView = rootView.findViewById(R.id.buying_recycler_view);
        sellRecyclerView = rootView.findViewById(R.id.selling_recycler_view);
        layoutManagerBuy = new LinearLayoutManager(getActivity());
        buyRecyclerView.setLayoutManager(layoutManagerBuy);
        layoutManagerSell = new LinearLayoutManager(getActivity());
        sellRecyclerView.setLayoutManager(layoutManagerSell);
        moneyView.setText("Money: $"+player.getMoney());
        MediaPlayer marketmusic= MediaPlayer.create(getActivity() ,R.raw.marketplace);
        marketmusic.setLooping(true);
        marketmusic.start();




        marketplace = Model.getInstance().getPlayerInteractor().getMarketplace();
        buyable = marketplace.getPlayerBuyableItems();

        buyAdapter = new MarketplaceBuyAdapter(buyable, new RecyclerViewClickListener() {
            @Override
            public void recyclerViewListClicked(View v, int position) {
                playerShip = player.getSpaceShip();
                Item item = buyAdapter.getItemAt(position);
                if (player.getMoney() >= item.getRegionPrice()) {
                    if (playerShip.canAddCargo()) {
                        Log.d("GTrader", "Player Contents: Money:" + player.getMoney()+ " Ship: " + playerShip.getNumberOfUsedCargoBays());
                        playerShip.addCargo(item);
                        player.pay(item.getRegionPrice());
                        moneyView.setText("Money: $" + player.getMoney());
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
            @Override
            public void recyclerViewListClickedRemove(View v, int position){

            }
        });
        buyRecyclerView.setAdapter(buyAdapter);
        sellable = marketplace.getPlayerSellableItems();
        sellAdapter = new MarketplaceSellAdapter(sellable, new RecyclerViewClickListener() {
            @Override
            public void recyclerViewListClicked(View v, int position) {
                playerShip = player.getSpaceShip();
                Item item = sellAdapter.getItemAt(position);
                Log.d("GTraderinv", playerShip.getCargo().toString());

                if (playerShip.hasCargo()) {
                    Log.d("GTrader", "Player Contents: Money:" + player.getMoney()+ " Ship: " + playerShip.getNumberOfUsedCargoBays());
                    player.getPaid(item.getRegionPrice());
                    playerShip.dropCargo(item);
                    moneyView.setText("Money: $" + player.getMoney());
                    sellable.clear();
                    sellable.addAll(marketplace.getPlayerSellableItems());
                    sellAdapter.notifyDataSetChanged();
                    Log.d("GTrader", "Player Contents: Money:" + player.getMoney()+ " Ship: " + playerShip.getNumberOfUsedCargoBays());
                } else {
                    Toast.makeText(getActivity(),
                            "You have no cargo", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void recyclerViewListClickedRemove(View v, int position){

            }
        });
        sellRecyclerView.setAdapter(sellAdapter);
        return rootView;
    }
}
