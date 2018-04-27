package com.example.vip.autocomplete;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

/**
 * AutoCompleteTextview实现热门提示的效果
 */
public class MainActivity extends AppCompatActivity {
    private AutoCompleteTextView auto;
    private ArrayAdapter<String> adapter;
    private String[] tips = {"Android studio", "China", "asdgasd", "the girl is beautiful", "the boy is handsome", "the animal can't kill",
            "Android is a good language"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        auto= (AutoCompleteTextView) findViewById(R.id.auto);
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,tips);
        auto.setAdapter(adapter);

    }
}
