package com.example.back4appmvcsubactivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.back4appmvcsubactivity.Model.Comment;
import com.example.back4appmvcsubactivity.Model.InterestPoint;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


import com.example.back4appmvcsubactivity.databinding.ActivityMainBinding;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private static final int SHOW_EDIT_ACTIVITY = 1;
    private static final int SHOW_ADD_ACTIVITY = 2;
    private ListView listView;
    TravelPointsApplication tpa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(tpa, AddPointActivity.class);
                startActivityForResult(intent, SHOW_ADD_ACTIVITY);
            }
        });

        listView = (ListView) findViewById(R.id.list);
        tpa = (TravelPointsApplication)getApplicationContext();

        tpa.getCollection().getServerPointsUpdate(listView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                InterestPoint item = (InterestPoint) listView.getItemAtPosition(position);
                Bundle bundle = new Bundle();
                bundle.putInt("position", position);
                bundle.putDouble("latitud", item.getLatitud());
                bundle.putDouble("longitud", item.getLongitud());
                Intent intent = new Intent(tpa, DisplayActivity.class);
                intent.putExtras(bundle);
                startActivityForResult(intent, SHOW_EDIT_ACTIVITY);

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       switch (item.getItemId()) {
            case R.id.action_get: {
               tpa.getCollection().getServerPointsUpdate(listView);
                break;
            }
            case R.id.action_new: {
                Intent intent = new Intent(tpa, AddPointActivity.class);
                startActivityForResult(intent, SHOW_ADD_ACTIVITY);
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SHOW_EDIT_ACTIVITY) {
                Bundle bundle = data.getExtras();
                String name = bundle.getString("name");
                int position = bundle.getInt("position");
                InterestPoint item = (InterestPoint) listView.getItemAtPosition(position);

                if (name == null) {
                    String comTxt = bundle.getString("commentText");
                    Comment aComment = new Comment();
                    aComment.setTexto(comTxt);
                    tpa.getCommentsCollection().addObjectUpdate(aComment, item, listView);
                } else {
                    item.setNombre(name);
                    tpa.getCollection().addObjectUpdate(item,listView);
                }

            } else if (requestCode == SHOW_ADD_ACTIVITY) {
                Bundle bundle = data.getExtras();
                Double latitud = bundle.getDouble("latitud");
                Double longitud = bundle.getDouble("longitud");
                InterestPoint aInterestPoint = new InterestPoint();
                //aInterestPoint.setNombre(name);
                aInterestPoint.setLatitud(latitud);
                aInterestPoint.setLongitud(longitud);

                tpa.getCollection().addObjectUpdate(aInterestPoint,listView);

            }

        }
    }




}