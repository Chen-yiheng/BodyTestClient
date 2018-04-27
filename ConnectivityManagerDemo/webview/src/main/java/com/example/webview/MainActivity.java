package com.example.webview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.Toast;

/**
 * 在android中，如果需要进行网页数据的访问，在底层有一个高速的浏览引擎WebKit可以实现。
 * WebKit是定义在核心系统库中的，没有方法直接进行访问，所以在sdk中封装了一个类WebView，
 * 可以调用底层的Webkit进行网页数据的访问
 * 即如果在android运用程序中需要进行网页数据的渲染访问，使用的类WebView
 * <p>
 * wv.setWebViewClient(new WebViewClient());设置本地客户端显示页面
 */
public class MainActivity extends AppCompatActivity {
     private EditText webAddress;
     private WebView wv;
     private String addressInfo;

     @Override
     protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_main);
          webAddress = (EditText) findViewById(R.id.web_address);
          wv = (WebView) findViewById(R.id.wv);
          webAddress.setSelection(webAddress.length());
     }

     public void goWeb(View view) {
          addressInfo = webAddress.getText().toString();
          if (!TextUtils.isEmpty(addressInfo.trim())) {
               wv.loadUrl(addressInfo);
               wv.setWebViewClient(new WebViewClient());
          } else {
               Toast.makeText(MainActivity.this, "请检查输入的信息", Toast.LENGTH_SHORT).show();
          }
     }

     @Override
     public boolean onCreateOptionsMenu(Menu menu) {
          menu.add(0,1,1,"退出");
          menu.add(0,2,2,"关于");
          return super.onCreateOptionsMenu(menu);
     }

     @Override
     public boolean onOptionsItemSelected(MenuItem item) {
          if(item.getItemId()==1){
              finish();
          }
          if(item.getItemId()==2){
               Intent intent=new Intent(MainActivity.this,AboutActivity.class);
               startActivity(intent);
          }
          return super.onOptionsItemSelected(item);
     }

     @Override
     public void onBackPressed() {
          if (wv.canGoBack()) {
               wv.goBack();
          } else {
               super.onBackPressed();
          }
     }
}
