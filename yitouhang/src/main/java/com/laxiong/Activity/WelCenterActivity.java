package com.laxiong.Activity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;

import com.laxiong.Basic.BackListener;
import com.laxiong.yitouhang.R;

/**
 * Created by win7 on 2016/4/6.
 * Types WelCenterActivity.java
 */
public class WelCenterActivity extends BaseActivity {
    private static final String TAG = "WelCenterActivity";
    private FrameLayout fl_back;
    private WebView wv_all;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcenter);
        init();
    }

    private void init() {
        fl_back = (FrameLayout) findViewById(R.id.fl_back);
        wv_all = (WebView) findViewById(R.id.wv_all);
        fl_back.setOnClickListener(new BackListener(this));
        wv_all.loadUrl("http://baidu.com");
        wv_all.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }

}
