package com.example.gtraderprototype.entity;

import com.example.gtraderprototype.model.Model;

import java.util.ArrayList;
import java.util.List;

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

    /**
     * getting items that the player can purchase
     * @return list of items
     */
    public ArrayList<Item> getPlayerBuyableItems(){
        Region region = Model.getInstance().getPlayerInteractor().getLocation();
        return region.sellableItems;
    }
}