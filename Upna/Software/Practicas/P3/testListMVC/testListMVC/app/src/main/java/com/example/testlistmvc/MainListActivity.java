package com.example.testlistmvc;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.testlistmvc.Modelos.InterestPoint;

public class MainListActivity extends Activity {

    ListView listView;
    ArrayAdapter<InterestPoint> todoItemsAdapter;
    TravelPointsApplication tpa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.list);
        tpa = (TravelPointsApplication)getApplicationContext();
        todoItemsAdapter = new ArrayAdapter<InterestPoint>(this, R.layout.row_layout, R.id.listText, tpa.getPoints());
        listView.setAdapter(todoItemsAdapter);
    }



}
