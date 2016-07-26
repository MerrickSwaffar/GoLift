package com.merrickswaffar.golift;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class DayOverview extends AppCompatActivity {

    TextView text1, text2, text3, text4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_overview);

        text1 = (TextView) findViewById(R.id.text1);
        text2 = (TextView) findViewById(R.id.text2);
        text3 = (TextView) findViewById(R.id.text3);
        text4 = (TextView) findViewById(R.id.text4);

        String date = User.getDate();

        if (HomeScreen.user.days.containsKey(date) && HomeScreen.user.days.get(date).meals.size() > 0){
            String output1 = "Nutrition summary:";
            text1.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimaryDark));
            text1.setText(output1);

            int totalCals = 0;
            int totalProtein = 0;
            int totalFat = 0;
            int totalCarbs = 0;
            ArrayList<Meal> mealList = HomeScreen.user.days.get(date).meals;
            for (Meal m: mealList){
                totalCals += m.calories;
                totalProtein += m.protein;
                totalFat += m.fat;
                totalCarbs += m.carbs;
            }

            float totalGrams = totalCarbs + totalFat + totalProtein;
            int proteinPercentage = Math.round((totalProtein/totalGrams) * 100);
            int fatPercentage = Math.round((totalFat/totalGrams) * 100);
            int carbPercentage = Math.round((totalCarbs/totalGrams) * 100);

            String output2 = "";
            output2 += "    Calories - " + totalCals + "\n";
            output2 += "    Protein - " + totalProtein + " Grams (" + proteinPercentage + "%)\n";
            output2 += "    Fat - " + totalFat + " Grams (" + fatPercentage + "%)\n";
            output2 += "    Carbohydrates - " + totalCarbs + " Grams (" + carbPercentage + "%)\n";

            text2.setText(output2);
        }else
            text1.setText("No meals have been entered today.\n");

        if (HomeScreen.user.days.containsKey(date) && HomeScreen.user.days.get(date).exercises.size() > 0){
            String output1 = "Exercise summary:";
            text3.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimaryDark));
            text3.setText(output1);

            String output2 = "";
            ArrayList<Exercise> eList = HomeScreen.user.days.get(date).exercises;
            for (Exercise e: eList){
                output2 += "    " + e.name + ":\n";
                if (e.sets != 0)
                    output2 += "        Number of sets - " + e.sets + "\n";
                if (e.reps != 0)
                    output2 += "        Number of reps per set - " + e.reps + "\n";
                if (e.weight != 0)
                    output2 += "        Weight - " + e.weight + "\n";
                if (e.time != 0)
                    output2 += "        Time - " + e.time + "\n";
            }
            text4.setText(output2);
        }else
            text3.setText("No exercises have been entered today.");
    }
}
