package com.guo.schoolrrshundai.ui;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.guo.adapter.ExchangedInfoAdapter;
import com.guo.entity.Order;
import com.guo.ui.base.BaseActivity;
import com.guo.utils.Constants;
import com.guo.utils.RestUtil;
import com.smiles.campus.R;
import com.smiles.campus.ui.listener.ButtonClickListener;

public class MainActivity extends BaseActivity {
	private ListView exchangedInfo_listview;
	private Button infoButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_push_main);
		init();
		// mCurrentMode = LocationMode.NORMAL;
		// requestLocButton.setText("普通");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
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
			return RestUtil.postData(MainActivity.class, arg0[0],
					array1, array2);
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			if (!Constants.isMyExceptions(result)) {
				if ("sign".equals(type)) {
					if (!(null == result) && result.indexOf("success") > -1) {
						ToastShort("加载成功！");
					} else {
						ToastShort("加载失败！");
					}
				} else if ("sysmessage".equals(type)) {
					ToastShort("系统消息");
				} else if ("exchangedInfo".equals(type)) {
					Type listType = new TypeToken<LinkedList<Order>>() {
					}.getType();
					Gson gson = new Gson();
					LinkedList<Order> infos = gson.fromJson(result, listType);
					List<String> list = new ArrayList<String>();
					for (Iterator iterator = infos.iterator(); iterator
							.hasNext();) {
						Order info = (Order) iterator.next();
						list.add("恭喜用户:" + info.getUserId() + "兑换"
								+ info.getCount() + "积分的"
								+ Constants.getDetailWay(info.getWay()) + "成功！");
					}

					exchangedInfo_listview.setAdapter(new ExchangedInfoAdapter(
							list, MainActivity.this));
				}
				if ("sign".equals(type)) {
					cancelProgressDialog();
				}
			} else {
				ToastShort("");
			}
		}

	}

	@Override
	protected void findViewById() {
		// TODO Auto-generated method stub
		exchangedInfo_listview = (ListView) findViewById(R.id.exchangedInfo_listview);
		// Binding UI and action
		infoButton = (Button) findViewById(R.id.personal_center);
	}

	@Override
	protected void initView() {
		// TODO Auto-generated method stub
		OnClickListener btnClickListener = new ButtonClickListener(this);
		infoButton.setOnClickListener(btnClickListener);
	}

	@Override
	public boolean process() {
		// TODO Auto-generated method stub
		return false;
	}

}
