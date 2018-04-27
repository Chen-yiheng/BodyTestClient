package com.example.resultfragment;

import android.app.Fragment;

/**
 * Created by vip on 2017/5/3.
 */

public class ContentActivity extends SingleFragmentActivity {
	@Override
	protected Fragment createFragment() {
		String title=getIntent().getStringExtra(TitleFragment.ARG);
		ContentFragment fragment=ContentFragment.newInstance(TitleFragment.ARG,title);
		return fragment;
	}
}
