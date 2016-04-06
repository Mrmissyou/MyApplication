package com.laxiong.Adapter;

import java.util.ArrayList;

import android.content.Context;

import com.laxiong.Calender.CalendarView;
import com.laxiong.Calender.CalendarView.CallBack;
import com.laxiong.Calender.CalendarViewBuilder;
import com.laxiong.Calender.CustomDate;


public class CalenderBean {

	/****
	 * 解析获取的数据可以暂时的加载在此
	 */
	public static final int ITEM = 0;
	public static final int SECTION = 1;
	public final int type;
	public final String text;
//	public final CalendarView[] mCalendarView;
	public final ArrayList<CalendarView> mCalendarView;
	public final  Context mContext ;
	
	public CalenderBean(int type, String text,ArrayList<CalendarView> mCalendarView,Context mContext) {
		this.type = type;
		this.text = text;
		this.mCalendarView = mCalendarView ;
		this.mContext = mContext ;
	}


	public static ArrayList<CalenderBean> getData(Context context,ArrayList<CalendarView> views){
		ArrayList<CalenderBean>  list=new ArrayList<CalenderBean>();
		
		list.add(new CalenderBean(SECTION, "一月份",views,context));
		list.add(new CalenderBean(ITEM, "2016-01-01",views,context));
		
		list.add(new CalenderBean(SECTION, "二月份",views,context));
		list.add(new CalenderBean(ITEM, "2016-02-01",views,context));
		
		list.add(new CalenderBean(SECTION, "三月份",views,context));
		list.add(new CalenderBean(ITEM, "2016-03-01",views,context));
		
		list.add(new CalenderBean(SECTION, "四月份",views,context));
		list.add(new CalenderBean(ITEM, "2016-04-01",views,context));
		
		list.add(new CalenderBean(SECTION, "五月份",views,context));
		list.add(new CalenderBean(ITEM, "2016-05-01",views,context));
		
		list.add(new CalenderBean(SECTION, "六月份",views,context));
		list.add(new CalenderBean(ITEM, "2016-06-01",views,context));
		
		list.add(new CalenderBean(SECTION, "七月份",views,context));
		list.add(new CalenderBean(ITEM, "2016-07-01",views,context));
		
		list.add(new CalenderBean(SECTION, "八月份",views,context));
		list.add(new CalenderBean(ITEM, "2016-08-01",views,context));
		
		list.add(new CalenderBean(SECTION, "九月份",views,context));
		list.add(new CalenderBean(ITEM, "2016-09-01",views,context));
		
		list.add(new CalenderBean(SECTION, "十月份",views,context));
		list.add(new CalenderBean(ITEM, "2016-10-01",views,context));
		
		list.add(new CalenderBean(SECTION, "十一月份",views,context));
		list.add(new CalenderBean(ITEM, "2016-11-01",views,context));
		
		list.add(new CalenderBean(SECTION, "十二月份",views,context));
		list.add(new CalenderBean(ITEM, "2016-12-01",views,context));
		
		return list;
	}



}
