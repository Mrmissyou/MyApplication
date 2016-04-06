package com.laxiong.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.laxiong.Common.Common;
import com.laxiong.fund.widget.GestureContentView;
import com.laxiong.fund.widget.GestureDrawline.GestureCallBack;
import com.laxiong.fund.widget.LockIndicator;
import com.laxiong.yitouhang.R;

public class ModifyGestureActivity extends BaseActivity implements OnClickListener{
	/***
	 * 修改手势密码
	 */
	
	public static final String PARAM_PHONE_NUMBER = "PARAM_PHONE_NUMBER"; /** 手机号码*/
	public static final String PARAM_INTENT_CODE = "PARAM_INTENT_CODE";/** 意图 */
	public static final String PARAM_IS_FIRST_ADVICE = "PARAM_IS_FIRST_ADVICE";/** 首次提示绘制手势密码，可以选择跳过 */

	private TextView mTextTitle;
	private TextView mTextCancel;
	private LockIndicator mLockIndicator;
	private TextView mTextTip;
	private FrameLayout mGestureContainer;
	private GestureContentView mGestureContentView;
	private TextView mTextReset;
	private String mParamSetUpcode = null;
	private String mParamPhoneNumber;
	private boolean mIsFirstInput = true;
	private String mFirstPassword = null;
	private String mConfirmPassword = null;
	private int mParamIntentCode;
	 @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_modify_gesture);
		initView();
		initData();
		setUpListeners();
	}
	 
	private void initView() {
		mTextTitle = (TextView) findViewById(R.id.text_title);
		mTextReset = (TextView) findViewById(R.id.text_reset);
		mTextReset.setClickable(false);
		mLockIndicator = (LockIndicator) findViewById(R.id.lock_indicator);
		mTextTip = (TextView) findViewById(R.id.text_tip);
		mGestureContainer = (FrameLayout) findViewById(R.id.gesture_container);
		
	}
	
	private void initData() {
		// 初始化一个显示各个点的viewGroup
	   mGestureContentView = new GestureContentView(this, false, "", new GestureCallBack(){
			@Override
			public void onGestureCodeInput(String inputCode) {
				if (!isInputPassValidate(inputCode)) {
					mTextTip.setText(Html.fromHtml("<font color='#c70c1e'>最少链接4个点, 请重新输入</font>"));
					mGestureContentView.clearDrawlineState(0L);
					return;
				}
				if (mIsFirstInput) {
					mFirstPassword = inputCode;
					updateCodeList(inputCode);
					mGestureContentView.clearDrawlineState(0L);
					mTextReset.setClickable(true);
					mTextReset.setText("重新设置手势密码");
				} else {
					if (inputCode.equals(mFirstPassword)) {
							Toast.makeText(ModifyGestureActivity.this, "设置成功", Toast.LENGTH_SHORT).show();
						//TODO 保手势存密码
						getSharedPreferences(Common.sharedPrefName, Context.MODE_PRIVATE).edit()
							.putString("patternstring", mFirstPassword).commit();
							
						mGestureContentView.clearDrawlineState(0L);
						
						setResult(1001);
						
						ModifyGestureActivity.this.finish();
						
					} else {
						mTextTip.setText(Html.fromHtml("<font color='#c70c1e'>与上一次绘制不一致，请重新绘制</font>"));
						// 左右移动动画
						Animation shakeAnimation = AnimationUtils.loadAnimation(ModifyGestureActivity.this, R.anim.shake);
						mTextTip.startAnimation(shakeAnimation);
						// 保持绘制的线，1.5秒后清除
						mGestureContentView.clearDrawlineState(1300L);
					}
				}
				mIsFirstInput = false;
			}
			@Override
			public void checkedSuccess() {
				
			}
			@Override
			public void checkedFail() {
				
			}
	   }); 
		// 设置手势解锁显示到哪个布局里面
		mGestureContentView.setParentView(mGestureContainer);
		updateCodeList("");
	}
	
	private void setUpListeners() {
		
		mTextReset.setOnClickListener(this);
	}
	
	private void updateCodeList(String inputCode) {
		// 更新选择的图案
		mLockIndicator.setPath(inputCode);
	}
	
	private boolean isInputPassValidate(String inputPassword) {
		if (TextUtils.isEmpty(inputPassword) || inputPassword.length() < 4) {
			return false;
		}
		return true;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.text_reset:
				mIsFirstInput = true;
				updateCodeList("");
				mTextTip.setText("绘制解锁图案");
				break;
		default:
			break;
		}
		
		
	}

}
