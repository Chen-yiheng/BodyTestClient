package com.example.resultfragment;

import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 *
 * 点击跳转到对应Activity的Fragment中，并且希望它能够返回参数，
 * 那么我们肯定是使用Fragment.startActivityForResult ;
 * 在Fragment中存在startActivityForResult（）以及onActivityResult（）方法，
 * 但是呢，没有setResult（）方法，用于设置返回的intent，
 * 这样我们就需要通过调用getActivity().setResult(ListTitleFragment.REQUEST_DETAIL, intent);。
 */
public class TitleActivity extends SingleFragmentActivity {

	@Override
	protected Fragment createFragment() {
		return new TitleFragment();
	}
}
