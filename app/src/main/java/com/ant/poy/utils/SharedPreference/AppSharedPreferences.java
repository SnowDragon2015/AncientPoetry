package com.ant.poy.utils.SharedPreference;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class AppSharedPreferences<T> {

	protected SharedPreferences sharedPreference;

	public AppSharedPreferences(Context context, String sharedPreferenceName) {
		sharedPreference = context.getSharedPreferences(sharedPreferenceName,
				Context.MODE_PRIVATE);
	}

	public void clear() {
		Editor editor = sharedPreference.edit();
		editor.clear().commit();
	}

	public String getString(T name, String defValue) {
		return sharedPreference.getString(name.toString(), defValue);
	}

	public void putString(T name, String value) {
		Editor editor = sharedPreference.edit();
		editor.putString(name.toString(), value);
		editor.commit();
	}
	public int getInt(String name, int defValue){
		return sharedPreference.getInt(name,defValue);
	}

	public Long getLong(String name, Long defValue) {
		return sharedPreference.getLong(name, defValue);

	}

	public boolean getBoolean(String name, boolean defValue) {
		return sharedPreference.getBoolean(name, defValue);

	}

	public void putBoolean(String name, boolean value) {
		Editor editor = sharedPreference.edit();
		editor.putBoolean(name.toString(), value);
		editor.commit();

	}

	public void putInt(String name, int value){
		Editor editor = sharedPreference.edit();
		editor.putInt(name.toString(), value);
		editor.commit();
	}

	public void putLong(String name, Long value) {
		Editor editor = sharedPreference.edit();
		editor.putLong(name.toString(), value);
		editor.commit();
	}
}
