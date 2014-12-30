package com.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/**
 * 
 * @ClassName: MD5Util 
 * @Description: (���ڶ�������ܵ���) 
 * @author xushenglin
 * @date 2014-12-17 ����5:18:09   
 */
public class MD5Util {
	/**
	 * 
	 * @Title: getMD5 
	 * @Description: (���ַ���ʹ��md5���ܵķ���) 
	 * @param @param str:�������
	 * @param @return  md5���ܺ�Ľ��
	 * @param @throws NoSuchAlgorithmException    �趨�ļ� 
	 * @return String    �������� 
	 * @throws 
	 */
	 public static String getMD5(String str) throws NoSuchAlgorithmException{
    	 byte [] buf = str.getBytes();
    	 //��ȡMD5����ʵ��
         MessageDigest md5 = MessageDigest.getInstance("MD5");
         //��byte�������
         md5.update(buf);
         byte [] tmp = md5.digest();
         StringBuilder sb = new StringBuilder();
         for (byte b:tmp) {
        	 /**
        	  * ���õ���MD5���ܽ��ת����16������ʽ
        	  */
             sb.append(Integer.toHexString(b&0xff));
         }
         return sb.toString();
    }

}
