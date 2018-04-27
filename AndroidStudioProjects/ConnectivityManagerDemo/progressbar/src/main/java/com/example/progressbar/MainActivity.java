package com.example.progressbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

/**
 * 进度条的风格不一样，有默认的圆形的，这种不能显示进度，
 * 也有横着的风格，这种可以设置两个进度
 *
 */
public class MainActivity extends AppCompatActivity {
     private Button progress;
     private ProgressBar bar;


     @Override
     protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_main);
          bar= (ProgressBar) findViewById(R.id.bar);
          progress= (Button) findViewById(R.id.progress);

          progress.setOnClickListener(new View.OnClickListener() {
               int i=0;
               @Override
               public void onClick(View v) {
                    if(0==i){
                         bar.setVisibility(View.VISIBLE);
                    }
                    if(i<100){
                         bar.setProgress(i);
                         bar.setSecondaryProgress(i+10);
                         i=i+10;
                    }else{
                         bar.setVisibility(View.GONE);
                         i=0;
                    }

               }
          });
     }
}
