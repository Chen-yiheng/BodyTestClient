package com.example.vip.lbs;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class MainActivity extends AppCompatActivity {
	private TextView show;
	private LocationManager locationManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		show = (TextView) findViewById(R.id.show);
		locationManager = (LocationManager) getSystemService(Context
			   .LOCATION_SERVICE);
		List<String> providerList = locationManager.getProviders(true);

		String provider = "";
		if (providerList.contains(LocationManager.GPS_PROVIDER)) {
			provider = LocationManager.GPS_PROVIDER;
		} else if (providerList.contains(LocationManager.NETWORK_PROVIDER)) {
			provider = LocationManager.NETWORK_PROVIDER;
		} else {
			Toast.makeText(this, "not provider can use", Toast.LENGTH_SHORT).show();
			return;
		}
		Log.w("tag", "已获得provider:" + provider);
		if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
			   != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
			   Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager
			   .PERMISSION_GRANTED) {
			return;
		}
		Location location = locationManager.getLastKnownLocation(provider);
		showLocation(location);
		locationManager.requestLocationUpdates(provider, 3000, 1, locationListener);
	}

	private LocationListener locationListener = new LocationListener() {
		@Override
		public void onLocationChanged(Location location) {
			showLocation(location);
		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {

		}

		@Override
		public void onProviderEnabled(String provider) {

		}

		@Override
		public void onProviderDisabled(String provider) {

		}
	};

	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			show.setText((String) msg.obj);
		}
	};

	/**
	 * Geocoding API，使用它的话也可以完成反向地理编码的工作
	 * 其工作原理是向谷歌的服务器发起一条 HTTP 请求，并将经纬度的值作为参数一同传递过去，
	 * 然后服务器会帮我们将这个经纬值转换成看得懂的位置信息，再将这些信息返回给手机端，
	 * 最后手机端去解析服务器返回的信息，并进行处理就可以了
	 *
	 * @param location     
	 */
	private void showLocation(Location location) {
		if (null == location) return;
		final String str = "经度：" + location.getLongitude() + "\n" + "维度：" +
			   location.getLatitude() + "\n";
		final StringBuffer urlBuffer = new StringBuffer();
		urlBuffer.append("http://maps.googleapis.com/maps/api/geocode/json?latlng=");
		urlBuffer.append(location.getLatitude()).append(",");
		urlBuffer.append(location.getLongitude());
		urlBuffer.append("&sensor=false");
		HttpUtil.sendHttpRequest(urlBuffer.toString(), new HttpListener() {
			@Override
			public void onFinish(String response) {
				try {
					JSONObject jsonObject = new JSONObject(response);
					JSONArray jsonArray = jsonObject.getJSONArray("results");
					if (jsonArray.length() > 0) {
						String address = jsonArray.getJSONObject(0).getString
							   ("formatted_address");
						Message message = new Message();
						message.obj = str + address;
						handler.sendMessage(message);
					}

				} catch (JSONException e) {
					e.printStackTrace();
				}
			}

			@Override
			public void onError(Exception e) {

			}
		});
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (null != locationManager) {
			if (ActivityCompat.checkSelfPermission(this, Manifest.permission
				   .ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
				   ActivityCompat.checkSelfPermission(this, Manifest.permission
						 .ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
			}
			locationManager.removeUpdates(locationListener);
		}

	}
}
