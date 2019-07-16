package com.example.gtraderprototype.entity;

import java.util.ArrayList;

public class Marketplace {
    private final Player player;
    public Marketplace(Player player){
        this.player = player;
    }
    public ArrayList<Item> getPlayerSellableItems(){
        Region region = player.getRegion();
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
        Region region = player.getRegion();
        return region.sellableItems;
    }
}