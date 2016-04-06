package com.laxiong.Activity;

import org.apache.http.Header;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.laxiong.Common.Common;
import com.laxiong.Common.InterfaceInfo;
import com.laxiong.Utils.HttpUtil;
import com.laxiong.yitouhang.R;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class RegistActivity extends BaseActivity implements OnClickListener{
	/***
	 * 注册
	 */
	private TextView mLoginBtn , mRegistBtn ,mGetCode;
	private FrameLayout mBack ;
	private ImageView mToggleBtn ;
	private EditText mPhoneEd , mPswdEd , mCodeEd, mInviteCodeEd ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_regist_layout);
		initView();
		initData();
	}

	private void initData() {
		
		mLoginBtn.setOnClickListener(this);
		mRegistBtn.setOnClickListener(this);
		mBack.setOnClickListener(this);
		mToggleBtn.setOnClickListener(this);
		
		mPhoneEd.addTextChangedListener(watcher);
		mPswdEd.addTextChangedListener(watcher);
		mCodeEd.addTextChangedListener(watcher);
		
		mGetCode.setOnClickListener(this);
	}

	private void initView() {
		
		mLoginBtn = (TextView)findViewById(R.id.loginBtn);
		mRegistBtn = (TextView)findViewById(R.id.registBtn);
		mBack = (FrameLayout)findViewById(R.id.backlayout);
		mToggleBtn = (ImageView)findViewById(R.id.toggle_img);
		
		mCodeEd = (EditText)findViewById(R.id.regist_code);
		mInviteCodeEd = (EditText)findViewById(R.id.regist_invite_code); // 邀请码
		mPhoneEd = (EditText)findViewById(R.id.regist_phone);
		mPswdEd = (EditText)findViewById(R.id.regist_pswd);
		mGetCode = (TextView)findViewById(R.id.getcode);
		
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
			case R.id.loginBtn:
				startActivity(new Intent(RegistActivity.this,
						LoginActivity.class));
				break;
			case R.id.registBtn:
				String mobile0 = mPhoneEd.getText().toString().replaceAll(" ", "");
				String pswd = mPswdEd.getText().toString().replace(" ", "");
				String code = mCodeEd.getText().toString().replace(" ", "");
				if(Common.inputContentNotNull(mobile0)&&Common.inputContentNotNull(pswd)&&Common.inputContentNotNull(code)){
					if(Common.isMobileNO(mobile0)){
						if(Common.inputPswdCount(code)){
							doRegist();
						}else{
							Toast.makeText(RegistActivity.this, "请输入密码6到20位字母和数字", 3).show();
						}
					}else{
						Toast.makeText(RegistActivity.this, "请输入正确的手机号", 3).show();
					}
				}else{
					Toast.makeText(RegistActivity.this, "手机号码或密码,验证码不能为空", 3).show();
				}
				
				break;
			case R.id.backlayout:
				this.finish();
				break;
			case R.id.toggle_img:
				Common.isCheck(mToggleBtn);
				break;
			case R.id.getcode:   // 验证码
				
				String mobile = mPhoneEd.getText().toString().replaceAll(" ", "");
				if(Common.inputContentNotNull(mobile)){
					if(Common.isMobileNO(mobile)){
						getCode();
					}else{
						Toast.makeText(RegistActivity.this, "请输入正确的手机号", 3).show();
					}
				}else{
					Toast.makeText(RegistActivity.this, "手机号码不能为空", 3).show();
				}
				
				break;
		}
	}
	
	// 获取验证码  code 
	int count;
	boolean stopThread;
	private void getCode(){
		//TODO  绑定参数
		RequestParams params = new RequestParams();
		params.put("type", "reg");
		params.put("phone", mPhoneEd.getText().toString().replaceAll(" ", ""));
		
		HttpUtil.post(InterfaceInfo.CODE_URL, params, new JsonHttpResponseHandler(){
			@Override
			public void onSuccess(int statusCode, Header[] headers,
					JSONObject response) {
				super.onSuccess(statusCode, headers, response);
				
				if(response!=null){
					try {
						Log.i("URL", "code码：="+response.getInt("code"));
						if (response.getInt("code") == 0) {
							Toast.makeText(RegistActivity.this, "成功获取验证码", 3).show();
						} else {
							if (response.getString("msg") != null) {
								Toast.makeText(RegistActivity.this, response.getString("msg"), 3).show();
								stopThread = true;
							}
						}
					} catch (Exception e) {

					}
				}
			}
			@Override
			public void onFailure(int statusCode, Header[] headers,
					String responseString, Throwable throwable) {
				super.onFailure(statusCode, headers, responseString, throwable);
				
				stopThread = true;
				Toast.makeText(RegistActivity.this, "发送失败", Toast.LENGTH_SHORT).show();
			}
			
		}, true);
		
		// 倒计时
		count = 59;
		stopThread = false;
		// timer
		new Thread(new Runnable() {
			public void run() {
				while (!stopThread && count > 0) {
					runOnUiThread(new Runnable() {
						public void run() {
							mGetCode.setText(count + "秒");
						}
					});
					try {
						Thread.sleep(1000);
					} catch (Exception e) {
					}
					count--;
				}

				runOnUiThread(new Runnable() {
					public void run() {
						mGetCode.setText("获取验证码");
						mGetCode.setClickable(true);
					}
				});
			}
		}).start();
	}
	
	//注册  Regist
	private void doRegist(){
		
		RequestParams params = new RequestParams();
		params.put("phone", mPhoneEd.getText().toString().replaceAll(" ", ""));
		params.put("pwd", mPswdEd.getText().toString().replace(" ", ""));
		params.put("code", Integer.valueOf(mCodeEd.getText().toString().replace(" ", "")));
		String InviteCode = isInviteCode();
		params.put("invite_id", InviteCode);
		
		HttpUtil.post(InterfaceInfo.USER_URL, params, new JsonHttpResponseHandler(){

			@Override
			public void onSuccess(int statusCode, Header[] headers,
					JSONObject response) {
				if(response!=null){
					try{
						if (response.getInt("code") == 0) {
							Toast.makeText(RegistActivity.this, "注册成功", 3).show();
						} else {
							if (response.getString("msg") != null) {
								Toast.makeText(RegistActivity.this, response.getString("msg"), 3).show();
							}
						}
					}catch (Exception e){
						
					}
				}
			}

			@Override
			public void onFailure(int statusCode, Header[] headers,
					Throwable throwable, JSONObject errorResponse) {
				Toast.makeText(RegistActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
			}
			
		}, true);
		
	}
	// 邀请码的处理
	private String isInviteCode(){
		if(mInviteCodeEd!=null){
			String InviteCode = mInviteCodeEd.getText().toString().replace(" ", "");
			if(Common.inputContentNotNull(InviteCode)){
				return InviteCode;
			}
			return null ;
		}else{
			return null ;
		}
	}
	// EditText 是否输入了
	
	TextWatcher watcher = new TextWatcher() {
		@Override
		public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
			
		}
		
		@Override
		public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
				int arg3) {
			
		}
		@Override
		public void afterTextChanged(Editable arg0) {
			if(!TextUtils.isEmpty(mPhoneEd.getText().toString())&&!TextUtils.isEmpty(mPswdEd.getText().toString())
					&&!TextUtils.isEmpty(mCodeEd.getText().toString())){
				if(Common.inputPswdCount(mCodeEd.getText().toString().trim())){
					mRegistBtn.setEnabled(true);
					mRegistBtn.setBackgroundResource(R.drawable.button_change_bg_border);
				
				}else{
					mRegistBtn.setEnabled(true);
					mRegistBtn.setBackgroundResource(R.drawable.button_grey_corner_border);
				}
			}else{
				mRegistBtn.setEnabled(false);
				mRegistBtn.setBackgroundResource(R.drawable.button_grey_corner_border);
			}
		}
	};	
	
}
