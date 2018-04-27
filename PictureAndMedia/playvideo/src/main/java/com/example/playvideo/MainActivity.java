package com.example.playvideo;

import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
	private Button playAndPause, replay;
	private VideoView videoView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		playAndPause = (Button) findViewById(R.id.play_pause);
		replay = (Button) findViewById(R.id.replay);
		videoView = (VideoView) findViewById(R.id.video);
		playAndPause.setOnClickListener(this);
		replay.setOnClickListener(this);
		initVideoPath();
		Log.i("tag","oncreate");
	}

	private void initVideoPath() {
		File file = new File(Environment.getExternalStorageDirectory(), "花朵.mp4");
		Log.i("tag", file.getPath() + "\n" + file.getAbsolutePath());
//		String path="file:///android_asset/myvedio.mp4";
//		videoView.setVideoPath(/*file.getPath()*/path);
//		VideoView vv = (VideoView)this.findViewById(R.id.videoView)
		String uri = "android.resource://" + getPackageName() + "/" + R.raw.myvedio;
		videoView.setVideoURI(Uri.parse(uri));
//		vv.start();

	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.play_pause:
				if (videoView.isPlaying()) {
					videoView.pause();
					playAndPause.setText("播放");
				} else {
					videoView.start();
					playAndPause.setText("暂停");
				}
				break;
			case R.id.replay:
				if (videoView.isPlaying()) {
					videoView.resume();
					playAndPause.setText("暂停");
				}
				break;

		}

	}
}
