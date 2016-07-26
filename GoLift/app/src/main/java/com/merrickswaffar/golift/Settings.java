package com.merrickswaffar.golift;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

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
                        HomeScreen.user.clear();
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
        startActivity(new Intent(getApplicationContext(), HomeScreen.class));
    }
}
