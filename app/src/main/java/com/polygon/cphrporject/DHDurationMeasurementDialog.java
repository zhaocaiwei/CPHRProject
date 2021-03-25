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
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatDialogFragment;

public class DHDurationMeasurementDialog extends AppCompatDialogFragment implements AdapterView.OnItemSelectedListener {
    Spinner dhLevelSpinner;
    String levelSelected;
    DHDMeasurementDialogListener dhdMeasurementDialogListener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dh_duration, null);
        dhLevelSpinner = view.findViewById(R.id.dh_duration_spinner);
        //set up spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.duration, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        dhLevelSpinner.setAdapter(adapter);
        dhLevelSpinner.setOnItemSelectedListener(this);
        builder.setView(view)
                .setTitle("Duration(Hours in 1 ~ 24):")
                .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dhdMeasurementDialogListener.applyDHDMeasurements(levelSelected);
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
            dhdMeasurementDialogListener = (DHDMeasurementDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()+
                    "must implement DHDMeasurementDialogListener");
        }
    }

    public interface DHDMeasurementDialogListener {
        void applyDHDMeasurements(String spinner);
    }
}