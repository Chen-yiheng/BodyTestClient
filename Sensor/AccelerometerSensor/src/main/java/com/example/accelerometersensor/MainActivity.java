package com.example.accelerometersensor;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
	private SensorManager sensorManager;
	private SensorEventListener listener = new SensorEventListener() {
		@Override
		public void onSensorChanged(SensorEvent event) {
			float xAcce = Math.abs(event.values[0]);
			float yAcce = Math.abs(event.values[1]);
			float zAcce = Math.abs(event.values[2]);
			if (xAcce > 15 || yAcce > 15 || zAcce > 15) {
				Toast.makeText(MainActivity.this, "手机摇一摇", Toast.LENGTH_SHORT).show();
			}
		}

		@Override
		public void onAccuracyChanged(Sensor sensor, int accuracy) {
		}
	};


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		sensorManager.registerListener(listener, sensor,
			   SensorManager.SENSOR_DELAY_NORMAL);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (null != sensorManager) {
			sensorManager.unregisterListener(listener);
		}
	}
}
