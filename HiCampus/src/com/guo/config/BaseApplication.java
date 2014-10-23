package com.guo.config;

import android.app.Application;
import android.content.res.Configuration;
import android.database.Cursor;

import com.guo.DB.UserDB;
import com.guo.utils.LogUtil;

public class BaseApplication extends Application {

	UserDB userDb;
	Cursor cursor;
	String account;
	String localdeviceId;

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
	}

	@Override
	public void onLowMemory() {
		// TODO Auto-generated method stub
		super.onLowMemory();
	}

	@Override
	public void onTerminate() {
		super.onTerminate();
		userDb = new UserDB(getApplicationContext());
		cursor = userDb.accurateSelect();
		if (cursor.moveToFirst()) {
			localdeviceId = cursor.getString(1);
			LogUtil.print("localdeviceId:" + localdeviceId);
			account = cursor.getString(2);
		}
		cursor.close();
	}

}
