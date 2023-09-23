package com.example.testListMVCMenuFloat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.testListMVCMenuFloat.Modelos.InterestPoint;

public class MainListActivity extends Activity {

    ListView listView;
    ArrayAdapter<InterestPoint> todoItemsAdapter;
    TravelPointsApplication tpa;
    final int MODIFICATION_COMPLETE = 1;
    final int RESULT_OK = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.list);
        tpa = (TravelPointsApplication)getApplicationContext();
        todoItemsAdapter = new ArrayAdapter<InterestPoint>(this, R.layout.row_layout, R.id.listText, tpa.getPoints());
        listView.setAdapter(todoItemsAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Bundle bundle = new Bundle();
                bundle.putInt("position", i);
                //Intent intent = new Intent(MainListActivity.this, Activity2.class);
                //intent.putExtras(bundle);
                //startActivityForResult(intent, MODIFICATION_COMPLETE);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

            todoItemsAdapter.notifyDataSetChanged();

    }



}
