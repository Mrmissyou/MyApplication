package com.laxiong.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.laxiong.yitouhang.R;

public class ChangePayPswdActivity1 extends BaseActivity implements OnClickListener{
	/****
	 * 修改支付密码第一层
	 */
	private TextView mNextPage ,mCode;
	private FrameLayout mBack ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_changepay_pswd1);
		initView();
		initData();
	}

	private void initData() {
		mNextPage.setOnClickListener(this);
		mCode.setOnClickListener(this);
		mBack.setOnClickListener(this);
	}

	private void initView() {
		mNextPage = (TextView)findViewById(R.id.nextpage);
		mCode = (TextView)findViewById(R.id.code);
		mBack = (FrameLayout)findViewById(R.id.back_layout);
		
		TextView mText =(TextView)findViewById(R.id.title);
		mText.setText("修改支付密码");
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
			case R.id.nextpage:
				startActivity(new Intent(ChangePayPswdActivity1.this,
						ChangePayPswdActivity2.class));
				break;
			case R.id.code:
				Toast.makeText(this, "获取验证码", 2).show();
				break;
				
			case R.id.back_layout:
				this.finish();
				break;
		}
	}
	
	
}
