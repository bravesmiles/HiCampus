package com.guo.jpushdemo;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import cn.jpush.android.api.InstrumentedActivity;
import cn.jpush.android.api.JPushInterface;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.guo.adapter.ImageAndTextAdapter;
import com.guo.config.Constants;
import com.guo.entity.Express;
//import com.guo.mywebsiteclient.R;
import com.guo.schoolrrshundai.ui.Daigou;
import com.guo.schoolrrshundai.ui.PersonalActivity;
import com.guo.schoolrrshundai.ui.SendExpress;
import com.guo.utils.DataPoster;
import com.smiles.campus.R;
import com.smiles.campus.map.CampusMap;
import com.smiles.campus.map.MapActivity;

public class PushMainActivity extends InstrumentedActivity implements OnClickListener {

	private Button mInit;
	private Button mSetting;
	private Button mStopPush;
	private Button mResumePush;
	private EditText msgText;

	private ListView exchangedInfo_listview;
	private Button personal_center_btn;
	private Button navi_daigou_btn;
	private Button navi_sendGoods_btn, navi_shundai_btn;

	public static boolean isForeground = false;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_push_main);
		findViewById();
		initView();

		registerMessageReceiver(); // used for receive msg
	}

	protected void findViewById() {
		exchangedInfo_listview = (ListView) findViewById(R.id.exchangedInfo_listview);
		personal_center_btn = (Button) findViewById(R.id.personal_center);
		navi_sendGoods_btn = (Button) findViewById(R.id.navi_sendGoods_btn);
		navi_shundai_btn = (Button) findViewById(R.id.navi_shundai_btn);
		navi_daigou_btn = (Button) findViewById(R.id.navi_daigou_btn);

		msgText = (EditText) findViewById(R.id.msg_rec);
		mSetting = (Button) findViewById(R.id.setting);
		mResumePush = (Button) findViewById(R.id.resumePush);
		mStopPush = (Button) findViewById(R.id.stopPush);
		mInit = (Button) findViewById(R.id.init);

	}

	protected void initView() {
		TextView mImei = (TextView) findViewById(R.id.tv_imei);
		String udid = ExampleUtil.getImei(getApplicationContext(), "");
		if (null != udid)
			mImei.setText("IMEI: " + udid);
		TextView mAppKey = (TextView) findViewById(R.id.tv_appkey);
		String appKey = ExampleUtil.getAppKey(getApplicationContext());
		if (null == appKey)
			appKey = "AppKey异常";
		mAppKey.setText("AppKey: " + appKey);
		String packageName = getPackageName();
		TextView mPackage = (TextView) findViewById(R.id.tv_package);
		mPackage.setText("PackageName: " + packageName);
		String versionName = ExampleUtil.GetVersion(getApplicationContext());
		TextView mVersion = (TextView) findViewById(R.id.tv_version);
		mVersion.setText("Version: " + versionName);

		mInit.setOnClickListener(this);
		mStopPush.setOnClickListener(this);
		mResumePush.setOnClickListener(this);
		mSetting.setOnClickListener(this);
		personal_center_btn.setOnClickListener(this);
		navi_sendGoods_btn.setOnClickListener(this);
		navi_daigou_btn.setOnClickListener(this);
		navi_shundai_btn.setOnClickListener(this);
		List<Express> res = new ArrayList<Express>();
		exchangedInfo_listview.setAdapter(new ImageAndTextAdapter(res, PushMainActivity.this));
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.init:
			init();
			break;
		case R.id.setting:
			Intent intent = new Intent(PushMainActivity.this, PushSetActivity.class);
			startActivity(intent);
			break;
		case R.id.stopPush:
			JPushInterface.stopPush(getApplicationContext());
			break;
		case R.id.resumePush:
			JPushInterface.resumePush(getApplicationContext());
			break;
		case R.id.navi_shundai_btn:
			startActivity(new Intent(PushMainActivity.this, MapActivity.class));
			break;
		case R.id.navi_sendGoods_btn:
			startActivity(new Intent(PushMainActivity.this, SendExpress.class));
			break;
		case R.id.navi_daigou_btn:
			startActivity(new Intent(PushMainActivity.this, Daigou.class));
			break;
		case R.id.personal_center:
//			startActivity(new Intent(PushMainActivity.this, PersonalActivity.class));
			startActivity(new Intent(PushMainActivity.this, CampusMap.class));
			break;
		}
	}

	// 初始化 JPush。如果已经初始化，但没有登录成功，则执行重新登录。
	private void init() {
		JPushInterface.init(getApplicationContext());
	}

	@Override
	protected void onResume() {
		isForeground = true;
		super.onResume();
	}

	@Override
	protected void onPause() {
		isForeground = false;
		super.onPause();
	}

	@Override
	protected void onDestroy() {
		unregisterReceiver(mMessageReceiver);
		super.onDestroy();
	}

	// for receive customer msg from jpush server
	private MessageReceiver mMessageReceiver;
	public static final String MESSAGE_RECEIVED_ACTION = "com.example.jpushdemo.MESSAGE_RECEIVED_ACTION";
	public static final String KEY_TITLE = "title";
	public static final String KEY_MESSAGE = "message";
	public static final String KEY_EXTRAS = "extras";

	public void registerMessageReceiver() {
		mMessageReceiver = new MessageReceiver();
		IntentFilter filter = new IntentFilter();
		filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
		filter.addAction(MESSAGE_RECEIVED_ACTION);
		registerReceiver(mMessageReceiver, filter);
	}

	public class MessageReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			if (MESSAGE_RECEIVED_ACTION.equals(intent.getAction())) {
				String messge = intent.getStringExtra(KEY_MESSAGE);
				String extras = intent.getStringExtra(KEY_EXTRAS);
				StringBuilder showMsg = new StringBuilder();
				showMsg.append(KEY_MESSAGE + " : " + messge + "\n");
				if (!ExampleUtil.isEmpty(extras)) {
					showMsg.append(KEY_EXTRAS + " : " + extras + "\n");
				}
				setCostomMsg(showMsg.toString());
			}
		}
	}

	private void setCostomMsg(String msg) {
		if (null != msgText) {
			msgText.setText(msg);
			msgText.setVisibility(android.view.View.VISIBLE);
		}
	}

	class MyTask extends AsyncTask<String, Integer, String> {
		String[] array1;
		String[] array2;
		String type;

		public MyTask(String type, String[] array1, String[] array2) {
			this.array1 = array1;
			this.array2 = array2;
			this.type = type;
		}

		@Override
		protected String doInBackground(String... arg0) {
			return new DataPoster().myHttpPostData(PushMainActivity.class, arg0[0], array1, array2);
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			if (!Constants.isMyExceptions(result)) {
				if ("sign".equals(type)) {
					if (!(null == result) && result.indexOf("success") > -1) {
					} else {
					}
				} else if ("sysmessage".equals(type)) {
				} else if ("exchangedInfo".equals(type)) {
					Type listType = new TypeToken<LinkedList<Express>>() {
					}.getType();
					Gson gson = new Gson();
					LinkedList<Express> infos = gson.fromJson(result, listType);
					List<String> list = new ArrayList<String>();
					for (Iterator iterator = infos.iterator(); iterator.hasNext();) {
						Express info = (Express) iterator.next();
						//list.add("恭喜用户:" + info.getUserId() + "兑换" + info.getCount() + "积分的" + Constants.getDetailWay(info.getWay()) + "成功！");
					}

					//exchangedInfo_listview.setAdapter(new ImageAndTextAdapter(list, PushMainActivity.this));
				}
				if ("sign".equals(type)) {
				}
			} else {
			}
		}

	}

}