package com.laxiong.Activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.laxiong.yitouhang.R;

public class FoundPswdActivity extends BaseActivity implements OnClickListener{
	/****
	 * 找回密码
	 */
	private TextView mCompletBtn ;
	private FrameLayout mBack ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_foundpswd_layout);
		initView();
		initData();
	}

	private void initData() {
		mCompletBtn.setOnClickListener(this);
		mBack.setOnClickListener(this);
	}

	private void initView() {
		mCompletBtn = (TextView)findViewById(R.id.complete_Btn);
		mBack = (FrameLayout)findViewById(R.id.back);
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
			case R.id.complete_Btn:
				Toast.makeText(this, "完成", 3).show();
				break;
			case R.id.back:
				this.finish();
				break;
		}
	}
}
