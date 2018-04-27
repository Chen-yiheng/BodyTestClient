package com.example.vip.chatpage;


/**
 * 发送和接收的信息类
 */
public class Message {
	/**
	 * 发送接收信息识别标志
	 */
	public final static int TYPE_SEND = 1;
	public final static int TYPE_RECEIVE = 0;
	/**
	 * 信息类型
	 */
	private int type;
	/**
	 * 信息的内容
	 */
	private String content;


	public Message(int type, String content) {
		this.content = content;
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
}
