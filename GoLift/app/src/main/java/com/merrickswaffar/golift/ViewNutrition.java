package com.merrickswaffar.golift;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ViewNutrition extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        switch (HomeScreen.user.bodyType) {
             case ectomorph:
                 setContentView(R.layout.activity_view_nutrition_ectomorph);
                 break;
             case mesomorph:
                 setContentView(R.layout.activity_view_nutrition_mesomorph);
                 break;
             case endomorph:
                 setContentView(R.layout.activity_view_nutrition_endomorph);
                 break;
             //goes to quiz if body type not determined
             default:
                 startActivity(new Intent(getApplicationContext(), TakeQuiz.class));
        }
    }

}
