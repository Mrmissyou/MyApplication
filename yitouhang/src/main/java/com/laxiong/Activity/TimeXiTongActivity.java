package com.laxiong.Activity;

import java.text.NumberFormat;

import android.app.ActionBar.LayoutParams;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.laxiong.yitouhang.R;

public class TimeXiTongActivity extends BaseActivity implements OnClickListener{
	 /***
	  * 时息通
	  */
	private FrameLayout mBack ;
	private TextView mJiGetMoney , mShareBtn ,mScrollIn , mScrollOut;
	private EditText mJiMoney , mJiDay ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_set_shixitong_layout);
		initView();
		initData();
	}

	private void initData() {
		
		mBack.setOnClickListener(this);
		mShareBtn.setOnClickListener(this);
		mScrollIn.setOnClickListener(this);
		mScrollOut.setOnClickListener(this);
		
		mJiDay.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
				String str = mJiDay.getText().toString().trim();
				if(str==null||str.equals("")||str.length() == 0){
					Toast.makeText(TimeXiTongActivity.this, "输入整数", 3).show();
				}
				
			}
			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				
			}
			@Override
			public void afterTextChanged(Editable arg0) {
				String day = mJiDay.getText().toString().trim();
				
				//TODO  TextView 的计算结果显示
				if(mJiMoney!=null&&mJiMoney.getText().toString().trim().length()!=0&&!mJiMoney.getText().toString().trim().equals("")){
					if(day!=null&&day.length()!=0&&!day.equals("")){
						
						int tD = Integer.parseInt(day);
						int tM = Integer.parseInt(mJiMoney.getText().toString().trim());
						double lu = 0.072 ;
						
						// 保留小数点三位
						NumberFormat mFormat = NumberFormat.getNumberInstance(); 
						mFormat.setMaximumFractionDigits(3);
						String comfixNum = mFormat.format(backComfix(tM,tD,lu));
						
						mJiGetMoney.setText(comfixNum);
						
					}
				}
			}
		});
		
		mJiMoney.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
				String str = mJiMoney.getText().toString().trim();
				if(str==null||str.equals("")||str.length() == 0){
					Toast.makeText(TimeXiTongActivity.this, "输入整数", 3).show();
				}
			}
			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				
			}
			@Override
			public void afterTextChanged(Editable arg0) {
				String money = mJiMoney.getText().toString().trim();
				
				//TODO  TextView 的计算结果显示
				if(mJiDay!=null&&mJiDay.getText().toString().trim().length()!=0&&!mJiDay.getText().toString().trim().equals("")){
					if(money!=null&&money.length()!=0&&!money.equals("")){
						
						int tM = Integer.parseInt(money);
						int tD = Integer.parseInt(mJiDay.getText().toString().trim());
						double lu = 0.72 ;
						
						// 保留小数点三位
						NumberFormat mFormat = NumberFormat.getNumberInstance(); 
						mFormat.setMaximumFractionDigits(3);
						String comfixNum = mFormat.format(backComfix(tM,tD,lu));
						
						mJiGetMoney.setText(comfixNum);
					}
				}
			}
		});
		
	}

	private void initView() {
		mJiDay = (EditText)findViewById(R.id.payday);
		mJiMoney = (EditText)findViewById(R.id.paymoney);
		mJiGetMoney = (TextView)findViewById(R.id.jigetmoney);
		mBack = (FrameLayout)findViewById(R.id.backlayout);
		mShareBtn = (TextView)findViewById(R.id.share);
		mScrollIn = (TextView)findViewById(R.id.scroll_in);
		mScrollOut = (TextView)findViewById(R.id.scroll_out);
	}

	
	@Override
	public void onClick(View v) {
		switch(v.getId()){
			case R.id.scroll_in:  // 转入
				startActivity(new Intent(TimeXiTongActivity.this,
						TransferInActivity.class));
				break;
			case R.id.backlayout:
				this.finish();
				break;
			case R.id.share:
				Toast.makeText(this, "分享", 2).show();
				break;
				
			case R.id.scroll_out:  // 转出
				startActivity(new Intent(TimeXiTongActivity.this,
						TransferOutActivity.class));
				break;
		}
	}
	
	
	/**显示计算器的PopupWindow*/
	private PopupWindow mPopWindJi ;
	private View mJiSuanView ;
	private void showJiSuanQi(){
		mJiSuanView = LayoutInflater.from(this).inflate(R.layout.jisuanqi_popwindow, null);
		final EditText mMoney = (EditText)mJiSuanView.findViewById(R.id.toumoney);
		final EditText mDays = (EditText)mJiSuanView.findViewById(R.id.touday);
		final TextView mComfix = (TextView)mJiSuanView.findViewById(R.id.comfit);
		ImageView imgConcel = (ImageView)mJiSuanView.findViewById(R.id.imgs_concel);
		
		// 投资金额
		mMoney.addTextChangedListener(new TextWatcher(){   
			@Override
			public void afterTextChanged(Editable arg0) {
				//TODO 假设是20000
				String str = mMoney.getText().toString().trim();
				
					//TODO  TextView 的计算结果显示
					if(mDays!=null&&!mDays.equals("")&&mDays.length()!=0){
						int tM = Integer.parseInt(str);
						int tD = Integer.parseInt(mDays.getText().toString().trim());
						double lu = 0.72 ;
						
						// 保留小数点三位
						NumberFormat mFormat = NumberFormat.getNumberInstance(); 
						mFormat.setMaximumFractionDigits(3);
						String comfixNum = mFormat.format(backComfix(tM,tD,lu));
						
						mComfix.setText(comfixNum);
					}
			}
			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
			}
			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				String str = mMoney.getText().toString().trim();
				try{
					if(str!= null)
					Integer.parseInt(str);
				}catch(Exception e){
					Toast.makeText(TimeXiTongActivity.this, "输入整数", 3).show();
				}
			}
		});
		// 投资天数
		mDays.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
				String str = mDays.getText().toString().trim();
				try{
					if(str!= null)
					Integer.parseInt(str);
				}catch(Exception e){
					Toast.makeText(TimeXiTongActivity.this, "输入整数", 3).show();
				}
			}
			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
			}
			@Override
			public void afterTextChanged(Editable arg0) {
				String str = mDays.getText().toString().trim();
				
					//TODO  TextView 的计算结果显示
					if(mMoney!=null&&!mMoney.equals("")&&mMoney.length()!=0){
						int tD = Integer.parseInt(str);
						int tM = Integer.parseInt(mMoney.getText().toString().trim());
						double lu = 0.072 ;
						
						// 保留小数点三位
						NumberFormat mFormat = NumberFormat.getNumberInstance(); 
						mFormat.setMaximumFractionDigits(3);
						String comfixNum = mFormat.format(backComfix(tM,tD,lu));
						
						mComfix.setText(comfixNum);
					}
			}
		});
		// 取消按钮
		imgConcel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if(mPopWindJi!=null&&mPopWindJi.isShowing()){
					mPopWindJi.dismiss();
					mPopWindJi = null ;
				}
			}
		});
		
		mPopWindJi = new PopupWindow(mJiSuanView,LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT,true);
		mPopWindJi.setTouchable(true);
		mPopWindJi.setOutsideTouchable(true);
		// 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
	    // 我觉得这里是API的一个bug
		mPopWindJi.setBackgroundDrawable(getResources().getDrawable(R.drawable.kefu_bg)); //设置半透明
		mPopWindJi.showAtLocation(mJiSuanView, Gravity.BOTTOM, 0, 0);
		
	}
	
	/**
	 * 计算器的算法
	 * money:本金
	 * day：日期
	 * lu：利率  7.2%
	 */
	private double backComfix(float money,float day, double lu){
		double backMoney = money*lu*(day/365)+money;
		
		return backMoney ;
	}
	
	// 转入按钮  输入密码
	private PopupWindow mInputPswdWindow ;
	private View mInputView ;
	private void inputOverToPswd(){
		
		mInputView = LayoutInflater.from(TimeXiTongActivity.this).inflate(R.layout.overtopswd_popwindow, null);
		
		ImageView comcelImags = (ImageView)mInputView.findViewById(R.id.imgs_concel);
		TextView  mForgetPswd = (TextView)mInputView.findViewById(R.id.forget_pswd);
		
		comcelImags.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if(mInputPswdWindow!=null&&mInputPswdWindow.isShowing()){
					mInputPswdWindow.dismiss();
					mInputPswdWindow = null ;
				}
			}
		});
		
		mForgetPswd.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Toast.makeText(TimeXiTongActivity.this, "忘记密码的操作", 2).show();
			}
		});
		
		mInputPswdWindow = new PopupWindow(mInputView,  LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT,true);
		mInputPswdWindow.setTouchable(true);
		mInputPswdWindow.setOutsideTouchable(true);
		 // 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
	    // 我觉得这里是API的一个bug
		mInputPswdWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.kefu_bg)); //设置半透明
		mInputPswdWindow.showAtLocation(mInputView, Gravity.BOTTOM, 0, 0);
	}
	
	// pay Menthod
	
	private PopupWindow mPayMathodWindow ;
	private View PayView ;
	private ImageView newCard_img,lateMoney_img,constranceBank_img ;
	private void payMenthodType(){
		
		PayView = LayoutInflater.from(TimeXiTongActivity.this).inflate(R.layout.pay_mathod_popwindow, null);
		
		RelativeLayout newCard = (RelativeLayout)PayView.findViewById(R.id.addnewcard);
		RelativeLayout lateMoney = (RelativeLayout)PayView.findViewById(R.id.latemoney);
		RelativeLayout constranceBank = (RelativeLayout)PayView.findViewById(R.id.concreatebank);
		TextView mConcel = (TextView)PayView.findViewById(R.id.concel);
		
		newCard_img = (ImageView)PayView.findViewById(R.id.change_img_addnewcard);
		lateMoney_img = (ImageView)PayView.findViewById(R.id.change_img_latemoney);
		constranceBank_img = (ImageView)PayView.findViewById(R.id.change_img_concreatebank);
		
		newCard.setOnClickListener(listenner);
		lateMoney.setOnClickListener(listenner);
		constranceBank.setOnClickListener(listenner);
		mConcel.setOnClickListener(listenner);
		
		mPayMathodWindow = new PopupWindow(PayView,LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT,true);
		mPayMathodWindow.setTouchable(true);
		mPayMathodWindow.setOutsideTouchable(true);
		 // 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
	    // 我觉得这里是API的一个bug
		mPayMathodWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.kefu_bg)); //设置半透明
		mPayMathodWindow.showAtLocation(PayView, Gravity.BOTTOM, 0, 0);
	}
	
	OnClickListener listenner = new OnClickListener() {
		@Override
		public void onClick(View v) {
			switch(v.getId()){
				case R.id.addnewcard:  // 加新卡
					newCard_img.setImageResource(R.drawable.img_read);
					lateMoney_img.setImageResource(R.drawable.img_no_read);
					constranceBank_img.setImageResource(R.drawable.img_no_read);
					break;
				case R.id.latemoney:  //  余额
					newCard_img.setImageResource(R.drawable.img_no_read);
					lateMoney_img.setImageResource(R.drawable.img_read);
					constranceBank_img.setImageResource(R.drawable.img_no_read);
					break;
				case R.id.concreatebank:  // 建设银行
					newCard_img.setImageResource(R.drawable.img_no_read);
					lateMoney_img.setImageResource(R.drawable.img_no_read);
					constranceBank_img.setImageResource(R.drawable.img_read);
					break;
				case R.id.concel:
					if(mPayMathodWindow!=null&&mPayMathodWindow.isShowing()){
						mPayMathodWindow.dismiss();
						mPayMathodWindow = null ;
					}
					break;
			}
		}
	};
	
	
	
	
	
}
