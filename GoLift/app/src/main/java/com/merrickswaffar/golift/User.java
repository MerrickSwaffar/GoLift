package com.merrickswaffar.golift;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Merrick on 7/12/2016.
 */
public class User implements Serializable {
    public enum BodyTypes {notDetermined, ectomorph, mesomorph, endomorph}
    public BodyTypes bodyType;
    public HashMap<String, Day> days;
    public int userAge, userWeight;
    public String userHeight;

    public User(){
        days = new HashMap<>();
        bodyType = BodyTypes.notDetermined;
    }

    public void setBodyType(BodyTypes b){
        this.bodyType = b;
    }

    public static String getDate() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        String date = df.format(c.getTime());
        return date;
    }

    public void setUserHeight(String userHeight) {
        this.userHeight = userHeight;
    }

    public void setUserAge(int userAge) {
        this.userAge = userAge;
    }

    public void setUserWeight(int userWeight) {
        this.userWeight = userWeight;
    }
}
