package com.example.tabernapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.fragment.app.DialogFragment;

import com.example.tabernapp.Models.Pedido;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class OutputEntregaDialog extends DialogFragment {

    // Activity manipulation data structures
    Activity activity;
    TabernAppApplication app;

    @SuppressLint("NewApi")
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Activity variables used to manipulate the activity who calls the dialog
        activity = getActivity();
        app = (TabernAppApplication) activity.getApplicationContext();
        Pedido cesta = app.getCesta();

        // Dialog manipulation
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.dialog_delivery, null);
        builder.setView(v)
                .setMessage("¡Entendido! Su pedido se entregara en: " +
                        cesta.getDireccionEntrega().toString() + " el " +
                        LocalDate.now().plus(1, ChronoUnit.WEEKS).toString()
                        + " a las 15.00h")
                .setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        cesta.setPreparado(false);
                        cesta.saveInBackground();
                        activity.finish();
                    }
                });

        return builder.create();
    }
}
