package com.example.vip.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;


/**
 *
 */
public class MyBroadcastReceiver extends BroadcastReceiver {
	private TextView show;

	public MyBroadcastReceiver(TextView show) {
		this.show = show;
		Log.i("tag", "创建一个新对象");
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.i("tag", "onReceive");
		Bundle bundle = intent.getExtras();
		String str = "姓名：" + bundle.getString("name") + "\n年龄：" + bundle.getInt("age") + "\n性别："
			   + bundle.getString("sex");
		show.setText(str);

	}
}
