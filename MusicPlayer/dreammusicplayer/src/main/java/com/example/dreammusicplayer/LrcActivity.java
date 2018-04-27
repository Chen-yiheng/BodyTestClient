package com.example.dreammusicplayer;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;


import com.example.dreammusicplayer.Lrcview.DefaultLrcParser;
import com.example.dreammusicplayer.Lrcview.LrcRow;
import com.example.dreammusicplayer.Lrcview.LrcView;
import com.example.dreammusicplayer.Utils.Constant;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by vip on 2017/5/19.
 */

public class LrcActivity extends AppCompatActivity implements View.OnClickListener {
	private Button return2Main;
	private TextView playingMusic, currTime, allTime;
	private ImageButton lrcPrevious, lrcPlaying, lrcNest;
	private LrcView lrcView;
	private PopupWindow popupWindow;
	private SeekBar musicSeekBar, voiceSeekBar, bondSeekBar;
	private List<String> list;
	private Handler handler;
	private MusicPlayerService musicService;
	public int playingIndex = 0;
	private String musicName;
	public static boolean finsh;

	private ServiceConnection connection = new ServiceConnection() {
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			MusicPlayerService.MusicBinder binder = (MusicPlayerService.MusicBinder) service;
			musicService = binder.getService();
			Log.i("tag", "musicService:" + musicService);
			list = musicService.musicList;
			playingIndex = musicService.playingMusicIndex;
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			musicService = null;
		}
	};
	private String name = "";
	private Runnable checkWhetherPlay = new Runnable() {
		public void run() {

			try {
				handler.postDelayed(checkWhetherPlay, 5);
				if (null != musicService) {
					musicName = list.get(musicService.playingMusicIndex);
					musicName = musicName.substring(0, musicName.indexOf(".mp3"));
					playingMusic.setText(musicName);
					if (!name.equals(musicName)) {
						Log.w("tag", "musicName1:" + musicName);
						Log.w("tag", "Name1:" + name);
						lrcView.reset();
						lrcView.setLrcRows(getLrcRows(musicName));
						Log.w("tag", "musicName2:" + musicName);
						name = musicName;
						Log.w("tag", "name2:" + name);

					}
					if (musicService.player.isPlaying()) {
						lrcPlaying.setBackgroundDrawable(getResources().getDrawable(R
							   .drawable
							   .lrc_playing));
						int now = musicService.player.getCurrentPosition();
						int all = musicService.player.getDuration();
//						Log.w("tag", "now:" + now);
//						Log.w("tag", "all:" + all);
						musicSeekBar.setMax(all);
						musicSeekBar.setProgress(now);
						allTime.setText(getTimeString(all));
						currTime.setText(getTimeString(now));
					} else {
						lrcPlaying.setBackgroundDrawable(getResources().getDrawable(R
							   .drawable
							   .pausing));
					}
				}
				if (finsh) {
					finsh = false;
					LrcActivity.this.finish();
				}
			} catch (Resources.NotFoundException e) {
			}
		}
	};


	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lrc);
		getSupportActionBar().hide();
		handler = new Handler();
		return2Main = (Button) findViewById(R.id.return2main);
		playingMusic = (TextView) findViewById(R.id.lrc_title);
		currTime = (TextView) findViewById(R.id.lrc_nowTime);
		allTime = (TextView) findViewById(R.id.lrc_time);
		lrcPrevious = (ImageButton) findViewById(R.id.lrc_previous);
		lrcPlaying = (ImageButton) findViewById(R.id.lrc_play);
		lrcNest = (ImageButton) findViewById(R.id.lrc_nest);
		lrcPrevious.setOnClickListener(this);
		lrcPlaying.setOnClickListener(this);
		lrcNest.setOnClickListener(this);
		return2Main.setOnClickListener(this);
		musicSeekBar = (SeekBar) findViewById(R.id.music_seekBar);
		lrcView = (LrcView) findViewById(R.id.lrcView);
		lrcView.setOnSeekToListener(new LrcView.OnSeekToListener() {
			@Override
			public void onSeekTo(int progress) {
				musicService.player.seekTo(progress);
			}
		});
		popupWindowInit();
		bindService(new Intent(this, MusicPlayerService.class), connection, BIND_AUTO_CREATE);
		handler.postDelayed(checkWhetherPlay, 50);
		bondSeekBar.setProgress((int) ((lrcView.getmCurScalingFactor() - LrcView
			   .MIN_SCALING_FACTOR) / (LrcView.MAX_SCALING_FACTOR - LrcView.MIN_SCALING_FACTOR)
			   * 100));
		musicSeekBar.setOnSeekBarChangeListener(onSeekBarChangeListener);
		voiceSeekBar.setOnSeekBarChangeListener(onSeekBarChangeListener);
		bondSeekBar.setOnSeekBarChangeListener(onSeekBarChangeListener);

	}

	private String getTimeString(int progress) {
		int msecTotal = progress / 1000;
		int min = msecTotal / 60;
		int msec = msecTotal % 60;
		String minStr = min < 10 ? "0" + min : "" + min;
		String msecStr = msec < 10 ? "0" + msec : "" + msec;
		return minStr + ":" + msecStr;
	}


	private void popupWindowInit() {
		View view = getLayoutInflater().inflate(R.layout.window_lrc, null, false);
		voiceSeekBar = (SeekBar) view.findViewById(R.id.voice);
		bondSeekBar = (SeekBar) view.findViewById(R.id.bondSize);
		popupWindow = new PopupWindow(view, ActionBar.LayoutParams.MATCH_PARENT, ActionBar
			   .LayoutParams.WRAP_CONTENT, true);
		popupWindow.setTouchable(true);
		popupWindow.setOutsideTouchable(true);
		popupWindow.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));

		popupWindow.getContentView().setFocusable(true);
		popupWindow.getContentView().setFocusableInTouchMode(true);
		popupWindow.getContentView().setOnKeyListener(new View.OnKeyListener() {
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if (keyCode == KeyEvent.KEYCODE_MENU && event.getRepeatCount() == 0 && event
					   .getAction() == KeyEvent.ACTION_DOWN) {
					if (null != popupWindow && popupWindow.isShowing()) {
						popupWindow.dismiss();
					}
					return true;
				}
				return false;
			}
		});
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_MENU && event.getRepeatCount() == 0) {
			if (null != popupWindow && !popupWindow.isShowing()) {
				popupWindow.showAtLocation(findViewById(R.id.activity_lrc), Gravity.BOTTOM,
					   0, 0);
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.lrc_previous:
				playingIndex--;
				checkPlayingIndex();
				comment(Constant.PLAY_COMMENT, playingIndex);
				break;
			case R.id.lrc_nest:
				playingIndex++;
				checkPlayingIndex();
				comment(Constant.PLAY_COMMENT, playingIndex);
				break;
			case R.id.lrc_play:
				comment(Constant.PAUSE_COMMENT, 0);
				break;
			case R.id.return2main:
				LrcActivity.this.finish();
				break;
		}

	}

	SeekBar.OnSeekBarChangeListener onSeekBarChangeListener = new SeekBar.OnSeekBarChangeListener
		   () {

		@Override
		public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
			if (seekBar == musicSeekBar) {
				lrcView.seekTo(progress, true, fromUser);
				if (fromUser) {
					showPlayerToast(getTimeString(progress));
				}
			} else if (seekBar == bondSeekBar) {
				float scalingFactor = LrcView.MIN_SCALING_FACTOR + progress * (LrcView
					   .MAX_SCALING_FACTOR - LrcView.MIN_SCALING_FACTOR) / 100;
				lrcView.setLrcScalingFactor(scalingFactor);
				showLrcToast((int) (scalingFactor * 100) + "%");
			} else if (seekBar == voiceSeekBar) {

			}
		}

		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {

		}

		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {
			if (seekBar == musicSeekBar) {
				musicService.player.seekTo(seekBar.getProgress());
			}
		}
	};


	public void comment(int comment, int index) {
		Intent intent = new Intent(LrcActivity.this, MusicPlayerService.class);
		intent.putExtra(MusicPlayerService.INTENT_COMMENT, comment);
		switch (comment) {
			case Constant.PLAY_COMMENT:
				intent.putExtra(MusicPlayerService.INTENT_MUSIC_PATH, index);
				break;
//			case Constant.UPDATE_COMMENT:
//				intent.putStringArrayListExtra(MusicPlayerService.INTENT_LIST, list);
//				break;
		}
		this.startService(intent);
	}

	protected void checkPlayingIndex() {
		if (playingIndex < 0) {
			playingIndex = list.size() - 1;
		}
		if (playingIndex >= list.size()) {
			playingIndex = 0;
		}
	}

	/**
	 * 获取歌词List集合
	 *
	 * @return
	 */
	private BufferedReader br;

	private List<LrcRow> getLrcRows(String mMusicName) {
		mMusicName += ".lrc";
		List<LrcRow> rows = null;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream
				   (Constant.SAVE_PATH + mMusicName), "gbk"));
			String line;
			StringBuffer sb = new StringBuffer();

			while ((line = br.readLine()) != null) {
				sb.append(line + "\n");
			}
