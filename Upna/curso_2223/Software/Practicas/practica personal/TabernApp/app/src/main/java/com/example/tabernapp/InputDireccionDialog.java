package com.example.tabernapp;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.tabernapp.Models.Direccion;
import com.parse.ParseException;
import com.parse.SaveCallback;

public class InputDireccionDialog extends DialogFragment {

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
                        Direccion dirToSrv = new Direccion();
                        dirToSrv.setNombre(nombre.getText().toString());
                        dirToSrv.setCalle(calle.getText().toString());
                        dirToSrv.setcPostal(cPostal.getText().toString());
                        dirToSrv.saveInBackground(new SaveCallback() {
                            @Override
                            public void done(ParseException e) {
                                if (e == null) {
                                    // Saved
                                    Log.v("Saved", "Object: " + dirToSrv.getObjectId() + " saved successfully\n");
                                } else {
                                    // error
                                    Toast.makeText(activity, "ERROR: " + e.getMessage() + "\n", Toast.LENGTH_SHORT).show();
                                    Log.e("NoSaved", e.getMessage() + "\n");
                                }
                            }
                        });
                        activity.setResult(TERMINATED_OK, new Intent());
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                        InputDireccionDialog.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }
}
