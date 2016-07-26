package com.merrickswaffar.golift;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class ShowExercises extends AppCompatActivity {

    TextView text1, text2, text3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_exercises);

        text1 = (TextView) findViewById(R.id.text1);
        text2 = (TextView) findViewById(R.id.text2);
        text3 = (TextView) findViewById(R.id.text3);
        String date = getIntent().getExtras().getString("date");

        if (HomeScreen.user.days.containsKey(date) && HomeScreen.user.days.get(date).exercises.size() > 0){
            String output1 = "Exercises for " + date + ":";
            text1.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimaryDark));
            text1.setText(output1);

            String output2 = "";
            ArrayList<Exercise> eList = HomeScreen.user.days.get(date).exercises;
            int totalSets = 0;
            for (Exercise e: eList){
                output2 += "    " + e.name + ":\n";
                if (e.sets != 0)
                    output2 += "        Number of sets - " + e.sets + "\n";
                if (e.reps != 0)
                    output2 += "        Number of reps - " + e.reps + "\n";
                if (e.weight != 0)
                    output2 += "        Weight - " + e.weight + "\n";
                if (e.time != 0)
                    output2 += "        Time - " + e.time + "\n";

                totalSets += e.sets;
            }
            text2.setText(output2);

            String output3 = "";
            output3 += "Daily Totals:\n";
            output3 += "    Exercises - " + eList.size() + "\n";
            output3 += "    Sets - " + totalSets + "\n";

            text3.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimaryDark));
            text3.setText(output3);
        }else
            text1.setText("No exercises were entered on " + date + ".");
    }
}
