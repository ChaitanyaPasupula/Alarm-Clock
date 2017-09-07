package com.example.chait.alarmcalender;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // we link the java class to the xml UI layout here.
        setContentView(R.layout.activity_main);

        // button object created with name of alarm clock by pulling id from alarmclock
        Button alarmclock = (Button) findViewById(R.id.alarmclock);
        // button object created with name of calendar by pulling id from alarmclock
        Button calendar = (Button) findViewById(R.id.calendar);

        // on click of the alarmclock button the following method gets executed and the intent triggers
        // to SecondActivity java class
        alarmclock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // intent gets created
                Intent i=new Intent(MainActivity.this, SecondActivity.class);
                startActivity(i);
            }
        });

         // on click of the calendar button the following method gets executed and the intent triggers to
        // FirstActivity java class

        calendar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FirstActivity.class));
            }
        });
    }

}
