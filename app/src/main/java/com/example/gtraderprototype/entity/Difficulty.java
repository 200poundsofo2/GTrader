package com.example.gtraderprototype.entity;

/**
 * Difficulty enum determines which difficulty the user decide to be in
 */
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

    /**
     * difficulty level the user chose
     * @return difficulty level
     */
    public int difficultyIndex(){
        return difficultyIndex;
    }
}
