package com.example.tabernapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.tabernapp.Models.Direccion;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class ListaDirecciones extends AppCompatActivity {

    // View components
    TabernAppApplication app;
    ListView listView;
    ArrayAdapter<Direccion> directionsAdapter;
    List<Direccion> local_direcciones = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_direcciones_view);

        // List
        app = (TabernAppApplication) getApplicationContext();
        actualizaVista();

        // Selecting an item asks the user for its deletion
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Bundle bundle = new Bundle();
                bundle.putString("nombre", local_direcciones.get(i).getNombre());
                DialogFragment newFragment = new DeleteDireccion(ListaDirecciones.this);
                newFragment.setArguments(bundle);
                newFragment.show(getSupportFragmentManager(), "borrar_direccion");
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onResume() {
        super.onResume();
        actualizaVista();
    }

    // View custom methods
    /**
     * Updates the ListView for any given modifications
     */
    public void actualizaVista() {
        listView = (ListView) findViewById(R.id.list_Dir);
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Direccion");
        query.findInBackground((objects, e) -> {
            if (e == null) {
                local_direcciones = (List<Direccion>)(List<?>) objects;
                directionsAdapter = new ArrayAdapter<Direccion>(this, R.layout.row_dir_layout,
                        R.id.listText_dir, local_direcciones);
                listView.setAdapter(directionsAdapter);
                directionsAdapter.notifyDataSetChanged();
                Log.v("Found", "Objects: " + objects.toString() + " were found\n");
            } else {
                // error
                Toast.makeText(this, "ERROR: " + e.getMessage() + "\n", Toast.LENGTH_SHORT).show();
                Log.e("NoFound", "Objects were not found\n");
            }
        });
    }
}