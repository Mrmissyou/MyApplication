package com.laxiong.Activity;

import android.app.ActionBar.LayoutParams;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.laxiong.yitouhang.R;

public class CountSettingActivity extends BaseActivity implements OnClickListener{
	/****
	 * 账户设置
	 */
	private RelativeLayout mCount ,mPswdControl ,mConnectKefu , mMessage;
	private FrameLayout mBack ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_count_setting_layout);
		initView();
		initData();
	}

	private void initData() {
		mCount.setOnClickListener(this);
		mPswdControl.setOnClickListener(this);
		mConnectKefu.setOnClickListener(this);
		mBack.setOnClickListener(this);
		mMessage.setOnClickListener(this);
	}

	private void initView() {
		mCount = (RelativeLayout)findViewById(R.id.count_setting);
		mPswdControl = (RelativeLayout)findViewById(R.id.pswdControl);
		mConnectKefu = (RelativeLayout)findViewById(R.id.connectKefu);
		mBack = (FrameLayout)findViewById(R.id.back_layout);
		mMessage = (RelativeLayout)findViewById(R.id.message);
		
		TextView mText = (TextView)findViewById(R.id.title);
		mText.setText("账户");
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
			case R.id.count_setting:
				startActivity(new Intent(this,PersonalSettingActivity.class));
				break;
			case R.id.pswdControl:			/**密码管理 */
				startActivity(new Intent(this,
						PswdConturalActivity.class));
				break;
			case R.id.connectKefu:			/**联系客服 */
				showConnectKefuSelectType();
				break;
				
			case R.id.back_layout:
				this.finish();
				break;
				
			case R.id.message:
				startActivity(new Intent(this,
						MessageActivity.class));
				break;
		}
		
	}
	
	 private PopupWindow mPopWindows ;  
	 private View KefuSelectView ;
	 private void showConnectKefuSelectType(){
		 
		 KefuSelectView = LayoutInflater.from(this).inflate(R.layout.kefu_popwindow, null);
		 
		 TextView onLineBtn = (TextView)KefuSelectView.findViewById(R.id.onlinekefu);
		 RelativeLayout kefuTelBtn = (RelativeLayout)KefuSelectView.findViewById(R.id.kefutel);
		 TextView contcelBtn = (TextView)KefuSelectView.findViewById(R.id.concel_kefu);
		 
		 onLineBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Toast.makeText(CountSettingActivity.this, "在线客服", 3).show();
			}
		});
		 
		 kefuTelBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Toast.makeText(CountSettingActivity.this, "客服电话：400-0888-888", 3).show();
			}
		});
		 
		 contcelBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				
				if(mPopWindows!=null&&mPopWindows.isShowing()){
					mPopWindows.dismiss();
					mPopWindows = null ;
				}
			}
		});
		 
		 mPopWindows = new PopupWindow(KefuSelectView,LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT,true);
		 mPopWindows.setTouchable(true);
		 mPopWindows.setOutsideTouchable(true);
		 // 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
	    // 我觉得这里是API的一个bug
		 mPopWindows.setBackgroundDrawable(getResources().getDrawable(R.drawable.kefu_bg)); //设置半透明
		 mPopWindows.showAtLocation(KefuSelectView, Gravity.BOTTOM, 0, 0);
		 
	 }
	 
	 
}
