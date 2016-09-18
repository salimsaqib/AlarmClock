package com.anbuilder.saqib.alarmclock;
import com.anbuilder.saqib.alarmclock.R;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class solution extends AppCompatActivity {

    public static TextView tv;
    public static Button b1,b2;
    PendingIntent pendingIntent;
    AlarmManager alarmManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solution);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        tv = (TextView) findViewById(R.id.solution);
        b1 =(Button)findViewById(R.id.continuequiz);
        b2 = (Button)findViewById(R.id.turnoff);
        Typeface type = Typeface.createFromAsset(getAssets(),"fonts/ComicSansMS3.ttf");
        tv.setTypeface(type);
        QuizHandle a = new QuizHandle(this);
        if(a.number==1) {
            tv.setText("The soultion is \n\n" + Quiz.Maininput[a.number+5]);
        }
        else
        {
            tv.setText("The soultion is \n\n" + Quiz.Maininput[a.number+6]);
        }
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(".Quiz"));
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Offffffffffffffffffffffffffffffffffffffff");
                AlarmReceiver.terminate();
                Intent intent5 = new Intent(solution.this, AlarmReceiver.class);
                pendingIntent = PendingIntent.getBroadcast(solution.this, 0, intent5, 0);
                alarmManager.cancel(pendingIntent);

                Intent intent4 = new Intent(solution.this, MainActivity.class);
                //intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent4);
            }
        });

    }

    /*protected void onClickTurnOff(View view)
    {
        System.out.println("Offffffffffffffffffffffffffffffffffffffff");
        AlarmReceiver.terminate();
        Intent intent5 = new Intent(solution.this, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(solution.this, 0, intent5, 0);
        alarmManager.cancel(pendingIntent);

        Intent intent4 = new Intent(solution.this, MainActivity.class);
        //intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent4);
    }*/



}
