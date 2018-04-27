package com.example.compass;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
	private SensorManager sensorManager;
	private ImageView compass;

	private SensorEventListener listener = new SensorEventListener() {
		float[] accelerometerValues = new float[3];
		float[] magneticValues = new float[3];
		float lastRotateDegree;

		@Override
		public void onSensorChanged(SensorEvent event) {
			if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
				accelerometerValues = event.values.clone();
			} else if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
				magneticValues = event.values.clone();
			}
			float[] R = new float[9];
			float[] values = new float[3];
			sensorManager.getRotationMatrix(R, null, accelerometerValues, magneticValues);
			sensorManager.getOrientation(R, values);
			float rotateDegree = (float) -Math.toDegrees(values[0]);
			if (Math.abs(rotateDegree - lastRotateDegree) > 1) {
				RotateAnimation rotateAnimation = new RotateAnimation(lastRotateDegree,
					   rotateDegree, Animation.RELATIVE_TO_SELF, 0.5f, Animation
					   .RELATIVE_TO_SELF, 0.5f);
				rotateAnimation.setFillAfter(true);
				compass.startAnimation(rotateAnimation);
				lastRotateDegree = rotateDegree;
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
		compass = (ImageView) findViewById(R.id.compass);
		sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		Sensor accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		Sensor magneticSensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
		sensorManager.registerListener(listener, accelerometerSensor, SensorManager
			   .SENSOR_DELAY_GAME);
		sensorManager.registerListener(listener, magneticSensor, SensorManager
			   .SENSOR_DELAY_GAME);
	}


}
