package com.laxiong.Activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.laxiong.yitouhang.R;

public class WithdrawCashDetailsActivity extends BaseActivity implements OnClickListener{
	/***
	 * 提现详情
	 */
	private TextView mTitle ;
	private FrameLayout mBack ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_withdrawcashdetails);
		initView();
		initData();
	}

	private void initData() {
		mBack.setOnClickListener(this);
	}

	private void initView() {
		mTitle = (TextView)findViewById(R.id.title);
		mTitle.setText("提现详情");
		mBack = (FrameLayout)findViewById(R.id.back_layout);
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
			case R.id.back_layout:
				this.finish();
				break;
		}
	}
	
	

}
