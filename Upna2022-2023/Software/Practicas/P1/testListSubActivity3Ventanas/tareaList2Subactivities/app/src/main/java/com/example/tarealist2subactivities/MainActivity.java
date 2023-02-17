package com.example.tarealist2subactivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayAdapter<String> todoItemsAdapter;
    public List<String> nameList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.list);
        initializeList(nameList);
        todoItemsAdapter = new ArrayAdapter<String>(this, R.layout.row_layout, R.id.listText, nameList);
        listView.setAdapter(todoItemsAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(new Intent(MainActivity.this, Activity2.class));
            }
        });
    }

    public void initializeList(List<String> aNameList) {
        for(int i=0; i<5; i++){
            aNameList.add(i,"¡Pulsa aquí!");
        }
    }
}