package com.example.intent;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Activit六大属性之Extra
 * Extra:表示的是额外的内容，用来进行intent的传递数据
 * putExtra（键值对）键是字符串类型
 * putExtras:传递的是一个bundle类型的对象，该对象中可以放置很多数据
 *
 * Activit六大属性之data
 *
 */

public class NewActivity extends AppCompatActivity {
    private Button btn;
    private EditText et;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
        btn = (Button) findViewById(R.id.btn2);
        et= (EditText) findViewById(R.id.et);
        final Intent intent=new Intent();
        final Bundle bundle=new Bundle();

        intent.setAction("com.example.intent.NewActivity.action");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle.putString("info",et.getText().toString());
//              intent.putExtra("info",et.getText().toString());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });


    }

}
