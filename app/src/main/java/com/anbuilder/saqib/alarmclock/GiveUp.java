package com.anbuilder.saqib.alarmclock;
import com.anbuilder.saqib.alarmclock.R;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Fragment;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.Vector;
import com.anbuilder.saqib.alarmclock.R;

public class GiveUp extends AppCompatActivity {

    PendingIntent pendingIntent;
    AlarmManager alarmManager;
    public static RadioGroup radioGroup;
    public static RadioButton radioButton1, radioButton2, radioButton3, radioButton4, radioButtonselect;
    public static TextView tv,tv2;
    public static BufferedReader reader = null;
    public static Bundle tempBundle;
    public static String[] input = new String[100];
    public static int count=1;
    QuizHandle2 instance = new QuizHandle2(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_give_up);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);

        radioButton1 = (RadioButton) findViewById(R.id.no1);
        radioButton2 = (RadioButton) findViewById(R.id.no2);
        radioButton3 = (RadioButton) findViewById(R.id.no3);
        radioButton4 = (RadioButton) findViewById(R.id.no4);

        tv = (TextView) findViewById(R.id.question);
        tv2 = (TextView) findViewById(R.id.hint);

        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);


        //StringBuilder builder = new StringBuilder();
        String filename = "input.txt";
        int i=1;

        try {
            reader = new BufferedReader(new InputStreamReader(
                    getAssets().open(filename)));

            String line;
            while ((line = reader.readLine()) != null)
            {
                line = line + "\n";
                input[i] = line;
                i++;

            }
            System.out.println("kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk");
            System.out.println(input[3]);



        } catch (IOException e) {
            e.printStackTrace();
        }

        QuizHandle2 a = new QuizHandle2(this);
        a.QuizGenerate();



    }
    public void onClickGiveUp(View view) {
        AlarmReceiver.terminate();
        Intent intent = new Intent(GiveUp.this, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(GiveUp.this, 0, intent, 0);
        alarmManager.cancel(pendingIntent);

        Intent intent2 = new Intent(GiveUp.this, MainActivity.class);
        //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent2);
    }

    public void onClickSubmit(View view) {


        QuizHandle2 a = new QuizHandle2(this);
        boolean b = a.match();

        if(b==true) {
            AlarmReceiver.terminate();
            Intent intent = new Intent(GiveUp.this, AlarmReceiver.class);
            pendingIntent = PendingIntent.getBroadcast(GiveUp.this, 0, intent, 0);
            alarmManager.cancel(pendingIntent);

            Intent intent2 = new Intent(GiveUp.this, MainActivity.class);
            //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent2);
        }
        else
        {
            System.out.println("zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz");
            QuizHandle2 ab = new QuizHandle2(this);
            ab.QuizGenerate();

        }
        //Quiz.count++;

    }



}

class QuizHandle2
{
    //Quiz a = new Quiz();

    public Activity activity;


    public QuizHandle2( Activity _activity){

        this.activity = _activity;


    }
    public static String[] input = new String[100];
    public void QuizGenerate() {

        //String filename = "input.txt";
        int number;
        while(true)
        {
            Random rand = new Random();

            number = rand.nextInt((30 - 1) + 1) + 1;

            if(number % 8 == 0) {



                for(int i =1 ;i<=7;i++)
                {
                    input[i] = GiveUp.input[number+i];
                }


                break;
            }
            else if(number==1) {



                for(int i =1 ;i<=7;i++)
                {
                    input[i] = GiveUp.input[number+i-1];
                }




                break;
            }
            else continue;

        }


        System.out.println("quiz inner "+input[5]);
        System.out.println("nuuuuuuuuuuuuuuuuumber  "+number);

        System.out.println("blaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");

        GiveUp.tv.setText("Guess the word \n\n" + input[1]);
        GiveUp.radioGroup.check(R.id.no1);

        GiveUp.radioButton1.setText(input[2]);
        GiveUp.radioButton2.setText(input[3]);
        GiveUp.radioButton3.setText(input[4]);
        GiveUp.radioButton4.setText(input[5]);

        GiveUp.tv2.setText("Hint: "+input[7]);




    }

    public boolean match() {


        int selectedButton = GiveUp.radioGroup.getCheckedRadioButtonId();
        GiveUp.radioButtonselect = (RadioButton)this.activity.findViewById(selectedButton);

        String method = GiveUp.radioButtonselect.getText().toString();


        //System.out.println("quiz outer "+input[3]);

        System.out.println("checked "+method);
        System.out.println(input[6]);
        if (method.equalsIgnoreCase(input[6])) return true;
        else return false;
    }
}





