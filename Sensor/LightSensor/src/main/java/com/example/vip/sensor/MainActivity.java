package com.example.vip.sensor;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
	private TextView lightLevel;
	private SensorManager sensorManager;
	private SensorEventListener sensorListener = new SensorEventListener() {
		@Override
		public void onSensorChanged(SensorEvent event) {
			float value=event.values[0];
			lightLevel.setText("光亮程度是"+value+"lx");
		}

		@Override
		public void onAccuracyChanged(Sensor sensor, int accuracy) {

		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		lightLevel = (TextView) findViewById(R.id.lightLevel);
		sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		Sensor lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
		sensorManager.registerListener(sensorListener, lightSensor, SensorManager
			   .SENSOR_DELAY_NORMAL);

	}

	@Override
	protected void onDestroy() {
		if (null != sensorManager) {
			sensorManager.unregisterListener(sensorListener);
		}
	}
}
