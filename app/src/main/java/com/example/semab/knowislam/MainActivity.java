package com.example.semab.knowislam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    public static String serverPath = "192.168.1.4";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void getNextActtivity(View v){


        Intent intent = new Intent(this, MainList.class);
        startActivity(intent);

    }
}
