package com.anbuilder.saqib.alarmclock;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.ToggleButton;

import java.util.Calendar;

import static android.media.RingtoneManager.TYPE_ALARM;

public class AlarmReceiver extends WakefulBroadcastReceiver {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(com.anbuilder.saqib.alarmclock.R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(com.anbuilder.saqib.alarmclock.R.id.toolbar);
        setSupportActionBar(toolbar);

        alarmTimePicker  = (TimePicker) findViewById(com.anbuilder.saqib.alarmclock.R.id.alarmTimePicker);
        alarmTextView = (TextView) findViewById(com.anbuilder.saqib.alarmclock.R.id.alarmText);
        alarmToggle = (ToggleButton) findViewById(com.anbuilder.saqib.alarmclock.R.id.alarmToggle);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
    }


        static MediaPlayer player = new MediaPlayer();
    @Override
    public void onReceive(final Context context, Intent intent) {
        //this will update the UI with message
        MainActivity inst = MainActivity.instance();
        inst.setAlarmText("Alarm! Wake up! Wake up!");

        //this will sound the alarm tone
        //this will sound the alarm once, if you wish to
        //raise alarm in loop continuously then use MediaPlayer and setLooping(true)
        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        player = MediaPlayer.create(context, notification);
        player.setLooping(true);
        player.start();





        //this will send a notification message
        ComponentName comp = new ComponentName(context.getPackageName(),
                AlarmService.class.getName());
        startWakefulService(context, (intent.setComponent(comp)));
        setResultCode(Activity.RESULT_OK);
    }
    public static void terminate()
    {
        player.stop();
    }



}