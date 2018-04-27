package com.example.vip.imageview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by vip on 2017/3/5.
 */
public class MyActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView image;
    private Button previous, next;
    private int[] images = {R.drawable.a, R.drawable.a1, R.drawable.a3, R.drawable.a4, R.drawable.a5, R.drawable.a6, R.drawable.a7, R.drawable.a8, R.drawable.a9, R.drawable.a10, R.drawable.a11, R.drawable.a1, R.drawable.a13};
    private int coursor;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_show);
        image = (ImageView) findViewById(R.id.Ime);
        previous = (Button) findViewById(R.id.previous);
        next = (Button) findViewById(R.id.next);
        previous.setOnClickListener(this);
        next.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.previous:
                coursor--;
                if(coursor<0){
                    coursor=images.length-1;
                }
                image.setImageResource(images[coursor]);
                break;
            case R.id.next:
                coursor++;
                if(coursor>images.length-1){
                    coursor=0;
                }image.setImageResource(images[coursor]);
                break;

        }

    }
}
