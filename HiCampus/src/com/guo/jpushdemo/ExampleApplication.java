package com.guo.jpushdemo;

import com.guo.DB.UserDB;

import android.app.Application;
import android.database.Cursor;
import android.util.Log;
import cn.jpush.android.api.JPushInterface;

/**
 * For developer startup JPush SDK
 * 
 * 一般建议在自定义 Application 类里初始化。也可以在主 Activity 里。
 */
public class ExampleApplication extends Application {
	private static final String TAG = "JPush";

	UserDB userDb;
	Cursor cursor;
	String account;
	String localdeviceId;
	private String userId;
	private String userPasswd;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPasswd() {
		return userPasswd;
	}

	public void setUserPasswd(String userPasswd) {
		this.userPasswd = userPasswd;
	}

	@Override
	public void onCreate() {
		Log.d(TAG, "[ExampleApplication] onCreate");
		super.onCreate();

		JPushInterface.setDebugMode(true); // 设置开启日志,发布时请关闭日志
		JPushInterface.init(this); // 初始化 JPush
	}
}
