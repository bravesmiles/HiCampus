package com.guo.schoolrrshundai.ui;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.guo.androidService.LocationService;
import com.smiles.campus.R;
//import com.guo.mywebsiteclient.R;
//import com.guo.utils.LogUtil;
import com.smiles.campus.service.RestServiceImpl;
import com.smiles.campus.utils.LogUtil;

public class MapActivity extends Activity {
	public MapView mapView = null;
	public BaiduMap baiduMap = null;
	public LocationClient locationClient = null;
	boolean isFirstLoc = true;// 是否首次定位
	private static RestServiceImpl restServiceImpl = new RestServiceImpl();
	public BDLocationListener myListener = new BDLocationListener() {
		@Override
		public void onReceiveLocation(BDLocation location) {
			if (location == null || mapView == null)
				return;
			MyLocationData locData = new MyLocationData.Builder().accuracy(location.getRadius()).direction(100).latitude(location.getLatitude()).longitude(location.getLongitude()).build();
			baiduMap.setMyLocationData(locData);

			// 启动service
			startService(new Intent().setClass(MapActivity.this, LocationService.class).putExtra("lat", locData.latitude).putExtra("lng", locData.longitude));
			LogUtil.print("dangq weizhi:" + locData.latitude + ";" + locData.longitude);
			if (isFirstLoc) {
				isFirstLoc = false;
				LatLng ll = new LatLng(location.getLatitude(), location.getLongitude());
				MapStatusUpdate u = MapStatusUpdateFactory.newLatLngZoom(ll, 18);
				baiduMap.animateMapStatus(u);
				// 启动service
				LogUtil.print("dangq weizhi222:" + ll.latitude + ";" + ll.longitude);
				startService(new Intent().setClass(MapActivity.this, LocationService.class).putExtra("lat", ll.latitude).putExtra("lng", ll.longitude));
			}
		}

		@Override
		public void onReceivePoi(BDLocation arg0) {
			// TODO Auto-generated method stub

		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 在使用SDK各组件之前初始化context信息，传入ApplicationContext
		// 注意该方法要再setContentView方法之前实现
		SDKInitializer.initialize(getApplicationContext());
		setContentView(R.layout.activity_map);

		mapView = (MapView) this.findViewById(R.id.mapView); // 获取地图控件引用
		baiduMap = mapView.getMap();
		// 开启定位图层
		baiduMap.setMyLocationEnabled(true);

		locationClient = new LocationClient(getApplicationContext()); // 实例化LocationClient类
		locationClient.registerLocationListener(myListener); // 注册监听函数
		this.setLocationOption(); // 设置定位参数
		locationClient.start(); // 开始定位

		// baiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL); // 设置为一般地图
		// baiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE); //设置为卫星地图
		// baiduMap.setTrafficEnabled(true); //开启交通图

		// 注册接收最新快递员位置信息的广播
		registerBoradcastReceiver();
	}

	public void registerBoradcastReceiver() {
		IntentFilter myIntentFilter = new IntentFilter();
		myIntentFilter.addAction("positionData");
		// 注册广播
		registerReceiver(mBroadcastReceiver, myIntentFilter);
	}

	private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			if (action.equals("positionData")) {
				String newPosition = intent.getStringExtra("newPosition");
				LogUtil.print("最新位置：" + newPosition + ";" + System.currentTimeMillis());
			}
		}

	};

	// 三个状态实现地图生命周期管理
	@Override
	protected void onDestroy() {
		// 退出时销毁定位
		locationClient.stop();
		baiduMap.setMyLocationEnabled(false);
		// TODO Auto-generated method stub
		super.onDestroy();
		mapView.onDestroy();
		mapView = null;
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		mapView.onResume();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		mapView.onPause();
	}

	/**
	 * 设置定位参数
	 */
	private void setLocationOption() {
		LocationClientOption option = new LocationClientOption();
		option.setOpenGps(true); // 打开GPS
		option.setCoorType("bd09ll"); // 返回的定位结果是百度经纬度,默认值gcj02
		option.setScanSpan(5000); // 设置发起定位请求的间隔时间为5000ms

		locationClient.setLocOption(option);
	}

	/**
	 * @todo
	 * 
	 *       在送快递人的数据提交后更新 1、开现场每35秒去请求服务器端数据一次 2、根据请求到的经纬度数据来更新地图上送快递人的图标位置
	 * @createTime 2014-10-23下午5:25:39
	 * 
	 * @author 叮当猫
	 * 
	 */
	private void updateExpressLocationData() {

	}

	/**
	 * @todo 更新送快递的人的标记
	 * 
	 * @createTime 2014-10-23下午5:33:14
	 * 
	 * @author 叮当猫
	 * 
	 */
	private void updateMarker(long lat, long lng) {
		LatLng expressPos = restServiceImpl.getLocOfMine(lat, lng);
		BitmapDescriptor bitmapA = BitmapDescriptorFactory.fromResource(R.drawable.icon_marka);
		OverlayOptions optionA = new MarkerOptions().position(expressPos).icon(bitmapA).draggable(true); // 设置手势拖拽;
		baiduMap.addOverlay(optionA);
	}
}
