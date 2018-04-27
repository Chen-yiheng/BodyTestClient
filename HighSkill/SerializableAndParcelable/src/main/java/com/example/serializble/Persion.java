package com.example.serializble;

import java.io.Serializable;

/**
 *Serialiable方式需要实现Serialiable接口
 *
 * Parcelable需要实现Parcelable接口
 *
 *
 */

public class Persion implements Serializable{

	private String name;
	private int age;
	private String sex;

	public Persion(int age, String name, String sex) {
		this.age = age;
		this.name = name;
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
}
