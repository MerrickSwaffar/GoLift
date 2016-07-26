package com.merrickswaffar.golift;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ViewProgress extends AppCompatActivity {

    CalendarView calendar;
    Button viewExercises, viewMeals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_progress);

        calendar = (CalendarView) findViewById(R.id.calendarView);

        viewMeals = (Button) findViewById(R.id.bMeals);
        viewExercises = (Button) findViewById(R.id.bExercises);

        View.OnClickListener onClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                String date = df.format(new Date(calendar.getDate()));
                switch (v.getId()){
                    case R.id.bMeals:
                        Intent i = new Intent(getApplicationContext(), ShowMeals.class);
                        i.putExtra("date", date);
                        startActivity(i);
                        break;
                    case R.id.bExercises:
                        Intent j = new Intent(getApplicationContext(), ShowExercises.class);
                        j.putExtra("date", date);
                        startActivity(j);
                }
            }
        };

        viewMeals.setOnClickListener(onClick);
        viewExercises.setOnClickListener(onClick);
    }
}
