package com.laxiong.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.laxiong.yitouhang.R;

public class ChangeCountActivity extends BaseActivity implements OnClickListener{
	/***
	 * 切换账号
	 */
	private TextView mRegistBtn,mFindPswd ,mComplete;
	private FrameLayout mBack;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_changecount_layout);
		initView();
		initData();
	}
	private void initData() {
		mFindPswd.setOnClickListener(this);
		mRegistBtn.setOnClickListener(this);
		mComplete.setOnClickListener(this);
		mBack.setOnClickListener(this);
	}
	private void initView() {
		mRegistBtn = (TextView)findViewById(R.id.registBtn);
		mFindPswd = (TextView)findViewById(R.id.findpswd);
		mComplete = (TextView)findViewById(R.id.completeBtn);
		mBack = (FrameLayout)findViewById(R.id.backlayout);
	}
	@Override
	public void onClick(View v) {
		switch(v.getId()){
			case R.id.registBtn:
				startActivity(new Intent(ChangeCountActivity.this,
						RegistActivity.class));
				break;
			case R.id.findpswd:
				startActivity(new Intent(ChangeCountActivity.this,
						FoundPswdActivity.class));
				break;
			case R.id.backlayout:
				ChangeCountActivity.this.finish();
				break;
			case R.id.completeBtn:
				Toast.makeText(ChangeCountActivity.this, "完成", 3).show();
				break;
		}
		
	}
}
