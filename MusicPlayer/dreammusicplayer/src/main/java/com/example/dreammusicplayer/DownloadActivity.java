package com.example.dreammusicplayer;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.dreammusicplayer.Utils.Constant;
import com.example.dreammusicplayer.download.DownloadService;
import com.example.dreammusicplayer.xmlparser.DownloadReceiver;
import com.example.dreammusicplayer.xmlparser.MusicInfo;
import com.example.dreammusicplayer.xmlparser.XMLContentHandler;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import java.io.FileInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.SAXParserFactory;

/**
 * 下载界面
 */

public class DownloadActivity extends AppCompatActivity implements Serializable {
	private final static int UPDATE = 1;
	private List<MusicInfo> musicInfos;
	private ListView downloadList;
	private List<Map<String, String>> list;
	private Handler handler;
	private String fileName;
	private Map<String, View> viewMap;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_download);
		downloadList = (ListView) findViewById(R.id.downloadList);
		list = new ArrayList<>();
		handler = new Handler();
		updateList();
		downloadList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				fileName = list.get(position).get("musicName");
				final String str = fileName;
				MusicInfo musicInfo = musicInfos.get(position);
				final String lrcName = musicInfo.getLrcName();
				Button button = (Button) view.findViewById(R.id.download);
				button.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						download(lrcName);
						download(str);
					}
				});

			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, UPDATE, 1, R.string.update);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == UPDATE) {
			updateList();
		}
		return super.onOptionsItemSelected(item);
	}

	public void updateList() {
		list.clear();
		fileName = "mp3.xml";
		musicInfos = xmlParser("mp3.xml");
		if (musicInfos == null) return;
		for (MusicInfo info : musicInfos) {
			Map<String, String> map = new HashMap<>();
			map.put("musicName", info.getMp3Name());
			map.put("musicSize", info.getMp3Size() + "k");
			list.add(map);
		}
		String[] from = {"musicName", "musicSize"};
		int[] to = {R.id.downMusicName, R.id.downMusicSize};
		SimpleAdapter adapter = new SimpleAdapter(this, list, R.layout.download_adapter_item,
			   from, to);
		downloadList.setAdapter(adapter);
	}

	public List<MusicInfo> xmlParser(String urlPath) {
		Log.w("tag", "xmlParser开始运行");
		try {
			if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
				download(urlPath);
//				while (!DownloadReceiver.isFinsihed()){
//					Thread.sleep(10);
//				}
				XMLReader reader = SAXParserFactory.newInstance().newSAXParser()
					   .getXMLReader();
				XMLContentHandler handler = new XMLContentHandler();
				reader.setContentHandler(handler);
				reader.parse(new InputSource(new FileInputStream(Constant.SAVE_PATH
					   + "/mp3.xml")));
				Log.w("tag", "xmlParser运行结束");
				return handler.getMusicInfos();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 从网络上下载指定名字的文件
	 *
	 * @param fileName
	 */
	public void download(String fileName) {
		Intent intent = new Intent(this, DownloadService.class);
		intent.putExtra("url", Constant.DOWNLOAD_PATH + fileName);
		startService(intent);
		Log.w("tag", "startService:" + intent);
		handler.postDelayed(checkDownloadFinished,0);

	}

	private Runnable checkDownloadFinished = new Runnable() {
		@Override
		public void run() {
			handler.postDelayed(checkDownloadFinished, 10);
			if (DownloadReceiver.isFinsihed()) {
				DownloadReceiver.setIsFinsihed(false);
				Toast.makeText(DownloadActivity.this, DownloadReceiver.getFileName() + "下载完成",
					   Toast.LENGTH_SHORT).show();
				handler.removeCallbacks(checkDownloadFinished);
			}

		}
	};


}
