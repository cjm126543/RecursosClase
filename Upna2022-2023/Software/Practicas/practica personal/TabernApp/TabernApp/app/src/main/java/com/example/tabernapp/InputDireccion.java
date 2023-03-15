package com.example.tabernapp;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.tabernapp.Models.Direccion;

public class InputDireccion extends DialogFragment {

    // Activity result constants
    final int TERMINATED_OK = 2;
    final int TERMINATED_WRONG = 3;
    final int ERROR = -1;

    EditText nombre, calle, cPostal;
    Activity activity;
    TabernAppApplication app;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Activity variables used to manipulate the activity who calls the dialog
        activity = getActivity();
        app = (TabernAppApplication) activity.getApplicationContext();

        // Dialog manipulation
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.dialog_direction, null);
        builder.setView(v)
                .setMessage(R.string.info_dialog)
                .setPositiveButton(R.string.start, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User inputted the dialog
                        nombre = (EditText) v.findViewById(R.id.name);
                        calle = (EditText) v.findViewById(R.id.road);
                        cPostal = (EditText) v.findViewById(R.id.postal_code);
                        Direccion new_dir = new Direccion(nombre.getText().toString(),
                                calle.getText().toString(), cPostal.getText().toString());

                        // Check terminating status
                        int code;
                        if (app.addDirection(new_dir) == true) code = TERMINATED_OK;
                        else code = TERMINATED_WRONG;
                        activity.setResult(code, new Intent());
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                        InputDireccion.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }
}
