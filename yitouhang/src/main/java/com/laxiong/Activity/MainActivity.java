package com.laxiong.Activity;

import android.annotation.SuppressLint;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.laxiong.Common.Settings;
import com.laxiong.Fragment.FinancingFragment;
import com.laxiong.Fragment.FristPagerFragment;
import com.laxiong.Fragment.MySelfFragment;
import com.laxiong.Fragment.VipFinancingFragment;
import com.laxiong.yitouhang.R;

public class MainActivity extends BaseActivity implements OnClickListener{
	/****
	 * 主页
	 */
	private RelativeLayout mFristPager , mFinancing , mMyself ;  // Bottom three Button Layout
	
	private TextView mFristPager_tv , mFinancing_tv , mMyself_tv ;
	private ImageView mFristPager_icon , mFinancing_icon ,mMyself_icon ;
	
	private FristPagerFragment mFristPagerFragment = null;
	private FinancingFragment mFinancingFragment = null;
	private MySelfFragment mMySelfFragment = null;
	
	private FragmentManager mFragmentManager = null ;
	
	private TextView mHead_title,mHead_left_select_textview , mHead_gongshilicai ;  // head Title TextView
	private FrameLayout mPersonSetting ;
	private RelativeLayout mHeadLayout ;
	
	private boolean isLogin = false ; // 判断是否登录了
	
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        WindowManager wm =this.getWindowManager();
        Settings.DISPLAY_HEIGHT = wm.getDefaultDisplay().getHeight();
        Settings.DISPLAY_WIDTH = wm.getDefaultDisplay().getWidth();
        
        
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

	@SuppressLint("NewApi")
	private void initData() {
		
		mFragmentManager = this.getFragmentManager();
//		FragmentTransaction  mTransaction = mFragmentManager.beginTransaction();
		
		mFristPager.setOnClickListener(this);
		mFinancing.setOnClickListener(this);
		mMyself.setOnClickListener(this);
		
		initFristFragment();  //初始化第一张碎片
	}

	private void initView() {
		
		mFristPager = (RelativeLayout)findViewById(R.id.fristpager);
		mFinancing = (RelativeLayout)findViewById(R.id.financing);
		mMyself = (RelativeLayout)findViewById(R.id.myself);
		
		mPersonSetting = (FrameLayout)findViewById(R.id.head_myself_details);
		mHead_gongshilicai = (TextView)findViewById(R.id.head_gongshilicai);
		mHead_left_select_textview = (TextView)findViewById(R.id.head_select_right_textview);
		mHead_title = (TextView)findViewById(R.id.head_title);
		
		mFristPager_icon = (ImageView)findViewById(R.id.fristpager_icon);
		mFristPager_tv = (TextView)findViewById(R.id.fristpager_tv);
		mFinancing_icon = (ImageView)findViewById(R.id.financing_icon);
		mFinancing_tv = (TextView)findViewById(R.id.financingr_tv);
		mMyself_icon = (ImageView)findViewById(R.id.myself_icon);
		mMyself_tv = (TextView)findViewById(R.id.myself_tv);
		
		// head 
		mHeadLayout = (RelativeLayout)findViewById(R.id.main_head_layout);
		
		mPersonSetting.setOnClickListener(listen);  //personal setting onClick
	}

	@SuppressLint("NewApi") @Override
	public void onClick(View view) {
		FragmentTransaction  mTransaction = mFragmentManager.beginTransaction();
		hideFragment(mTransaction);
		switch(view.getId()){
			case R.id.financing:
				setButtomBackground(1);
				if(mFinancingFragment == null){		// 理财页面
					mFinancingFragment = new FinancingFragment();
					mTransaction.add(R.id.setContent, mFinancingFragment);
				}else{
					mTransaction.show(mFinancingFragment);
				}
				
				break;
			case R.id.fristpager:
				setButtomBackground(0);
				if(mFristPagerFragment == null){     // 首页页面
					mFristPagerFragment = new FristPagerFragment();
					mTransaction.add(R.id.setContent, mFristPagerFragment);
				}else{
					mTransaction.show(mFristPagerFragment);
				}
				
				break;
			case R.id.myself:
				
				//TODO 判断是否  已经登录了
//				if(isLogin){
					setButtomBackground(2);
					if(mMySelfFragment == null){		 // 资产页面
						mMySelfFragment = new MySelfFragment();
						mTransaction.add(R.id.setContent, mMySelfFragment);
					}else{
						mTransaction.show(mMySelfFragment);
					}
//				}else{
//					startActivity(new Intent(MainActivity.this,
//							RegistActivity.class));
//					
//					//TODO 之后直接显示到首页
//					setButtomBackground(0);
//					if(mFristPagerFragment == null){     // 首页页面
//						mFristPagerFragment = new FristPagerFragment();
//						mTransaction.add(R.id.setContent, mFristPagerFragment);
//					}else{
//						mTransaction.show(mFristPagerFragment);
//					}
//					
//				}
				break;
				
		}
		mTransaction.commit();
	}
	
