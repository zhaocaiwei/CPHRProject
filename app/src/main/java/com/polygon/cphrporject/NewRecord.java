package com.polygon.cphrporject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class NewRecord extends AppCompatActivity implements View.OnClickListener,
        PHIntensityMeasurementDialog.PHMeasurementDialogListener,
        DHIntensityMeasurementDialog.DHMeasurementDialogListener,
        PHDurationMeasurementDialog.PHDMeasurementDialogListener,
        DHDurationMeasurementDialog.DHDMeasurementDialogListener,
        DHInterferenceMeasurementDialog.DHIMeasurementDialogListener {

    TextView phIntensityMeasurement;
    TextView phDurationMeasurement;
    TextView dhIntensityMeasurement;
    TextView dhDurationMeasurement;
    TextView dhInterferenceMeasurement;

    String phIntensitySelected;
    String phIntensityDragged;
    String dhIntensitySelected;
    String dhIntensityDragged;
    String phDuration;
    String dhDuration;
    String dhInterference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_record);
        findViewById(R.id.help_persistent_headache).setOnClickListener(this);
        findViewById(R.id.help_Debilitating_headache).setOnClickListener(this);
        findViewById(R.id.ph_intensity_hint).setOnClickListener(this);
        findViewById(R.id.ph_duration_hint).setOnClickListener(this);
        findViewById(R.id.dh_intensity_hint).setOnClickListener(this);
        findViewById(R.id.dh_duration_hint).setOnClickListener(this);
        findViewById(R.id.dh_interference_hint).setOnClickListener(this);
        findViewById(R.id.submit_btn).setOnClickListener(this);
        phIntensityMeasurement = findViewById(R.id.ph_intensity_measurement);
        phDurationMeasurement = findViewById(R.id.ph_duration_measurement);
        dhIntensityMeasurement = findViewById(R.id.dh_intensity_measurement);
        dhDurationMeasurement = findViewById(R.id.dh_duration_measurement);
        dhInterferenceMeasurement = findViewById(R.id.dh_interference_measurement);

        Intent intent = getIntent();
        if(intent.getStringExtra("date")!= null){
            Toast.makeText(this,intent.getStringExtra("date"),Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.help_persistent_headache:
                helpPersistentHeadache();
                break;
            case R.id.help_Debilitating_headache:
                helpDebilitatingHeadache();
                break;
            case R.id.ph_intensity_hint:
                openPhIntensityDialog();
                break;
            case R.id.ph_duration_hint:
                openPhDurationDialog();
                break;
            case R.id.dh_intensity_hint:
                openDhIntensityDialog();
                break;
            case R.id.dh_duration_hint:
                openDhDurationDialog();
                break;
            case R.id.dh_interference_hint:
                openDhInterferenceDialog();
                break;
            case R.id.submit_btn:
                saveNewRecordToDB();
                Intent intent = new Intent(this,EndOfRecord.class);
                startActivity(intent);
                break;
        }
    }

    private void saveNewRecordToDB() {
    }

    private void openDhInterferenceDialog() {
        DHInterferenceMeasurementDialog dhInterferenceMeasurementDialog = new DHInterferenceMeasurementDialog();
        dhInterferenceMeasurementDialog.show(getSupportFragmentManager(),"dh interference measurement");
    }

    private void openDhDurationDialog() {
        DHDurationMeasurementDialog dhDurationMeasurementDialog = new DHDurationMeasurementDialog();
        dhDurationMeasurementDialog.show(getSupportFragmentManager(),"dh duration measurement");
    }

    private void openDhIntensityDialog() {
        DHIntensityMeasurementDialog dhMeasurementDialog = new DHIntensityMeasurementDialog();
        dhMeasurementDialog.show(getSupportFragmentManager(),"dh intensity measurement");
    }

    private void openPhDurationDialog() {
        PHDurationMeasurementDialog phDurationMeasurementDialog = new PHDurationMeasurementDialog();
        phDurationMeasurementDialog.show(getSupportFragmentManager(),"ph duration measurement");
    }

    private void openPhIntensityDialog() {
        PHIntensityMeasurementDialog phMeasurementDialog = new PHIntensityMeasurementDialog();
        phMeasurementDialog.show(getSupportFragmentManager(),"ph intensity measurement");
    }

    private void helpPersistentHeadache(){
            PhInfoDialog explainPersistentHeadache = new PhInfoDialog();
            explainPersistentHeadache.show(getSupportFragmentManager(),"explain persistent headache");
    }

    private void helpDebilitatingHeadache(){
            DhInfoDialog explainDebilitatingHeadahce = new DhInfoDialog();
            explainDebilitatingHeadahce.show(getSupportFragmentManager(),"explain debilitating headache");
    }

    @Override
    public void applyMeasurements(String bar, String spinner) {
        phIntensityMeasurement.setText(spinner+"/10");
        phIntensityDragged = bar;
        phIntensitySelected = spinner;
    }

    @Override
    public void applyDHMeasurements(String bar, String spinner) {
        dhIntensityMeasurement.setText(spinner+"/10");
        dhIntensityDragged = bar;
        dhIntensitySelected = spinner;
    }

    @Override
    public void applyPHDMeasurements(String spinner) {
        phDurationMeasurement.setText(spinner+"hour(s)");
        phDuration = spinner;
    }

    @Override
    public void applyDHDMeasurements(String spinner) {
        dhDurationMeasurement.setText(spinner+"hour(s)");
        dhDuration = spinner;
    }

    @Override
    public void applyDHIMeasurements(String spinner) {
        dhInterferenceMeasurement.setText(spinner+"/10");
        dhInterference = spinner;
    }
}
