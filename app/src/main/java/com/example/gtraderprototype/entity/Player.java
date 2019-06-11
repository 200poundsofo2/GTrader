package com.example.gtraderprototype.entity;

import java.util.HashMap;
import java.util.Map;

public class Player {
    public String name;
    public int difficulty;
    public Map<String, Double> skillSet = new HashMap<>();

    public Player(String name, int difficulty){
        this.name = name;
        this.difficulty = difficulty;
    }
    public void addSkill(String skillName, double skillValue){
        if(skillSet.get(skillName)!=null){
            skillSet.remove(skillName);
        }
        skillSet.put(skillName, skillValue);

    }

}

