/**
 * 
 */
package com.smiles.campus.utils;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;

/**
 * @author yaojliu
 * 
 */
public class ImageLoader extends AsyncTask<String, Void, Bitmap> {


	private Bitmap bmp;

	private Bitmap loadImageFromWeb(String url) {
		try {
//			InputStream is = (InputStream) new URL(url).getContent();
//			Drawable d = Drawable.createFromStream(is, "src name");
//			return d;
			URL ulrn = new URL(url);
            HttpURLConnection con = (HttpURLConnection)ulrn.openConnection();
            InputStream is = con.getInputStream();
            bmp = BitmapFactory.decodeStream(is);
		} catch (Exception e) {
			LogUtil.log(LogUtil.USER_STATUS_TAG, "Can't get verify image: " + e);
			return null;
		}
		return bmp;
	}

	@Override
	protected Bitmap doInBackground(String... params) {
		// TODO Auto-generated method stub
		String url = params[0];
		return loadImageFromWeb(url);
	}
}