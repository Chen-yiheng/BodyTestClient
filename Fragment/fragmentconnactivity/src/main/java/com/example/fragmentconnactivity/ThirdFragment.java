package com.example.fragmentconnactivity;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by vip on 2017/5/1.
 */

public class ThirdFragment extends Fragment implements View.OnClickListener{
	private Button third;


	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
		   savedInstanceState) {
		View view=inflater.inflate(R.layout.fragment_third,container,false);
		third= (Button) view.findViewById(R.id.thirdFragment);
		third.setOnClickListener(this);
		return view;
	}

	@Override
	public void onClick(View v) {
		if(getActivity()instanceof OnFragmentClikListener){
			((OnFragmentClikListener)getActivity()).onClik(v);
		}

	}
}
