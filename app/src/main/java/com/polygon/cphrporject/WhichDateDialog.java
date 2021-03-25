package com.polygon.cphrporject;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatDialogFragment;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class WhichDateDialog extends AppCompatDialogFragment implements View.OnClickListener {
    String yesterdayDate;
    String theDayBeforeYesterdayDate;
    Button yesterday;
    Button theDayBeforeYesterday;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.which_date, null);
        yesterday = view.findViewById(R.id.yesterday);
        theDayBeforeYesterday = view.findViewById(R.id.the_day_before_yesterday);
        yesterday.setOnClickListener(this);
        theDayBeforeYesterday.setOnClickListener(this);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, -24);
        yesterdayDate = dateFormat.format(calendar.getTime());

        DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(Calendar.HOUR_OF_DAY, -48);
        theDayBeforeYesterdayDate = dateFormat.format(calendar1.getTime());


        builder.setView(view)
                .setTitle("Which day's record do you wish to fill in?")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

        return builder.create();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.yesterday:
                Intent intent = new Intent(getContext(), NewRecord.class);
                intent.putExtra("date", yesterdayDate);
                startActivity(intent);
                break;
            case R.id.the_day_before_yesterday:
                Intent intent1 = new Intent(getContext(), NewRecord.class);
                intent1.putExtra("date", theDayBeforeYesterdayDate);
                startActivity(intent1);
                break;
        }
    }

}
