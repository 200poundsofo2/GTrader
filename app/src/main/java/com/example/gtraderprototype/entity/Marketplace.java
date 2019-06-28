package com.example.gtraderprototype.entity;

import java.util.ArrayList;

public class Marketplace {
    private Player player;
    private Region region;
    public Marketplace(Player player, Region region){
        this.player = player;
        this.region = region;
    }
    public ArrayList<Item> getPlayerSellableItems(){
        ArrayList<Item> allowablePlayerItemList = new ArrayList<>();
        Item[] playerItems = player.getShip().getCargo();
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
        return region.sellableItems;
    }
}