package com.example.tabernapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
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
    ArrayAdapter<Item> catalogueItemsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.catalogo_view);

        // List
        listView = (ListView) findViewById(R.id.listCatalogue);
        app = (TabernAppApplication) getApplicationContext();
        catalogueItemsAdapter = new ArrayAdapter<Item>(this, R.layout.row_layout, R.id.listText, app.getCatalogo());
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // TODO
    }
}