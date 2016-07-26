package com.merrickswaffar.golift;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class UpdateUserInfo extends AppCompatActivity {

    Button update;
    EditText etFeet, etInches, etWeight, etAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user_info);

        etFeet = (EditText) findViewById(R.id.feet);
        etInches = (EditText) findViewById(R.id.inches);
        etWeight = (EditText) findViewById(R.id.weight);
        etAge = (EditText) findViewById(R.id.age);

        update = (Button) findViewById(R.id.bUpdate);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String feet = etFeet.getText().toString();
                String inches = etInches.getText().toString();
                if (feet.length() > 0) {
                    if (inches.length() < 1) inches = "0";
                    String height = feet + "\'" + inches + "\"";
                    HomeScreen.user.setUserHeight(height);
                }

                String weight = etWeight.getText().toString();
                if (weight.length() > 0) {
                    int w = Integer.parseInt(weight);
                    HomeScreen.user.setUserWeight(w);
                }

                String age = etAge.getText().toString();
                if (weight.length() > 0) {
                    int a = Integer.parseInt(age);
                    HomeScreen.user.setUserAge(a);
                }

                Toast.makeText(getApplicationContext(),"User information updated", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), UserInfo.class));
            }
        });
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
}
