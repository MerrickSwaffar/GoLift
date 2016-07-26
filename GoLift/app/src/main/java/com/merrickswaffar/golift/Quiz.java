package com.merrickswaffar.golift;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Quiz extends AppCompatActivity implements View.OnClickListener{

    TextView question;
    Button a, b, c;
    public static int ecto, meso, endo, qNumber;

    static final String[][] questions =
            {{"1. My shoulders Are:", "Narrower than my hips", "The same width as my hips", "Wider than my hips"},
             {"2. What does your body tend to do?", "Stay skinny", "Stay lean", "Stay heavier"},
             {"3. How does your body look?", "Mostly Straight", "Like an hourglass", "Pear-shaped"},
             {"4. How do you gain weight?", "I have a difficult time gaining weight", "I have an average time gaining weight", "I gain weight easily"},
             {"5. How do you lose weight?", "I lose weight easily", "I have an average time losing weight", "I have a difficult time losing weight"},
             {"6. How is your bone structure?", "Thin", "Medium", "Thick"},
             {"7. What were you like as a child (ages 9-12)?", "Thin", "Average", "Chubby"},
             {"8. How often are you hungry?", "Rarely", "Sometimes", "All the time"},
             {"9. How do you gain muscle?", "I have a difficult time gaining muscle", "I have an average time gaining muscle", "I gain muscle easily"},
             {"10. If I wrap my hand around my wrist, my thumb and middle finger:", "Don't touch", "Just touch", "Overlap"}
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        ecto=0; meso = 0; endo = 0; qNumber = 0;

        question = (TextView) findViewById(R.id.question);
        a = (Button) findViewById(R.id.aButton);
        b = (Button) findViewById(R.id.bButton);
        c = (Button) findViewById(R.id.cButton);

        question.setText(questions[qNumber][0]);
        a.setText(questions[qNumber][1]);
        b.setText(questions[qNumber][2]);
        c.setText(questions[qNumber][3]);
        qNumber++;

        a.setOnClickListener(this);
        b.setOnClickListener(this);
        c.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.aButton:
                ecto++;
                break;
            case R.id.bButton:
                meso++;
                break;
            case R.id.cButton:
                endo++;
                break;
        }
        if (qNumber > 9){
            if(ecto > meso && ecto > endo) HomeScreen.user.setBodyType(User.BodyTypes.ectomorph);
            else if(endo > meso && endo > ecto) HomeScreen.user.setBodyType(User.BodyTypes.endomorph);
            else HomeScreen.user.setBodyType(User.BodyTypes.mesomorph);
            startActivity(new Intent(getApplicationContext(), QuizResults.class));
            return;
        }
        question.setText(questions[qNumber][0]);
        a.setText(questions[qNumber][1]);
        b.setText(questions[qNumber][2]);
        c.setText(questions[qNumber][3]);
        qNumber++;
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
