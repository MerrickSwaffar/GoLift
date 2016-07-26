package com.merrickswaffar.golift;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class QuizResults extends AppCompatActivity {

    TextView text2, text3;
    Button nutrition, excercise;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_results);

        text2 = (TextView) findViewById(R.id.text2);
        text3 = (TextView) findViewById(R.id.text3);

        String output = "\t" + Quiz.ecto*10 + "% ectomorph\n";
        output += "\t" + Quiz.meso*10 + "% mesomorph\n";
        output += "\t" + Quiz.endo*10 + "% endomorph\n";

        text2.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimaryDark));
        text2.setText(output);

        String output2 = "It appears as though your body type fits best into the ";
        output2 += HomeScreen.user.bodyType.toString() + " category. ";
        output2 += "To find out what this means, and to view nutrition or exercise suggestions, ";
        output2 += "chose one of the options below.";
        text3.setText(output2);

        View.OnClickListener onClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(v.getId()){
                    case R.id.nutrition:
                        startActivity(new Intent(getApplicationContext(), ViewNutrition.class));
                        break;
                    case R.id.exercise:
                        startActivity(new Intent(getApplicationContext(), ViewWorkouts.class));
                        break;
                }
            }
        };

        nutrition = (Button) findViewById(R.id.nutrition);
        nutrition.setOnClickListener(onClick);
        excercise = (Button) findViewById(R.id.exercise);
        excercise.setOnClickListener(onClick);

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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(), HomeScreen.class));
    }
}
