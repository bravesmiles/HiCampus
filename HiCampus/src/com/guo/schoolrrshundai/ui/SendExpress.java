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

public class SendExpress extends BaseActivity {

	private EditText expectTime;
	private EditText goods_type;
	private EditText goods_name;
	private EditText goods_weight;
	private EditText goods_price;
	private EditText get_way;
	private EditText fromAddress;
	private EditText desAddress;

	private Button submit_btn, cancel_btn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sendexpress);
		findViewById();
		initView();
	}

	@Override
	protected void findViewById() {
		expectTime = (EditText) findViewById(R.id.expectTime);
		goods_type = (EditText) findViewById(R.id.goods_type);
		goods_name = (EditText) findViewById(R.id.goods_name);
		goods_weight = (EditText) findViewById(R.id.goods_weight);
		goods_price = (EditText) findViewById(R.id.goods_price);
		get_way = (EditText) findViewById(R.id.get_way);
		fromAddress = (EditText) findViewById(R.id.fromAddress);
		desAddress = (EditText) findViewById(R.id.desAddress);

		submit_btn = (Button) findViewById(R.id.submit_btn);
		cancel_btn = (Button) findViewById(R.id.cancel_btn);

	}

	@Override
	protected void initView() {
		submit_btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				new MyTask().execute(Constants.SENDANEXPRESS);
			}
		});
		cancel_btn.setOnClickListener(new OnClickListener() {
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
					+ new DataPoster().myHttpPostData(SendExpress.class, Constants.SENDANEXPRESS, new String[] { "userId", "expectTime", "goods_type", "goods_name", "goods_weight", "goods_price",
							"get_way", "fromAddress", "desAddress" }, new String[] { ((ExampleApplication) getApplication()).getUserId(), expectTime.getText().toString(),
							goods_type.getText().toString(), goods_name.getText().toString(), goods_weight.getText().toString(), goods_price.getText().toString(), get_way.getText().toString(),
							fromAddress.getText().toString(), desAddress.getText().toString() }));
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
