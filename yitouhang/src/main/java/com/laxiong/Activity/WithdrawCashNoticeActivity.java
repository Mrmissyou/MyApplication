package com.laxiong.Activity;

import com.laxiong.yitouhang.R;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.TextView;

public class WithdrawCashNoticeActivity extends BaseActivity implements OnClickListener{
	/***
	 * 提现说明
	 */
	private FrameLayout mBack ;
	private TextView mTitle ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_withdrawcashnotice);
		initView();
		initData();
	}

	private void initData() {
		mBack.setOnClickListener(this);
	}

	private void initView() {
		mBack = (FrameLayout)findViewById(R.id.back_layout);
		mTitle = (TextView)findViewById(R.id.title);
		mTitle.setText("提现说明");
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
