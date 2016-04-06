package com.laxiong.Activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.laxiong.yitouhang.R;

public class NameSettingActivity extends BaseActivity implements OnClickListener{
	/***
	 * 昵称设置
	 */
	private FrameLayout mBack ;
	private TextView mSave ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_name_setting);
		initView();
		initData();
	}

	private void initView() {
		mBack = (FrameLayout)findViewById(R.id.back_layout);
		mSave = (TextView)findViewById(R.id.save);
	}

	private void initData() {
		mBack.setOnClickListener(this);
		mSave.setOnClickListener(this);
	}

	@Override
	public void onClick(View V) {
		switch(V.getId()){
			case R.id.back_layout:
				this.finish();
				break;
				
			case R.id.save:
				Toast.makeText(this, "保存成功", 2).show();
				break;
		}
	}
	
	
}
