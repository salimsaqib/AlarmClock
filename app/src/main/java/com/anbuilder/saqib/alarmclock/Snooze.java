package com.anbuilder.saqib.alarmclock;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.TimePicker;

import com.anbuilder.saqib.alarmclock.R;

import java.sql.Time;
import java.util.Calendar;

public class Snooze extends AppCompatActivity {
    PendingIntent pendingIntent;
    AlarmManager alarmManager;
    private TimePicker alarmTimePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snooze);

        alarmTimePicker  = (TimePicker) findViewById(R.id.alarmTimePicker);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
    }

    public void onClickSnooze(View view){
        AlarmReceiver.terminate();
        snooze();
        Intent intent = new Intent(Snooze.this,Quiz.class);
        //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
    public void snooze()
    {

        long currentTimeMillis = System.currentTimeMillis();
        long nextUpdateTimeMillis = currentTimeMillis+10000;
        Intent i = new Intent(Snooze.this, AlarmReceiver.class);
        //startActivity(i);
        pendingIntent = PendingIntent.getBroadcast(Snooze.this, 0, i, 0);
        alarmManager.set(AlarmManager.RTC, nextUpdateTimeMillis, pendingIntent);
    }


}
