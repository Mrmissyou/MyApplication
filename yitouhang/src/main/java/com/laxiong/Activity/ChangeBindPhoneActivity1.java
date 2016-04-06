package com.laxiong.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.laxiong.yitouhang.R;

public class ChangeBindPhoneActivity1 extends BaseActivity implements OnClickListener{
	/***
	 * 修改手机号的第一层
	 */
	private TextView nextPage , mCode;
	private FrameLayout mBack ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_changebindphone1);
		initView();
		initData();
	}
	private void initData() {
		nextPage.setOnClickListener(this);
		mBack.setOnClickListener(this);
		mCode.setOnClickListener(this);
	}
	private void initView() {
		
		nextPage = (TextView)findViewById(R.id.nextPage);
		mBack = (FrameLayout)findViewById(R.id.back_layout);
		mCode = (TextView)findViewById(R.id.code1);
		TextView mText = (TextView)findViewById(R.id.title);
		mText.setText("修改手机");
	}
	@Override
	public void onClick(View v) {
		switch(v.getId()){
			case R.id.nextPage:		/**下一步**/
				startActivity(new Intent(ChangeBindPhoneActivity1.this, 
						ChangeBindPhoneActivity2.class));
				break;
				
			case R.id.back_layout:
				this.finish();
				break;
				
			case R.id.code:
				Toast.makeText(this, "获取验证码", 2).show();
				break;
		}	
	}
	
}
