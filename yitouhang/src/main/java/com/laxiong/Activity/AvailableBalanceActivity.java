package com.laxiong.Activity;

import android.annotation.SuppressLint;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.laxiong.Fragment.AccumulatedEarningsFragment;
import com.laxiong.Fragment.AvailableBalanaceFragment;
import com.laxiong.yitouhang.R;

public class AvailableBalanceActivity extends BaseActivity implements OnClickListener{
	/***
	 * 可用余额  累计收益
	 */
	private AvailableBalanaceFragment mAvailableFragment ;  // 可用余额
	private AccumulatedEarningsFragment mAccumulatedFragment ;// 累计收益
	private TextView mYesterdayBalanceTv , mAvailablebanlanceTv ;  // 昨日收益 和 累计收益的  按钮
	
	private FragmentManager mFragmentManager = null ;
	private FrameLayout mBack ;
	private TextView mTakeCare ;   // 说明
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_availablebalance);
		initView();
		initData();
		initFristFrag();
	}
	@SuppressLint("NewApi") 
	private void initData() {
		mFragmentManager = this.getFragmentManager();
		
		mYesterdayBalanceTv.setOnClickListener(this);
		mAvailablebanlanceTv.setOnClickListener(this);
		
		mBack.setOnClickListener(this);
		mTakeCare.setOnClickListener(this);
	}
	
	private void initView() {
		mYesterdayBalanceTv = (TextView)findViewById(R.id.yesterdaybalance);
		mAvailablebanlanceTv = (TextView)findViewById(R.id.availablebanlance);
		
		mBack = (FrameLayout)findViewById(R.id.back_layout);
		mTakeCare = (TextView)findViewById(R.id.talkcarefor);
		
	}
	@SuppressLint("NewApi") @Override
	public void onClick(View V) {
		FragmentTransaction  mTransaction = mFragmentManager.beginTransaction();
		hideFragment(mTransaction);
		switch(V.getId()){
			case R.id.yesterdaybalance:  //　可用余额
				foundChange(1);
				if(mAvailableFragment==null){
					mAvailableFragment = new AvailableBalanaceFragment();
					mTransaction.add(R.id.setContent, mAvailableFragment);
				}else{
					mTransaction.show( mAvailableFragment);
				}
				mTransaction.commit();
				break;
			case R.id.availablebanlance:   // 累计收益
				foundChange(2);
				if(mAccumulatedFragment==null){
					mAccumulatedFragment = new AccumulatedEarningsFragment();
					mTransaction.add(R.id.setContent, mAccumulatedFragment);
				}else{
					mTransaction.show( mAccumulatedFragment);
				}
				mTransaction.commit();
				break;
			case R.id.back_layout:
				this.finish();
				break;
			case R.id.talkcarefor:
				startActivity(new Intent(AvailableBalanceActivity.this,
						EarningsSayActivity.class));
				break;
		}
		
	}
	
	@SuppressLint("NewApi") 
	private void hideFragment(FragmentTransaction  mTransaction){
		if(mAvailableFragment!=null){
			mTransaction.hide(mAvailableFragment);
		}
		if(mAccumulatedFragment!=null){
			mTransaction.hide(mAccumulatedFragment);
		}
	}
	
	private void foundChange(int index){
		switch(index){
			case 1:			 // 昨日收益   按钮
				mYesterdayBalanceTv.setTextSize(16.0f);
				mAvailablebanlanceTv.setTextSize(12.0f);
				break ;
			case 2:			//累计收益  按钮
				mAvailablebanlanceTv.setTextSize(16.0f);
				mYesterdayBalanceTv.setTextSize(12.0f);
				break;
		}
	}
	
	@SuppressLint("NewApi") 
	private void initFristFrag(){
		FragmentTransaction  mTransaction = mFragmentManager.beginTransaction();
		mAvailableFragment = new AvailableBalanaceFragment();
		mTransaction.add(R.id.setContent, mAvailableFragment);
		mTransaction.commit();
		foundChange(1);
	}
	
	
}
