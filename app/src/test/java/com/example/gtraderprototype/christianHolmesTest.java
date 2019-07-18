package com.example.gtraderprototype;

import com.example.gtraderprototype.entity.Item;
import com.example.gtraderprototype.entity.TechLevel;
import com.example.gtraderprototype.entity.Region;

import java.util.ArrayList;

import static org.junit.Assert.fail;

class christianHolmesTest {
    public void testSellableItemsInArrayList() {
        Region region = new Region("sub system", 1.0, 1.0);
        Item[] items = Item.values();
        ArrayList<Item> sellableItems = new ArrayList<>();
        for (Item item : items) {
            int numericTechValue = region.getTechLevel().getTechLevel();
            boolean regionCanSell = numericTechValue >= item.getMinimumTechLevelToProduce();
            if (regionCanSell) {
                item.setRegionPrice(numericTechValue, region.getResources(), region.getRegionBasedEvent());
                sellableItems.add(item);
            }
        }
        for (int i = 0;i< sellableItems.size(); i++) {
            for (int j = 0;j< region.sellableItems.size(); j++) {
                if (sellableItems.get(i).equals(region.sellableItems.get(i))) {
                    break;
                }
                if(j == region.sellableItems.size()){
                    fail("the item : " + sellableItems.get(i) + " in teh sellable list was not found in the region");
                }
            }
        }

    }

    public void testBuyableItemsInArrayList() {
        Region region = new Region("sub system", 1.0, 1.0);
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
                if (buyableItems.get(i).equals(region.sellableItems.get(i))) {
                    break;
                }
                if(j == region.sellableItems.size()){
                    fail("the item : " + buyableItems.get(i) + " was not found in the region");
                }
            }
        }
    }
}
