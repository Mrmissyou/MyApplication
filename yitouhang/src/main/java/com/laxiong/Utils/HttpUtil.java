package com.laxiong.Utils;

import android.widget.Toast;

import com.laxiong.Application.YiTouApplication;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.BinaryHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class HttpUtil {
	private static AsyncHttpClient client = new AsyncHttpClient(); // 实例化对象

	public final static String CONTENT_TYPE = "application/x-www-form-urlencoded";

	static {
		client.setTimeout(60000); // 设置链接超时，如果不设置，默认为60s
	}

	public static void put(String urlString, AsyncHttpResponseHandler res, String authorization) {
		checkNet();
		client.addHeader("client", "android");
		client.addHeader("ACCEPT", "*/*");
		client.addHeader("Authorization", "Basic " + authorization);
		client.addHeader("Content-Type", CONTENT_TYPE);
		client.put(urlString, res);
	}

	public static void put(String urlString, RequestParams params, AsyncHttpResponseHandler res, String authorization) {
		checkNet();
		client.addHeader("client", "android");
		client.addHeader("ACCEPT", "*/*");
		client.addHeader("Authorization", "Basic " + authorization);
		client.addHeader("Content-Type", CONTENT_TYPE);
		client.put(urlString, params, res);
	}

	public static void put(String urlString, RequestParams params, JsonHttpResponseHandler res, String authorization) {
		checkNet();
		client.addHeader("client", "android");
		client.addHeader("ACCEPT", "*/*");
		client.addHeader("Authorization", "Basic " + authorization);
		client.addHeader("Content-Type", CONTENT_TYPE);
		client.addHeader("Bear", "9cfe953a2a6aef02aaf6971bc7ca62c3f0167e67");
		client.put(urlString, params, res);
	}

	public static void put(String url, RequestParams params, JsonHttpResponseHandler responseHandler,
			boolean needBear) {
		checkNet();
		client.addHeader("client", "android");
		client.addHeader("ACCEPT", "*/*");
		client.addHeader("Content-Type", CONTENT_TYPE);
		if (needBear)
			client.addHeader("Bear", "9cfe953a2a6aef02aaf6971bc7ca62c3f0167e67");
		client.put(url, params, responseHandler);
	}

	public static void get(String urlString, AsyncHttpResponseHandler res) // 用一个完整url获取一个string对象
	{
		checkNet();
		client.addHeader("client", "android");
		client.addHeader("ACCEPT", "*/*");
		client.get(urlString, res);
	}

	public static void get(String urlString, AsyncHttpResponseHandler res, String authorization) // 用一个完整url获取一个string对象
	{
		checkNet();
		client.addHeader("client", "android");
		client.addHeader("ACCEPT", "*/*");
		client.addHeader("Authorization", "Basic " + authorization);
		client.addHeader("Content-Type", CONTENT_TYPE);
		client.get(urlString, res);
	}

	public static void get(String urlString, JsonHttpResponseHandler res, String authorization) // 用一个完整url获取一个string对象
	{
		checkNet();
		client.addHeader("client", "android");
		client.addHeader("ACCEPT", "*/*");
		client.addHeader("Authorization", "Basic " + authorization);
		client.addHeader("Content-Type", CONTENT_TYPE);
		client.get(urlString, res);
	}

	public static void get(String urlString, RequestParams params, AsyncHttpResponseHandler res, String authorization) {
		checkNet();
		client.addHeader("client", "android");
		client.addHeader("ACCEPT", "*/*");
		client.addHeader("Authorization", "Basic " + authorization);
		client.addHeader("Content-Type", CONTENT_TYPE);
		client.get(urlString, params, res);
	}

	public static void get(String urlString, RequestParams params, AsyncHttpResponseHandler res) // url里面带参数
	{
		checkNet();
		client.addHeader("client", "android");
		client.addHeader("ACCEPT", "*/*");
		client.get(urlString, params, res);
	}

	public static void get(String urlString, JsonHttpResponseHandler res) // 不带参数，获取json对象或者数组
	{
		checkNet();
		client.addHeader("client", "android");
		client.addHeader("ACCEPT", "*/*");
		client.get(urlString, res);
	}

	public static void get(String urlString, RequestParams params, JsonHttpResponseHandler res, boolean needBear) // 带参数，获取json对象或者数组
	{
		checkNet();
		client.addHeader("client", "android");
		client.addHeader("ACCEPT", "*/*");
		if (needBear)
			client.addHeader("bear", "9cfe953a2a6aef02aaf6971bc7ca62c3f0167e67");
		client.get(urlString, params, res);
	}

	public static void get(String uString, BinaryHttpResponseHandler bHandler) // 下载数据使用，会返回byte数据
	{
		checkNet();
		client.get(uString, bHandler);
	}

	public static AsyncHttpClient getClient() {
		return client;
	}

	public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler,
			boolean needBear) {
		checkNet();
		client.addHeader("client", "android");
		client.addHeader("ACCEPT", "*/*");
		if (needBear)
			client.addHeader("Bear", "9cfe953a2a6aef02aaf6971bc7ca62c3f0167e67");
		client.post(url, params, responseHandler, CONTENT_TYPE);
	}

	public static void post(String url, RequestParams params, JsonHttpResponseHandler responseHandler,
			boolean needBear) {
		checkNet();
		client.addHeader("client", "android");
		client.addHeader("ACCEPT", "*/*");
		if (needBear)
			client.addHeader("Bear", "9cfe953a2a6aef02aaf6971bc7ca62c3f0167e67");
		client.post(url, params, responseHandler, CONTENT_TYPE);
	}

	public static void post(String url, RequestParams params, JsonHttpResponseHandler responseHandler,
			String authorization) {
		checkNet();
		client.addHeader("client", "android");
		client.addHeader("ACCEPT", "*/*");
		client.addHeader("Authorization", "Basic " + authorization);
		client.post(url, params, responseHandler, CONTENT_TYPE);
	}
	/**检测网络**/
	public static void checkNet() {
		/*
		 * check network state 
		 */
		try {
			if (!CommonUtils.checkNetworkState(YiTouApplication.getInstance())) {
				Toast.makeText(YiTouApplication.getInstance(), "当前没有网络，请检查网络后再试", Toast.LENGTH_SHORT).show();
				return;
			}
		} catch (Exception e) {
		}
	}

}
