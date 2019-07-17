package com.example.gtraderprototype;

import com.example.gtraderprototype.entity.Item;
import com.example.gtraderprototype.entity.Region;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class christianHolmesTestsTest {

    @Test
    public void testSellableItemsInArrayList() {
        Region region = new Region();
        Item[] items = Item.values();
        ArrayList<Item> sellableItems = new ArrayList<>();
        for (Item item : items) {
            int numericTechValue = region.getTechLevel().getTechLevel();
            boolean regionCanSell = numericTechValue >= item.getMinimumTechLevelToProduce();
            if (regionCanSell) {
                sellableItems.add(item);
            }
        }
        for (int i = 0;i< sellableItems.size(); i++) {
            for (int j = 0;j< region.sellableItems.size(); j++) {
                if (sellableItems.get(i).getName().equals(region.sellableItems.get(i).getName())) {
                    break;
                }
                if(j == region.sellableItems.size()){
                    fail("the item : " + sellableItems.get(i) + " in teh sellable list was not found in the region");
                }
            }
        }

    }

    @Test
    public void testBuyableItemsInArrayList() {
        Region region = new Region();
        Item[] items = Item.values();
        ArrayList<Item> buyableItems = new ArrayList<>();
        for (Item item : items) {
            int numericTechValue = region.getTechLevel().getTechLevel();
            boolean regionCanBuy = numericTechValue >= item.getMinimumTechLevelToUse();
            if (regionCanBuy) {
                buyableItems.add(item);
            }
        }
        for (int i = 0;i< buyableItems.size(); i++) {
            for (int j = 0;j< region.sellableItems.size(); j++) {
                if (buyableItems.get(i).getName().equals(region.sellableItems.get(i).getName())) {
                    break;
                }
                if(j == region.sellableItems.size()){
                    fail("the item : " + buyableItems.get(i) + " was not found in the region");
                }
            }
        }
    }
}