package com.laxiong.Activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.laxiong.yitouhang.R;

public class TransferInResultActivity extends BaseActivity implements OnClickListener{
	/***
	 * 转入结果
	 */
	private FrameLayout mBack ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tranferin_result);
		initView();
		initData();
	}

	private void initData() {
		mBack.setOnClickListener(this);
	}

	private void initView() {
		TextView mTitle = (TextView)findViewById(R.id.title);
		mTitle.setText("转入");
		
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
