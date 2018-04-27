package com.example.broadcastreceiver;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * 测试发送标准广播和有序广播
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
	private Button sendBroadcast, sendOrderedBroadcast;
	public final static String MY_BROADCAST_ACTION = "android.MY_BROADCAST";
	public final static String ORDERED_BROADCAST_ACTION = "android.ordered_broadcast";
	public final static String INTENT_FLAG = "broadcast";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		sendBroadcast = (Button) findViewById(R.id.send1);
		sendOrderedBroadcast = (Button) findViewById(R.id.send2);
		sendBroadcast.setOnClickListener(this);
		sendOrderedBroadcast.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.send1:
				Intent intent = new Intent(MY_BROADCAST_ACTION);
				intent.putExtra(INTENT_FLAG, "普通广播");
				this.sendBroadcast(intent);
				break;
			case R.id.send2:
				Intent intent1 = new Intent(ORDERED_BROADCAST_ACTION);
				intent1.putExtra(INTENT_FLAG, "有序广播");
				//发送有序广播，可以在manifest中设置接收者的优先级，优先级越高者，
				// 先开始接收，而且高优先级者可以截断和修改广播，使后面的人无法接收到广播
				sendOrderedBroadcast(intent1, null);
				break;
		}


	}
}
