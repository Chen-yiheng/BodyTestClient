package com.example.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 *
 */

public class Receiver1 extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {
		switch (intent.getAction()) {
			case MainActivity.MY_BROADCAST_ACTION:
				Toast.makeText(context, intent.getStringExtra(MainActivity.INTENT_FLAG) +
					   "receiver1", Toast.LENGTH_SHORT).show();
				break;
			case MainActivity.ORDERED_BROADCAST_ACTION:
				Toast.makeText(context, intent.getStringExtra(MainActivity.INTENT_FLAG) +
					   "receiver1第二", Toast.LENGTH_SHORT).show();
//				截断广播
				abortBroadcast();
				break;

		}
	}
}
