package com.laxiong.Activity;

import android.os.Bundle;
import android.widget.TextView;

import com.laxiong.yitouhang.R;

public class BuyingResultActivity extends BaseActivity {
	/***
	 * 购买结果页面
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_buying_result);
		initView();
		
	}

	private void initView() {
		TextView mTitle = (TextView)findViewById(R.id.title);
		mTitle.setText("购买结果");
	}
	
	

}
