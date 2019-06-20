package com.example.gtraderprototype.entity;

enum NaturalResource {

    water(30),furs(250),food(105),ore(390);
    //the basic credit, may vary according to tech level and circumstances

    private int credit;
    private String owner;
    NaturalResource(int credit){
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
}
