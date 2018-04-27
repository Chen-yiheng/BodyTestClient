package com.example.serializble;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 *
 */

public class SecondActivity extends AppCompatActivity {
	private TextView textView;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		textView = (TextView) findViewById(R.id.textview);
		Intent intent = getIntent();
		Persion persion = (Persion) intent.getSerializableExtra("persion");
		People people = intent.getParcelableExtra("people");
		textView.setText(persion.getName() + "," + persion.getAge() + "," + persion.getSex() +
			   "\n" + people.getName() + "," + people.getAge() + "," + people.getSex());


	}
}
