package com.example.gson;

/**
 *
 */

public class Person {
	private String id;
	private String name;
	private String age;
	private String sex;

	public Person(String age, String id, String name, String sex) {
		this.age = age;
		this.id = id;
		this.name = name;
		this.sex = sex;
	}

	public Person() {
	}

	public void setAge(String age) {
		this.age = age;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAge() {
		return age;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getSex() {
		return sex;
	}
}
