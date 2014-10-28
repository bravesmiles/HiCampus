package com.guo.androidService;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.guo.config.Constants;
import com.guo.jpushdemo.ExampleApplication;
import com.guo.utils.DataPoster;
//import com.guo.utils.LogUtil;
import com.smiles.campus.utils.LogUtil;

public class LocationService extends Service {
	public static boolean forFlag = true;
	private double lat;
	private double lng;

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		lat = intent.getDoubleExtra("lat", 0);
		lng = intent.getDoubleExtra("lng", 0);
		LogUtil.print("要上传的数据：" + lat + "," + lng);

		new Thread() {

			@Override
			public void run() {
				try {
					while (forFlag) {
						LogUtil.print("resfromService:"
								+ new DataPoster().myHttpPostData(LocationService.class, Constants.UPDATE_POSITION, new String[] { "userId", "position" }, new String[] {
										((ExampleApplication) getApplicationContext()).getUserId(), lat + "|" + lng }));
						sendBroadcast(new Intent().setAction("positionData").putExtra(
								"newPosition",
								new DataPoster().myHttpPostData(LocationService.class, Constants.UPDATE_POSITION, new String[] { "userId", "position" }, new String[] {
										((ExampleApplication) getApplicationContext()).getUserId(), lat + "|" + lng })));

						try {
							Thread.sleep(3000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}.start();

		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Intent localIntent = new Intent();
		localIntent.putExtra("lat", lat);
		localIntent.putExtra("lng", lng);
		localIntent.setClass(this, LocationService.class); // 销毁时重新启动Service
		this.startService(localIntent);

	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
