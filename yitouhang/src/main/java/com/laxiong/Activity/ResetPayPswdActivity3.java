package com.laxiong.Activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.laxiong.yitouhang.R;

public class ResetPayPswdActivity3 extends BaseActivity implements OnClickListener{
	/****
	 * 重置支付密码第三层
	 */
	private TextView mComplete ;
	private FrameLayout mBack ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_resetpay_pswd3);
		initView();
		initData();
		
	}

	private void initData() {
		mComplete.setOnClickListener(this);
		mBack.setOnClickListener(this);
	}

	private void initView() {
		mComplete = (TextView)findViewById(R.id.complete);
		mBack = (FrameLayout)findViewById(R.id.back_layout);
		
		TextView mText = (TextView)findViewById(R.id.title);
		mText.setText("重置支付密码");
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
			case R.id.complete:
				Toast.makeText(this, "完成", 2).show();
				break;
				
			case R.id.back_layout:
				this.finish();
				break;
		}
	}
	
}
