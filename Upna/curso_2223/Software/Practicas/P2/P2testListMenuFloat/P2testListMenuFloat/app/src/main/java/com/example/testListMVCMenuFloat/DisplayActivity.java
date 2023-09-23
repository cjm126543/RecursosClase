package com.example.testListMVCMenuFloat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.testListMVCMenuFloat.Modelos.InterestPoint;

import java.util.ArrayList;
import java.util.List;

public class DisplayActivity extends AppCompatActivity {

    EditText nombre, latitud, longitud;
    Bundle bundle;
    Button send;
    TravelPointsApplication tpa;
    List<InterestPoint> puntos = new ArrayList<>();
    InterestPoint punto;
    final int POINT_SUCCESFULLY_CHANGED = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        // Recogemos aplicacion
        tpa = (TravelPointsApplication) getApplicationContext();
        puntos = tpa.getPoints();

        // Recogemos info
        bundle = getIntent().getExtras();
        int pos = bundle.getInt("position");

        // Buscamos en Application el elemento y escribimos
        nombre = (EditText) findViewById(R.id.editTextNombre);
        latitud = (EditText) findViewById(R.id.editTextLatitud);
        longitud = (EditText) findViewById(R.id.editTextLongitud);

        // Enviamos la informacion
        send = (Button) findViewById(R.id.buttonEnviar);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InterestPoint punto;
                if (pos < puntos.size()) {
                    punto = puntos.get(pos);
                } else {
                    punto = new InterestPoint();
                }
                punto.setNombre(nombre.getText().toString());
                punto.setLatitud(Double.parseDouble(latitud.getText().toString()));
                punto.setLongitud(Double.parseDouble(longitud.getText().toString()));
                if (pos >= puntos.size()) puntos.add(punto);
                setResult(POINT_SUCCESFULLY_CHANGED, new Intent());
                finish();
            }
        });
    }
}