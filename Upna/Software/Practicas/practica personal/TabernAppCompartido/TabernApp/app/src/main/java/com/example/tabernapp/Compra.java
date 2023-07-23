package com.example.tabernapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.tabernapp.Models.Direccion;
import com.example.tabernapp.Models.Item;
import com.example.tabernapp.Models.Pedido;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Compra extends AppCompatActivity {

    // View components
    TabernAppApplication app;
    Spinner spinner;
    TextView descripcionCesta, totalPrecio;
    Button aceptar, cancelar;

    // Application manipulation data structures
    List<Direccion> direcciones = new ArrayList<>();
    List<String> dirStrings = new ArrayList<>();
    Pedido carro;
    Direccion dir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.compra_view);

        // Retrieve list of directions from app
        app = (TabernAppApplication) getApplicationContext();
        direcciones = app.getDirecciones();

        // Fill the spinner with directions
        for (Direccion d: direcciones) {
            dirStrings.add(d.toString());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, dirStrings);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setAdapter(adapter);

        // Fill the textView with the actual basket products
        descripcionCesta = (TextView) findViewById(R.id.textViewCesta);
        String str = "";
        carro = app.getCesta();
        for (Map.Entry<Item, Integer> set: carro.getAllArticulos().entrySet()) {
            Item item = set.getKey();
            int quantity = set.getValue();
            if (quantity != 0) {
                str = str + quantity + "x (" + item.getPrecioUd() + "€/ud) " + item.toString() + "\n";
            }
        }
        descripcionCesta.setText(str);

        // Fill the total value for all product in the textView
        carro.setTotalPrecio(carro.getAllArticulos());
        totalPrecio = (TextView) findViewById(R.id.textViewTotal);
        totalPrecio.setText("Total: " + carro.getTotalPrecio() + "€");

        // Buttons functionality
        aceptar = (Button) findViewById(R.id.buttonAceptar);
        cancelar = (Button) findViewById(R.id.buttonCancelar);

        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dir = app.getDirecciones().get(spinner.getSelectedItemPosition());
                app.getCesta().setDireccionEntrega(dir);
                DialogFragment newFragment = new OutputEntrega();
                newFragment.show(getSupportFragmentManager(), "direccion_entrega");
            }
        });

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                app.removeAllFromCesta();
                finish();
            }
        });
    }
}