package com.example.back4appmvcsubactivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DisplayActivity extends Activity {

	private static final int ACTIVITY_RESULT = 9;
    Double lon,lat;
    int position;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display);
		Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        lon= bundle.getDouble("longitud");
		lat= bundle.getDouble("latitud");
        position= bundle.getInt("position");
        TextView textView =((TextView) findViewById(R.id.text_longitud));
		textView.setTextSize(40);
		textView.setText(lon.toString());
		TextView textView2 =((TextView) findViewById(R.id.text_latitud));
		textView2.setTextSize(40);
		textView2.setText(lat.toString());

		// Show comment button
		Button showComms = (Button) findViewById(R.id.button_comms);
		showComms.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Bundle bun = new Bundle();
				bun.putInt("position", position);
				Intent intent1 = new Intent(DisplayActivity.this, CommentListActivity.class);
				intent1.putExtras(bun);
				startActivityForResult(intent1, ACTIVITY_RESULT);
			}
		});

		// Create comment button
		Button createComm = (Button) findViewById(R.id.button_comms2);
		createComm.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				EditText commentText = (EditText) findViewById(R.id.edit_comment);
				// Anhadir un nuevo comentario asociado al punto
				Bundle bun = new Bundle();
				bun.putInt("position", position);
				bun.putString("commentText", commentText.getText().toString());
				Intent in = new Intent();
				in.putExtras(bun);
				setResult(RESULT_OK, in);
				finish();
			}
		});

	}

	public void saveLocationName(View view) {
		Bundle bundle = new Bundle();
		bundle.putInt("position", position);
        EditText editText = (EditText) findViewById(R.id.edit_point_name);
        String point_name = editText.getText().toString();
        bundle.putString("name", point_name);
        Intent intent = new Intent();
		intent.putExtras(bundle);
		setResult(RESULT_OK, intent);
		finish();
	}
}
