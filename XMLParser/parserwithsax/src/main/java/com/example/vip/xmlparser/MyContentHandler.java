package com.example.vip.xmlparser;


import android.util.Log;
import android.widget.TextView;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * 实现ContentHandler接口解析XML文件，但由于ContentHandler有太多要实现方法，
 * 所以只需继承DefaultHandler这个实现了ContentHandler接口的类就行了。
 */
public class MyContentHandler extends DefaultHandler {
	private final String TAG = "tag";
	private int id;
	private String personName;
	private String personSex;
	private String personAge;
	private StringBuilder builder;
	private TextView tv;


	public MyContentHandler(TextView tv) {
		this.tv = tv;
	}

	@Override
	public void startDocument() throws SAXException {
		Log.w(TAG, "开始解析文件");
	}


	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes)
		   throws SAXException {
		builder = new StringBuilder();

	}

	@Override
	public void characters(char[] ch, int start, int length) {
		builder.append(ch, start, length);
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		String str = builder.toString();
		if ("id".equals(localName)) {
			id = Integer.parseInt(str);
		} else if ("name".equals(localName)) {
			personName = str;
		} else if ("age".equals(localName)) {
			personAge = str;
		} else if ("sex".equals(localName)) {
			personSex = str;
		} else if ("person".equals(localName)) {
			print();
		}
	}

	public void print() {
		String str = "id：" + id + "\n" + "名字：" + personName + "\n" + "性别：" + personSex + "\n" +
			   "年龄：" + personAge +
			   "\n";
		Log.w("tag", str);
		if (null != tv) {
			tv.setText(tv.getText() + str);
		}
	}

	@Override
	public void endDocument() throws SAXException {
		Log.w(TAG, "结束解析文件");
	}


}
