package com.example.tabernapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

import com.example.tabernapp.Models.Direccion;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class DeleteDireccionDialog extends DialogFragment {

    // Activity result constants
    final int TERMINATED_OK = 2;
    final int TERMINATED_WRONG = 3;
    final int ERROR = -1;

    ListaDireccionesActivity parent;
    Activity activity;
    TabernAppApplication app;

    // Constructor
    public DeleteDireccionDialog(ListaDireccionesActivity activity) {
        this.parent = activity;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Activity variables used to manipulate the activity who calls the dialog
        activity = getActivity();
        app = (TabernAppApplication) activity.getApplicationContext();
        String nom = getArguments().getString("nombre");

        // Dialog manipulation
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.dialog_delete_direction, null))
                .setMessage("¿Desea eliminar esta direccion?")
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // User deletes the direction
                        int code;
                        ParseQuery<ParseObject> query = ParseQuery.getQuery("Direccion");
                        query.whereEqualTo("nombre", nom);
                        try {
                            Direccion dir = app.removeDirection((Direccion) query.find().get(0));
                            Log.v("Deleted", "Object removed successfully\n");
                            code = TERMINATED_OK;
                        } catch (ParseException e) {
                            Toast.makeText(parent, "ERROR: " + e.getMessage() + "\n", Toast.LENGTH_SHORT).show();
                            Log.e("NoDeleted", e.getMessage() + "\n");
                            code = TERMINATED_WRONG;
                        }
                        parent.actualizaVista();
                        activity.setResult(code, new Intent());
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // User cancelled the dialog
                        DeleteDireccionDialog.this.getDialog().cancel();
                    }
                });

        return builder.create();
    }
}
