package com.polygon.cphrporject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Calendar;
import java.util.Date;

public class MissingEntry extends AppCompatActivity implements View.OnClickListener{

    Button yesterdayBtn;
    Button theDayBeforeYesterdayBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_missing_entry);

        yesterdayBtn = findViewById(R.id.yesterday_btn);
        theDayBeforeYesterdayBtn = findViewById(R.id.the_day_before_yesterday_btn);
        yesterdayBtn.setOnClickListener(this);
        theDayBeforeYesterdayBtn.setOnClickListener(this);
        Date yesterday = new Date(System.currentTimeMillis()-24*60*60*1000);
        Date theDayBeforeYesterday = new Date(System.currentTimeMillis()-48*60*60*1000);
        yesterdayBtn.setText(yesterday.toString());
        theDayBeforeYesterdayBtn.setText(theDayBeforeYesterday.toString());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.yesterday_btn:
                break;
            case R.id.the_day_before_yesterday_btn:
                break;
        }
    }
}
