package com.laxiong.Activity;

import android.app.ActionBar.LayoutParams;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.laxiong.View.PasswordInputView;
import com.laxiong.yitouhang.R;

public class PswdConturalActivity extends BaseActivity implements OnClickListener{
	/***
	 * 密码管理
	 */
	private RelativeLayout mChangeLoginPswd ,mChangePayPswd ,mResetPayPswd ,mChangeGesturePswd;
	private FrameLayout mBack ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pswd_contural);
		initView();
		initData();
	}

	private void initView() {
		mChangeLoginPswd = (RelativeLayout)findViewById(R.id.changeloginpswd);
		mChangePayPswd = (RelativeLayout)findViewById(R.id.changepaypswd);
		mResetPayPswd = (RelativeLayout)findViewById(R.id.resetpaypswd);
		mChangeGesturePswd = (RelativeLayout)findViewById(R.id.changegesturepswd);
		mBack = (FrameLayout)findViewById(R.id.back_layout);
		
		TextView mText = (TextView)findViewById(R.id.title);
		mText.setText("密码管理");
	}

	private void initData() {
		mChangeLoginPswd.setOnClickListener(this);
		mChangePayPswd.setOnClickListener(this);
		mResetPayPswd.setOnClickListener(this);
		mChangeGesturePswd.setOnClickListener(this);
		mBack.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View V) {
		switch(V.getId()){
			case R.id.changeloginpswd:    /**登录密码**/
				startActivity(new Intent(PswdConturalActivity.this,
						ChangeLoginPswdActivity.class));
				break;
			case R.id.changepaypswd:   /**修改支付密码**/
				showChangePayPswd();
				break;
			case R.id.resetpaypswd:  /**重置支付密码**/
				startActivity(new Intent(PswdConturalActivity.this,
						ResetPayPswdActivity1.class));
				break;
			case R.id.changegesturepswd: /**修改手势密码**/
				
				Intent intent = new Intent(PswdConturalActivity.this,
						ModifyGestureActivity.class) ;
				startActivityForResult(intent, 1001);
				
				break;
				
			case R.id.back_layout:
				this.finish();
				break;
		}
	}
	private PopupWindow mPopupWinds ;
	private View mChangePswdView ;
	private void showChangePayPswd(){
		
		mChangePswdView = LayoutInflater.from(this).inflate(R.layout.changepaypswd_popwindow, null);
		// 输入的支付密码的
		final PasswordInputView mPayPswd = (PasswordInputView)mChangePswdView.findViewById(R.id.paypswd);
		ImageView ImageConcel = (ImageView)mChangePswdView.findViewById(R.id.imgs_concel);
		
		mPayPswd.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
				String str = mPayPswd.getText().toString();
				try{
					if(str!= null)
					Integer.parseInt(str);
				}catch(Exception e){
					Toast.makeText(PswdConturalActivity.this, "密码输入不正确", 3).show();
				}
				
			}
			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				
			}
			@Override
			public void afterTextChanged(Editable arg0) {
				String str = mPayPswd.getText().toString();
				if(str!=null&&str.length()==6){
					Toast.makeText(PswdConturalActivity.this, str, 3).show();
					//TODO  输完密码后跳转的页面,携带参数
					startActivity(new Intent(PswdConturalActivity.this,
							ChangePayPswdActivity1.class));
					
					if(mPopupWinds!=null&&mPopupWinds.isShowing()){
						mPopupWinds.dismiss();
						mPopupWinds = null ;
					}
				}
			}
		});
		
		ImageConcel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if(mPopupWinds!=null&&mPopupWinds.isShowing()){
					mPopupWinds.dismiss();
					mPopupWinds = null ;
				}
			}
		});
		
		mPopupWinds = new PopupWindow(mChangePswdView, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, true);
		mPopupWinds.setTouchable(true);
		mPopupWinds.setOutsideTouchable(true);
		// 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
	    // 我觉得这里是API的一个bug
		mPopupWinds.setBackgroundDrawable(getResources().getDrawable(R.drawable.kefu_bg)); //设置半透明
		mPopupWinds.showAtLocation(mChangePswdView, Gravity.BOTTOM, 0, 0);
		
	}
	
	// 成功修改手势密码 后
	private PopupWindow mSuccessModifyWinds ;
	private View mSuccessView ;
	private void successModifyGesture(){
		
		mSuccessView = LayoutInflater.from(PswdConturalActivity.this).inflate(R.layout.success_modifygestrue_popwindow, null);
		
		TextView mAuthentication = (TextView)mSuccessView.findViewById(R.id.setid);
		TextView mIknow = (TextView)mSuccessView.findViewById(R.id.iknow);
		
		mAuthentication.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Toast.makeText(PswdConturalActivity.this, "去认证", 3).show();
			}
		});
		
		mIknow.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if(mSuccessModifyWinds!=null&&mSuccessModifyWinds.isShowing()){
					mSuccessModifyWinds.dismiss();
					mSuccessModifyWinds = null ;
				}
			}
		});
		
		mSuccessModifyWinds = new PopupWindow(mSuccessView, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, true);
		mSuccessModifyWinds.setTouchable(true);
		mSuccessModifyWinds.setOutsideTouchable(true);
		// 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
	    // 我觉得这里是API的一个bug
		mSuccessModifyWinds.setBackgroundDrawable(getResources().getDrawable(R.drawable.kefu_bg)); //设置半透明
		mSuccessModifyWinds.showAtLocation(mSuccessView, Gravity.BOTTOM, 0, 0);
		
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		 //requestCode标示请求的标示   resultCode表示有数据
		if(resultCode == 1001){
			successModifyGesture();
		}
	}
	
	
	
	
}
