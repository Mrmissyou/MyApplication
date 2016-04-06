package com.laxiong.Utils;

import java.net.SocketException;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo.State;

public class CommonUtils {

	// 检查网络状态
	public static boolean checkNetworkState(Context context) throws SocketException {
		ConnectivityManager connectivityManager = (ConnectivityManager) context
					.getSystemService(Activity.CONNECTIVITY_SERVICE);

		State mobileState = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();
		State wifiState = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();
		// 未连接网络
		if (!mobileState.equals(State.CONNECTED) && !mobileState.equals(State.CONNECTING)
					&& !wifiState.equals(State.CONNECTED) && !wifiState.equals(State.CONNECTING)) {
				return false;
		}

		return true;
	}
	
	
}
