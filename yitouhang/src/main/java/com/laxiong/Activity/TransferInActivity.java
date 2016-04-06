package com.laxiong.Activity;

import android.annotation.SuppressLint;
import android.app.ActionBar.LayoutParams;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.laxiong.yitouhang.R;

public class TransferInActivity extends BaseActivity implements OnClickListener{
	/****
	 * 转入的
	 */
	private FrameLayout mBack ;
	private TextView mBankCan ; // 银行限制
	private LinearLayout mPayMethod ;
	private TextView mTransferinBtn ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_transferin);
		initView();
		initData();
	}

	private void initData() {
		mBack.setOnClickListener(this);
		mBankCan.setOnClickListener(this);
		mPayMethod.setOnClickListener(this);
		mTransferinBtn.setOnClickListener(this);
	}

	private void initView() {
		mBack = (FrameLayout)findViewById(R.id.in_backlayout);
		mBankCan = (TextView)findViewById(R.id.bankcan);
		mPayMethod = (LinearLayout)findViewById(R.id.selectpaymethod);
		mTransferinBtn = (TextView)findViewById(R.id.transferinnow);
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
			case R.id.in_backlayout:
				this.finish();
				break;
			case R.id.selectpaymethod:
				payMenthodType();
				break;
			case R.id.transferinnow:
				inputOverToPswd();
				break;
			case R.id.bankcan:
				Toast.makeText(this, "银行限额", 2).show();
				break;
		}
	}

	// pay Menthod
	
		private PopupWindow mPayMathodWindow ;
		private View PayView ;
		private ImageView newCard_img,lateMoney_img,constranceBank_img ;
		private void payMenthodType(){
			
			PayView = LayoutInflater.from(TransferInActivity.this).inflate(R.layout.pay_mathod_popwindow, null);
			
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
	
		// 转入按钮  输入密码
		private PopupWindow mInputPswdWindow ;
		private View mInputView ;
		private void inputOverToPswd(){
			
			mInputView = LayoutInflater.from(TransferInActivity.this).inflate(R.layout.overtopswd_popwindow, null);
			
			ImageView comcelImags = (ImageView)mInputView.findViewById(R.id.imgs_concel);
			TextView  mForgetPswd = (TextView)mInputView.findViewById(R.id.forget_pswd);
			TextView concelBtn = (TextView)mInputView.findViewById(R.id.concel_btn);
			TextView sureBtn = (TextView)mInputView.findViewById(R.id.sure_btn);
			
			comcelImags.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					if(mInputPswdWindow!=null&&mInputPswdWindow.isShowing()){
						mInputPswdWindow.dismiss();
						mInputPswdWindow = null ;
					}
				}
			});
			
			concelBtn.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					if(mInputPswdWindow!=null&&mInputPswdWindow.isShowing()){
						mInputPswdWindow.dismiss();
						mInputPswdWindow = null ;
					}
				}
			});
			
			sureBtn.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					startActivity(new Intent(TransferInActivity.this,
							TransferInResultActivity.class));
					
					if(mInputPswdWindow!=null&&mInputPswdWindow.isShowing()){
						mInputPswdWindow.dismiss();
						mInputPswdWindow = null ;
					}
					
				}
			});
			
			mForgetPswd.setOnClickListener(new OnClickListener() {
				@SuppressLint("InlinedApi") @Override
				public void onClick(View arg0) {
					Toast.makeText(TransferInActivity.this, "忘记密码的操作", 2).show();
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
		
	
}
