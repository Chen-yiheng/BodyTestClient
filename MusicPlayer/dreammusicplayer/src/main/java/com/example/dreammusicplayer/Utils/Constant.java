package com.example.dreammusicplayer.Utils;

import android.os.Environment;

/**
 * Created by vip on 2017/4/28.
 */

public class Constant {
	public static final String SAVE_PATH = Environment.getExternalStorageDirectory()
		   .getAbsolutePath() + "/mp3/";
	public static final String DOWNLOAD_PATH = "http://169.254.224.16:8080/MP3/";


	public static final int PLAY_COMMENT=1;
	public static final int PAUSE_COMMENT=2;
	public static final int START_COMMENT=3;
	public static final int UPDATE_COMMENT=6;
	public static final int NEXT_COMMENT=5;
	public static final int PREVIOUS_COMMENT=4;


}
