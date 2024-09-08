package com.dp_th.localechanger;
import androidx.multidex.MultiDex;


import static androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM;
import static androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;

import com.dp.localechanger.LocaleApplication;


public class MyApplication extends LocaleApplication {

	public MyApplication() {}

	@Override
	public void onCreate() {
		super.onCreate();
		//AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_FOLLOW_SYSTEM);
		AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES);
	}


	@Override
	protected void attachBaseContext(@NonNull Context base) {
		super.attachBaseContext(base);
		MultiDex.install(this);
	}

}