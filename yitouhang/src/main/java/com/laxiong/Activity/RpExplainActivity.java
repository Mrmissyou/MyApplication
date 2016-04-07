package com.laxiong.Activity;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import com.laxiong.View.CommonActionBar;
import com.laxiong.yitouhang.R;

public class RpExplainActivity extends BaseActivity {
    private static final String TAG = "RpExplainActivity";
    private CommonActionBar actionbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rp_explain);
        init();
    }

    private void init() {
        actionbar = (CommonActionBar) findViewById(R.id.actionbar);
        actionbar.setBackListener(this);
    }
}
