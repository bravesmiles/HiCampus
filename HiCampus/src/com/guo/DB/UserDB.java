package com.guo.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.guo.utils.Encrypt;
import com.smiles.campus.utils.LogUtil;

public class UserDB extends SQLiteOpenHelper {
	private final static String DATABASE_NAME = "users.db";
	private final static int DATABASE_VERSION = 31;
	private final static String TABLE_NAME = "user_table";
	public final static String T_ID = "t_id";

	public final static String USER_ACCOUNT = "user_name";
	public final static String USER_DEVICEID = "user_deviceid";
	public final static String USER_NICKNAME = "nickName";
	public final static String USER_PASSWD = "user_passwd";
	public final static String USER_PHONE = "user_phone";
	public final static String USER_EMAIL = "user_email";
	public final static String USER_QQ = "user_qq";
	public final static String USER_CFT = "user_cft";
	public final static String USER_ALIPAY = "user_alipay";
	public final static String USER_XUNLEI = "user_xunlei";
	public final static String USER_KUAIBO = "user_kuaibo";

	public UserDB(Context context) {
		// TODO Auto-generated constructor stub
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		LogUtil.print("create db");
		String sql = "CREATE TABLE " + TABLE_NAME + " (" + T_ID
				+ " INTEGER primary key autoincrement, " + USER_DEVICEID
				+ " text," + USER_ACCOUNT + " text, " + USER_NICKNAME
				+ " text, " + USER_PASSWD + " text, " + USER_PHONE + " text, "
				+ USER_EMAIL + " text, " + USER_QQ + " text, " + USER_CFT
				+ " text, " + USER_ALIPAY + " text, " + USER_XUNLEI + " text, "
				+ USER_KUAIBO + " text" + ");";
		LogUtil.print("create db" + sql);
		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		LogUtil.print("create db，new db:" + newVersion + ";" + oldVersion);
		String sql = "DROP TABLE IF EXISTS " + TABLE_NAME;
		db.execSQL(sql);
		onCreate(db);
	}

	public Cursor select() {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db
				.query(TABLE_NAME, null, null, null, null, null, null);
		return cursor;
	}

	public Cursor accurateSelect() {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery("select * from user_table limit 0,1", null);
		return cursor;
	}

	public long insert(String deviceId, String account, String nickName,
			String password, String phone, String email, String qq, String cft,
			String alipay, String xunlei, String kuaibo) {
		SQLiteDatabase db = this.getWritableDatabase();
		/* ContentValues */
		LogUtil.print("deviceId：" + deviceId + ";" + account);
		ContentValues cv = new ContentValues();
		cv.put(USER_ACCOUNT, account);
		cv.put(USER_DEVICEID, deviceId);
		cv.put(USER_NICKNAME, nickName);
		cv.put(USER_PASSWD, Encrypt.Md5(password));
		cv.put(USER_PHONE, phone);
		cv.put(USER_EMAIL, email);
		cv.put(USER_QQ, qq);
		cv.put(USER_CFT, cft);
		cv.put(USER_ALIPAY, alipay);
		cv.put(USER_XUNLEI, xunlei);
		cv.put(USER_KUAIBO, kuaibo);
		long row = db.insert(TABLE_NAME, null, cv);
		return row;
	}

	public void delete(int id) {
		SQLiteDatabase db = this.getWritableDatabase();
		String where = T_ID + " = ?";
		String[] whereValue = { Integer.toString(id) };
		db.delete(TABLE_NAME, where, whereValue);
	}

	public void update(int id, String deviceId, String account,
			String nickName, String password, String phone, String email,
			String qq, String cft, String alipay, String xunlei, String kuaibo) {
		SQLiteDatabase db = this.getWritableDatabase();
		String where = T_ID + " = ?";
		String[] whereValue = { Integer.toString(id) };

		ContentValues cv = new ContentValues();
		cv.put(USER_DEVICEID, deviceId);
		cv.put(USER_NICKNAME, nickName);
		cv.put(USER_PASSWD, Encrypt.Md5(password));
		cv.put(USER_PHONE, phone);
		cv.put(USER_EMAIL, email);
		cv.put(USER_QQ, qq);
		cv.put(USER_CFT, cft);
		cv.put(USER_ALIPAY, alipay);
		cv.put(USER_XUNLEI, xunlei);
		cv.put(USER_KUAIBO, kuaibo);
		db.update(TABLE_NAME, cv, where, whereValue);
	}
}
