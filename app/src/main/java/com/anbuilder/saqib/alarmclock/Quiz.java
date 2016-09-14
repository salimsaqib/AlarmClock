package com.anbuilder.saqib.alarmclock;
import com.anbuilder.saqib.alarmclock.R;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Quiz extends AppCompatActivity {

    PendingIntent pendingIntent;
    AlarmManager alarmManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
    }

    public void onClickSubmit(View view){
        AlarmReceiver.terminate();
        Intent i = new Intent(Quiz.this, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(Quiz.this, 0, i, 0);
        alarmManager.cancel(pendingIntent);

        Intent intent = new Intent(Quiz.this,MainActivity.class);
        //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }
}
