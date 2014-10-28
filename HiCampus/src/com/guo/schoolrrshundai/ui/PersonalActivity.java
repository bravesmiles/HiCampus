package com.guo.schoolrrshundai.ui;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import cn.jpush.android.api.InstrumentedActivity;

import com.guo.adapter.ImageAndTextAdapterNoIntro;
//import com.guo.mywebsiteclient.R;
import com.smiles.campus.R;

public class PersonalActivity extends InstrumentedActivity {

	private ListView personal_info_listview;
	public static boolean isForeground = false;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_personal);
		findViewById();
		initView();
	}

	protected void findViewById() {
		personal_info_listview = (ListView) findViewById(R.id.personal_info_listview);

	}

	protected void initView() {
		List<Integer> icons = new ArrayList<Integer>();
		icons.add(R.drawable.icon_earnranking);
		icons.add(R.drawable.icon_sendandget);
		icons.add(R.drawable.icon_sendandget);
		icons.add(R.drawable.icon_marka);
		icons.add(R.drawable.icon_yijian);
		icons.add(R.drawable.icon_update);
		icons.add(R.drawable.icon_setting);
		icons.add(R.drawable.icon_liuliang);

		List<String> res = new ArrayList<String>();
		res.add("余额");
		res.add("送过的货");
		res.add("发过的货");
		res.add("更多赚钱内容");
		res.add("意见反馈");
		res.add("检测更新");
		res.add("系统设置");
		res.add("流量检测");
		personal_info_listview.setAdapter(new ImageAndTextAdapterNoIntro(res, icons, PersonalActivity.this));
		personal_info_listview.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

			}
		});
	}

}