package com.lottery.common.util;

/**
 * 密码计算工具
 * @author liuzhenghang
 * @since 2016年11月6日 下午9:36:44
 */
public class PasswordUtil {
	
	private static final String KEYT = "ygwypwd";
	private static final Integer MD5_LENGTH = 32;
	/**
	 * md5(md5($keyt+$pay_password).$keyt);
     * ¥keyt = bqpaypwd
	 * @param password
	 * @returng
	 */
	public static String encrypt(String password) {
		if( password.length() == MD5_LENGTH ){//如果长度已经是md5的长度,则不加密,直接保存
			return password;
		}
		String md5stepone = Md5Utils.encryptMD5(KEYT+password, "utf-8");
		String md5steptwo = Md5Utils.encryptMD5(md5stepone+KEYT, "utf-8");
		
		return md5steptwo;
	}

	public static void main(String[] args) {
		System.out.println(encrypt("834029"));
		System.out.println(encrypt("163758"));
		System.out.println(encrypt("123456a"));
	}
}
