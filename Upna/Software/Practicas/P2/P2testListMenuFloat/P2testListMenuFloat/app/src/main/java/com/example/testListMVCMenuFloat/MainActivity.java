/*
Errores práctica:
	- A partir del elemento 3 no reacciona a pulsar.
	- Reload añade una nueva fila cuando no deberia.
 */

package com.example.testListMVCMenuFloat;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.IntegerRes;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import com.example.testListMVCMenuFloat.Modelos.InterestPoint;
import com.example.testListMVCMenuFloat.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    TravelPointsApplication tpa;
    private final int SHOW_ADDACTIVITY = 3;
    private final int SHOW_DISPLAYACTIVITY = 2;
    ListView listView;
    ArrayAdapter<InterestPoint> todoItemsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Lista
        listView = (ListView) findViewById(R.id.list);
        tpa = (TravelPointsApplication) getApplicationContext();
        todoItemsAdapter = new ArrayAdapter<InterestPoint>(this, R.layout.row_layout, R.id.listText, tpa.getPoints());
        listView.setAdapter(todoItemsAdapter);

        // Barra de acciones
        setSupportActionBar(binding.toolbar);

        // Boton flotante
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("position", tpa.getPoints().size() + 1);
                Intent intent = new Intent(getApplicationContext(), DisplayActivity.class);
                intent.putExtras(bundle);
                startActivityForResult(intent, SHOW_DISPLAYACTIVITY);
            }
        });

        // Elemento de la lista clickado
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Bundle bundle = new Bundle();
                bundle.putInt("position", i);
                Intent intent = new Intent(MainActivity.this, DisplayActivity.class);
                intent.putExtras(bundle);
                startActivityForResult(intent, SHOW_DISPLAYACTIVITY);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        todoItemsAdapter.notifyDataSetChanged();
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
                todoItemsAdapter.notifyDataSetChanged();
                break;
            }

            case R.id.action_new: {
                List<InterestPoint> ps = tpa.getPoints();
                InterestPoint p = new InterestPoint();
                p.setNombre("Nuevo punto: ");
                p.setLatitud(0.00);
                p.setLongitud(0.00);
                ps.add(p);
                todoItemsAdapter.notifyDataSetChanged();
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

}