package com.guo.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.smiles.campus.utils.LogUtil;

/**
 * the class to connect server
 * 
 * @author gzm
 * 
 */
public class RestUtil {
	
	public static final String REG_URL = "http://www.bashukeji.com/mingzhiweixin/server/registAction.asp";
	public static final String LOG_URL = "http://www.bashukeji.com/mingzhiweixin/phone/index.asp";

	public static final String NET_ERROR = "neterror";

	/**
	 * httpclient post method
	 * 
	 * @param cls
	 *            the invoked class
	 * @param url
	 *            the url of server
	 * @param keyItem
	 *            BasicNameValuePair's parameters key
	 * @param valueItem
	 *            BasicNameValuePair's parameters value
	 * @return result
	 */
	public static String postData(Class<?> cls, String baseurl,
			String[] keyItem, String[] valueItem) {
		String resurl = baseurl + "?";
		String result = null;
		HttpClient httpClient = null;
		try {
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			HttpPost httpPost = new HttpPost(baseurl);
			for (int i = 0; i < keyItem.length; i++) {
				params.add(new BasicNameValuePair(keyItem[i], valueItem[i]));
				if (i == (keyItem.length - 1)) {
					resurl = resurl + keyItem[i] + "=" + valueItem[i];
				} else {
					resurl = resurl + keyItem[i] + "=" + valueItem[i] + "&";
				}
			}
			httpPost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));

			HttpParams httpParameters;
			int timeoutConnection = 10000;
			int timeoutSocket = 10000;
			httpParameters = new BasicHttpParams();
			HttpConnectionParams.setConnectionTimeout(httpParameters,
					timeoutConnection);
			HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);
			httpClient = new DefaultHttpClient(httpParameters);
			HttpResponse httpResponse = httpClient.execute(httpPost);

			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				result = EntityUtils.toString(httpResponse.getEntity());
				result.replaceAll("\r", "");
			} else {
				result = Constants.SERVERERROR;
			}
			LogUtil.print(cls.getSimpleName() + resurl + ";result:" + result);
		} catch (Exception e) {
			result = Constants.NETERROR;
			e.printStackTrace();
		} finally {
			if (httpClient != null) {
				httpClient.getConnectionManager().shutdown();
			}
		}
		return result;
	}

	/**
	 * httpclient get method
	 * 
	 * @param url
	 *            the server url and parameters
	 * @return result invoke eg: String return_str = new
	 *         HttpDownloader().getDataFromNet(Config.GETHUSHI_AND_PATI_URL +
	 *         "?loginid=" + Config.NOWPEOPLEID + "&loginpasswd=" +
	 *         Config.NOWPEOPLEPASSWD + "&actions=GetPatiInfo2" + "&inpatiid=" +
	 *         saomiaores);
	 */
	public static String getData(Class<?> cls, String url) {
		HttpParams params = new BasicHttpParams();
		HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
		HttpProtocolParams.setContentCharset(params,
				HTTP.DEFAULT_CONTENT_CHARSET);
		HttpProtocolParams.setUseExpectContinue(params, true);
		ConnManagerParams.setMaxTotalConnections(params, 100);
		HttpConnectionParams.setConnectionTimeout(params, 10000);
		HttpConnectionParams.setSoTimeout(params, 10000);
		SchemeRegistry schReg = new SchemeRegistry();
		schReg.register(new Scheme("http", PlainSocketFactory
				.getSocketFactory(), 80));
		ClientConnectionManager conMgr = new ThreadSafeClientConnManager(
				params, schReg);

		HttpClient httpClient = new DefaultHttpClient(conMgr, params);
		HttpPost httpPost = new HttpPost(url);
		int res = 0;
		StringBuilder builder = null;
		String return_str = "";
		try {
			res = httpClient.execute(httpPost).getStatusLine().getStatusCode();
			if (res == 200) {
				HttpResponse httpResponse = null;
				httpResponse = httpClient.execute(httpPost);
				builder = new StringBuilder();
				BufferedReader bufferedReader2 = null;
				bufferedReader2 = new BufferedReader(new InputStreamReader(
						httpResponse.getEntity().getContent()));
				for (String s = bufferedReader2.readLine(); s != null; s = bufferedReader2
						.readLine()) {
					builder.append(s);
				}
				if (builder != null) {
					return_str = builder.toString();
				}
			}
		} catch (Exception e) {
			return_str = NET_ERROR;
		} finally {
			httpClient.getConnectionManager().shutdown();
		}
		LogUtil.print("url_from_" + cls.getSimpleName() + ":" + url + "    "
				+ "result:" + return_str);
		return return_str;
	}
}
