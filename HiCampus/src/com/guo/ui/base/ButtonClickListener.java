/**
 * 
 */
package com.guo.ui.base;

import com.guo.schoolrrshundai.ui.MainActivity;
import com.smiles.campus.map.BaseMapDemo;
import com.smiles.campus.ui.LoginActivity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * @author yaojliu
 * Cick listener for all buttons, recognized by tag
 */
public class ButtonClickListener implements OnClickListener {

	/* (non-Javadoc)
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	private BaseActivity baseActivity;
	
	public ButtonClickListener(BaseActivity baseActivity) {
		super();
		this.baseActivity = baseActivity;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Button button = (Button) v;
		String text = (String) button.getText();
		
		Log.i("Button Clicked", text);
		
		if(BaseActivity.isLogged()){
			Log.i("User Status", "logged in");
		}
		else{
			Log.i("User Status", "not logged");
			baseActivity.openActivity(LoginActivity.class);
		}
		
		if(text.equals("个人信息")){
			baseActivity.openActivity(BaseMapDemo.class);
		}
		
	}

}
