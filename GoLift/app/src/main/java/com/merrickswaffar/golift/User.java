package com.merrickswaffar.golift;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Merrick on 7/12/2016.
 */
public class User implements Serializable {
    public String bodyType;
    public List<Day> days;

    User(){
        days = new ArrayList<Day>();
    }
}
