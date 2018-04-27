package com.example.intent;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class ThirdActivity extends AppCompatActivity {
     private TextView tv;
     private Button call,send,browse;

     @Override
     protected void onCreate(@Nullable Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_third);
          tv = (TextView) findViewById(R.id.third_tv);
          call = (Button) findViewById(R.id.call);
          send= (Button) findViewById(R.id.send);
          browse= (Button) findViewById(R.id.browse);
          Intent intent = getIntent();

          if (null != intent) {
//               String info = intent.getStringExtra("info");
               Bundle bundle = intent.getExtras();
               final String str = bundle.getString("info");
               tv.setText(/*info+"\n"+*/str);

               call.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                         takeCall(str);
                    }
               });
               send.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                         takeSend(str);
                    }

               });

               browse.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                         takeBrowse(str);
                    }
               });



          }



     }

     /**
      * 跳到所要浏览网页的页面
      */
     private void takeBrowse(String str) {
          Intent intent=new Intent();
          intent.setAction(Intent.ACTION_VIEW);
          intent.setData(Uri.parse("http://"+str));
          startActivity(intent);
     }

     /**
      * 跳到系统发送信息的activity
      * @param str
      */
     private void takeSend(String str) {
          Intent intent=new Intent();
          intent.setData(Uri.parse("smsto://"+str));
          intent.setAction(Intent.ACTION_SENDTO);
          startActivity(intent);
     }

     /**
      * 跳到拨打电话的activity
      * @param str
      */
     public void takeCall(String str) {
          Intent intent = new Intent();
          intent.setData(Uri.parse("tel://" + str));
//          intent.setAction(Intent.ACTION_DIAL);//转到拨号页面
          intent.setAction(Intent.ACTION_CALL);//直接拨号，需要权限
          startActivity(intent);
     }


}
