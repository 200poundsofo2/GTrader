package com.example.gtraderprototype.entity;

import android.util.Log;

import com.example.gtraderprototype.model.Model;

import java.util.ArrayList;

public class Marketplace {
    public Marketplace(){

    }
    public ArrayList<Item> getPlayerSellableItems(){
        Region region = Player.getPlayer().getRegion();
        ArrayList<Item> allowablePlayerItemList = new ArrayList<>();
        Item[] playerItems = Player.getPlayer().getShip().getCargo();
        for (Item regionItem : region.buyableItems) {
            for(Item playerItem : playerItems){
                if( regionItem.equals(playerItem) ){
                    allowablePlayerItemList.add(playerItem);
                }
            }
        }
        return allowablePlayerItemList;
    }
    public ArrayList<Item> getPlayerBuyableItems(){
        Log.d("GTraderBuyable", Player.getPlayer().toString());
        Region region = Model.getInstance().getPlayerInteractor().getPlayer().getRegion();
        return region.sellableItems;
    }
}