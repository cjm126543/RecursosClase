package com.example.tareamain2subactivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Activity2 extends AppCompatActivity {

    Button volver, seguir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        Intent intent = getIntent();
        volver = (Button) findViewById(R.id.button2);
        seguir = (Button) findViewById(R.id.button3);
        seguir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Activity2.this, Activity3.class));
            }
        });
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Activity2.this, MainActivity.class));
                finish();
            }
        });
    }
}