package com.laxiong.Adapter;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.laxiong.Activity.GuXiBaoActivity;
import com.laxiong.Activity.TimeXiTongActivity;
import com.laxiong.View.CircleProgressBar;
import com.laxiong.View.PinnedSectionListView.PinnedSectionListAdapter;
import com.laxiong.yitouhang.R;

public class VipPinnedListViewAdapter extends BaseAdapter implements PinnedSectionListAdapter{
	/****
	 * this is common Adapter for PinnedSectionListView
	 * VIP适配器
	 */
	private Context mContext ;
	private ArrayList<Bean> list;
	
	public VipPinnedListViewAdapter(Context mContext,ArrayList<Bean> list){
		this.mContext = mContext ;
		setList(list);
	}
	/***
	 * List的get和set方法
	 */
	public ArrayList<Bean> getList() {
		return list;
	}
	public void setList(ArrayList<Bean> list) {
		if(list!=null){
			this.list = list;
		}else{
			list=new ArrayList<Bean>();
		}
	}

	@Override
	public int getCount() {
		return list.size();
	}
	@Override
	public Object getItem(int position) {
		return list.get(position);
	}
	@Override
	public long getItemId(int position) {
		return position;
	}
	@Override
	public View getView(int position, View converView, ViewGroup arg2) {
	  ViewHonder vh=null;
	  final Bean bean=(Bean) getItem(position);
	  if(converView==null){
			vh=new ViewHonder();
			vh.iv = new ImageView(mContext);
			switch(bean.type){
				case Bean.SECTION:
					converView=LayoutInflater.from(mContext).inflate(R.layout.item_section,null);
					vh.mText_section=(TextView)converView.findViewById(R.id.section);
					
					break;
				case Bean.ITEM:
					converView=LayoutInflater.from(mContext).inflate(R.layout.item_items,null);
					vh.mText_section=(TextView)converView.findViewById(R.id.tv1);
					vh.mCircleProgressView=(CircleProgressBar)converView.findViewById(R.id.cricleprogress);
					
					vh.id_tag = (TextView)converView.findViewById(R.id.id_tag);
					vh.vip_addbf = (TextView)converView.findViewById(R.id.vip_baifenbi);
					
					break;
			}
			converView.setTag(vh);
	  }else{
		  vh = (ViewHonder)converView.getTag();
	  }
	  //TODO  赋值操作u
	  vh.mText_section.setText(bean.text);
	  
	  if(bean.type == Bean.SECTION){
		  
		  
	  }
//		  vh.mText_section.setBackgroundColor(Color.parseColor("#DBDBDB"));
	  
	  if(bean.imgsUrl.equals("time")){  // TODO 确定点击的是时息通(有问题)===Bug
		  if(converView!=null)
			  converView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View arg0) {
					mContext.startActivity(new Intent(mContext,
							TimeXiTongActivity.class));
					
				}
			});
	  }else if(bean.imgsUrl.equals("gu")){  // 固息宝
		  if(converView!=null)
			  converView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View arg0) {
					mContext.startActivity(new Intent(mContext,
							GuXiBaoActivity.class));
					
				}
			});
	  }
	  
	  if (bean.type == Bean.ITEM) {
		  	if(vh.mCircleProgressView!=null)
//		  		vh.mCircleProgressView.setValue(42);
		  		vh.mCircleProgressView.setProgress(27.5f, vh.iv);
		  	
		  	 vh.id_tag.setText("五万起投");
			 vh.vip_addbf.setVisibility(View.VISIBLE);
	  }
	  
	  return converView ;
	}
	@Override
	public boolean isItemViewTypePinned(int viewType) {
		return  viewType == Bean.SECTION;
	}
	@Override
	public int getViewTypeCount() {
		return 2;
	}
	@Override
	public int getItemViewType(int position) {
		return ((Bean)getItem(position)).type;
	}
	
	public void refresh(ArrayList<Bean> arr){
        setList(arr);
        notifyDataSetChanged();
    }
	
	/**
	 * ViewHonder的类
	 */
	class ViewHonder{
		TextView mText_section ;
//		CircleProgressView mCircleProgressView ;    // 没有百分比的
		CircleProgressBar mCircleProgressView ;
		TextView mText_1 ;
		TextView mText_2 ;
		TextView mText_3 ;
		
		ImageView iv ; //放cricleprogressbar的节点
		TextView id_tag ;
		TextView vip_addbf ;
	}
	
	
}
