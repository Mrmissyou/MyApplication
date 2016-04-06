package com.laxiong.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.laxiong.yitouhang.R;

public class LoginActivity extends BaseActivity implements OnClickListener{
	/***
	 * 登录
	 */
	private TextView mRegistBtn , mLoginBtn ,mFindPswd,mChangeCount;
	private FrameLayout mBack ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login_layout);
		initView();
		initData();
	}
	private void initData() {
		mRegistBtn.setOnClickListener(this);
		mLoginBtn.setOnClickListener(this);
		mFindPswd.setOnClickListener(this);
		mChangeCount.setOnClickListener(this);
		mBack.setOnClickListener(this);
	}
	private void initView() {
		mRegistBtn = (TextView)findViewById(R.id.registBtn);
		mLoginBtn = (TextView)findViewById(R.id.loginBtn);
		mChangeCount = (TextView)findViewById(R.id.changecount);
		mFindPswd = (TextView)findViewById(R.id.findpswd);
		mBack = (FrameLayout)findViewById(R.id.backlayout);
	}
	@Override
	public void onClick(View v) {
		switch(v.getId()){
			case R.id.registBtn:
				startActivity(new Intent(LoginActivity.this,
						RegistActivity.class));
				break;
			case R.id.loginBtn:
				Toast.makeText(this, "登录", 2).show();
				break;
			case R.id.changecount:
				startActivity(new Intent(LoginActivity.this,
						ChangeCountActivity.class));			
				break;
			case R.id.findpswd:
				startActivity(new Intent(LoginActivity.this,
						FoundPswdActivity.class));
				break;
			case R.id.backlayout:
				this.finish();
				break;
		}
	}
}
