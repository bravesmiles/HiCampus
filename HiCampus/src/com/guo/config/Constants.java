package com.guo.config;

import java.io.InputStream;

import org.apache.http.util.EncodingUtils;

import android.content.Context;

public class Constants {

	public final static String BASE_URL = "http://www.bashukeji.com/mingzhiweixin/";
	public final static String SERVER_URL = "http://61.139.95.212:8082/ShunBianEarn/user.do";
	public final static String SERVER_DOWNLOAD_URL = "http://61.139.95.212:8082/ShunBianEarn/apk/user.do";
	public final static String SERVER_UPDATE_URL = "http://61.139.95.212:8082/ShunBianEarn/apk/version.xml";

	public final static String NULLPOINTER = "";
	public final static String NOTENOUGH = "notenough";
	public final static String NETERROR = "neterror";
	public final static String HAVEDOWN = "havedone";
	public final static String NOTHING = "nothing";
	public final static String UNKNOWN = "unknown";
	public final static String SERVERERROR = "servererror";

	public static String myExceptions(String exceptionStr) {
		String exceptionIntro = exceptionStr;
		if (NOTENOUGH.equals(exceptionStr)) {
			exceptionIntro = "申请条件不够!";
		} else if (NETERROR.equals(exceptionStr)) {
			exceptionIntro = "网络错误!";
		} else if (HAVEDOWN.equals(exceptionStr)) {
			exceptionIntro = "已经完成!";
		} else if (NOTHING.equals(exceptionStr)) {
			exceptionIntro = "无结果!";
		} else if (UNKNOWN.equals(exceptionStr)) {
			exceptionIntro = "未知异常!";
		} else if (SERVERERROR.equals(exceptionStr)) {
			exceptionIntro = "服务器端在维护!";
		} else if (NULLPOINTER.equals(exceptionStr)) {
			exceptionIntro = "未获取到数据!";
		}
		return exceptionIntro;

	}

	public static boolean isMyExceptions(String exceptionStr) {
		boolean flag = false;
		if (NOTENOUGH.equals(exceptionStr)) {
			flag = true;
		} else if (NETERROR.equals(exceptionStr)) {
			flag = true;
		} else if (HAVEDOWN.equals(exceptionStr)) {
			flag = true;
		} else if (NOTHING.equals(exceptionStr)) {
			flag = true;
		} else if (UNKNOWN.equals(exceptionStr)) {
			flag = true;
		} else if (SERVERERROR.equals(exceptionStr)) {
			flag = true;
		} else if (NULLPOINTER.equals(exceptionStr)) {
			flag = true;
		}
		return flag;

	}

	public static String getDetailWay(String wayStr) {
		String str = "";
		if ("qq".equals(wayStr)) {
			str = "Q币";
		} else if ("phone".equals(wayStr)) {
			str = "电话费";
		} else if ("alipay".equals(wayStr)) {
			str = "支付宝";
		} else if ("cft".equals(wayStr)) {
			str = "财付通";
		} else if ("xl".equals(wayStr)) {
			str = "迅雷会员";
		}
		return str;
	}

	public static String getFromAssets(Context context, String fileName) {
		String result = "";
		try {
			InputStream in = context.getResources().getAssets().open(fileName);
			// 获取文件的字节数
			int lenght = in.available();
			// 创建byte数组
			byte[] buffer = new byte[lenght];
			// 将文件中的数据读到byte数组中
			in.read(buffer);
			result = EncodingUtils.getString(buffer, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
