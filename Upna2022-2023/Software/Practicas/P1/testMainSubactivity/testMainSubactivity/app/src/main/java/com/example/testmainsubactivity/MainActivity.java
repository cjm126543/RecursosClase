package com.example.testmainsubactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final int SHOW_SUBACTIVITY = 1;
    String name="no_name";

    public void displayMessage(View view) {
        Bundle bundle = new Bundle();
        bundle.putString("name", name);
        Intent intent = new Intent(getApplicationContext(), DisplayActivity.class);
        intent.putExtras(bundle);
        startActivityForResult(intent, SHOW_SUBACTIVITY);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            Bundle bundle = data.getExtras();
            String name= bundle.getString("name");
            TextView textView =((TextView) findViewById(R.id.textViewDisplay));
            textView.setTextSize(40);
            textView.setText(name);
        }
    }

}