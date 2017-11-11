package com.dff.weixin.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class SignUtil {
	/*
	 * 检测传过来的数值
	 */
	private static String token = "dff";
	private static String toStr;

	public static boolean checkSignature(String signature, String timestamp, String nonce) {
		// 1.将token，timestamp，nonce进行字典排序，放入数组，用sort方法。
		String[] arr = new String[] { token, timestamp, nonce };
		Arrays.sort(arr);
		// 2.将三个参数拼接成一个字符串进行SHA-1加密
		StringBuilder content = new StringBuilder();
		for (int i = 0; i < arr.length; i++) {
			content.append(arr[i]);
		}
		// 进行SHA-1加密
		MessageDigest md = null;
		try {
			// 加密准备工作
			md = MessageDigest.getInstance("SHA-1");
			byte[] bs = md.digest(content.toString().getBytes());
			// 将bs进行加密并转换为String
			toStr = byteToStr(bs);

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		//将content置空
		content = null;
		//将加密的数据和signature相比较，避免出现空指针异常，先判断是否为空
		return toStr != null ? toStr.equals(signature.toUpperCase()):false;
	}
	
	/*
	 * 将byte类型转换为String类型
	 */
	private static String byteToStr(byte[] bs) {
		String strDigest = "";
		for (int i = 0; i < bs.length; i++) {
			//添加加密
			strDigest += byteToHexStr(bs[i]);
		}
		return strDigest;
	}
	/*
	 * @discrible 对byte类型进行加密
	 * @author xuhai
	 */
	
	private static String byteToHexStr(byte b) {
		char[] Digit = {'1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
		char[] temArr = new char[2];
		//将b向右边移动四位
		temArr[0] = Digit[(b>>>4)&(0X0F)];
		temArr[1] = Digit[b & 0X0F];
		String s = new String(temArr);
		return s;
	}

}
