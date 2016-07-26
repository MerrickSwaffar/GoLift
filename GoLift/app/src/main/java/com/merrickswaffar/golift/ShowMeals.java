package com.merrickswaffar.golift;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ShowMeals extends AppCompatActivity {

    TextView text1, text2, text3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_meals);

        text1 = (TextView) findViewById(R.id.text1);
        text2 = (TextView) findViewById(R.id.text2);
        text3 = (TextView) findViewById(R.id.text3);
        String date = getIntent().getExtras().getString("date");

        if (HomeScreen.user.days.containsKey(date) && HomeScreen.user.days.get(date).meals.size() > 0){
            String output1 = "Meals for " + date + ":";
            text1.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimaryDark));
            text1.setText(output1);

            String output2 = "";
            int totalCals = 0;
            int totalProtein = 0;
            int totalFat = 0;
            int totalCarbs = 0;
            ArrayList<Meal> mealList = HomeScreen.user.days.get(date).meals;
            for (Meal m: mealList){
                output2 += "    " + m.name + ":\n";
                output2 += "        Calories - " + m.calories + "\n";
                output2 += "        Protein - " + m.protein + " Grams\n";
                output2 += "        Fat - " + m.fat + " Grams\n";
                output2 += "        Carbohydrates - " + m.carbs + " Grams\n";

                totalCals += m.calories;
                totalProtein += m.protein;
                totalFat += m.fat;
                totalCarbs += m.carbs;
            }
            text2.setText(output2);

            float totalGrams = totalCarbs + totalFat + totalProtein;
            int proteinPercentage = Math.round((totalProtein/totalGrams) * 100);
            int fatPercentage = Math.round((totalFat/totalGrams) * 100);
            int carbPercentage = Math.round((totalCarbs/totalGrams) * 100);

            String output3 = "";
            output3 += "Daily Totals:\n";
            output3 += "    Calories - " + totalCals + "\n";
            output3 += "    Protein - " + totalProtein + " Grams (" + proteinPercentage + "%)\n";
            output3 += "    Fat - " + totalFat + " Grams (" + fatPercentage + "%)\n";
            output3 += "    Carbohydrates - " + totalCarbs + " Grams (" + carbPercentage + "%)\n";

            text3.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimaryDark));
            text3.setText(output3);
        }else
            text1.setText("No Meals Were Entered on " + date + ".");
    }
}
