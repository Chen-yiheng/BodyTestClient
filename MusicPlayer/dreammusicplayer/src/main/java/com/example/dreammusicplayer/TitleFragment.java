package com.example.dreammusicplayer;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;


/**
 * Created by vip on 2017/5/16.
 */

public class TitleFragment extends Fragment implements View.OnClickListener {
	private ImageButton menu;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}



	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
		   savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_title, container, false);
		menu = (ImageButton) view.findViewById(R.id.menu);
		menu.setOnClickListener(this);
		menu.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
			@Override
			public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu
				   .ContextMenuInfo menuInfo) {
				menu.add(0, MainActivity.UPDATE, 1, R.string.refresh);
				menu.add(0, MainActivity.ABOUT, 3, R.string.about);
				menu.add(0, MainActivity.EXST, 4, R.string.exst);
				menu.add(0, MainActivity.DOWNLOAD, 2, R.string.download);
			}
		});
		return view;
	}

	@Override
	public void onClick(View v) {
		menu.showContextMenu();
	}




}
