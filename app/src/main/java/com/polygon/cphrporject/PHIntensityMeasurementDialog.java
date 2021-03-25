package com.polygon.cphrporject;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatDialogFragment;

public class PHIntensityMeasurementDialog extends AppCompatDialogFragment implements AdapterView.OnItemSelectedListener {
    SeekBar phLevelBar;
    Spinner phLevelSpinner;
    String levelSelected;
    String levelDragged;
    PHMeasurementDialogListener phMeasurementDialogListener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.avg_persistent_headache_level, null);
        phLevelBar = view.findViewById(R.id.ph_level_bar);
        phLevelSpinner = view.findViewById(R.id.headache_spinner);
        //set up spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.pain_levels, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        phLevelSpinner.setAdapter(adapter);
        phLevelSpinner.setOnItemSelectedListener(this);
        phLevelBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                phLevelBar.setProgress(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                levelDragged = String.valueOf(seekBar.getProgress());
            }
        });
        builder.setView(view)
                .setTitle("Persistent Headache in the Last 24 Hours:")
                .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        phMeasurementDialogListener.applyMeasurements(levelDragged,levelSelected);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

        return builder.create();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        levelSelected = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            phMeasurementDialogListener = (PHMeasurementDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()+
                    "must implement PHMeasurementDialogListener");
        }
    }

    public interface PHMeasurementDialogListener {
        void applyMeasurements(String bar, String spinner);
    }
}
