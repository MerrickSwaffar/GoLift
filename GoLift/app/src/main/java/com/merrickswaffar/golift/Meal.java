package com.merrickswaffar.golift;

import java.io.Serializable;

/**
 * Created by Merrick on 7/12/2016.
 */
public class Meal implements Serializable{
    String name;
    int calories, protein, fat, carbs;

    public Meal(String name, int calories, int protein, int fat, int carbs){
        this.name = name;
        this.calories = calories;
        this.protein = protein;
        this.fat = fat;
        this.carbs = carbs;
    }
}
