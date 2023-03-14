package com.example.testListMVCSubActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.testListMVCSubActivity.Modelos.InterestPoint;

import java.util.ArrayList;
import java.util.List;

public class Activity2 extends AppCompatActivity {

    EditText nombre, latitud, longitud;
    Bundle bundle;
    Button back;
    TravelPointsApplication tpa;
    List<InterestPoint> puntos = new ArrayList<>();
    InterestPoint punto;
    final int POINT_SUCCESFULLY_CHANGED = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        // Recogemos aplicacion
        tpa = (TravelPointsApplication) getApplicationContext();
        puntos = tpa.getPoints();

        // recogemos info
        bundle = getIntent().getExtras();
        int pos = bundle.getInt("position");

        // Buscamos en Application el elemento y escribimos
        nombre = (EditText) findViewById(R.id.editNombre);
        latitud = (EditText) findViewById(R.id.editLatitud);
        longitud = (EditText) findViewById(R.id.editLongitud);

        // Interaccion de los botones
        back = (Button) findViewById(R.id.backBut);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                punto = puntos.get(pos);
                punto.setNombre(nombre.getText().toString());
                punto.setLatitud(Double.parseDouble(latitud.getText().toString()));
                punto.setLongitud(Double.parseDouble(longitud.getText().toString()));
                setResult(POINT_SUCCESFULLY_CHANGED, new Intent());
                finish();
            }
        });

    }
}