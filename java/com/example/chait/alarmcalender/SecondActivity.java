package com.example.chait.alarmcalender;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;


public class SecondActivity extends AppCompatActivity {

   AlarmManager alarmManager;
    private TimePicker timePicker;
   private TextView textUpdate;
    Context context;
    private PendingIntent pendingIntent;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        this.context = this;
        // getting the alarmmanager service
        alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
        // initialize timepicker
        timePicker = (TimePicker) findViewById(R.id.timePicker);
        // initialize textview
        textUpdate = (TextView) findViewById(R.id.textUpdate);
        // creating the instance of calender
        final Calendar calendar = Calendar.getInstance();
        // buttons initialing
        Button alarm_on = (Button) findViewById(R.id.alarm_on);
         // intent to trigger to alarm receiver class with context
        final Intent intent = new Intent(this.context,AlarmReceiver.class);
        //setting the onclicklistener to buttons
        alarm_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  //setting the calender instance with hour and minute that we picked from timepicker
                calendar.set(Calendar.HOUR_OF_DAY,timePicker.getHour());
                calendar.set(Calendar.MINUTE,timePicker.getMinute());
                // getting the values of hour and minute into strings
                String hour = String.valueOf(timePicker.getHour());
                String minute = String.valueOf(timePicker.getMinute());
                // changing time into 12 hour format
                if(timePicker.getHour()>12){
                    hour = String.valueOf(timePicker.getHour()-12);
                }
                if(timePicker.getMinute() <10){
                    minute = "0"+String.valueOf(timePicker.getMinute());
                }

                //put extra string in the intent to indicate that you pressed alarm on button
                intent.putExtra("extra","alarm on");

                 // Creating the pending intent
                pendingIntent = PendingIntent.getBroadcast(SecondActivity.this,0,
                        intent,PendingIntent.FLAG_UPDATE_CURRENT);

                 // set the alarm manager
                alarmManager.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);

                textUpdate.setText("Alarm set to "+ hour  +":"+ minute);

            }
        });

        Button alarm_off = (Button) findViewById(R.id.alarm_off);

        alarm_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //put extra string in the intent to indicate that you pressed alarm off button
                intent.putExtra("extra","alarm off");
                //Stop the alarm by broadcasting
                sendBroadcast(intent);
                //Cancel the alarm
                try {
                    alarmManager.cancel(pendingIntent);
                }catch (NullPointerException e){
                    e.getMessage();
                }
                textUpdate.setText("Alarm Off");
            }
        });

    }
}
