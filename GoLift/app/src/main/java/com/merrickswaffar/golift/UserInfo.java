package com.merrickswaffar.golift;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class UserInfo extends AppCompatActivity {

    TextView height, weight, age, bodyType;
    Button update, quiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinfo);

        height = (TextView) findViewById(R.id.height);
        String userHeight = HomeScreen.user.getUserHeight();
        if (userHeight == null)
            height.append("unknown");
        else height.append(userHeight);

        weight = (TextView) findViewById(R.id.weight);
        int userWeight = HomeScreen.user.getUserWeight();
        if (userWeight < 1)
            weight.append("unknown");
        else weight.append(userWeight+"");

        age = (TextView) findViewById(R.id.age);
        int userAge = HomeScreen.user.getUserAge();
        if (userAge < 1)
            age.append("unknown");
        else age.append(userAge+"");

        bodyType = (TextView) findViewById(R.id.bodyType);
        User.BodyTypes btype = HomeScreen.user.getBodyType();
        if (btype == User.BodyTypes.notDetermined)
            bodyType.append("unknown");
        else bodyType.append(btype.toString());

        View.OnClickListener onClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.update:
                        startActivity(new Intent(getApplicationContext(), UpdateUserInfo.class));
                        break;
                    case R.id.quiz:
                        startActivity(new Intent(getApplicationContext(), TakeQuiz.class));
                }
            }
        };

        update = (Button) findViewById(R.id.update);
        update.setOnClickListener(onClick);

        quiz = (Button) findViewById(R.id.quiz);
        quiz.setOnClickListener(onClick);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(), HomeScreen.class));
    }
}
