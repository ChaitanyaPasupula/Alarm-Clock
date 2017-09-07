package com.example.chait.alarmcalender;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;


public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("We are in the Receiver","Superrr!");

        // get the extra data from the intents

        String string = intent.getExtras().getString("extra");

        //intent creation to the ringtone service
        Intent serviceIntent = new Intent(context,RingtoneService.class);

        //pass the extra strings to Ringtone service class

        serviceIntent.putExtra("extra",string);

        context.startService(serviceIntent);
    }
}
