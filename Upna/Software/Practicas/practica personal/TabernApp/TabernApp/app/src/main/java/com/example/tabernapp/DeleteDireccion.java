package com.example.tabernapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.fragment.app.DialogFragment;

import com.example.tabernapp.Models.Direccion;

public class DeleteDireccion extends DialogFragment {

    // Activity result constants
    final int TERMINATED_OK = 2;
    final int TERMINATED_WRONG = 3;
    final int ERROR = -1;

    ListaDirecciones parent;
    Activity activity;
    TabernAppApplication app;

    // Constructor
    public DeleteDireccion(ListaDirecciones activity) {
        this.parent = activity;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Activity variables used to manipulate the activity who calls the dialog
        activity = getActivity();
        app = (TabernAppApplication) activity.getApplicationContext();
        int position = getArguments().getInt("position_dir");

        // Dialog manipulation
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.dialog_delete_direction, null))
                .setMessage("¿Desea eliminar esta direccion?")
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // User deletes the direction
                        Direccion dir = app.removeDirection(app.getDirecciones().get(position));
                        int code;
                        if (!dir.equals(null)) code = TERMINATED_OK;
                        else code = TERMINATED_WRONG;
                        activity.setResult(code, new Intent());
                        parent.actualizaVista();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // User cancelled the dialog
                        DeleteDireccion.this.getDialog().cancel();
                    }
                });

        return builder.create();
    }
}
