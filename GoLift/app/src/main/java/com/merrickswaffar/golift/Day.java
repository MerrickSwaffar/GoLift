package com.merrickswaffar.golift;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Merrick on 7/12/2016.
 */
public class Day {
    public int dailyHeight, dailyWeight;
    public List<Workout> workouts;
    public List<Meal> meals;

    Day() {
        workouts = new ArrayList<Workout>();
        meals = new ArrayList<Meal>();
    }
}
