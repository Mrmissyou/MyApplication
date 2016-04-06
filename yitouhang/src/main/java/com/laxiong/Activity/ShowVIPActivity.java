package com.laxiong.Activity;

import com.laxiong.yitouhang.R;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;

public class ShowVIPActivity extends BaseActivity implements OnClickListener{
	/****
	 * VIP通道的
	 */
	private FrameLayout mBack ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_vip_layout);
		initView();
		initData();
	}

	private void initData() {
		mBack.setOnClickListener(this);
	}

	private void initView() {
		mBack = (FrameLayout)findViewById(R.id.back_layout);
	}

	@Override
	public void onClick(View V) {
		switch(V.getId()){
			case R.id.back_layout:
				this.finish();
				break;
		}
	}
	
	
}
