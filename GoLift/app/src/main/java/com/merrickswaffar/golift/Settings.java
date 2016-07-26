package com.merrickswaffar.golift;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Settings extends AppCompatActivity {

    TextView clearData, updateUserInfo, takeQuiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        View.OnClickListener onClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.clearUserData:
                        HomeScreen.clearUserData();
                        Toast.makeText(getApplicationContext(), "User data deleted", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.updateUserInfo:
                        startActivity(new Intent(getApplicationContext(), UpdateUserInfo.class));
                        break;
                    case R.id.takeQuiz:
                        startActivity(new Intent(getApplicationContext(), TakeQuiz.class));
                }
            }
        };

        clearData = (TextView) findViewById(R.id.clearUserData);
        clearData.setOnClickListener(onClick);

        updateUserInfo = (TextView) findViewById(R.id.updateUserInfo);
        updateUserInfo.setOnClickListener(onClick);

        takeQuiz = (TextView) findViewById(R.id.takeQuiz);
        takeQuiz.setOnClickListener(onClick);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(), HomeScreen.class));
    }
}
