package com.laxiong.Activity;

import android.os.Bundle;
import android.widget.TextView;

import com.laxiong.yitouhang.R;

public class TransferOutActivity extends BaseActivity {
	/***
	 * 转出
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_transferout);
		initView();
	}

	private void initView() {
		TextView mTitle = (TextView)findViewById(R.id.title);
		mTitle.setText("转出");
	}
	
	
	
}