	@SuppressLint("NewApi") 
	private void hideFragment(FragmentTransaction  mTransaction){
		if(mFristPagerFragment!=null){
			mTransaction.hide(mFristPagerFragment);
		}
		if(mFinancingFragment!=null){
			mTransaction.hide(mFinancingFragment);
		}
		if(mMySelfFragment!=null){
			mTransaction.hide(mMySelfFragment);
		}
		if(mVipFragment!=null){
			mTransaction.hide(mVipFragment);
		}
	}
	
	private void setButtomBackground(int index){
		setInitBackground();
		
		//TODO 点击切换时切换的颜色和图片Buttom
		switch(index){
			case 0:  // fristpager
				//TODO 
				mHead_gongshilicai.setVisibility(View.VISIBLE);
				mHead_gongshilicai.setText("(原共识理财)");
				mHead_left_select_textview.setVisibility(View.GONE);
				mHead_title.setVisibility(View.VISIBLE);
				mHead_title.setText("壹投行");
				mHead_title.setTextColor(Color.parseColor("#ffffff"));
				mHeadLayout.setBackgroundColor(Color.parseColor("#EE4E42"));  // 红色
				
				
				break;
			case 1:  // financing
				mHead_gongshilicai.setVisibility(View.GONE);
				mHead_left_select_textview.setVisibility(View.VISIBLE);
				mHead_left_select_textview.setText("VIP");                //  show VIP
				mHead_left_select_textview.setTextColor(Color.parseColor("#ffffff"));
				mHead_title.setVisibility(View.VISIBLE);
				mHead_title.setText("理财");
				mHead_title.setTextColor(Color.parseColor("#ffffff"));
				mHead_left_select_textview.setOnClickListener(listen);
				mHeadLayout.setBackgroundColor(Color.parseColor("#EE4E42"));  // 红色
				
				
				break;
			case 2:  // myself
				mHead_gongshilicai.setVisibility(View.GONE);
				mHead_left_select_textview.setVisibility(View.VISIBLE);
				mHead_left_select_textview.setText("日历账单");
				mHead_left_select_textview.setTextColor(Color.parseColor("#ffffff"));
				mHead_title.setVisibility(View.VISIBLE);
				mHead_title.setText("资产");
				mHead_title.setTextColor(Color.parseColor("#ffffff"));
				mHead_left_select_textview.setOnClickListener(listen);
				mHeadLayout.setBackgroundColor(Color.parseColor("#EE4E42"));  // 红色
				
				
				break;
		
		}
	}
	// set to initbackground
	private void setInitBackground(){
		//TODO 设置为初始的颜色和图片
		
		
	}
	
	@SuppressLint("NewApi") 
	private void initFristFragment(){
		FragmentTransaction  mTransaction = mFragmentManager.beginTransaction();
		mFristPagerFragment = new FristPagerFragment();
		mTransaction.add(R.id.setContent, mFristPagerFragment);
		mTransaction.commit();
	}
	
	OnClickListener listen = new OnClickListener() {
		@Override
		public void onClick(View v) {
			switch(v.getId()){
				case R.id.head_myself_details:
					startActivity(new Intent(MainActivity.this,
							CountSettingActivity.class));
					
					break;
				case R.id.head_select_right_textview:
					if(mHead_left_select_textview == null)
						return ;
					if(mHead_left_select_textview.getText().toString().equals("VIP")){
						
						vipAndFinance(2);
						financingToVipEachOther(2);
//						TODO 原本是跳转到 VIP的页面的
//						startActivity(new Intent(MainActivity.this,
//								ShowVIPActivity.class));
						
					}else if(mHead_left_select_textview.getText().toString().equals("理财")){
						
						vipAndFinance(1);
						financingToVipEachOther(1);
						
					}else if(mHead_left_select_textview.getText().toString().equals("日历账单")){
						startActivity(new Intent(MainActivity.this,
								CalanderCountActivity.class));
					}else{
						return ;
					}
					break;
			}
		}
	};
	
	private VipFinancingFragment mVipFragment ;
	//change each other financing and vip
	private void financingToVipEachOther(int tag){
		switch(tag){
			case 1:	//理财
				mHead_title.setText("理财");
				mHead_left_select_textview.setText("VIP");
				break;
			case 2:	 //vip
				mHead_title.setText("VIP");
				mHead_left_select_textview.setText("理财");
				break;
		}
	}
	//碎片切换
	@SuppressLint("NewApi") 
	private void vipAndFinance(int tag){
		FragmentTransaction  mTransaction = mFragmentManager.beginTransaction();
		hideFragment(mTransaction);
		switch(tag){
			case 1:  //理财
				if(mFinancingFragment == null){		// 理财页面
					mFinancingFragment = new FinancingFragment();
					mTransaction.add(R.id.setContent, mFinancingFragment);
				}else{
					mTransaction.show(mFinancingFragment);
				}
				
				break;
			case 2:  //vip
				if(mVipFragment == null){
					mVipFragment = new VipFinancingFragment();
					mTransaction.add(R.id.setContent, mVipFragment);
				}else{
					mTransaction.show(mVipFragment);
				}
				
				break;
		}
		mTransaction.commit();
	}
	
}
