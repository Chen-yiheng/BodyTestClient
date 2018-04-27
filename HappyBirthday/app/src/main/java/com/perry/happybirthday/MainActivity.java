package com.perry.happybirthday;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout yourLayout, birthdayLayout, myLayout;
    private Fragment yourFragment, birthdayFragment, myFragment;
    private ImageView yourView, birthdayView, myView;
    private Toolbar toolbar;

    @Override 
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        yourLayout = (LinearLayout) findViewById(R.id.yourLayout);
        birthdayLayout = (LinearLayout) findViewById(R.id.birthdayLayout);
        myLayout = (LinearLayout) findViewById(R.id.myLayout);
        yourView = (ImageView) findViewById(R.id.yourView);
        birthdayView = (ImageView) findViewById(R.id.birthdayView);
        myView = (ImageView) findViewById(R.id.myView);
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        yourLayout.setOnClickListener(this);
        birthdayLayout.setOnClickListener(this);
        myLayout.setOnClickListener(this);
        yourFragment=new YourFragment();
        myFragment=new MyFragment();
        FragmentManager manager = getFragmentManager();
        birthdayFragment = manager.findFragmentById(R.id.fragment);
        if (birthdayFragment == null) {
            birthdayFragment = new BirthdayFragment();
            manager.beginTransaction().add(R.id.fragment, birthdayFragment).commit();
        }
        changeSelectedImage(R.id.birthdayView);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.yourLayout:
                changeSelectedImage(R.id.yourView);
                getFragmentManager().beginTransaction().replace(R.id.fragment,
                        yourFragment).commit();

                break;
            case R.id.birthdayLayout:
                changeSelectedImage(R.id.birthdayView);
                getFragmentManager().beginTransaction().replace(R.id.fragment, 
                        birthdayFragment).commit();
                break;
            case R.id.myLayout:
                changeSelectedImage(R.id.myView);
                getFragmentManager().beginTransaction().replace(R.id.fragment,
                        myFragment).commit();
                break;
        }
    }

    void changeSelectedImage(int i) {
        switch (i) {
            case R.id.yourView:
                toolbar.setVisibility(View.VISIBLE);
                yourView.setImageResource(R.drawable.your2);
                birthdayView.setImageResource(R.drawable.cake);
                myView.setImageResource(R.drawable.my);
                break;
            case R.id.birthdayView:
                toolbar.setVisibility(View.GONE);
                yourView.setImageResource(R.drawable.your);
                birthdayView.setImageResource(R.drawable.cake2);
                myView.setImageResource(R.drawable.my);
                break;
            case R.id.myView:
                toolbar.setVisibility(View.VISIBLE);
                yourView.setImageResource(R.drawable.your);
                birthdayView.setImageResource(R.drawable.cake);
                myView.setImageResource(R.drawable.my2);
                break;
        }
    }

}


















