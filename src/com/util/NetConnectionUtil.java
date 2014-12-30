package com.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 
 * @ClassName: NetConnectionUtil 
 * @Description: 该类用于检测网络是否有链接 
 * @author xushenglin
 * @date 2014-12-23 下午1:51:15   
**/
public class NetConnectionUtil {
	/**
	 * 
	 * @Title: checkNetworkAvailable 
	 * @Description: 该方法用于检测网络是否有链接 
	 * @param @param context
	 * @param @return    设定文件 
	 * @return boolean   网络是否获得链接
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
