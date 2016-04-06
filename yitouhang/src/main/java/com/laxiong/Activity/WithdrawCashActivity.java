package com.laxiong.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.laxiong.yitouhang.R;

public class WithdrawCashActivity extends BaseActivity implements OnClickListener{
	/***
	 * 提现
	 */
	private FrameLayout mBack ;
	private TextView mNextBtn ;
	private ImageView mNotice ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_withdrawcash);
		initView();
		initData();
	}

	private void initData() {
		mBack.setOnClickListener(this);
		mNextBtn.setOnClickListener(this);
		mNotice.setOnClickListener(this);
	}

	private void initView() {
		mBack = (FrameLayout)findViewById(R.id.back_layout);
		mNextBtn = (TextView)findViewById(R.id.nextbtn);
		mNotice = (ImageView)findViewById(R.id.notice);
	}

	@Override
	public void onClick(View V) {
		switch(V.getId()){
			case R.id.back_layout:
				this.finish();				
				break;
			case R.id.nextbtn:
				payPswdMethod();
				break;
			case R.id.notice:
				startActivity(new Intent(WithdrawCashActivity.this,
						WithdrawCashNoticeActivity.class));
				break;
		}
	}
	
	/***
	 * 提现的PopupWindow
	 */
	private PopupWindow mPayPswd;
	private View mPayView ;
	private void payPswdMethod(){
		
		mPayView = LayoutInflater.from(WithdrawCashActivity.this).inflate(R.layout.paypassword_popupwindow, null);
		TextView mSureBtn = (TextView)mPayView.findViewById(R.id.surebtm);
		TextView mConcelBtn = (TextView)mPayView.findViewById(R.id.concelbtn);
		ImageView concelImgs = (ImageView)mPayView.findViewById(R.id.imgs_concel);
		
		
		mSureBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				//TODO 先支付
				Toast.makeText(WithdrawCashActivity.this, "提现申请已经成功提交", 2).show();
				
				startActivity(new Intent(WithdrawCashActivity.this,
						WithdrawCashDetailsActivity.class));
				
				if(mPayPswd!=null&&mPayPswd.isShowing()){
					mPayPswd.dismiss();
					mPayPswd = null ;
				}
			}
		});
		
		concelImgs.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if(mPayPswd!=null&&mPayPswd.isShowing()){
					mPayPswd.dismiss();
					mPayPswd = null ;
				}
			}
		});
		
		mConcelBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if(mPayPswd!=null&&mPayPswd.isShowing()){
					mPayPswd.dismiss();
					mPayPswd = null ;
				}
			}
		});
		
		mPayPswd = new PopupWindow(mPayView,LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT,true);
		mPayPswd.setTouchable(true);
		mPayPswd.setOutsideTouchable(true);
		 // 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
	    // 我觉得这里是API的一个bug
		mPayPswd.setBackgroundDrawable(getResources().getDrawable(R.drawable.kefu_bg)); //设置半透明
		mPayPswd.showAtLocation(mPayView, Gravity.BOTTOM, 0, 0);
		
	}
	
	
}
