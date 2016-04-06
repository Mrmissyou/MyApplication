package com.laxiong.Activity;

import java.text.NumberFormat;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.laxiong.View.VerticalNumberProgressBar;
import com.laxiong.yitouhang.R;

public class GuXiBaoActivity extends BaseActivity implements OnClickListener{
	/****
	 * 固息宝
	 */
	private RelativeLayout mLayout_progressbar ;
	private TextView mProgressNum ,mShareBtn , mBuyBtn;
	private VerticalNumberProgressBar mProgressBar ;
	private FrameLayout mBack ;
	private ImageView mJiSuanQi ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_set_guxibao);
		initView();
		initData();
	}
	private void initData() {
		mShareBtn.setOnClickListener(this);
		mBack.setOnClickListener(this);
		mJiSuanQi.setOnClickListener(this);
		mBuyBtn.setOnClickListener(this);
		
		setProgressNumHeight();
	}
	private void initView() {
		mLayout_progressbar = (RelativeLayout)findViewById(R.id.progressbar_layout);
		mProgressNum = (TextView)findViewById(R.id.numText);
		mProgressBar =(VerticalNumberProgressBar)findViewById(R.id.numberbar);
		mJiSuanQi = (ImageView)findViewById(R.id.jisuanqi);
		mBuyBtn = (TextView)findViewById(R.id.buying);
		
		
		mBack = (FrameLayout)findViewById(R.id.backlayout);
		mShareBtn = (TextView)findViewById(R.id.share);
	}
	@Override
	public void onClick(View v) {
		switch(v.getId()){
			case R.id.share:
				Toast.makeText(this, "分享", 2).show();
				break;
			case R.id.backlayout:
				this.finish();
				break;
			case R.id.jisuanqi:
				showJiSuanQi();
				break;
			case R.id.buying:
				startActivity(new Intent(GuXiBaoActivity.this,
						BuyingActivity.class));
				break;
		}
	}
	
	// set progress textview height
	private void setProgressNumHeight(){
		int widthMeasureSpec = MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int heightMeasureSpec = MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
		
        mLayout_progressbar.measure(widthMeasureSpec, heightMeasureSpec);
        mProgressNum.measure(widthMeasureSpec, heightMeasureSpec);
        
        mProgressBar.setProgress(78.9f);
        int layoutHeight = mLayout_progressbar.getMeasuredHeight();
        int progressTextHeight = mProgressNum.getMeasuredHeight();
        
        // 设置进度的运行高度大于  TextView本身默认的高度时
        if(((mProgressBar.getProgress() / 100 )*layoutHeight) > progressTextHeight){
        	 LayoutParams tvlp =  mProgressNum.getLayoutParams();
        	 tvlp.height = (int)((mProgressBar.getProgress() / 100 )*layoutHeight);
        	 mProgressNum.setLayoutParams(tvlp);
        }
        mProgressNum.setText(mProgressBar.getProgress()+"%");
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
					Toast.makeText(GuXiBaoActivity.this, "输入整数", 3).show();
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
					Toast.makeText(GuXiBaoActivity.this, "输入整数", 3).show();
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
	
	
	
}
