package com.example.vip.imageview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * src:就是I
 *
 */
public class FullscreenActivity extends AppCompatActivity {
    private ImageView image;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);
        image= (ImageView) findViewById(R.id.image);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(FullscreenActivity.this,"点击了黄益凛",Toast.LENGTH_SHORT);
            }
        });

    }
}
