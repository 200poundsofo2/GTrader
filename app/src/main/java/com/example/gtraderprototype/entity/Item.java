package com.example.gtraderprototype.entity;

import java.util.Random;

public enum Item {
    Water("Water", 0, 0	, 2,
            30, 3,	4, RegionBasedEvent.DROUGHT,
            Resources.LOTSOFWATER, Resources.DESERT,30, 50),
    Furs("Furs", 0, 0	, 0,
            250, 10,	10, RegionBasedEvent.COLD,
            Resources.RICHFAUNA, Resources.LIFELESS,230, 280),
    Food("Food", 1, 0	, 1,
            100, 5,	5, RegionBasedEvent.CROPFAIL,
            Resources.RICHSOIL, Resources.POORSOIL,90, 160),
    Ore("Ore", 2, 2	, 3,
            350, 20,	10, RegionBasedEvent.WAR,
            Resources.MINERALRICH, Resources.MINERALPOOR,350, 420),
    Games("Games", 3, 1	, 6,
            250, -10,	5, RegionBasedEvent.BOREDOM,
            Resources.ARTISTIC, Resources.NotApplicable,160, 270),
    Firearms("Firearms", 3, 1	, 5,
            1250, -75,	100, RegionBasedEvent.WAR,
            Resources.WARLIKE, Resources.NotApplicable,600, 1100),
    Medicine("Medicine", 4, 1	, 6,
            650, -20,	10, RegionBasedEvent.PLAGUE,
            Resources.LOTSOFHERBS, Resources.NotApplicable,400, 700),
    Machines("Machines", 4, 3	,
            5,	 900, -30,	5,
            RegionBasedEvent.LACKOFWORKERS, Resources.NotApplicable, Resources.NotApplicable,
            600, 800),
    Narcotics("Narcotics", 5, 0	, 5,
            3500, -125,	150, RegionBasedEvent.BOREDOM,
            Resources.WEIRDMUSHROOMS, Resources.NotApplicable,2000, 3000),
    Robots("Robots", 6, 4	, 7,
            5000, -150,	100, RegionBasedEvent.LACKOFWORKERS,
            Resources.NotApplicable, Resources.NotApplicable,3500, 5000);
    private final String name;
    private final int minimumTechLevelToProduce;
    private final int minimumTechLevelToUse;
    private final int techProductionLevel;
    private final int basePrice;
    private final int priceIncreasePerTechLevel;
    private final int variance;
    private final RegionBasedEvent regionPriceIncreaseEvent;
    private final Resources conditionForPriceIncrease;
    private final Resources conditionForPriceDecrease;
    private final int minimumPrice;
    private final int maximumPrice;
    private int regionPrice;
      Item(String name, int minimumTechLevelToProduce, int minimumTechLevelToUse,
           int techProductionLevel, int basePrice, int priceIncreasePerTechLevel,
           int variance, RegionBasedEvent regionPriceIncreaseEvent,
           Resources conditionForPriceIncrease, Resources conditionForPriceDecrease,
           int minimumPrice, int maximumPrice){
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
    public String getName() {
        return name;
    }

    public int getMinimumTechLevelToProduce() {
        return minimumTechLevelToProduce;
    }

    public int getMinimumTechLevelToUse() {
        return minimumTechLevelToUse;
    }

    public int getTechProductionLevel() {
        return techProductionLevel;
    }

    public int getBasePrice() {
        return basePrice;
    }

    public int getPriceIncreasePerTechLevel() {
        return priceIncreasePerTechLevel;
    }

    public int getVariance() {
        return variance;
    }

    public RegionBasedEvent getRegionPriceIncreaseEvent() {
        return regionPriceIncreaseEvent;
    }

    public Resources getConditionForPriceIncrease() {
        return conditionForPriceIncrease;
    }

    public Resources getConditionForPriceDecrease() {
        return conditionForPriceDecrease;
    }

    public int getMinimumPrice() {
        return minimumPrice;
    }

    public int getMaximumPrice() {
        return maximumPrice;
    }
    public void setRegionPrice(int techLevel,
                               Resources regionResource, RegionBasedEvent regionEvent){
        Random rand = new Random();
        int resultingVariance = rand.nextInt(variance);
        regionPrice = basePrice + (priceIncreasePerTechLevel *
                (techLevel - minimumTechLevelToProduce)) + (int) ((30 * .01 * resultingVariance) + .5);
        if(conditionForPriceIncrease.getResourceLevel() == regionResource.getResourceLevel()) {
            regionPrice = (int)(regionPrice * 1.5);
        }else if(conditionForPriceDecrease.getResourceLevel() ==
                regionResource.getResourceLevel()) {
            regionPrice = (int)(regionPrice * .5);
        }
         if(regionEvent.getRegionEvent() == regionPriceIncreaseEvent.getRegionEvent()){
             regionPrice = regionPrice * 5;
         }
     }
    public int getRegionPrice(){ return regionPrice; }
}
