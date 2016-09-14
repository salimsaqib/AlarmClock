package com.anbuilder.saqib.alarmclock;
import com.anbuilder.saqib.alarmclock.R;

import android.app.AlarmManager;
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
import java.util.Vector;
import com.anbuilder.saqib.alarmclock.R;

public class Quiz extends AppCompatActivity {

    PendingIntent pendingIntent;
    AlarmManager alarmManager;
    private  static RadioGroup radioGroup;
    private static RadioButton radioButton1,radioButton2,radioButton3,radioButton4,radioButtonselect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.check(R.id.no1);
        radioButton1 = (RadioButton)findViewById(R.id.no1);
        radioButton2 = (RadioButton)findViewById(R.id.no2);
        radioButton3 = (RadioButton)findViewById(R.id.no3);
        radioButton4 = (RadioButton)findViewById(R.id.no4);

        quiz();

        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

    }


    public boolean quiz()
    {
        String[] input = new String[100];
        String filename = "input.txt";

        TextView tv = (TextView)findViewById(R.id.question);


        int i =0;

        BufferedReader reader = null;
        StringBuilder builder = new StringBuilder();

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


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //System.out.println(input[0]);

        tv.setText("Guess the word \n\n" + input[0]);

        radioButton1.setText(input[1]);
        radioButton2.setText(input[2]);
        radioButton3.setText(input[3]);
        radioButton4.setText(input[4]);



        int selectedButton = radioGroup.getCheckedRadioButtonId();
        radioButtonselect = (RadioButton) findViewById(selectedButton);

        String method = radioButtonselect.getText().toString();

        System.out.println(method);
        System.out.println(input[5]);



        if (method.equalsIgnoreCase(input[5])) return true;
        else return false;



    }
    public void onClickSubmit(View view){

        boolean a = quiz();
        if (a==true)
        {
            AlarmReceiver.terminate();
            Intent intent = new Intent(Quiz.this, AlarmReceiver.class);
            pendingIntent = PendingIntent.getBroadcast(Quiz.this, 0, intent, 0);
            alarmManager.cancel(pendingIntent);

            Intent intent2 = new Intent(Quiz.this,MainActivity.class);
            //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent2);
        }

    }


}
