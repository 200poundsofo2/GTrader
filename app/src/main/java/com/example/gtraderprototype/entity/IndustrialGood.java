package com.example.gtraderprototype.entity;

import java.util.HashSet;
import java.util.Random;

enum TradeGood{

    water(30,0,0,3,4,30,50,Condition.DROUGHT, Condition.LOTSOFWATER,Condition.DESERT),
    furs(250,0,0,10,10, 230, 280, Condition.COLD, Condition.RICHFAUNA,Condition.LIFELESS),
    food(100,1,0,5,5,90,160, Condition.CROPFAIL,Condition.RICHSOIL,Condition.POORSOIL),
    ore(350,2,2,20,10,350,420,Condition.WAR,Condition.MINERALRICH,Condition.MINERALPOOR),

    games(250,3,1,-10,5,160,270,Condition.BOREDOM,Condition.ARTISTIC,null),
    firearms(1250,3,1,-75,100,600,1100,Condition.WAR,Condition.WARLIKE,null),
    medicine(650,4,1,-20,10,400,700,Condition.PLAGUE, Condition.LOTSOFHERBS,null),
    machines(900,4,3,-30,5,600,800,Condition.LACKOFWORKERS,null,null),
    narcotics(3500,5,0,-125,150,2000,3000,Condition.BOREDOM,Condition.WEIRDMUSHROOMS,null),
    robots(5000,6,4,-150,100,3500,5000,Condition.LACKOFWORKERS,null,null );
    //the basic credit, may vary according to tech level and circumstances

    private int basePrice; //the basic credit, may vary according to tech level and circumstances
    private String owner;
    private int MTLP; //Minimum Tech Level to Produce this resource
    private int MTLU; //Minimum Tech Level to Use this resource
    private int IPL; //Price increase per tech level
    private int Var; //variance is the maximum percentage that the price can vary above or below the base
    private int MTL; //Min price offered in space trade with random trader (not on a region)
    private int MTH; //Max price offered in space trade with random trader (not on a region)
    private Condition IE; // Radical price increase event
    private Condition CR; //condition is present, the price of this resource is unusually low
    private Condition ER; //this condition is present, the resource is expensive

    TradeGood(int basePrice, int MTLP, int MTLU, int IPL, int Var, int MTL, int MTH, Condition IE, Condition CR, Condition ER){
        this.basePrice = basePrice;
        this.MTLP = MTLP;
        this.MTLU = MTLU;
        this.IPL = IPL;
        this.Var=Var;
        this.MTL = MTL;
        this.MTH = MTH;
        this.IE = IE;
        this.CR =CR;
        this.ER=ER;
    }
    public int getbasePrice(){
        return basePrice;
    }
    public String getOwner(){
        return owner;
    }
    public void setOwner(String owner){
        this.owner=owner;
    }

    public int getRegionPrice(HashSet<Condition> conditionSet, TechLevel techLevel){
        int price = basePrice+IPL*(techLevel.getLevel()-MTLP);
        Random rand=new Random();
        int variance=rand.nextInt(Var+1)*basePrice;
        if(rand.nextBoolean()){
            price+=variance;
        }else{
            price-=variance;
        }
        if(conditionSet.contains(IE)){
            price*=1.2;
        }
        if(conditionSet.contains(CR)){
            price*=0.8;
        }
        if(conditionSet.contains(ER)){
            price*=4;
        }
        return price;
    }

    public int getRandomTraderPrice(){
        Random random=new Random();
        return MTL+random.nextInt(MTH+1);
    }
}
