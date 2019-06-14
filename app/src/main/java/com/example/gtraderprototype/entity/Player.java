package com.example.gtraderprototype.entity;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class Player { //singleton design pattern: Player.getPlayer()
    public static  volatile Player player = null;

    private String name;
    private int difficulty;
    private Map<String, Double> skillSet = new HashMap<>();
    private List<String> Inventory;
    private boolean isPirate;
    private int money;
    private String spaceship;
    private String currentLocation;


    private Player(){}
    public static Player getPlayer(){
        if (player == null){
            synchronized (Player.class){
                if(player == null){
                    player = new Player();
                }
            }
        }
        return player;
    }

    public void addSkill(String skillName, double skillValue){
        if(getPlayer().skillSet.get(skillName)!=null){
            getPlayer().skillSet.remove(skillName);
        }
        getPlayer().skillSet.put(skillName, skillValue);
    }
    public Map<String, Double> getSkills(){
        return getPlayer().skillSet;
    }

    public void setName(String name) {
        getPlayer().name = name;
    }
    public String getName(){
        return getPlayer().name;
    }

    public void setDifficulty(int d) {
        getPlayer().difficulty = d;
    }
    public int getDifficulty(){
        return getPlayer().difficulty;
    }
    public void setPirate(boolean pirate){
        getPlayer().isPirate=pirate;
    }
    public boolean checkIsPirate(){
        return getPlayer().isPirate;
    }



}

