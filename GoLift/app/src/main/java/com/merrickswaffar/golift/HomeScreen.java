package com.merrickswaffar.golift;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class HomeScreen extends AppCompatActivity {

    Button bWorkout, bMeals, bTraining, bNutrition, bProgress;
    NavigationView navigationView;
    View.OnClickListener onClick;
    public static User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        try{
            FileInputStream fileIn = openFileInput("userData");
            ObjectInputStream objIn = new ObjectInputStream(fileIn);
            user = (User) objIn.readObject();
            fileIn.close();
            objIn.close();
        }catch (Exception e) {
            user = new User();
        }

        navigationView = (NavigationView)findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.settings:
                        startActivity(new Intent(getApplicationContext(), Settings.class));
                        break;

                    case R.id.userInfo:
                        startActivity(new Intent(getApplicationContext(), UserInfo.class));
                        break;
                    case R.id.history:
                        startActivity(new Intent(getApplicationContext(), ViewProgress.class));
                        break;
                }

                return false;
            }
        });

        onClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.logWorkout:
                        startActivity(new Intent(getApplicationContext(), WorkoutLog.class));
                        break;
                    case R.id.logMeals:
                        startActivity(new Intent(getApplicationContext(), MealLog.class));
                        break;
                    case R.id.training:
                        startActivity(new Intent(getApplicationContext(), ViewWorkouts.class));
                        break;
                    case R.id.nutrition:
                        startActivity(new Intent(getApplicationContext(), ViewNutrition.class));
                        break;
                    case R.id.progress:
                        startActivity(new Intent(getApplicationContext(), DayOverview.class));
                        break;
                }
            }
        };

        bWorkout = (Button) findViewById(R.id.logWorkout);
        bWorkout.setOnClickListener(onClick);

        bMeals = (Button) findViewById(R.id.logMeals);
        bMeals.setOnClickListener(onClick);

        bTraining = (Button) findViewById(R.id.training);
        bTraining.setOnClickListener(onClick);

        bNutrition = (Button) findViewById(R.id.nutrition);
        bNutrition.setOnClickListener(onClick);

        bProgress = (Button) findViewById(R.id.progress);
        bProgress.setOnClickListener(onClick);
    }

    @Override
    protected void onStop() {
        super.onStop();
        try{
            FileOutputStream fileOut = openFileOutput("userData", Context.MODE_PRIVATE);
            ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
            objOut.writeObject(user);
            fileOut.close();
            objOut.close();
        }catch(IOException e){
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "User data could not be saved", Toast.LENGTH_SHORT).show();
        }
    }
}
