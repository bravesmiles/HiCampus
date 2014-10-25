/**
 * 
 */
package com.smiles.campus.ui.listener;

import com.guo.ui.base.BaseActivity;
import com.smiles.campus.map.CampusMap;
import com.smiles.campus.ui.LoginActivity;
import com.smiles.campus.utils.LogUtil;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * @author yaojliu Cick listener for all buttons, recognized by tag
 */
public class ButtonClickListener implements OnClickListener {

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	private BaseActivity activity;

	public ButtonClickListener(BaseActivity activity) {
		super();
		this.activity = activity;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Button button = (Button) v;
		String text = (String) button.getText();

		LogUtil.log(LogUtil.BUTTON_CLICK_TAG, text);
//		Log.i("Button Clicked", text);

		if (BaseActivity.isLogged()) {
			LogUtil.log(LogUtil.USER_STATUS_TAG, "logged in");
		} else {
			LogUtil.log(LogUtil.USER_STATUS_TAG, "not logged");
			if(!LoginActivity.isAlreadyHere()){
				activity.openActivity(LoginActivity.class);
				return;
			}
		}
		
		switch(text){
		case "个人信息":
			activity.openActivity(CampusMap.class);
			activity.finish();
			break;
		case "登录":
			LogUtil.log(LogUtil.USER_STATUS_TAG, "login request");
			activity.showProgressDialog("Loading..");
			activity.openActivity(CampusMap.class);
			activity.cancelProgressDialog();
			break;
		case "取消":
			LogUtil.log(LogUtil.USER_STATUS_TAG, "login canceled");
			activity.finish();
			break;
		}

	}

}
