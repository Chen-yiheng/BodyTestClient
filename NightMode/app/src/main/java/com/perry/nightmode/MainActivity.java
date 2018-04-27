package com.perry.nightmode;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements CompoundButton
        .OnCheckedChangeListener {
    private CheckBox zhuhuCheckBox, jianshuCheckBox;
    private TextView zhuhuView, jianshuView;
    private RecyclerView recycleView;
    private LinearLayout allLayout;
    private DayNightHelp dayNightHelp;
    private List<Fruit> fruitList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        dayNightHelp = new DayNightHelp(this);
        if (dayNightHelp.isDay()) {
            setTheme(R.style.DayTheme);
        } else {
            setTheme(R.style.NightTheme);
        }
        setContentView(R.layout.activity_main);
        initView();
        finishAddData();

    }

    private void initView() {
        zhuhuCheckBox = (CheckBox) findViewById(R.id.selectZhihu);
        jianshuCheckBox = (CheckBox) findViewById(R.id.selectJianshu);
        zhuhuCheckBox.setOnCheckedChangeListener(this);
        jianshuCheckBox.setOnCheckedChangeListener(this);
        zhuhuView = (TextView) findViewById(R.id.zhuhu_text);
        jianshuView = (TextView) findViewById(R.id.jianshu_text);
        recycleView = (RecyclerView) findViewById(R.id.recyclerView);
        allLayout = (LinearLayout) findViewById(R.id.activity_main);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        switch (compoundButton.getId()) {
            case R.id.selectJianshu:
                changeToJianshuNightMode();
                break;
            case R.id.selectZhihu:
                changeToZhihuNightMode();
                break;
        }
    }

    private void finishAddData() {
        fruitList = new ArrayList<>();
        fruitList.add(new Fruit(R.drawable.apple, "苹果", "苹果是一种很好吃的水果"));
        fruitList.add(new Fruit(R.drawable.banana, "香蕉", "吃香蕉要剥皮"));
        fruitList.add(new Fruit(R.drawable.orange, "橙子", "橙皮可以腌制，，也很好吃"));
        fruitList.add(new Fruit(R.drawable.watermelon, "西瓜", "西瓜是一种很好吃的水果"));
        fruitList.add(new Fruit(R.drawable.strawberry, "草莓", "草莓是一种很好吃的水果"));
        fruitList.add(new Fruit(R.drawable.grape, "葡萄", "葡萄是一种很好吃的水果"));
        fruitList.add(new Fruit(R.drawable.mango, "芒果", "芒果是一种很好吃的水果"));
        fruitList.add(new Fruit(R.drawable.pineapple, "地菠萝", "地菠萝是一种很好吃的水果"));
        fruitList.add(new Fruit(R.drawable.pear, "梨", "梨是一种很好吃的水果"));
        fruitList.add(new Fruit(R.drawable.cherry, "樱桃", "苹果是一种很好吃的水果"));
        fruitList.add(new Fruit(R.drawable.pear, "梨", "梨是一种很好吃的水果"));
        fruitList.add(new Fruit(R.drawable.pear, "梨", "梨是一种很好吃的水果"));
        fruitList.add(new Fruit(R.drawable.pear, "梨", "梨是一种很好吃的水果"));
        fruitList.add(new Fruit(R.drawable.pear, "梨", "梨是一种很好吃的水果"));
        fruitList.add(new Fruit(R.drawable.cherry, "樱桃", "樱桃是一种很好吃的水果"));
        fruitList.add(new Fruit(R.drawable.pear, "梨", "梨是一种很好吃的水果"));
        fruitList.add(new Fruit(R.drawable.pear, "梨", "梨是一种很好吃的水果"));
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        recycleView.setLayoutManager(layoutManager);
        recycleView.setAdapter(new RecyclerViewAdapter(fruitList, R.layout.recyclerview_item));
    }

    private void changeTheme() {
        if (dayNightHelp.isDay()) {
            setTheme(R.style.NightTheme);
            dayNightHelp.setMode(DayNightHelp.NIGHT);
        } else if (dayNightHelp.isNight()) {
            setTheme(R.style.DayTheme);
            dayNightHelp.setMode(DayNightHelp.DAY);
        }
    }


    private void changeToJianshuNightMode() {
        changeTheme();
        changeAllViewBgColor();

    }


    private void changeToZhihuNightMode() {
        showAnimation();
        changeTheme();
        changeAllViewBgColor();
    }

    private void changeAllViewBgColor() {
        TypedValue backgroundColor = new TypedValue();
        TypedValue textColor = new TypedValue();
        Resources.Theme theme = getTheme();
        theme.resolveAttribute(R.attr.backgroundColor, backgroundColor, true);
        theme.resolveAttribute(R.attr.textColor, textColor, true);
        allLayout.setBackgroundResource(backgroundColor.resourceId);
        Resources resources = getResources();
        zhuhuView.setTextColor(resources.getColor(textColor.resourceId));
        jianshuView.setTextColor(resources.getColor(textColor.resourceId));
        for (int i = 0; i < recycleView.getChildCount(); i++) {
            ViewGroup layout = (ViewGroup) recycleView.getChildAt(i);
            TextView textView = layout.findViewById(R.id.fruit_name);
            TextView textView1 = layout.findViewById(R.id.fruit_introduce);
            textView.setTextColor(resources.getColor(textColor.resourceId));
            textView1.setTextColor(resources.getColor(textColor.resourceId));
        }

        Class<RecyclerView> recyclerViewClass = RecyclerView.class;
        try {
            Field field = recyclerViewClass.getDeclaredField("mRecycler");
            field.setAccessible(true);
            Method method = Class.forName(RecyclerView.Recycler.class.getName())
                    .getDeclaredMethod("clear", new Class[0]);
            method.setAccessible(true);
            method.invoke(field.get(recycleView), new Object[0]);
            RecyclerView.RecycledViewPool recycledViewPool = recycleView.getRecycledViewPool();
            recycledViewPool.clear();

        } catch (Exception e) {

        }

    }

    private void showAnimation() {
        final View decorView = getWindow().getDecorView();
        Bitmap cacheBitmap = getCacheBitmapFromView(decorView);
        if (decorView instanceof ViewGroup && cacheBitmap != null) {
            final View view = new View(this);
            view.setBackgroundDrawable(new BitmapDrawable(getResources(), cacheBitmap));
            ViewGroup.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup
                    .LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            ((ViewGroup) decorView).addView(view, layoutParams);
            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, "alpha", 1f, 0f);
            objectAnimator.setDuration(300);
            objectAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    ((ViewGroup) decorView).removeView(view);
                }
            });
            objectAnimator.start();
        }

    }

    private Bitmap getCacheBitmapFromView(View view) {
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache(true);
        Bitmap drawingCache = view.getDrawingCache();
        Bitmap bitmap = null;
        if (drawingCache != null) {
            bitmap = Bitmap.createBitmap(drawingCache);
            view.setDrawingCacheEnabled(false);
        }
        return bitmap;
    }


}
