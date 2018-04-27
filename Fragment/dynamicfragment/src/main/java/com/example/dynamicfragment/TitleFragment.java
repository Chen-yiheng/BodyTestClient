package com.example.dynamicfragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

/**
 * Created by vip on 2017/5/1.
 */

public class TitleFragment extends Fragment {
	private ImageButton imageButton;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
		   savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_title, container, false);
		imageButton = (ImageButton) view.findViewById(R.id.title_image);
		imageButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(getActivity(), "美猴王：我只是一只普通的猴子", Toast.LENGTH_SHORT).show();
			}
		});
		return view;
	}

}
