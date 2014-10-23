package com.guo.ui.base;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.Spanned;
import android.widget.TextView;
import android.widget.Toast;

import com.guo.config.AppManager;
import com.smiles.campus.R;

public abstract class BaseActivity extends Activity {

	public static final String TAG = BaseActivity.class.getSimpleName();

	protected Handler mHandler = null;
	protected TextView title;
	ProgressDialog progressDialog = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		AppManager.getInstance().addActivity(this);
	}

	public static void setTitleText(Activity context, String titleStr) {
		// View view = context.getLayoutInflater().inflate(viewId, null);
		TextView title = (TextView) context.findViewById(R.id.activity_title_text);
		title.setText(titleStr);
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}

	/**
	 * 绑定控件id
	 */
	protected abstract void findViewById();

	/**
	 * 初始化控件
	 */
	protected abstract void initView();

	/**
	 * 通过类名启动Activity
	 * 
	 * @param pClass
	 */
	protected void openActivity(Class<?> pClass) {
		openActivity(pClass, null);
	}

	/**
	 * 通过类名启动Activity，并且含有Bundle数据
	 * 
	 * @param pClass
	 * @param pBundle
	 */
	protected void openActivity(Class<?> pClass, Bundle pBundle) {
		Intent intent = new Intent(this, pClass);
		if (pBundle != null) {
			intent.putExtras(pBundle);
		}
		startActivity(intent);
	}

	/**
	 * 通过Action启动Activity
	 * 
	 * @param pAction
	 */
	protected void openActivity(String pAction) {
		openActivity(pAction, null);
	}

	/**
	 * 通过Action启动Activity，并且含有Bundle数据
	 * 
	 * @param pAction
	 * @param pBundle
	 */
	protected void openActivity(String pAction, Bundle pBundle) {
		Intent intent = new Intent(pAction);
		if (pBundle != null) {
			intent.putExtras(pBundle);
		}
		startActivity(intent);
	}

	protected void ToastLong(String content) {
		Toast.makeText(this, content, Toast.LENGTH_LONG).show();
	}

	public void ToastShort(String content) {
		Toast.makeText(this, content, Toast.LENGTH_SHORT).show();
	}

	/** 加载进度条 */
	public void showProgressDialog(String content) {
		if (progressDialog != null) {
			progressDialog.cancel();
		}
		progressDialog = new ProgressDialog(this);
		Drawable drawable = getResources().getDrawable(R.drawable.loading_animation);
		progressDialog.setIndeterminateDrawable(drawable);
		progressDialog.setIndeterminate(true);
		progressDialog.setCancelable(true);
		progressDialog.setMessage(content);
		progressDialog.show();
	}

	public void cancelProgressDialog() {
		if (progressDialog != null) {
			progressDialog.cancel();
		}
	}

	/** 含有标题、内容、两个按钮的对话框 **/
	protected void showAlertDialog(String title, Spanned spanned, String positiveText, DialogInterface.OnClickListener onPositiveClickListener, String negativeText,
			DialogInterface.OnClickListener onNegativeClickListener) {
		new AlertDialog.Builder(this).setTitle(title).setMessage(spanned).setPositiveButton(positiveText, onPositiveClickListener).setNegativeButton(negativeText, onNegativeClickListener).show();
	}

}
