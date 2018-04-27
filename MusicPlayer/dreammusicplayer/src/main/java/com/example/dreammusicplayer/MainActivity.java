package com.example.dreammusicplayer;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;


import com.example.dreammusicplayer.Utils.Constant;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
	public static final int UPDATE = 0;
	public static final int ABOUT = 1;
	public static final int EXST = 2;
	public static final int DOWNLOAD = 3;
	private ArrayList<String> list;
	private ListView musicList;
	private SeekBar seekBar;
	private ArrayAdapter adapter;
	private Button play, nest, previous, playingActivity;
	private TextView playingMusic;
	private Handler handler;
	private MusicPlayerService musicService;
	public int playingIndex = 0;
	private ServiceConnection connection = new ServiceConnection() {
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			MusicPlayerService.MusicBinder binder = (MusicPlayerService.MusicBinder) service;
			musicService = binder.getService();
			Log.i("tag", "musicService:" + musicService);
		}


		@Override
		public void onServiceDisconnected(ComponentName name) {
			musicService = null;
		}
	};


	private TitleFragment fragment;
	public static boolean finsh;


	private Runnable checkWhetherPlay = new Runnable() {
		public void run() {
			try {
				handler.postDelayed(checkWhetherPlay, 5);
				if (null != musicService) {
					playingMusic.setText(list.get(musicService.playingMusicIndex));
					if (musicService.player.isPlaying()) {
						play.setBackgroundDrawable(getResources().getDrawable(R.drawable
							   .playing));
						seekBar.setMax(musicService.player.getDuration());
						seekBar.setProgress(musicService.player.getCurrentPosition());
					} else {
						play.setBackgroundDrawable(getResources().getDrawable(R.drawable
							   .pausing));
					}
				}
				if (finsh) {
					finsh = false;
					MainActivity.this.finish();
				}
			} catch (Resources.NotFoundException e) {
			}
		}
	};


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getSupportActionBar().hide();
		setContentView(R.layout.activity_main);
		play = (Button) findViewById(R.id.playing);
		nest = (Button) findViewById(R.id.nest);
		previous = (Button) findViewById(R.id.previous);
		playingActivity = (Button) findViewById(R.id.playingPage);
		playingMusic = (TextView) findViewById(R.id.playingMusic);
		musicList = (ListView) findViewById(R.id.lv);
		seekBar = (SeekBar) findViewById(R.id.musicSeekBar);
		list = new ArrayList<>();
		handler = new Handler();
		android.app.FragmentManager manager = getFragmentManager();
		fragment = (TitleFragment) manager.findFragmentById(R.id.title_fragment);
		if (null == fragment) {
			fragment = new TitleFragment();
		}
		manager.beginTransaction().add(R.id.title_fragment, fragment).commit();
		play.setOnClickListener(this);
		nest.setOnClickListener(this);
		previous.setOnClickListener(this);
		playingActivity.setOnClickListener(this);
		musicList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long
				   id) {
				playingIndex = position;
				comment(Constant.PLAY_COMMENT, playingIndex);
			}
		});
		loadMusicFile();
		seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
				if (fromUser) {
					musicService.player.seekTo(progress);
				}
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {

			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {

			}
		});

	}

	@Override
	protected void onResume() {
		super.onResume();
		if (null != musicService) {
			playingMusic.setText(list.get(musicService.playingMusicIndex));
		}
		handler.postDelayed(checkWhetherPlay, 10);
		bindService(new Intent(this, MusicPlayerService.class), connection, Context
			   .BIND_AUTO_CREATE);

	}

	public void comment(int comment, int index) {
		Intent intent = new Intent(MainActivity.this, MusicPlayerService.class);
		intent.putExtra(MusicPlayerService.INTENT_COMMENT, comment);
		switch (comment) {
			case Constant.PLAY_COMMENT:
				intent.putExtra(MusicPlayerService.INTENT_MUSIC_PATH, index);
				break;
			case Constant.UPDATE_COMMENT:
				intent.putStringArrayListExtra(MusicPlayerService.INTENT_LIST, list);
				break;
		}
		this.startService(intent);
	}


	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.playing:
				comment(Constant.PAUSE_COMMENT, 0);
				break;
			case R.id.nest:
				playingIndex++;
				checkPlayingIndex();
				comment(Constant.PLAY_COMMENT, playingIndex);
				break;
			case R.id.previous:
				playingIndex--;
				comment(Constant.PLAY_COMMENT, playingIndex);
				break;
			case R.id.playingPage:
				Intent intent = new Intent(this, LrcActivity.class);
				startActivity(intent);
				break;
		}

	}

	protected void checkPlayingIndex() {
		if (playingIndex < 0) {
			playingIndex = list.size() - 1;
		}
		if (playingIndex >= list.size()) {
			playingIndex = 0;
		}
	}

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//
//	}


	@Override
	public boolean onContextItemSelected(MenuItem item) {
		if (item.getItemId() == UPDATE) {
			loadMusicFile();
		} else if (item.getItemId() == ABOUT) {

		} else if (item.getItemId() == DOWNLOAD) {
			Intent intent = new Intent(MainActivity.this, DownloadActivity.class);
			startActivity(intent);
		} else if (item.getItemId() == EXST) {
			finish();
		}
		return super.onContextItemSelected(item);
	}

//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//
//	}

	public void loadMusicFile() {
		list.clear();
		File f = new File(Constant.SAVE_PATH);
		File[] files = f.listFiles();
		if (files == null) return;
		for (File file : files) {
			String fileName = file.getName();
			if (fileName.endsWith(".mp3")) {
				list.add(fileName);
			}
		}
		adapter = new ArrayAdapter<>(this, R.layout.simple_adapter_item, list);
		musicList.setAdapter(adapter);
		comment(Constant.UPDATE_COMMENT, 0);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

		handler.removeCallbacks(checkWhetherPlay);
		unbindService(connection);
	}
}
