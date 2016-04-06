package com.laxiong.Activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.laxiong.yitouhang.R;

public class ChangeBindPhoneActivity2 extends BaseActivity implements OnClickListener{
	/***
	 * 修改手机号的第二层
	 */
	private TextView finishBtn ,mCode;
	private FrameLayout mBack ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_changebindphone2);
		initView();
		initData();
	}
	private void initData() {
		finishBtn.setOnClickListener(this);
		mCode.setOnClickListener(this);
		mBack.setOnClickListener(this);
	}
	private void initView() {
		
		finishBtn = (TextView)findViewById(R.id.finishBtn);
		mCode = (TextView)findViewById(R.id.code);
		mBack = (FrameLayout)findViewById(R.id.back_layout);
		
		TextView mText = (TextView)findViewById(R.id.title);
		mText.setText("修改手机");
	}
	@Override
	public void onClick(View v) {
		switch(v.getId()){
			case R.id.finishBtn:		/** 完成按钮**/
				Toast.makeText(ChangeBindPhoneActivity2.this, "成功完成修改手机号码", 3).show();
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
