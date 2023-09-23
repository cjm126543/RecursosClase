package com.example.back4appmvcsubactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

public class CommentListActivity extends AppCompatActivity {

    int position;
    TravelPointsApplication tpa;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_list);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        position = bundle.getInt("position");
        tpa = (TravelPointsApplication) getApplicationContext();
        listView = (ListView) findViewById(R.id.list_comments);

        tpa.getCommentsCollection().getServerCommentsUpdate(tpa.getOneInterestPoint(position), listView);
    }
}