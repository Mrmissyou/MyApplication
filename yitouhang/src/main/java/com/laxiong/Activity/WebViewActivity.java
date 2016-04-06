package com.laxiong.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.laxiong.yitouhang.R;

public class WebViewActivity extends BaseActivity {
	/****
	 * html 页面  协议 活动 等
	 */
	private WebView mWebView ;
	private String urls ="https://licai.gongshidai.com/wap/public/invite/testtest.html";  //链接
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_webview);
		initData();
		
	}

	@SuppressLint({ "NewApi", "SetJavaScriptEnabled" }) 
	private void initData() {
		mWebView = (WebView)findViewById(R.id.webviews);
		
//		mWebView.loadUrl(url); //WebView加载web资源
//		//覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
//		mWebView.setWebViewClient(new WebViewClient(){
//			@Override
//			public boolean shouldOverrideUrlLoading(WebView view, String url) {
//				//返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
//				view.loadUrl(url);
//				return true;
//			}
//		});
		
		WebSettings settings = mWebView.getSettings();
		settings.setUseWideViewPort(true);
		settings.setLoadWithOverviewMode(true);
		settings.setJavaScriptEnabled(true);
		settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
		settings.setDomStorageEnabled(true);
		
		/*
		 * 4.0-4.2 防范攻击者植入js
		 */
		mWebView.removeJavascriptInterface("searchBoxJavaBredge_");
		
		mWebView.setWebChromeClient(new WebChromeClient() {

		});
		
		mWebView.setWebViewClient(new WebViewClient(){
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				if(url.startsWith("gongshi1://")){
					startActivity(new Intent(WebViewActivity.this,
							GuXiBaoActivity.class));
					WebViewActivity.this.finish();
				}
				mWebView.loadUrl(url);
				return true ;
			}
		});
		
		mWebView.loadUrl(urls);
		
	}
	
	

}
