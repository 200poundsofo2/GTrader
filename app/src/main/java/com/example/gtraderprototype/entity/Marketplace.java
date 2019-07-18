package com.example.gtraderprototype.entity;

import com.example.gtraderprototype.model.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * a entity that contains all of the sellable items in a region
 * and all the goods that a player can sell
 */
public class Marketplace {
    private final Player player;
    private ArrayList<Item> playerSellableItems;
    private ArrayList<Item> playerBuyableItems;

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
     * a function that determines what goods can be bought by the player
     * @return a list of buyable items
     */
    public ArrayList<Item> getPlayerBuyableItems(){
        Region region = Model.getInstance().getPlayerInteractor().getLocation();
        return region.sellableItems;
    }
}