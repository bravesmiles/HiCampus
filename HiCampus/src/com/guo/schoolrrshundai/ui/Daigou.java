package com.guo.schoolrrshundai.ui;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.guo.config.Constants;
import com.guo.jpushdemo.ExampleApplication;
//import com.guo.mywebsiteclient.R;
import com.guo.ui.base.BaseActivity;
import com.guo.utils.DataPoster;
//import com.guo.utils.LogUtil;
import com.smiles.campus.R;
import com.smiles.campus.utils.LogUtil;

public class Daigou extends BaseActivity {

	private EditText daigou_expectTime;
	private EditText daigou_goods_name;
	private EditText daigou_pay;
	private EditText daigou_shouhuoAddress;

	private Button daigou_submit_btn, daigou_cancel_btn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_daigou);
		findViewById();
		initView();
	}

	@Override
	protected void findViewById() {
		daigou_expectTime = (EditText) findViewById(R.id.daigou_expectTime);
		daigou_goods_name = (EditText) findViewById(R.id.daigou_goods_name);
		daigou_pay = (EditText) findViewById(R.id.daigou_pay);
		daigou_shouhuoAddress = (EditText) findViewById(R.id.daigou_shouhuoAddress);

		daigou_submit_btn = (Button) findViewById(R.id.daigou_submit_btn);
		daigou_cancel_btn = (Button) findViewById(R.id.daigou_cancel_btn);

	}

	@Override
	protected void initView() {
		daigou_submit_btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				new MyTask().execute(Constants.DAIGOUURL);
			}
		});
		daigou_cancel_btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

			}
		});
	}

	class MyTask extends AsyncTask<String, Integer, String> {

		@Override
		protected String doInBackground(String... arg0) {

			LogUtil.print("sendEx:"
					+ new DataPoster().myHttpPostData(Daigou.class, Constants.SENDANEXPRESS, new String[] { "userId", "expectTime", "goods_name", "goods_pay", "shouhuoAdd" }, new String[] {
							((ExampleApplication) getApplication()).getUserId(), daigou_expectTime.getText().toString(), daigou_goods_name.getText().toString(), daigou_pay.getText().toString(),
							daigou_shouhuoAddress.getText().toString() }));
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
		}

	}

	@Override
	public boolean process() {
		// TODO Auto-generated method stub
		return false;
	}
}
