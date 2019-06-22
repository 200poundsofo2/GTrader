package com.example.gtraderprototype.entity;

enum IndustrialGood implements TradeGood{

    games(180),firearms(725),medicine(510),narcotics(2625),robots(3950 );
    //the basic credit, may vary according to tech level and circumstances

    private int credit;
    private String owner;

    IndustrialGood(int credit){
        this.credit = credit;
    }
    public int getCredit(){
        return credit;
    }
    public void setCredit(){
        this.credit = credit;
    }
    public String getOwner(){
        return owner;
    }
    public void setOwner(String owner){
        this.owner=owner;
    }

    @Override
    public void adjustCreditTechLevel(){
        System.out.println("to be implemented later");
    }

    @Override
    public void adjustCreditCircumstance(){
        System.out.println("to be implemented later");
    }
}
