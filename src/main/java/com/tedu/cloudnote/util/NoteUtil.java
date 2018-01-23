package com.tedu.cloudnote.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import org.apache.tomcat.util.codec.binary.Base64;

public class NoteUtil {
	
	/**
	 * ���� UUID�㷨����һ������ֵ
	 * @return ����ֵ
	 */
	public static String createId(){
		UUID uuid = UUID.randomUUID();
		String id = uuid.toString();
		return id;
	}
	
	/**
	 * ������ܴ���(MD5)
	 * @param src ԭ����
	 * @return ���ܺ����������
	 */
	public static String md5(String src){
		
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] output = md.digest(src.getBytes());//���ܴ���
			//�����ܽ��output����base64ת���ַ������
			String ret = Base64.encodeBase64String(output);
			return ret;
		} catch (NoSuchAlgorithmException e) {
			throw new NoteException("�������ʧ��", e);
		}
	}
	
	public static void main(String[] args) {
		System.out.println(createId());
	}
}
