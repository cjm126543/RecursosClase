package com.example.p2testlistmenufloat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddNameActivity extends AppCompatActivity {
    private final int RESULT_OK = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_name);
        Button but = (Button) findViewById(R.id.sendButton);
        EditText inTxt = (EditText) findViewById(R.id.editText);

        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = inTxt.getText().toString();
                Intent intent = new Intent();
                intent.putExtra("msg", input);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}