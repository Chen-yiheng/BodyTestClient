package com.example.vip.service;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
	private ServiceConnection  serviceConnection;
	private TextView calculationInfo;
	private int a,b;
	private boolean isBind;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		calculationInfo= (TextView) findViewById(R.id.calculationInfo);
		serviceConnection=new ServiceConnection() {
			@Override
			public void onServiceConnected(ComponentName name, IBinder service) {
				MyService.MyBinder binder= (MyService.MyBinder) service;
				Log.i("tag","3333+5555="+binder.add(3333,5555));
				Log.i("tag",binder.getService().toString());
				calculationInfo.setText("3333+5555="+binder.add(3333,5555));
			}

			@Override
			public void onServiceDisconnected(ComponentName name) {

			}
		};
	}

	public void onBind(View view){
		bindService(new Intent(this,MyService.class),serviceConnection,BIND_AUTO_CREATE);
		isBind=true;

	}
	public void onUnbind(View view){
		if(isBind){
			unbindService(serviceConnection);
		}
	}

//	public void onCalculation(View view){
//		String str=calculationInfo.getText().toString();
//		getAandB(str,"+");
//		getAandB(str,"-");
//		getAandB(str,"*");
//		getAandB(str,"/");
//
//
//	}

	public void  getAandB(String str,String separator){
		if(str.contains(separator)){
			a=Integer.getInteger(str.substring(0,str.indexOf(separator)));
			if(str.contains("=")){
				b=Integer.getInteger(str.substring(str.indexOf(separator),str.indexOf("=")));
			}else {
				b=Integer.getInteger(str.substring(str.indexOf(separator)));
				calculationInfo.setText(calculationInfo.getText()+"=");
			}
		}
	}
}
