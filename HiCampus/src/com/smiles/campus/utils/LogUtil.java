/**
 * 
 */
package com.smiles.campus.utils;

import android.util.Log;

/**
 * @author yaojliu
 * Log utils with defined tag and log level(default is debug)
 */
public class LogUtil {
	
	public static String DEBUG_TAG = "Debugging";
	public static String USER_STATUS_TAG = "User Status";
	public static String BUTTON_CLICK_TAG = "Button Clicked";
	
	private static int logLevel = Log.DEBUG;

	public static int getLogLevel() {
		return logLevel;
	}

	public static void setLogLevel(int logLevel) {
		LogUtil.logLevel = logLevel;
	} 
	
	public static void log(String tag, String msg){
		Log.println(logLevel, tag, msg);
	}
	
	public static void print(String msg){
		Log.println(logLevel, DEBUG_TAG, msg);
	}
	
}
