package com.example.gtraderprototype.entity;

import java.util.Random;

/**
 * item enum that initialize all the items
 */
public enum Item {
    Water("Water", 0, 0	, 2,	 30, 3,	4, RegionBasedEvent.DROUGHT, Resources.LOTSOFWATER, Resources.DESERT,30, 50),
    Furs("Furs", 0, 0	, 0,	 250, 10,	10, RegionBasedEvent.COLD, Resources.RICHFAUNA, Resources.LIFELESS,230, 280),
    Food("Food", 1, 0	, 1,	 100, 5,	5, RegionBasedEvent.CROPFAIL, Resources.RICHSOIL, Resources.POORSOIL,90, 160),
    Ore("Ore", 2, 2	, 3,	 350, 20,	10, RegionBasedEvent.WAR, Resources.MINERALRICH, Resources.MINERALPOOR,350, 420),
    Games("Games", 3, 1	, 6,	 250, -10,	5, RegionBasedEvent.BOREDOM, Resources.ARTISTIC, Resources.NotApplicable,160, 270),
    Firearms("Firearms", 3, 1	, 5,	 1250, -75,	100, RegionBasedEvent.WAR, Resources.WARLIKE, Resources.NotApplicable,600, 1100),
    Medicine("Medicine", 4, 1	, 6,	 650, -20,	10, RegionBasedEvent.PLAGUE, Resources.LOTSOFHERBS, Resources.NotApplicable,400, 700),
    Machines("Machines", 4, 3	, 5,	 900, -30,	5, RegionBasedEvent.LACKOFWORKERS, Resources.NotApplicable, Resources.NotApplicable,600, 800),
    Narcotics("Narcotics", 5, 0	, 5,	 3500, -125,	150, RegionBasedEvent.BOREDOM, Resources.WEIRDMUSHROOMS, Resources.NotApplicable,2000, 3000),
    Robots("Robots", 6, 4	, 7,	 5000, -150,	100, RegionBasedEvent.LACKOFWORKERS, Resources.NotApplicable, Resources.NotApplicable,3500, 5000);
    private String name;
    private int minimumTechLevelToProduce;
    private int minimumTechLevelToUse;
    private int techProductionLevel;
    private int basePrice;
    private int priceIncreasePerTechLevel;
    private int variance;
    private RegionBasedEvent regionPriceIncreaseEvent;
    private Resources conditionForPriceIncrease;
    private Resources conditionForPriceDecrease;
    private int minimumPrice;
    private int maximumPrice;
    private int regionPrice;
      Item(String name, int minimumTechLevelToProduce, int minimumTechLevelToUse, int techProductionLevel, int basePrice, int priceIncreasePerTechLevel, int variance, RegionBasedEvent regionPriceIncreaseEvent, Resources conditionForPriceIncrease, Resources conditionForPriceDecrease, int minimumPrice, int maximumPrice){
        this.name = name;
        this.minimumTechLevelToProduce = minimumTechLevelToProduce;
        this.minimumTechLevelToUse = minimumTechLevelToUse;
        this.techProductionLevel = techProductionLevel;
        this.basePrice = basePrice;
        this.priceIncreasePerTechLevel = priceIncreasePerTechLevel;
        this.variance = variance;
        this.regionPriceIncreaseEvent = regionPriceIncreaseEvent;
        this.conditionForPriceIncrease = conditionForPriceIncrease;
        this.conditionForPriceDecrease = conditionForPriceDecrease;
        this.minimumPrice = minimumPrice;
        this.maximumPrice = maximumPrice;
    }

    /**
     * getting name of the item
     * @return name of the item
     */
    public String getName() {
        return name;
    }
    /**
     * getting the minimum tech level to produce this item
     * @return minimum tech level
     */
    public int getMinimumTechLevelToProduce() {
        return minimumTechLevelToProduce;
    }
    /**
     * getting the minimum tech level to use this item
     * @return minimum tech level
     */
    public int getMinimumTechLevelToUse() {
        return minimumTechLevelToUse;
    }
    /**
     * getting the tech level of production
     * @return tech level
     */
    public int getTechProductionLevel() {
        return techProductionLevel;
    }

    /**
     * getting the base price of an item, which fluctuate based on region
     * @return base price
     */
    public int getBasePrice() {
        return basePrice;
    }

    /**
     * getting the fluctuated price of the item due to region tech level
     * @return increased price
     */
    public int getPriceIncreasePerTechLevel() {
        return priceIncreasePerTechLevel;
    }

    /**
     * variance of the item
     * @return variance
     */
    public int getVariance() {
        return variance;
    }

    /**
     * event that's based on the region, which increase price of an item
     * @return increased amount of price
     */
    public RegionBasedEvent getRegionPriceIncreaseEvent() {
        return regionPriceIncreaseEvent;
    }

    /**
     * price change due to resources scarcity
     * @return increased amount of price
     */
    public Resources getConditionForPriceIncrease() {
        return conditionForPriceIncrease;
    }
    /**
     * price change due to resources abundance
     * @return decreased amount of price
     */
    public Resources getConditionForPriceDecrease() {
        return conditionForPriceDecrease;
    }

    /**
     * getting the minimum price of an item
     * @return the minimum price
     */
    public int getMinimumPrice() {
        return minimumPrice;
    }
    /**
     * getting the max price of an item
     * @return the max price
     */
    public int getMaximumPrice() {
        return maximumPrice;
    }

    /**
     * setting how the price of an item fluctuate based on region
     * @param techLevel tech level of the region
     * @param regionResource resources of the region
     * @param regionEvent event of the region
     */
    public void setRegionPrice(int techLevel, Resources regionResource, RegionBasedEvent regionEvent){
        Random rand = new Random();
        int resultingVariance = rand.nextInt(variance);
        regionPrice = basePrice + priceIncreasePerTechLevel * ( techLevel - minimumTechLevelToProduce) + (int)((30 *.01*resultingVariance) + .5);
        if(conditionForPriceIncrease.getResourceLevel() == regionResource.getResourceLevel()) {
            regionPrice = (int)(regionPrice * 1.5);
        }else if(conditionForPriceDecrease.getResourceLevel() == regionResource.getResourceLevel()) {
            regionPrice = (int)(regionPrice * .5);
        }
         if(regionEvent.getRegionEvent() == regionPriceIncreaseEvent.getRegionEvent()){
             regionPrice = (int)(regionPrice * 5);
         }
     }

    /**
     * getting the price of an item in a region
     * @return price of the item
     */
    public int getRegionPrice(){ return regionPrice; }
}
