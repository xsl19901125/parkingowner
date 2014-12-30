package com.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/**
 * 
 * @ClassName: MD5Util 
 * @Description: (用于对密码加密的类) 
 * @author xushenglin
 * @date 2014-12-17 下午5:18:09   
 */
public class MD5Util {
	/**
	 * 
	 * @Title: getMD5 
	 * @Description: (对字符串使用md5加密的方法) 
	 * @param @param str:传入参数
	 * @param @return  md5加密后的结果
	 * @param @throws NoSuchAlgorithmException    设定文件 
	 * @return String    返回类型 
	 * @throws 
	 */
	 public static String getMD5(String str) throws NoSuchAlgorithmException{
    	 byte [] buf = str.getBytes();
    	 //获取MD5加密实例
         MessageDigest md5 = MessageDigest.getInstance("MD5");
         //对byte数组加密
         md5.update(buf);
         byte [] tmp = md5.digest();
         StringBuilder sb = new StringBuilder();
         for (byte b:tmp) {
        	 /**
        	  * 将得到的MD5加密结果转换成16进制形式
        	  */
             sb.append(Integer.toHexString(b&0xff));
         }
         return sb.toString();
    }

}
