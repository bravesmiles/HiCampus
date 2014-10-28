package com.smiles.campus.ui;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.ExecutionException;

import com.guo.DB.UserDB;
import com.guo.config.Constants;
import com.guo.ui.base.BaseActivity;
import com.smiles.campus.R;
import com.smiles.campus.ui.listener.ButtonClickListener;
import com.smiles.campus.utils.ImageLoader;
import com.smiles.campus.utils.LogUtil;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class LoginActivity extends BaseActivity {

	public static String VERIFY_IMG_URL = Constants.PHONE_BASE_URL + "code.asp";
	public static String LOGIN_URL = Constants.PHONE_BASE_URL + "code.asp";
	public static String REGISTER_URL = Constants.PHONE_BASE_URL + "code.asp";

	private static boolean isAlreadyHere;

	private Button loginButton;
	private Button cancelButton;

	private EditText account;
	private EditText password;

	private EditText verifyCode;
	private ImageView verifyImage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		init();
		isAlreadyHere = true;
	}

	private boolean login() {
		String userName = this.account.getText().toString();
		String password = this.password.getText().toString();
//		String verifyCode = this.verifyCode.getText().toString();
		
		LogUtil.log(LogUtil.USER_STATUS_TAG, userName + ", " + password + ", " + verifyCode);
		
		return true;
	}

	@Override
	protected void findViewById() {
		// TODO Auto-generated method stub
		loginButton = (Button) findViewById(R.id.regist_submit_btn);
		cancelButton = (Button) findViewById(R.id.regist_cancel_btn);

		account = (EditText) findViewById(R.id.old_user_account);
		password = (EditText) findViewById(R.id.old_user_passwd);

//		verifyImage = (ImageView) findViewById(R.id.verify_img);
//		verifyCode = (EditText) findViewById(R.id.verify_code);
	}

	@Override
	protected void initView() {
		// TODO Auto-generated method stub
		OnClickListener btnClickListener = new ButtonClickListener(this);
		loginButton.setOnClickListener(btnClickListener);
		cancelButton.setOnClickListener(btnClickListener);

		ImageLoader imageLoader = new ImageLoader();
		imageLoader.execute(VERIFY_IMG_URL);

//		try {
//			verifyImage.setImageBitmap(imageLoader.get());
//		} catch (InterruptedException | ExecutionException e) {
//			// TODO Auto-generated catch block
//			LogUtil.log(LogUtil.USER_STATUS_TAG, e.toString());
//		}
	}

	public static boolean isAlreadyHere() {
		return isAlreadyHere;
	}

	// public static void setAlreadyHere(boolean isAlreadyHere) {
	// LoginActivity.isAlreadyHere = isAlreadyHere;
	// }

	@Override
	public void finish() {
		// TODO Auto-generated method stub
		LoginActivity.isAlreadyHere = false;
		super.finish();
	}

	@Override
	public boolean process() {
		// TODO Auto-generated method stub
		return login();
	}
}
