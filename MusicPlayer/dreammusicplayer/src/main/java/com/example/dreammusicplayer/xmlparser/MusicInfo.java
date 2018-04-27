package com.example.dreammusicplayer.xmlparser;

/**
 * Created by vip on 2017/4/26.
 */

public class MusicInfo {
	private String mp3Name;
	private int mp3Size;
	private String lrcName;
	private int lrcSize;
	private String mp3Long;


	public MusicInfo(String mp3Name, int mp3Size, String lrcName, int lrcSize,String mp3Long) {
		this.mp3Name = mp3Name;
		this.mp3Size = mp3Size;
		this.lrcName = lrcName;
		this.lrcSize = lrcSize;
		this.mp3Long=mp3Long;
	}

	public String getMp3Long() {
		return mp3Long;
	}

	public void setMp3Long(String mp3Long) {
		this.mp3Long = mp3Long;
	}

	public MusicInfo() {
	}

	public String getMp3Name() {
		return mp3Name;
	}

	public void setMp3Name(String mp3Name) {
		this.mp3Name = mp3Name;
	}

	public int getMp3Size() {
		return mp3Size;
	}

	public void setMp3Size(int mp3Size) {
		this.mp3Size = mp3Size;
	}

	public String getLrcName() {
		return lrcName;
	}

	public void setLrcName(String lrcName) {
		this.lrcName = lrcName;
	}

	public int getLrcSize() {
		return lrcSize;
	}

	public void setLrcSize(int lrcSize) {
		this.lrcSize = lrcSize;
	}

	@Override
	public String toString() {
		return "MusicInfo:" +
			   "mp3Name=" + mp3Name + "\t" +
			   "mp3Size=" + mp3Size +"\t"+
			   "lrcName=" + lrcName + "\t" +
			   "lrcSize=" + lrcSize +"\t"+
			   "mp3Long=" + mp3Long + "\n";
	}
}
