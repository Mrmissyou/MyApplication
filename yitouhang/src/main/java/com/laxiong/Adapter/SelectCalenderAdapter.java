package com.laxiong.Adapter;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.laxiong.Calender.CalendarView;
import com.laxiong.Common.Settings;
import com.laxiong.View.PinnedSectionListView.PinnedSectionListAdapter;
import com.laxiong.yitouhang.R;

public class SelectCalenderAdapter  extends BaseAdapter implements PinnedSectionListAdapter{
	
	
	private ArrayList<CalenderBean> mlist ;
	private Context mContext ;
	public SelectCalenderAdapter(Context mContext,ArrayList<CalenderBean> mlist){
		this.mContext = mContext ;
		this.mlist =mlist ;
	}
	
	@Override
	public int getCount() {
		if(mlist.size() ==0){
			return 0 ;
		}else{
			return mlist.size();
		}
	}
	@Override
	public Object getItem(int arg0) {
		return mlist.get(arg0);
	}
	@Override
	public long getItemId(int arg0) {
		return arg0;
	}
	@Override
	public View getView(int position, View converView, ViewGroup arg2) {
		ViewHonder vh = null ;
		CalenderBean bean = (CalenderBean)getItem(position);
		if(converView == null){
			vh = new ViewHonder();
			switch(bean.type){
				case CalenderBean.ITEM:   // 日历显示
					AbsListView.LayoutParams lp = new AbsListView.LayoutParams(LayoutParams.MATCH_PARENT,Settings.DISPLAY_WIDTH-(Settings.DISPLAY_WIDTH/7));
					converView = (bean.mCalendarView).get(position);
					converView.setLayoutParams(lp);
					
					break;
				case CalenderBean.SECTION:   //标题
					converView = LayoutInflater.from(mContext).inflate(R.layout.item_section, null);
					vh.mMonth = (TextView)converView.findViewById(R.id.section);
					break;
			}
			converView.setTag(vh);
		}else{
			vh = (ViewHonder)converView.getTag();
		}
		// TODO 操作
		if(bean.type == CalenderBean.ITEM){
			
//			if(converView instanceof CalendarView){
//				CalendarView mCalender = (CalendarView) converView;
//				mCalender.setCalenderMonth(position+1);
//			}	
			
		}
		if(bean.type == CalenderBean.SECTION){
			vh.mMonth.setText(bean.text); // 赋值月
		}
		
		return converView;
	}

	@Override
	public boolean isItemViewTypePinned(int viewType) {
		return viewType == CalenderBean.SECTION;
	}
	@Override
	public int getViewTypeCount() {
		return 2;
	}
	@Override
	public int getItemViewType(int position) {
		return ((CalenderBean)getItem(position)).type;
	}
	// 复用的方法
	class ViewHonder{
		TextView mMonth;
		CalendarView mCalender;
	}
	
	
}
