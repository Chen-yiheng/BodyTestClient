package com.example.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 *
 */

public class Receiver2 extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {
		switch (intent.getAction()) {
			case MainActivity.MY_BROADCAST_ACTION:
				Toast.makeText(context, intent.getStringExtra(MainActivity.INTENT_FLAG) +
					   "receiver2", Toast.LENGTH_SHORT).show();
				break;
			case MainActivity.ORDERED_BROADCAST_ACTION:
				Toast.makeText(context, intent.getStringExtra(MainActivity.INTENT_FLAG) +
					   "receiver2第三", Toast.LENGTH_SHORT).show();
				break;

		}
	}
}
