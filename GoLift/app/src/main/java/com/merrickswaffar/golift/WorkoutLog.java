package com.merrickswaffar.golift;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class WorkoutLog extends AppCompatActivity {

    EditText etName, etSets, etReps, etWeight, etTime;
    Button enter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_log);

        etName = (EditText) findViewById(R.id.name);
        etSets = (EditText) findViewById(R.id.sets);
        etReps = (EditText) findViewById(R.id.reps);
        etWeight= (EditText) findViewById(R.id.weight);
        etTime= (EditText) findViewById(R.id.time);

        enter = (Button) findViewById(R.id.bAdd);
        enter.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V){
                String name = etName.getText().toString();
                if (name.length() < 1) {
                    Toast.makeText(getApplicationContext(), "Please Enter Name", Toast.LENGTH_SHORT).show();
                    return;
                }
                String sets = etSets.getText().toString();
                int s;
                if (sets.length() < 1)
                    s = 0;
                else
                    s = Integer.parseInt(sets);

                String reps = etReps.getText().toString();
                int r;
                if (reps.length() < 1)
                    r = 0;
                else
                    r = Integer.parseInt(reps);

                String weight = etWeight.getText().toString();
                int w;
                if (weight.length() < 1)
                    w = 0;
                else
                    w = Integer.parseInt(weight);

                String time = etTime.getText().toString();
                int t;
                if (time.length() < 1)
                    t = 0;
                else
                    t = Integer.parseInt(time);

                etName.setText("");
                etSets.setText("");
                etReps.setText("");
                etWeight.setText("");
                etTime.setText("");
                add(name, s, r, w, t);
            }
        });
    }

    public void add(String name, int sets, int reps, int weight, int time){
        String date = User.getDate();
        if (!HomeScreen.user.days.containsKey(date))
            HomeScreen.user.days.put(date, new Day());
        Exercise exercise = new Exercise(name, sets, reps, weight, time);
        HomeScreen.user.days.get(date).addExercise(exercise);
        Toast.makeText(getApplicationContext(),"Exercise Added: " + name, Toast.LENGTH_SHORT).show();
    }
}
