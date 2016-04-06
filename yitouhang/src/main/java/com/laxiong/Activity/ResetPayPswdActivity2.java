package com.laxiong.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.laxiong.yitouhang.R;

public class ResetPayPswdActivity2 extends BaseActivity implements OnClickListener{
	/****
	 * 重置支付密码第二层
	 */
	private TextView mNextPage ;
	private FrameLayout mBack ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_resetpay_pswd2);
		initView();
		initData();
		
	}

	private void initData() {
		mNextPage.setOnClickListener(this);
		mBack.setOnClickListener(this);
	}

	private void initView() {
		mNextPage = (TextView)findViewById(R.id.nextpage);
		mBack = (FrameLayout)findViewById(R.id.back_layout);
		
		TextView mText = (TextView)findViewById(R.id.title);
		mText.setText("重置支付密码");
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
			case R.id.nextpage:
				startActivity(new Intent(ResetPayPswdActivity2.this,
						ResetPayPswdActivity3.class));
				break;
				
			case R.id.back_layout:
				this.finish();
				break;
			
		}
	}
	
}
