package com.example.vip.chatpage;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * 聊天页面主程序
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{
	private ListView listView;
	private EditText editText;
	private Button send;
	private MsgAdapter adapter;
	private List<Message>list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		listView= (ListView) findViewById(R.id.list_view);
		editText= (EditText) findViewById(R.id.text);
		send= (Button) findViewById(R.id.send);
		initMessage();
		adapter=new MsgAdapter(this,R.layout.list_item,list);
		send.setOnClickListener(this);
		listView.setAdapter(adapter);
	}
	private void initMessage(){
		list=new ArrayList<>();
		list.add(new Message(0,"我爱你"));
		list.add(new Message(0,"你爱我吗"));
		list.add(new Message(0,"请给我一次机会好吗？"));
	}

	@Override
	public void onClick(View v) {
		String str=editText.getText().toString();
		if(!"".equals(str)){
			list.add(new Message(1,str));
			adapter.notifyDataSetChanged();
			listView.setSelection(list.size());
			editText.setText("");

		}
	}
}
