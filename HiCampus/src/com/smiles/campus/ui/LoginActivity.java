package com.smiles.campus.ui;

import com.guo.ui.base.BaseActivity;
import com.smiles.campus.R;
import com.smiles.campus.ui.listener.ButtonClickListener;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View.OnClickListener;
import android.widget.Button;

public class LoginActivity extends BaseActivity {
	
	private static boolean isAlreadyHere;
	
	private Button loginButton;
	private Button cancelButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		init();
		isAlreadyHere = true;
	}

	@Override
	protected void findViewById() {
		// TODO Auto-generated method stub
		loginButton = (Button) findViewById(R.id.regist_submit_btn);
		cancelButton= (Button) findViewById(R.id.regist_cancel_btn);

	}

	@Override
	protected void initView() {
		// TODO Auto-generated method stub
		OnClickListener btnClickListener = new ButtonClickListener(this);
		loginButton.setOnClickListener(btnClickListener);
		cancelButton.setOnClickListener(btnClickListener);
	}

	public static boolean isAlreadyHere() {
		return isAlreadyHere;
	}

//	public static void setAlreadyHere(boolean isAlreadyHere) {
//		LoginActivity.isAlreadyHere = isAlreadyHere;
//	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub
		LoginActivity.isAlreadyHere = false;
		super.finish();
	}
}
