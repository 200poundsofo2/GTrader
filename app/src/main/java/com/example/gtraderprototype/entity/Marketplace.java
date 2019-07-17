package com.example.gtraderprototype.entity;

import java.util.ArrayList;

/**
 * a entity that contains all of the sellable items in a region
 * and all the goods that a player can sell
 */
public class Marketplace {
    private final Player player;

    /**
     * the constructor for the market place
     * @param player the player
     */
    public Marketplace(Player player){
        this.player = player;
    }

    /**
     * a function that determines which goods from the player's cargo is sellable
     * @return list of sellable items
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
     * a function that determines what goods can be bought by the player
     * @return a list of buyable items
     */
    public ArrayList<Item> getPlayerBuyableItems(){
        Region region = player.getRegion();
        return region.sellableItems;
    }
}