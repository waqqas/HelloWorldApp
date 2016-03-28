package com.example.mbella.helloworldapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.taplytics.sdk.Taplytics;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Insert the following line of code to initialize the Taplytics SDK
        Taplytics.startTaplytics(this, "1d60abdc9953ab49c851cb247782ad98a8732482");


        setContentView(R.layout.activity_main);
    }
}
