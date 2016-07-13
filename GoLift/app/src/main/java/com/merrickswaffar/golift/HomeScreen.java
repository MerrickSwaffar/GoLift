package com.merrickswaffar.golift;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomeScreen extends AppCompatActivity {

    Button bWorkout, bMeals, bTraining, bNutrition, bProgress;
    TextView settings;
    View.OnClickListener onClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

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
                        startActivity(new Intent(getApplicationContext(), ViewProgress.class));
                        break;
                    case R.id.settings:
                        startActivity(new Intent(getApplicationContext(), Settings.class));
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

        settings = (TextView) findViewById(R.id.settings);
        settings.setOnClickListener(onClick);
    }
}
