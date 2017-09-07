package com.example.chait.alarmcalender;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.CalendarView;
import android.widget.Toast;



public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // we link the java class to the xml UI layout here.
        setContentView(R.layout.activity_first);

       // CalendarView object created on using the widget id calendarView and object named calendar.
        CalendarView calendar = (CalendarView) findViewById(R.id.calendarView);

        // when you get clicked on the calendar on a certain date the following method gets executed
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                //months in android start from 0 to 11, so we add +1 to month that slove the problem and store the date in string.
                //to display in the toast.
                String date = (month+1)+"/"+dayOfMonth+"/"+year;
                //Toast can be used to display information for the short period of time. Here the date you clicked is toasted
                Toast.makeText(FirstActivity.this," "+date,Toast.LENGTH_SHORT).show();
            }
        });


    }
}
