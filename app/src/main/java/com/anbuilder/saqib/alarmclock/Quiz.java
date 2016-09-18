package com.anbuilder.saqib.alarmclock;
import com.anbuilder.saqib.alarmclock.R;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Fragment;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Typeface;
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

public class Quiz extends AppCompatActivity {

    PendingIntent pendingIntent;
    AlarmManager alarmManager;
    public static RadioGroup radioGroup;
    public static RadioButton radioButton1, radioButton2, radioButton3, radioButton4, radioButtonselect;
    public static TextView tv,tv2,tv3;
    public static BufferedReader reader = null;
    public static Bundle tempBundle;
    public static String[] Maininput = new String[100];
    public static int count=1;
    QuizHandle instance = new QuizHandle(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);

        radioButton1 = (RadioButton) findViewById(R.id.no1);
        radioButton2 = (RadioButton) findViewById(R.id.no2);
        radioButton3 = (RadioButton) findViewById(R.id.no3);
        radioButton4 = (RadioButton) findViewById(R.id.no4);

        tv = (TextView) findViewById(R.id.question);
        tv2 = (TextView) findViewById(R.id.hint);
        tv3 = (TextView) findViewById(R.id.alarmText);
        Typeface type = Typeface.createFromAsset(getAssets(),"fonts/ComicSansMS3.ttf");
        tv2.setTypeface(type);
        tv.setTypeface(type);
        tv3.setTypeface(type);
        tv3.setText("You have to solve this quiz");
        radioButton1.setTypeface(type);
        radioButton2.setTypeface(type);
        radioButton3.setTypeface(type);
        radioButton4.setTypeface(type);

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
                Maininput[i] = line;
                i++;

            }



        } catch (IOException e) {
            e.printStackTrace();
        }

        QuizHandle a = new QuizHandle(this);
        a.QuizGenerate();



    }

    public void onClickSubmit(View view) {


        QuizHandle a = new QuizHandle(this);
        boolean b = a.match();

        if(b==true) {
            AlarmReceiver.terminate();
            Intent intent = new Intent(Quiz.this, AlarmReceiver.class);
            pendingIntent = PendingIntent.getBroadcast(Quiz.this, 0, intent, 0);
            alarmManager.cancel(pendingIntent);

            Intent intent2 = new Intent(Quiz.this, MainActivity.class);
            //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent2);
        }
        else
        {
            //System.out.println("zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz");
            //QuizHandle ab = new QuizHandle(this);
            //ab.QuizGenerate();
            Intent intent3 = new Intent(Quiz.this, solution.class);
            //intent3.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent3);

        }
        Quiz.count++;

    }



}

class QuizHandle
{
    //Quiz a = new Quiz();

    public Activity activity;


    public QuizHandle( Activity _activity){

        this.activity = _activity;


    }
    public static String[] input = new String[100];
    public static int number;
    public void QuizGenerate() {

        //String filename = "input.txt";

        while(true)
        {
            Random rand = new Random();

            number = rand.nextInt((30 - 1) + 1) + 1;

            if(number % 8 == 0) {


                for(int i =1 ;i<=7;i++)
                {
                    input[i] = Quiz.Maininput[number+i];
                }


                break;
            }
            else if(number==1) {


                for(int i =1 ;i<=7;i++)
                {
                    input[i] = Quiz.Maininput[number+i-1];
                }

                break;
            }
            else continue;

        }


        System.out.println("quiz inner "+input[5]);
        System.out.println("nuuuuuuuuuuuuuuuuumber  "+number);

        System.out.println("blaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");

        Quiz.tv.setText("Guess the word \n\n" + input[1]);
        Quiz.radioGroup.check(R.id.no1);

        Quiz.radioButton1.setText(input[2]);
        Quiz.radioButton2.setText(input[3]);
        Quiz.radioButton3.setText(input[4]);
        Quiz.radioButton4.setText(input[5]);

        Quiz.tv2.setText("Hint: " + input[7]);




    }

    public boolean match() {


        int selectedButton = Quiz.radioGroup.getCheckedRadioButtonId();
        Quiz.radioButtonselect = (RadioButton)this.activity.findViewById(selectedButton);

        String method = Quiz.radioButtonselect.getText().toString();


        //System.out.println("quiz outer "+input[3]);

        System.out.println("checked "+method);
        System.out.println(input[6]);
        if (method.equalsIgnoreCase(input[6])) return true;
        else return false;
    }
}





