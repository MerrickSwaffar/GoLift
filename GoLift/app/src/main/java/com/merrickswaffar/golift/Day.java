package com.merrickswaffar.golift;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Merrick on 7/12/2016.
 */
public class Day implements Serializable{
    public ArrayList<Exercise> exercises;
    public ArrayList<Meal> meals;

    public Day() {
        exercises = new ArrayList<Exercise>();
        meals = new ArrayList<Meal>();
    }

    public void addExercise(Exercise exercise){
        exercises.add(exercise);
    }

    public void addMeal(Meal meal){
        meals.add(meal);
    }
}
