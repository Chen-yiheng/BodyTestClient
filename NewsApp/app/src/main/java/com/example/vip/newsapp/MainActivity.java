package com.example.vip.newsapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * 简易版的新闻App，该App既能兼容手机也能兼容平板，xit将会自动检测，如屏幕宽度大于320dp时，
 * 会加载acticity_main.xml(sw320dp)这个布局，否则加载activity_main.xml这个布局.
 */
public class MainActivity extends AppCompatActivity {
	private ListView titleList;
	private List<News> newsList;
	private NewsAdapter adapter;
	private ContentFragment fragment;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getSupportActionBar().hide();
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager
			   .LayoutParams.FLAG_FULLSCREEN);
		titleList = (ListView) findViewById(R.id.list_view);
		initNewList();
		adapter = new NewsAdapter(this, R.layout.adapter_item, newsList);
		titleList.setAdapter(adapter);
		fragment = (ContentFragment) getFragmentManager().findFragmentById(R.id
			   .content_fragment);
		titleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				if (null == MainActivity.this.findViewById(R.id.content_fragment)) {
					ContentActivity.actionStart(MainActivity.this, newsList.get(position));
				} else {
					fragment.refreshNews(newsList.get(position));
				}
			}
		});


	}

	private void initNewList() {
		newsList = new ArrayList<>();
		newsList.add(new News("中国发射卫星失败",
			   "中国于2017年7月2号凌晨5点30分发射长征5" +
					 "号卫星失败，该卫星采用全新的技术，" +
					 "在国际上找不到任何可参考资料。虽然失败了，" +
					 "但我相信中国的科技人员会总结教训，找出问题，" +
					 "一定会解决这个难题的。"));
		newsList.add(new News("印度军队非法闯入中国领土",
			   "2017年7月2号，印度军队非法越过中印国界，并拒绝退出中国领土。" +
					 "中国外交部，发出严重警告。但我觉得，还警告个屁，" +
					 "人家都在头上拉屎了，还警告，应该打他妈的，打他也打他妈"));
	}
}
