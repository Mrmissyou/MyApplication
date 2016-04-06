package com.laxiong.Activity;

import com.laxiong.yitouhang.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.TextView;

public class TrueNameActivity2 extends BaseActivity implements OnClickListener{
	/***
	 * 实名认证第二步
	 */
	private TextView mNextPage ;
	private FrameLayout mBack ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_true_name2);
		initView();
		initData();
	}
	private void initData() {
		mNextPage.setOnClickListener(this);
		mBack.setOnClickListener(this);
		
	}
	private void initView() {
		mNextPage = (TextView)findViewById(R.id.nextpager);
		mBack = (FrameLayout)findViewById(R.id.back_layout);
		TextView mTitle = (TextView)findViewById(R.id.title);
		mTitle.setText("实名认证");
		
	}
	@Override
	public void onClick(View V) {
		switch(V.getId()){
			case R.id.nextpager:
				startActivity(new Intent(TrueNameActivity2.this,
						TrueNameActivity3.class));
				break;
			case R.id.back_layout:
				this.finish();
				break;
		}
	}
	
}
