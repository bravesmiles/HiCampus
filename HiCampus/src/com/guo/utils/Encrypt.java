package com.guo.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import javax.crypto.Cipher;

import com.smiles.campus.utils.LogUtil;

/**
 * description: cateT
 * 
 * ime：2014-5-5下午04:30:57
 * 
 * updateTime：
 * 
 * author：叮当猫
 */
public class Encrypt {
	public static String Md5(String plainText) {
		StringBuffer buf = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plainText.getBytes());
			byte b[] = md.digest();

			int i;

			buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return buf.toString();
	}

	public static String md52(String val) {

		try {
			LogUtil.print(val);
			String result = null;
			val = new String(val.getBytes("UTF-8"));
			if (val != null && val.length() > 0) {
				try {

					MessageDigest md5 = MessageDigest.getInstance("MD5");
					byte[] buff = val.getBytes();
					md5.update(buff, 0, buff.length);
					result = String.format("%032x", new BigInteger(1, md5.digest()));
				} catch (Throwable e) {
				}
			}

			return result;

		} catch (Throwable e) {
			// handle Throwable
		}
		return "";
	}
	
	/**
	 * Change byte array to String.
	 * 
	 * @return byte[]
	 */
	public String bytesToString(byte[] encrytpByte) {
		String result = "";
		for (Byte bytes : encrytpByte) {
			result += (char) bytes.intValue();
		}
		return result;
	}

	/**
	 * Encrypt String.
	 * 
	 * @return byte[]
	 */
	public byte[] encrypt(RSAPublicKey publicKey, byte[] obj) {
		if (publicKey != null) {
			try {
				Cipher cipher = Cipher.getInstance("RSA");
				cipher.init(Cipher.ENCRYPT_MODE, publicKey);
				return cipher.doFinal(obj);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * Basic decrypt method
	 * 
	 * @return byte[]
	 */
	public byte[] decrypt(RSAPrivateKey privateKey, byte[] obj) {
		if (privateKey != null) {
			try {
				Cipher cipher = Cipher.getInstance("RSA");
				cipher.init(Cipher.DECRYPT_MODE, privateKey);
				return cipher.doFinal(obj);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return null;
	}

}
