package com.example.playmusic;

import android.media.MediaPlayer;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener,
	   View.OnClickListener {
	private Button preMusic, nestMusic, playAndPause;
	private ListView listView;
	private List<String> musicList;
	private MediaPlayer mediaPlayer;
	private ArrayAdapter<String> adapter;
	private int playingIndex;
	private TextView playingMucicName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		listView = (ListView) findViewById(R.id.music_list);
		preMusic = (Button) findViewById(R.id.previous);
		playAndPause = (Button) findViewById(R.id.play_pause);
		nestMusic = (Button) findViewById(R.id.nest);
		playingMucicName = (TextView) findViewById(R.id.playMusic_name);
		mediaPlayer = new MediaPlayer();
		initMusicList();
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, musicList);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(this);
		preMusic.setOnClickListener(this);
		playAndPause.setOnClickListener(this);
		nestMusic.setOnClickListener(this);


	}

	private void initMusicList() {
		musicList = new ArrayList<>();
		File file = new File(Environment.getExternalStorageDirectory() + "/mp3");
		if (!file.exists()) {
			file.mkdirs();
		}
		File[] files = file.listFiles();
		if (files == null) return;
		for (File f : files) {
			if (f.getName().endsWith(".mp3")) {
				musicList.add(f.getName());
			}
		}
	}

	@Override
	public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
		playingIndex = i;
		playMusic();


	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.previous:
				playingIndex--;
				playMusic();
				break;
			case R.id.nest:
				playingIndex++;
				playMusic();
				break;
			case R.id.play_pause:
				pauseAndPlay();


		}

	}

	private void pauseAndPlay() {
		if (mediaPlayer.isPlaying()) {
			mediaPlayer.pause();
			playAndPause.setText("播放");
		} else {
			mediaPlayer.start();
			playAndPause.setText("暂停");
		}
	}

	private void playMusic() {
		checkPlayingIndex();
		mediaPlayer.reset();
		try {
			mediaPlayer.setDataSource(Environment.getExternalStorageDirectory() + "/mp3/" +
				   musicList.get(playingIndex));
			mediaPlayer.prepare();
			mediaPlayer.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
		playingMucicName.setText(musicList.get(playingIndex));
		playAndPause.setText("暂停");
		listView.setSelection(playingIndex);
	}

	private void checkPlayingIndex() {
		if (playingIndex > musicList.size() - 1) {
			playingIndex = 0;
		}
		if (playingIndex < 0) {
			playingIndex = musicList.size() - 1;
		}

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		mediaPlayer.stop();
		mediaPlayer.release();
	}
}
