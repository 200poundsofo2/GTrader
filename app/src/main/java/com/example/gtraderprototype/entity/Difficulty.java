package com.example.gtraderprototype.entity;

public enum Difficulty {
    Beginner(0),
    Easy(1),
    Normal(2),
    Hard(3),
    Impossible(4);

    int difficultyIndex;
    Difficulty(int level){
        this.difficultyIndex = level;
    }
    public int difficultyIndex(){
        return difficultyIndex;
    }
}
