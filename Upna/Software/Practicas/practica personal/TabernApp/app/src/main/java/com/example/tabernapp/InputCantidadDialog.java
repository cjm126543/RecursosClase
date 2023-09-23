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

import com.example.tabernapp.Models.Pedido;

public class InputCantidadDialog extends DialogFragment {

    // Activity result constants
    final int START_OK = 1;
    final int TERMINATED_OK = 2;
    final int TERMINATED_WRONG = 3;
    final int ERROR = -1;

    EditText cantidad;

    CategoriaActivity parent;
    Activity activity;
    TabernAppApplication app;
    Pedido basket;

    // Constructor
    public InputCantidadDialog(CategoriaActivity activity) { this.parent = activity; }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Activity variables used to manipulate the activity who calls the dialog
        activity = getActivity();
        app = (TabernAppApplication) activity.getApplicationContext();
        basket = app.getCesta();
        Bundle args = getArguments();
        int type = args.getInt("tipo");
        int prevQuant = args.getInt("prevQuant");


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
                        int prev = prevQuant;
                        int quant = 0;
                        String cant = cantidad.getText().toString();
                        if (!cant.isEmpty()) {
                            quant = prev + Integer.parseInt(cant);
                        }

                        // Add item to basket
                        app.updateCesta(app.getCategoria(type).get(parent.getCard()), quant);
                        Intent intent = new Intent(activity, CompraActivity.class);
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
                        InputCantidadDialog.this.getDialog().cancel();
                    }
                });

        return builder.create();
    }
}
