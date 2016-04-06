package com.laxiong.Activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.laxiong.yitouhang.R;

public class EarningsSayActivity extends BaseActivity implements OnClickListener{
	/***
	 * 收益说明
	 */
	private FrameLayout mBack ;
	private TextView mTitle ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_earnings);
		mBack = (FrameLayout)findViewById(R.id.back_layout);
		mTitle = (TextView)findViewById(R.id.title);
		mBack.setOnClickListener(this);
		mTitle.setText("收益说明");
	}

	@Override
	public void onClick(View v) {
		this.finish();
	}

}