//			System.out.println(sb.toString());
			rows = DefaultLrcParser.getIstance().getLrcRows(sb.toString());
		} catch (IOException e) {
			return null;
		}
		return rows;
	}

	private TextView mPlayerToastTv;
	private Toast mPlayerToast;
	private Toast mLrcToast;


	private void showPlayerToast(String text) {
		if (mPlayerToast == null) {
			mPlayerToast = new Toast(this);
			mPlayerToastTv = (TextView) LayoutInflater.from(this).inflate(R.layout.toast, null);
			mPlayerToast.setView(mPlayerToastTv);
			mPlayerToast.setDuration(Toast.LENGTH_SHORT);
		}
		mPlayerToastTv.setText(text);
		mPlayerToast.show();
	}

	private TextView mLrcToastTv;

	private void showLrcToast(String text) {
		if (mLrcToast == null) {
			mLrcToast = new Toast(this);
			mLrcToastTv = (TextView) LayoutInflater.from(this).inflate(R.layout.toast, null);
			mLrcToast.setView(mLrcToastTv);
			mLrcToast.setDuration(Toast.LENGTH_SHORT);
		}
		mLrcToastTv.setText(text);
		mLrcToast.show();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		handler.removeCallbacks(checkWhetherPlay);
		try {
			if (null != br) {
				br.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		lrcView.reset();
	}
}
