package com.example.testlistsubactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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
    }

    public void initializeList(List<String> aNameList) {
        for(int i=0; i<5; i++){
            aNameList.add(i,"mismo nombre, press");
        }
    }
}