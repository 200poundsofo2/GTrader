package com.example.gtraderprototype.entity;

public interface TradeGood { //can be traded
    //adjust credit according to the tech level
    public void adjustCreditTechLevel();

    //adjust credit according to the circumstances
    public void adjustCreditCircumstance();
    
}
