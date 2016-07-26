package com.merrickswaffar.golift;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class MealLog extends AppCompatActivity {

    EditText etName, etCalories, etProtein, etFat, etCarbs;
    Button enter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_log);

        etName = (EditText) findViewById(R.id.name);
        etCalories = (EditText) findViewById(R.id.calories);
        etProtein = (EditText) findViewById(R.id.protein);
        etFat = (EditText) findViewById(R.id.fat);
        etCarbs = (EditText) findViewById(R.id.carbs);

        enter = (Button) findViewById(R.id.bAddMeal);
        enter.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V){
                String name = etName.getText().toString();
                if (name.length() < 1) {
                    Toast.makeText(getApplicationContext(), "Please Enter Name", Toast.LENGTH_SHORT).show();
                    return;
                }
                String calories = etCalories.getText().toString();
                int cal;
                if (calories.length() < 1)
                    cal = 0;
                else
                    cal = Integer.parseInt(calories);

                String protein = etProtein.getText().toString();
                int p;
                if (protein.length() < 1)
                    p = 0;
                else
                    p = Integer.parseInt(protein);

                String fat = etFat.getText().toString();
                int f;
                if (fat.length() < 1)
                    f = 0;
                else
                    f = Integer.parseInt(fat);

                String carbs = etCarbs.getText().toString();
                int c;
                if (carbs.length() < 1)
                    c = 0;
                else
                    c = Integer.parseInt(carbs);

                etName.setText("");
                etCalories.setText("");
                etProtein.setText("");
                etFat.setText("");
                etCarbs.setText("");
                add(name, cal, p, f, c);
            }
        });
    }

    public void add(String name, int calories, int protein, int fat, int carbs){
        String date = User.getDate();
        if (!HomeScreen.user.days.containsKey(date))
            HomeScreen.user.days.put(date, new Day());
        Meal meal = new Meal(name, calories, protein, fat, carbs);
        HomeScreen.user.days.get(date).addMeal(meal);
                Toast.makeText(getApplicationContext(),"Meal Added: " + name, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        try{
            FileOutputStream fileOut = openFileOutput("userData", Context.MODE_PRIVATE);
            ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
            objOut.writeObject(HomeScreen.user);
            fileOut.close();
            objOut.close();
        }catch(IOException e){
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "User data could not be saved", Toast.LENGTH_SHORT).show();
        }
    }
}
