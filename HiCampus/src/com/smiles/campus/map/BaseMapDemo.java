package com.smiles.campus.map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.baidu.location.LocationClient;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMapOptions;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.TextOptions;
import com.baidu.mapapi.map.BaiduMap.OnMarkerDragListener;
import com.baidu.mapapi.model.LatLng;
import com.smiles.campus.R;
import com.smiles.campus.service.RestServiceImpl;

/**
 * 演示MapView的基本用法
 */
public class BaseMapDemo extends Activity {
	@SuppressWarnings("unused")
	private static final String LTAG = BaseMapDemo.class.getSimpleName();
	private MapView mMapView;
	private BaiduMap mBaiduMap;
	
	private static RestServiceImpl restServiceImpl = new RestServiceImpl();

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent intent = getIntent();
		if (intent.hasExtra("x") && intent.hasExtra("y")) {
			// 当用intent参数时，设置中心点为指定点
			Bundle b = intent.getExtras();
			LatLng p = new LatLng(b.getDouble("y"), b.getDouble("x"));
			mMapView = new MapView(this,
					new BaiduMapOptions().mapStatus(new MapStatus.Builder()
							.target(p).build()));
		} else {
			mMapView = new MapView(this, new BaiduMapOptions());
		}
		setContentView(mMapView);
		mBaiduMap = mMapView.getMap();

		// 定义Maker坐标点
		LatLng pointOfMine = restServiceImpl.getLocOfMine();
		LatLng pointOfOrder = restServiceImpl.getLocByUserId("order");
//		LatLng pt1 = new LatLng(39.93923, 116.357428);  
//		LatLng pt2 = new LatLng(39.91923, 116.327428);  
		LatLng pt3 = new LatLng(39.89923, 116.347428);  
//		LatLng pt4 = new LatLng(39.89923, 116.367428);  
//		LatLng pt5 = new LatLng(39.91923, 116.387428);  

		// 构建Marker图标
		BitmapDescriptor bitmapA = BitmapDescriptorFactory
				.fromResource(R.drawable.icon_marka);
		BitmapDescriptor bitmapB = BitmapDescriptorFactory
				.fromResource(R.drawable.icon_markb);

		// 构建MarkerOption，用于在地图上添加Marker
		OverlayOptions optionA = new MarkerOptions().position(pointOfMine)
				.icon(bitmapA).draggable(true); // 设置手势拖拽;

		OverlayOptions optionB = new MarkerOptions().position(pointOfOrder)
				.icon(bitmapB).draggable(true); // 设置手势拖拽;

		// 在地图上添加Marker，并显示
		mBaiduMap.addOverlay(optionA);
		mBaiduMap.addOverlay(optionB);

		LatLng llText = pt3;

		OverlayOptions textOption = new TextOptions().bgColor(0xAAFFFF00)
				.fontSize(24).fontColor(0xFFFF00FF).text("Hello kitty..")
				.position(llText);
		// 在地图上添加该文字对象并显示
		mBaiduMap.addOverlay(textOption);

		// 调用BaiduMap对象的setOnMarkerDragListener方法设置marker拖拽的监听
		mBaiduMap.setOnMarkerDragListener(new OnMarkerDragListener() {

			@Override
			public void onMarkerDrag(Marker marker) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onMarkerDragEnd(Marker marker) {
				// TODO Auto-generated method stub
				Log.i("Marker",
						marker.getTitle() + " ends at: " + marker.getPosition());

			}

			@Override
			public void onMarkerDragStart(Marker marker) {
				// TODO Auto-generated method stub
				Log.i("Marker",
						marker.getTitle() + " starts at: "
								+ marker.getPosition());

			}
		});
	}

	@Override
	protected void onPause() {
		super.onPause();
		// activity 暂停时同时暂停地图控件
		mMapView.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
		// activity 恢复时同时恢复地图控件
		mMapView.onResume();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		// activity 销毁时同时销毁地图控件
		mMapView.onDestroy();
	}

}
