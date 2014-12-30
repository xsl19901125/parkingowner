package com.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 
 * @ClassName: NetConnectionUtil 
 * @Description: �������ڼ�������Ƿ������� 
 * @author xushenglin
 * @date 2014-12-23 ����1:51:15   
**/
public class NetConnectionUtil {
	/**
	 * 
	 * @Title: checkNetworkAvailable 
	 * @Description: �÷������ڼ�������Ƿ������� 
	 * @param @param context
	 * @param @return    �趨�ļ� 
	 * @return boolean   �����Ƿ�������
	 * @throws 
	*
	 */
	public static boolean checkNetworkAvailable(Context context) {
		ConnectivityManager connectivity = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity == null) {
			return false;
		} else {
			NetworkInfo[] info = connectivity.getAllNetworkInfo();
			if (info != null) {
				for (int i = 0; i < info.length; i++) {
					if (info[i].getState() == NetworkInfo.State.CONNECTED) {
						NetworkInfo netWorkInfo = info[i];
						if (netWorkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
							return true;
						} else if (netWorkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
							return true;
						}
					}
				}
			}
		}

		return false;
	}
}
