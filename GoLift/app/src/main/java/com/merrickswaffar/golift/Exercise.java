package com.merrickswaffar.golift;

import java.io.Serializable;

/**
 * Created by Merrick on 7/12/2016.
 */
public class Exercise implements Serializable{

    String name;
    int weight, sets, reps, time;

    public Exercise(String name, int sets, int reps, int weight, int time){
        this.name = name;
        this.sets = sets;
        this.reps = reps;
        this.weight = weight;
        this.time = time;
    }
}
