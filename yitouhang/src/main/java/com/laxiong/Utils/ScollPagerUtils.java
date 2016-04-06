package com.laxiong.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.laxiong.View.ChildViewPager;
import com.laxiong.View.ChildViewPager.OnSingleTouchListener;
import com.laxiong.yitouhang.R;

/****
 */
public class ScollPagerUtils implements OnPageChangeListener{
	
	private String[] urls ;  // URL Array
	private Context mContext ;
	/****
	 * initView include PagerView and dot
	 */
	private ChildViewPager mChildViewPager ;
	private LinearLayout mDotLinearlayout ;
	List<ImageView> ImagList = new ArrayList<ImageView>();  //ViewPager ImageView Collection
	List<ImageView> dotList = new ArrayList<ImageView>();  //dot ImageView Collection
	
	private int prePoint = 300; // 前面的小点的位置
	private int currentItem; //当前页面
	 
	private PagerChangeAdapter mPagerChangeAdapter ;
	private IndicateImageUtilLj mBannerIndicator ;
	
	public ScollPagerUtils(String[] urls,Context mContext,ChildViewPager mChildViewPager,
			LinearLayout mDotLinearlayout ){
		this.mContext = mContext ;
		this.urls = urls ;
		this.mChildViewPager = mChildViewPager ;
		this.mDotLinearlayout = mDotLinearlayout ;
		mChildViewPager.setOnPageChangeListener(this);
		initDotView();
		initChildView();
		mPagerChangeAdapter = new PagerChangeAdapter();
		mChildViewPager.setAdapter(mPagerChangeAdapter);
	}
	
	/****
	 * init Linearlayout dot
	 */
	private void initDotView(){
		
		mDotLinearlayout.removeAllViewsInLayout();
		for (int i = 0; i < urls.length; i++) {
			ImageView point = new ImageView(mContext);
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT);
			params.setMargins(DensityUtils.dp2px(mContext, 5), 0, 0, 0);
			point.setLayoutParams(params);
			if (i == (prePoint % urls.length)) {
				point.setBackgroundResource(R.drawable.home_point_icon);
			} else {
				point.setBackgroundResource(R.drawable.home_point_normal_icon);
			}
			dotList.add(point);
			mDotLinearlayout.addView(point);
		}
	}
	
	/****
	 * init ChildViewPager Images
	 */
	private void initChildView(){
		for (int i = 0; i < urls.length; i++) {
			ImageView imgs = new ImageView(mContext);
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,
					LayoutParams.MATCH_PARENT);
			imgs.setScaleType(ScaleType.FIT_XY);
			imgs.setLayoutParams(params);
			//				1			2										3								   4
			Glide.with(mContext).load(urls[i]).centerCrop().placeholder(R.drawable.downloading).crossFade().into(imgs);
			ImagList.add(imgs);
		}
		
		// 滑动时触发的事件
		mChildViewPager.setOnSingleTouchListener(new OnSingleTouchListener(){
			@Override
			public void onSingleTouch(){
			// TODO  滑动或点击轮播图时，跳转到的web页面的内容  Example
				if(urls.length > 0){
					String uri = urls[mChildViewPager.getCurrentItem() % urls.length];
					if(uri!=null){
						String url = urls[mChildViewPager.getCurrentItem() % urls.length] ;
//					    mContext.startActivity(new Intent(mContext,BannerActivity.class).putExtra("ImagsUrl", url));
									
					}
				}
			}
		});		
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		
	}
	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		
	}
	@Override
	public void onPageSelected(int position) {
		setDotBackground(position);
		
		if(ImagList.size() > 0){
			if(mPagerChangeAdapter!=null){
				mPagerChangeAdapter.notifyDataSetChanged();
			}else{
				mPagerChangeAdapter = new PagerChangeAdapter();
				if (urls.length > 1) // 如果广告多于1张，无限循环
				mChildViewPager.setCurrentItem(300);
				
				if(mBannerIndicator==null)
					mBannerIndicator = new IndicateImageUtilLj((Activity) mContext,mChildViewPager,null);
				
				mBannerIndicator.initTask();
				mBannerIndicator.startRepeat();
			}
		}
	}
	/****
	 * setAdapter for ChildViewPager
	 */
	class PagerChangeAdapter extends PagerAdapter{

		@Override
		public int getCount() {
			if(ImagList!=null){
				return ImagList.size();
			}else{
				return 0 ;
			}
		}
		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}
		@Override
		public void destroyItem(View container, int position, Object object) {
			 ((ChildViewPager)container).removeView(ImagList.get(position % ImagList.size()));  
		}
		@Override
		public Object instantiateItem(View container, int position) {
			((ChildViewPager) container).addView((View) ImagList.get(position % ImagList.size()), 0);
			return ImagList.get(position % ImagList.size());
		}
	}
	/***
	 * set dot Background
	 */
	private void setDotBackground(int selectItem){
		for(int i = 0; i < urls.length; i++){
			if(i == selectItem){
				dotList.get(i).setBackgroundResource(R.drawable.home_point_icon);
				currentItem = i ;
			}else{
				dotList.get(i).setBackgroundResource(R.drawable.home_point_normal_icon);
			}
		}
	}
	/***
	 * set Auto play pic
	 */
	class autoPlayImages implements Runnable{
		@Override
		public void run() {
			// TODO Auto-generated method stub
			currentItem = (currentItem +1) % urls.length;
			//更新界面
			handler.obtainMessage().sendToTarget();
		}
	}
	
	/***
	 * Start play pic
	 */
	private ScheduledExecutorService scheduledExecutorService = null;
	public void startPlayPic(){
		if(scheduledExecutorService == null){
			scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
			scheduledExecutorService.scheduleWithFixedDelay(new autoPlayImages(), 2, 2, TimeUnit.SECONDS);
		}
	}
	
	/****
	 * Handler set 
	 */
	private Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			mChildViewPager.setCurrentItem(currentItem);
		};
	};
	
	
	
}
