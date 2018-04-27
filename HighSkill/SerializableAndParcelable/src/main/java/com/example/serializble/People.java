package com.example.serializble;

import android.os.Parcel;
import android.os.Parcelable;

/**
 *
 */

public class People implements Parcelable {
	private String name;
	private int age;
	private String sex;

	public People() {
	}

	public People(int age, String name, String sex) {
		this.age = age;
		this.name = name;
		this.sex = sex;
	}

	@Override
	public int describeContents() {
		return 0;
	}


	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(name);
		dest.writeInt(age);
		dest.writeString(sex);
	}

	public static final Parcelable.Creator<People> CREATOR = new Creator<People>() {

		/**
		 * 写入和读出的顺序要一致
		 */
		@Override
		public People createFromParcel(Parcel source) {
			People people = new People();
			people.setName(source.readString());
			people.setAge(source.readInt());
			people.setSex(source.readString());
			return people;
		}

		@Override
		public People[] newArray(int size) {
			return new People[size];
		}
	};


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
