package com.example.tabernapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.tabernapp.Models.Direccion;

public class ListaDirecciones extends AppCompatActivity {

    // View components
    TabernAppApplication app;
    ListView listView;
    ArrayAdapter<Direccion> directionsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_direcciones_view);

        // List
        listView = (ListView) findViewById(R.id.list_Dir);
        app = (TabernAppApplication) getApplicationContext();
        directionsAdapter = new ArrayAdapter<Direccion>(this, R.layout.row_dir_layout,
                R.id.listText_dir, app.getDirecciones());
        listView.setAdapter(directionsAdapter);

        // Selecting an item asks the user for its deletion
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Bundle bundle = new Bundle();
                bundle.putInt("position_dir", i);
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

    // View custom methods
    /**
     * Updates the ListView for any given modifications
     */
    public void actualizaVista() {
        directionsAdapter.notifyDataSetChanged();
    }
}