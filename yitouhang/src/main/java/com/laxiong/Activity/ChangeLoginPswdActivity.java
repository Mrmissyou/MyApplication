package com.laxiong.Activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.laxiong.yitouhang.R;

public class ChangeLoginPswdActivity extends BaseActivity implements OnClickListener{
	/***
	 * 修改登录密码
	 */
	private FrameLayout mBack ;
	private TextView mComplete ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_change_login_pswd);
		initView();
		initData();
	}

	private void initData() {
		mBack.setOnClickListener(this);
		mComplete.setOnClickListener(this);
	}

	private void initView() {
		mBack = (FrameLayout)findViewById(R.id.back_layout);
		mComplete = (TextView)findViewById(R.id.complete);
		
		TextView mText = (TextView)findViewById(R.id.title);
		mText.setText("修改登录密码");
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
			case R.id.back_layout:
				this.finish();
				break;
			case R.id.complete:
				Toast.makeText(this, "完成", 2).show();
				break;
		}
	}
	
}
