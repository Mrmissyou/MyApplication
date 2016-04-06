package com.laxiong.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.laxiong.yitouhang.R;

public class TrueNameActivity1 extends BaseActivity implements OnClickListener{
	/***
	 * 实名认证第一步
	 */
	private TextView mNextPage ;
	private FrameLayout mBack ;
	private ImageView toggleRead ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_true_name1);
		initView();
		initData();
	}
	
	private void initData() {
		mNextPage.setOnClickListener(this);
		mBack.setOnClickListener(this);
		toggleRead.setOnClickListener(this);
	}
	
	
	private void initView() {
		mNextPage = (TextView)findViewById(R.id.nextpager);
		mBack = (FrameLayout)findViewById(R.id.back_layout);
		toggleRead = (ImageView)findViewById(R.id.toggle);
		TextView mTitle = (TextView)findViewById(R.id.title);
		mTitle.setText("实名认证");
	}
	@Override
	public void onClick(View V) {
		switch(V.getId()){
			case R.id.nextpager:
				startActivity(new Intent(TrueNameActivity1.this,
						TrueNameActivity2.class));
				break;
			case R.id.back_layout:
				this.finish();
				break;
				
			case R.id.toggle:
				readProtocol();
				break ;
		}
	}
	/***
	 * 阅读协议
	 */
	boolean isRead = true ;
	private void readProtocol() {
		if(isRead){
			toggleRead.setImageResource(R.drawable.img_no_read);
			isRead = false ;
		}else{
			toggleRead.setImageResource(R.drawable.img_read);
			isRead = true ;
		}
	}
	
}
