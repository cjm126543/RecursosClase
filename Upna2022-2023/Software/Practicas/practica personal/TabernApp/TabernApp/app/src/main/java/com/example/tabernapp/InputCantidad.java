package com.example.tabernapp;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class InputCantidad extends DialogFragment {

    // Activity result constants
    final int START_OK = 1;
    final int TERMINATED_OK = 2;
    final int TERMINATED_WRONG = 3;
    final int ERROR = -1;

    EditText cantidad;
    TextView articulo;

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
        View v = inflater.inflate(R.layout.dialog_quantity, null);
        builder.setView(v)
                .setMessage("¿Cuantos desea pedir?")
                .setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        cantidad = (EditText) v.findViewById(R.id.quantity);
                        int quant = Integer.parseInt(cantidad.getText().toString());

                        // TODO llamar a nueva actividad carrito
                        Bundle bundle = new Bundle();
                        bundle.putInt("requested_quantity", quant);
                        Intent intent = new Intent(activity, Compra.class);
                        intent.putExtras(bundle);
                        startActivityForResult(intent, START_OK);

                        // Check terminating status
                        int code;
                        if (quant <= 1) code = TERMINATED_WRONG;
                        else code = TERMINATED_OK;
                        activity.setResult(code, new Intent());
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // User cancelled the dialog
                        InputCantidad.this.getDialog().cancel();
                    }
                });

        return builder.create();
    }
}
