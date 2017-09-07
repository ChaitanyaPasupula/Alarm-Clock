package com.example.chait.alarmcalender;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;


public class RingtoneService extends Service{

    MediaPlayer ringtone;
    boolean isRunning;
    int startId;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        Log.i("Local Service","Recevied start id"+ startId + ":"+intent);


           // initializing notification manager object to the notification service from the predefine methods.
       final NotificationManager notificationManager = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);

        // Create intent to trigger back to the second activity java class to off the alarm when it notification gets on
        Intent intent1 = new Intent(this.getApplicationContext(), SecondActivity.class);
        // pending intent creation to delay the intent until the alarm hits.
        PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent1, 0);

        // notification setting with attributes like title , text and auto cancel the notification when gets clicked.
        Notification notification  = new Notification.Builder(this)
                .setContentTitle("Alarm Ringing" + "!")
                .setContentText("Click me!")
                .setSmallIcon(R.drawable.notification_icon)
                .setContentIntent(pIntent)
                .setAutoCancel(true)
                .build();

        // get the extra data from intent from the alarm receiver class
        String string = intent.getExtras().getString("extra");

        // converting the extra string values of intents to startId boolean values.
        assert string != null;
        switch (string) {
            case "alarm on":
                startId = 1;
                break;
            case "alarm off":
                startId = 0;
                break;
            default:
                startId = 0;
                break;
        }

        //Conditional Statement if/else

        // if ringtone is not playing and the alarm on button is pressed then start playing ringtone
        if(!this.isRunning && startId == 1) {
            Log.e("if there was not sound ", " and you want start");

            ringtone = MediaPlayer.create(this,R.raw.games_of_thrones);
            ringtone.start();
           notificationManager.notify(0, notification);

            this.isRunning = true;
            this.startId = 0;

        }
        else if (!this.isRunning && startId == 0){
            Log.e("if there was not sound ", " and you want end");

            this.isRunning = false;
            this.startId = 0;

        }

        else if (this.isRunning && startId == 1){
            Log.e("if there is sound ", " and you want start");

            this.isRunning = true;
            this.startId = 0;

        }
        else {
            Log.e("if there is sound ", " and you want end");

            ringtone.stop();
            ringtone.reset();
            this.isRunning = false;
            this.startId = 0;
        }


        Log.e("MyActivity", "In the service");

           //START_NOT_STICKY says that, after returning from onStartCommand(), if the process is killed with no
        // remaining start commands to deliver, then the service will be stopped instead of restarted.
        return START_NOT_STICKY;
    }
    @Override
    public void onDestroy(){
        //Destroying the service

        super.onDestroy();
        this.isRunning = false;
    }
}
