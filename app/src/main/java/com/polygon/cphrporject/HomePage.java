package com.polygon.cphrporject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.TimePicker;

import java.util.Calendar;

public class HomePage extends AppCompatActivity implements View.OnClickListener {

    int min, hour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        findViewById(R.id.reminder_btn).setOnClickListener(this);
        findViewById(R.id.reminder_settings_btn).setOnClickListener(this);
        findViewById(R.id.cancel_reminder_btn).setOnClickListener(this);
        findViewById(R.id.missing_entries_btn).setOnClickListener(this);
        findViewById(R.id.new_entry_btn).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.reminder_btn:
                setReminder();
                break;
            case R.id.reminder_settings_btn:
                reminderSettings();
                break;
            case R.id.cancel_reminder_btn:
                deleteReminder();
                break;
            case R.id.new_entry_btn:
                //todo:if today's entry has been entered, ask if they want to modify the data
                Intent intent = new Intent(getApplicationContext(), NewRecord.class);
                startActivity(intent);
                break;
            case R.id.missing_entries_btn:
                //todo:pass the date to the next activity
                openMissingEntriesDialog();
                break;
        }
    }

    private void openMissingEntriesDialog() {
        WhichDateDialog whichDateDialog = new WhichDateDialog();
        whichDateDialog.show(getSupportFragmentManager(),"missing record dialog");
    }

    private void setReminder() {
        createNotificationChannel();
        Intent intent = new Intent(getApplicationContext(), ReminderBroadcast.class);
        final PendingIntent pendingIntent = PendingIntent.getBroadcast(HomePage.this, 0, intent, 0);
        final AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        //initialize time picker dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(
                HomePage.this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                        hour = hourOfDay;
                        min = minute;
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(0, 0, 0, hour, min);
                        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
                    }
                }, 12, 0, false
        );
        timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        timePickerDialog.updateTime(hour, min);
        timePickerDialog.show();

    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Remind the Patient";
            String description = "Channel for reminding the patient";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel("notifyPatient", name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

    }

    private void reminderSettings() {
        Intent intent = new Intent(Settings.ACTION_CHANNEL_NOTIFICATION_SETTINGS);
        intent.putExtra(Settings.EXTRA_APP_PACKAGE, getPackageName());
        intent.putExtra(Settings.EXTRA_CHANNEL_ID, "notifyPatient");
        startActivity(intent);
    }

    private void deleteReminder() {
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        // The id of the channel.
        String id = "notifyPatient";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.deleteNotificationChannel(id);
        }
    }
}
