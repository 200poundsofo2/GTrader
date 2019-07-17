package com.example.gtraderprototype.entity;

import com.example.gtraderprototype.model.Model;

import java.util.ArrayList;
import java.util.List;

public class Marketplace {
    private Player player;
    public Marketplace(Player player){
        this.player = player;
    }
    public ArrayList<Item> getPlayerSellableItems(){
        Region region = Model.getInstance().getPlayerInteractor().getLocation();
        ArrayList<Item> allowablePlayerItemList = new ArrayList<>();
        List<Item> playerItems = player.getSpaceship().getCargo();
        for (Item regionItem : region.buyableItems) {
            for(Item playerItem : playerItems){
                if(regionItem.equals(playerItem) ){
                    allowablePlayerItemList.add(playerItem);
                }
            }
        }
        return allowablePlayerItemList;
    }
    public ArrayList<Item> getPlayerBuyableItems(){
        Region region = Model.getInstance().getPlayerInteractor().getLocation();
        return region.sellableItems;
    }
}