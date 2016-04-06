package com.laxiong.Fragment;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.laxiong.Activity.GuXiBaoActivity;
import com.laxiong.Activity.TimeXiTongActivity;
import com.laxiong.Activity.WebViewActivity;
import com.laxiong.Common.Settings;
import com.laxiong.Utils.ScollPagerUtils;
import com.laxiong.View.ChildViewPager;
import com.laxiong.yitouhang.R;

@SuppressLint("NewApi") 
public class FristPagerFragment extends Fragment implements OnClickListener{
	/****
	 * 首页的碎片
	 */
	private View FristView = null ;
	private ChildViewPager mChildViewPager = null ;
	private LinearLayout mLinearDot = null ;
	private Context mContext = null ;
	
	private RelativeLayout mNewBiao , mGuXiBao , mTimeXiTong ;  // 新手标  固息宝  时息通
	
	private String[] str ={
			
			"http://a.hiphotos.baidu.com/album/w%3D2048/sign=5c4fe8d4a5c27d1ea5263cc42fedac6e/024f78f0f736afc3f1416515b219ebc4b7451274.jpg",
			"http://c.hiphotos.baidu.com/album/w%3D2048/sign=739d5cd03ac79f3d8fe1e3308e99cc11/7a899e510fb30f24f807f52cc995d143ad4b037b.jpg",
			"http://d.hiphotos.baidu.com/album/w%3D2048/sign=9644b9d5d0c8a786be2a4d0e5331c83d/d1160924ab18972b675c19e5e7cd7b899e510abe.jpg"
	};
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		FristView = inflater.inflate(R.layout.fristpager_layout, null);
		mContext = getActivity() ;
		initView();
		initData();
		setListen();
		return FristView;
	}

	private void initData() {
		
		ScollPagerUtils mScollPagerUtils = new ScollPagerUtils(str,mContext,mChildViewPager,mLinearDot);
		mScollPagerUtils.startPlayPic();
		
	}

	private void initView() {
		
		mChildViewPager = (ChildViewPager) FristView.findViewById(R.id.childviewpager);
		mLinearDot = (LinearLayout)FristView.findViewById(R.id.layout_dot);
		mNewBiao = (RelativeLayout)FristView.findViewById(R.id.new_biao);
		mGuXiBao = (RelativeLayout)FristView.findViewById(R.id.gu_xibao);
		mTimeXiTong = (RelativeLayout)FristView.findViewById(R.id.time_xitong);
		
		// set Height 
//		mChildViewPager.getLayoutParams().height = Settings.DISPLAY_WIDTH * 350 / 750 ;
		
	}

	private void setListen(){
		mNewBiao.setOnClickListener(this);
		mGuXiBao.setOnClickListener(this);
		mTimeXiTong.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		switch(v.getId()){
			case R.id.new_biao:	// TODO  测试WebView功能
				startActivity(new Intent(getActivity(),
						WebViewActivity.class));
				break;
			case R.id.gu_xibao:
				startActivity(new Intent(getActivity(),
						GuXiBaoActivity.class));
				break;
			case R.id.time_xitong:
				startActivity(new Intent(getActivity(),
						TimeXiTongActivity.class));
				break;
		}
	}
	
	
}
