package com.tedu.cloudnote.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import org.apache.tomcat.util.codec.binary.Base64;

public class NoteUtil {
	
	/**
	 * 利用 UUID算法生成一个主键值
	 * @return 主键值
	 */
	public static String createId(){
		UUID uuid = UUID.randomUUID();
		String id = uuid.toString();
		return id;
	}
	
	/**
	 * 密码加密处理(MD5)
	 * @param src 原密码
	 * @return 加密后的密码内容
	 */
	public static String md5(String src){
		
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] output = md.digest(src.getBytes());//加密处理
			//将加密结果output利用base64转成字符串输出
			String ret = Base64.encodeBase64String(output);
			return ret;
		} catch (NoSuchAlgorithmException e) {
			throw new NoteException("密码加密失败", e);
		}
	}
	
	public static void main(String[] args) {
		System.out.println(createId());
	}
}
