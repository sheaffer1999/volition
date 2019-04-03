package com.recoveryenhancementsolutions.volition;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.HorizontalScrollView;
import android.widget.TextView;


public class PlanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            setContentView(R.layout.activity_plan_land);
        }
        else {
            setContentView(R.layout.activity_plan_port);
        }
        fillViews();
    }

    public void fillViews()
    {
        String sampletext = "Visit 1 Visit 1";

        final HorizontalScrollView hscroller = findViewById(R.id.hscroller);
        hscroller.post(new Runnable() {
            public void run() {
                hscroller.fullScroll(HorizontalScrollView.FOCUS_RIGHT);
            }
        });
        TextView textView = (TextView) findViewById(R.id.textview_day_1);
        textView.setText(sampletext);
        textView = findViewById(R.id.textview_day_2);
        textView.setText(sampletext);
        textView = findViewById(R.id.textview_day_3);
        textView.setText(sampletext);
        textView = findViewById(R.id.textview_day_4);
        textView.setText(sampletext);
        textView = findViewById(R.id.textview_day_5);
        textView.setText(sampletext);
        textView = findViewById(R.id.textview_day_6);
        textView.setText(sampletext);
        textView = findViewById(R.id.textview_day_7);
        textView.setText(sampletext);
        textView = findViewById(R.id.day_of_week_1);
        textView.setText("S");
        textView = findViewById(R.id.day_of_week_2);
        textView.setText("S");
        textView = findViewById(R.id.day_of_week_3);
        textView.setText("F");
        textView = findViewById(R.id.day_of_week_4);
        textView.setText("T");
        textView = findViewById(R.id.day_of_week_5);
        textView.setText("W");
        textView = findViewById(R.id.day_of_week_6);
        textView.setText("T");
        textView = findViewById(R.id.day_of_week_7);
        textView.setText("M");
    }
}