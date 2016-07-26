package com.merrickswaffar.golift;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ViewWorkouts extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //depending on how body type is determined from quiz
        switch (HomeScreen.user.bodyType) {
             case ectomorph:
                setContentView(R.layout.activity_view_workouts_ectomorph);
                break;
             case mesomorph:
                setContentView(R.layout.activity_view_workouts_mesomorph);
                 break;
             case endomorph:
                setContentView(R.layout.activity_view_workouts_endomorph);
                 break;
             //goes to quiz if body type not determined
             default:
                startActivity(new Intent(getApplicationContext(), TakeQuiz.class));

        }
    }
}