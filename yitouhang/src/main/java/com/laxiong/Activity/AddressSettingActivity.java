package com.laxiong.Activity;

import com.laxiong.yitouhang.R;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

public class AddressSettingActivity extends BaseActivity implements OnClickListener{
	/***
	 * 地址设置
	 */
	private FrameLayout mBack ;
	private TextView mSaveBtn ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_address_setting);
		initView();
		initData();
	}

	private void initView() {
		mBack = (FrameLayout)findViewById(R.id.back_layout);
		mSaveBtn = (TextView)findViewById(R.id.savebtn);
	}

	private void initData() {
		mBack.setOnClickListener(this);
		mSaveBtn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
			case R.id.back_layout:
				this.finish();
				break;
			case R.id.savebtn:
				Toast.makeText(this, "保存成功", 2).show();
				break;
		}
	}
	
}
