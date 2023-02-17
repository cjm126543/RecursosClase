/*
Errores práctica:
	- A partir del elemento 3 no reacciona a pulsar.
	- Reload añade una nueva fila cuando no deberia.
 */

package com.example.p2testlistmenufloat;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import com.example.p2testlistmenufloat.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private final int SHOW_ADDACTIVITY = 3;
    ListView listView;
    ArrayAdapter<String> todoItemsAdapter;
    public List<String> nameList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Lista
        listView = (ListView) findViewById(R.id.list);
        todoItemsAdapter = new ArrayAdapter<String>(this, R.layout.row_layout, R.id.listText, nameList);
        initializeList(nameList);
        listView.setAdapter(todoItemsAdapter);

        // Barra de acciones
        setSupportActionBar(binding.toolbar);

        // Boton flotante
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //        .setAction("Action", null).show();
                Intent intent = new Intent(getApplicationContext(), AddNameActivity.class);
                startActivityForResult(intent, SHOW_ADDACTIVITY);
            }
        });

        // Elemento de la lista clickado
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String txt = nameList.get(i).toString();
                Intent intent = new Intent(MainActivity.this, DisplayActivity.class);
                intent.putExtra("mensaje seleccionado", txt);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SHOW_ADDACTIVITY && resultCode == SHOW_ADDACTIVITY) {
            String msg = data.getStringExtra("msg");
            nameList.add(msg);
            todoItemsAdapter.notifyDataSetChanged();
        }
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
            case R.id.action_reload: {
                for (int i = 0; i < nameList.size(); ++i) {
                    nameList.set(i, "Nuevo elemento");
                }
                todoItemsAdapter.notifyDataSetChanged();
            }

            case R.id.action_new: {
                nameList.add("Nueva fila");
                todoItemsAdapter.notifyDataSetChanged();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    public void initializeList(List<String> aNameList) {
        for(int i=0; i<5; i++){
            aNameList.add(i,"¡Pulsa aquí!" + " " + i);
        }
    }
}