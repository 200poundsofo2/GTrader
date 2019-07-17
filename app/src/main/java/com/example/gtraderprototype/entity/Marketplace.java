package com.example.gtraderprototype.entity;

import java.util.ArrayList;

/**
 * marketplace where users can purchase items
 */
public class Marketplace {
    private Player player;

    /**
     * initializing the player in the marketplace
     * @param player the player in the marketplace
     */
    public Marketplace(Player player){
        this.player = player;
    }

    /**
     * getting items that a player has that are can be traded
     * @return list of items
     */
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

    /**
     * getting items that the player can purchase
     * @return list of items
     */
    public ArrayList<Item> getPlayerBuyableItems(){
        Region region = player.getRegion();
        return region.sellableItems;
    }
}