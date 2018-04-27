package com.example.dreammusicplayer.xmlparser;

import android.util.Log;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vip on 2017/4/25.
 */

public class XMLContentHandler extends DefaultHandler {
	private MusicInfo musicInfo;
	private List<MusicInfo> musicInfos;
	private String tagName;
	private StringBuffer buffer;

	public XMLContentHandler() {

	}

	@Override
	public void startDocument() throws SAXException {
		super.startDocument();
		buffer=new StringBuffer();
		musicInfos = new ArrayList();
		Log.w("tag", "开始解析");
	}



	/**
	 * @param uri        命名空间
	 * @param localName  不带命名空间前缀的标签名
	 * @param qName      带命名空间前缀的标签名
	 * @param attributes 全部的属性名和值
	 * @throws SAXException
	 */
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes)
		   throws SAXException {
		super.startElement(uri,localName,qName,attributes);
		buffer.setLength(0);
//		Log.i("tag","startElement,tagName:"+localName);
		if ("music".equals(localName)) {
			musicInfo = new MusicInfo();
		}
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		super.characters(ch,start,length);
		buffer.append(ch, start, length);

	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		super.endElement(uri,localName,qName);
		tagName=localName;
		String value=buffer.toString();
		if(null==value||"".equals(value)||"".equals(tagName))return;
//		Log.i("tag","tagName:"+tagName);
//		Log.i("tag", "value:" + value);
		if ("name".equals(tagName)) {
			musicInfo.setMp3Name(value);
		} else if ("size".equals(tagName)) {
//			Log.i("tag","Integer.parseInt(value):"+Integer.parseInt(value));
			musicInfo.setMp3Size(Integer.parseInt(value));
		} else if ("lrc.name".equals(tagName)) {
			musicInfo.setLrcName(value);
		} else if ("lrc.size".equals(tagName)) {
//			Log.i("tag","lrc.size"+Integer.parseInt(value.trim()));
			musicInfo.setLrcSize(Integer.parseInt(value.trim()));
		}else if("music.long".equals(tagName)){
			musicInfo.setMp3Long(value);
		}
		Log.w("tag", "musicInfo:" + musicInfo);
		if ("music".equals(localName)) {
			musicInfos.add(musicInfo);
			Log.w("tag","加入一个musicInfo："+musicInfo);
		}

	}

	@Override
	public void endDocument() throws SAXException {
		super.endDocument();
		Log.w("tag", "结束解析");
	}

	public List<MusicInfo> getMusicInfos() {
		return musicInfos;
	}
}
