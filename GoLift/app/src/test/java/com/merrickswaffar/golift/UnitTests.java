package com.merrickswaffar.golift;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import static org.junit.Assert.*;

public class UnitTests {

    //test the user class
    @Test
    public void userTest(){
        User u = new User();

        String height = "6\'3\"";
        int weight = 175;
        int age = 20;
        User.BodyTypes bodyType = User.BodyTypes.ectomorph;

        u.setUserHeight(height);
        u.setUserWeight(weight);
        u.setUserAge(age);
        u.setBodyType(bodyType);

        assertEquals(height, u.getUserHeight());
        assertEquals(weight, u.getUserWeight());
        assertEquals(age, u.getUserAge());
        assertEquals(bodyType, u.getBodyType());
    }

    //test the ability to add days, meals, and exercises for a user
    @Test
    public void dayTest() throws Exception {
        User u = new User();
        String date = User.getDate();
        Day d = new Day();
        Meal m = new Meal("lunch", 600, 43, 17, 50);
        Exercise e = new Exercise("Bench Press", 4, 6, 215, 10);
        d.addMeal(m);
        d.addExercise(e);

        u.days.put(date, d);

        assertTrue(u.days.containsKey(date));
        assertEquals(u.days.get(date), d);
        assertTrue(u.days.get(date).meals.contains(m));
        assertTrue(u.days.get(date).exercises.contains(e));
    }

    //test the ability to save and load user data, as well as the function that clears user data
    @Test
    public void saveAndClearUserDataTest(){
        User u = new User();
        String height = "6\'3\"";
        int weight = 175;
        int age = 20;
        User.BodyTypes bodyType = User.BodyTypes.ectomorph;

        u.setUserHeight(height);
        u.setUserWeight(weight);
        u.setUserAge(age);
        u.setBodyType(bodyType);

        assertEquals(height, u.getUserHeight());
        assertEquals(weight, u.getUserWeight());
        assertEquals(age, u.getUserAge());
        assertEquals(bodyType, u.getBodyType());

        try{
            FileOutputStream fileOut = new FileOutputStream("user");
            ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
            objOut.writeObject(u);
            fileOut.close();
            objOut.close();
        }catch(IOException e){
            e.printStackTrace();
        }

        u.clear();
        assertEquals(null, u.getUserHeight());
        assertEquals(0, u.getUserWeight());
        assertEquals(0, u.getUserAge());
        assertEquals(User.BodyTypes.notDetermined, u.getBodyType());

        try{
            FileInputStream fileIn = new FileInputStream("user");
            ObjectInputStream objIn = new ObjectInputStream(fileIn);
            u = (User) objIn.readObject();
            fileIn.close();
            objIn.close();
        }catch (Exception e) {
            u = new User();
        }

        assertEquals(height, u.getUserHeight());
        assertEquals(weight, u.getUserWeight());
        assertEquals(age, u.getUserAge());
        assertEquals(bodyType, u.getBodyType());
    }

    //test the ability to save and load user meals and exercises
    @Test
    public void saveDayTest(){
        User u = new User();
        String date = User.getDate();
        Day d = new Day();
        Meal m = new Meal("lunch", 600, 43, 17, 50);
        Exercise e = new Exercise("Bench Press", 4, 6, 215, 10);
        d.addMeal(m);
        d.addExercise(e);

        u.days.put(date, d);

        assertTrue(u.days.containsKey(date));
        assertEquals(u.days.get(date), d);
        assertTrue(u.days.get(date).meals.contains(m));
        assertTrue(u.days.get(date).exercises.contains(e));

        try{
            FileOutputStream fileOut = new FileOutputStream("user");
            ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
            objOut.writeObject(u);
            fileOut.close();
            objOut.close();
        }catch(IOException i){
            i.printStackTrace();
        }

        u.clear();
        assertTrue(u.days.isEmpty());

        try{
            FileInputStream fileIn = new FileInputStream("user");
            ObjectInputStream objIn = new ObjectInputStream(fileIn);
            u = (User) objIn.readObject();
            fileIn.close();
            objIn.close();
        }catch (Exception i) {
            u = new User();
        }

        assertTrue(u.days.containsKey(date));
        assertFalse(u.days.isEmpty());
        assertFalse(u.days.get(date).meals.isEmpty());
        assertFalse(u.days.get(date).exercises.isEmpty());
    }
}