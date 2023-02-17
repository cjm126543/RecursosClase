package com.example.p2testlistmenufloat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        Bundle bundle = getIntent().getExtras();
        String msg = bundle.getString("mensaje seleccionado");
        TextView txtv = (TextView) findViewById(R.id.textView);
        txtv.setText(msg);
    }
}