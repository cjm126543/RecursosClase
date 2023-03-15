package com.example.tabernapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.tabernapp.Models.Item;


public class Catalogo extends AppCompatActivity {

    // Activity result constants
    final int START_OK = 0;
    final int START_WRONG = 1;
    final int TERMINATED_OK = 2;
    final int TERMINATED_WRONG = 3;
    final int ERROR = -1;

    // View components
    TabernAppApplication app;
    ListView listView;
    ListAdapterCustom catalogueItemsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.catalogo_view);

        // List
        listView = (ListView) findViewById(R.id.listCatalogue);
        app = (TabernAppApplication) getApplicationContext();
        catalogueItemsAdapter = new ListAdapterCustom(this, app.getCatalogo());
        listView.setAdapter(catalogueItemsAdapter);

        // Item from list listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Bundle bundle = new Bundle();
                bundle.putInt("listPosition", i);
                Intent intent = new Intent(Catalogo.this, Categoria.class);
                intent.putExtras(bundle);
                startActivityForResult(intent, START_OK);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_new_dir: {
                DialogFragment newFragment = new InputDireccion();
                newFragment.show(getSupportFragmentManager(), "nueva_direccion");
                break;
            }

            case R.id.action_list_dir: {
                Intent intent = new Intent(Catalogo.this, ListaDirecciones.class);
                startActivityForResult(intent, START_OK);
                break;
            }

            case R.id.action_show_cart: {
                // TODO Llamar a actividad de compra
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}