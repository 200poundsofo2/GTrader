package com.example.gtraderprototype.entity;

/**
 * Difficulty enum determines which difficulty the user decide to be in
 */
public enum Difficulty {
    Beginner(),
    Easy(),
    Normal(),
    Hard(),
    Impossible();

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
