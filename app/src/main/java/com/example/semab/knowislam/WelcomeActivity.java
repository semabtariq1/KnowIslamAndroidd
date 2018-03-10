package com.example.semab.knowislam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class WelcomeActivity extends AppCompatActivity {
//kkaaaaaaaaaaaaaakksal
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    // to go on next activity from welcome screne on next button press
    public void welcomeNext(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        //update
    }
}
