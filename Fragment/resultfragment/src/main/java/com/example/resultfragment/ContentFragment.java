package com.example.resultfragment;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Random;

/**
 * 两个Fragment在同一个Activity中：例如，点击当前Fragment中按钮，弹出一个对话框（DialogFragment），
 * 在对话框中的操作需要返回给触发的Fragment中,由于是在一个Activity中，那么肯定不是使用startActivityForResult；
 * 但是我们返回的数据，依然在onActivityResult中进行接收。
 */

public class ContentFragment extends Fragment {
	private String title;
	private String backValue;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle bundle = getArguments();
		if (null != bundle) {
			title = bundle.getString(TitleFragment.ARG);
			switch (title) {
				case "成功":
					backValue = "在于矢志不移";
					break;
				case "天才":
					backValue = "在于努力";
					break;
				case "失败":
					backValue = "因为缺少了持之不懈";
					break;

			}
		}
	}

	public static ContentFragment newInstance(String key, String argument) {
		Bundle bundle = new Bundle();
		bundle.putString(key, argument);
		ContentFragment contentFragment = new ContentFragment();
		contentFragment.setArguments(bundle);
		return contentFragment;

	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
		   savedInstanceState) {
		Random random = new Random();
		TextView textView = new TextView(getActivity());
		ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams
			   .MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
		textView.setLayoutParams(params);
		textView.setText(title);
		textView.setTextSize(25);
		textView.setGravity(Gravity.CENTER);
		textView.setBackgroundColor(Color.argb(random.nextInt(100), random.nextInt(255), random
			   .nextInt(255), random.nextInt(255)));
		textView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				EvaluateDialog dialog=new EvaluateDialog();
				dialog.setTargetFragment(ContentFragment.this,TitleFragment.REQUEST_CODE);
				dialog.show(getFragmentManager(),"dialog");
			}
		});
		return textView;
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode==TitleFragment.REQUEST_CODE&&resultCode==TitleFragment.RESULT_CODE){
			String evaluate=data.getStringExtra(EvaluateDialog.DIALOG_ARGMENT);
			Intent intent = new Intent();
			intent.putExtra(TitleFragment.ARG, backValue+"---->"+evaluate);
			getActivity().setResult(TitleFragment.RESULT_CODE, intent);

		}
	}
}
