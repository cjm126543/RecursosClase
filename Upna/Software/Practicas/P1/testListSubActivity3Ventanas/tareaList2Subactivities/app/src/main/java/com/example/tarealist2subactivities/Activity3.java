package com.example.tarealist2subactivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Activity3 extends AppCompatActivity {

    Button vent1, vent2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);
        vent1 = (Button) findViewById(R.id.buttonVent1);
        vent2 = (Button) findViewById(R.id.buttonVent2);

        vent1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Activity3.this, MainActivity.class));
                finish();
            }
        });

        vent2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Activity3.this, Activity2.class));
                finish();
            }
        });
    }
}