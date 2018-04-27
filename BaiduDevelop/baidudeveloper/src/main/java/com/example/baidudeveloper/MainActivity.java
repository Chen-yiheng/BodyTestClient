package com.example.baidudeveloper;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.map.LocationData;
import com.baidu.mapapi.map.MapController;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationOverlay;
import com.baidu.mapapi.map.PopupClickListener;
import com.baidu.mapapi.map.PopupOverlay;
import com.baidu.platform.comapi.basestruct.GeoPoint;

import java.util.List;

/**
 * SHA1:1F:C9:90:BF:63:45:5E:E9:7E:13:17:0C:BC:3F:DE:A7:1D:3E:A0:BD
 */
public class MainActivity extends AppCompatActivity {
	private BMapManager bMapManager;
	private MapView mapView;
	private LocationManager locationManager;
	private LocationListener locationListener = new LocationListener() {
		@Override
		public void onLocationChanged(Location location) {
			showLocationOnMap(location);
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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		bMapManager = new BMapManager(this);
		bMapManager.init("p06vA7PoUEdV9Is8wqk2njCicn0s6411", null);
		setContentView(R.layout.activity_main);
		mapView = (MapView) findViewById(R.id.mapView);
		mapView.setBuiltInZoomControls(true);
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		String provider = "";
		List<String> providerList = locationManager.getProviders(true);
		if (providerList.contains(LocationManager.GPS_PROVIDER)) {
			provider = LocationManager.GPS_PROVIDER;
		} else if (providerList.contains(LocationManager.NETWORK_PROVIDER)) {
			provider = LocationManager.NETWORK_PROVIDER;
		} else {
			Toast.makeText(this, "没有位置提供者可以使用，请打开位置服务", Toast.LENGTH_SHORT).show();
			return;
		}
		if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
			   != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
			   Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager
			   .PERMISSION_GRANTED) {
			return;
		}
		Location location = locationManager.getLastKnownLocation(provider);
		showLocationOnMap(location);
		locationManager.requestLocationUpdates(provider, 1000, 1, locationListener);

	}

	private void showLocationOnMap(Location location) {
		if (null == location) return;
		MapController controller = mapView.getController();
		controller.setZoom(17);
		GeoPoint point = new GeoPoint((int) (location.getLatitude() * 1E6), (int) (location
			   .getLongitude() * 1e6));
		controller.setCenter(point);
		LocationData locationData = new LocationData();
		locationData.latitude = location.getLatitude();
		locationData.longitude = location.getLongitude();
		MyLocationOverlay overlay = new MyLocationOverlay(mapView);
		overlay.setData(locationData);
		mapView.getOverlays().add(overlay);
		mapView.refresh();
		PopupOverlay popupoverlay = new PopupOverlay(mapView, new PopupClickListener() {
			@Override
			public void onClickedPopup(int i) {
				Toast.makeText(MainActivity.this, "点击了第" + i + "张图片", Toast.LENGTH_SHORT)
					   .show();
			}
		});
		Bitmap[] bitmaps = new Bitmap[3];
		bitmaps[0] = BitmapFactory.decodeResource(getResources(), R.drawable.left);
		bitmaps[1] = BitmapFactory.decodeResource(getResources(), R.drawable.middle);
		bitmaps[2] = BitmapFactory.decodeResource(getResources(), R.drawable.right);
		popupoverlay.showPopup(bitmaps, point, 19);
	}

	@Override
	protected void onResume() {
		mapView.onResume();
		if (bMapManager != null) {
			bMapManager.start();
		}
		super.onResume();
	}

	@Override
	protected void onPause() {
		mapView.onPause();
		if (null != bMapManager) {
			bMapManager.stop();
		}
		super.onPause();
	}

	@Override
	protected void onDestroy() {
		mapView.destroy();
		if (bMapManager != null) {
			bMapManager.destroy();
			bMapManager = null;
		}
		if (null != locationManager) {
			if (ActivityCompat.checkSelfPermission(this, Manifest.permission
				   .ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
				   ActivityCompat.checkSelfPermission(this, Manifest.permission
						 .ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
				return;
			}
			locationManager.removeUpdates(locationListener);
		}
		super.onDestroy();
	}
}
