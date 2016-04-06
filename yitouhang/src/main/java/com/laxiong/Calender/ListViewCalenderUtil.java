package com.laxiong.Calender;

import java.util.ArrayList;

import android.util.Log;


public class ListViewCalenderUtil {
	/***
	 * 获取12月的月份
	 */
	private static ListViewCalenderUtil instance ;
	private ListViewCalenderUtil(){}
	public static synchronized ListViewCalenderUtil getInstance(){
		if(instance == null){
			instance = new ListViewCalenderUtil();
		}
		return instance ;
	}
	
	
	private ArrayList<CalendarView> allMonth;   //排序好的12个月			
	
	public ArrayList<CalendarView> allMonthCalenderView(CustomDate date,CalendarView[] calendarViews){
		allMonth = new ArrayList<CalendarView>();
		int currentMonth = date.getMonth();
		Log.i("Calender", "====日历的当月====："+currentMonth);
		// 当月以下的
		if(currentMonth >= 1){
			for(int j=currentMonth ;j>1;j--){
				for(int i=1;i<j;i++){
					calendarViews[currentMonth-j].leftSilde();
				}
				allMonth.add(calendarViews[currentMonth-j]);
				Log.i("Calender", "====日历的第====："+(currentMonth-j)+"月"+";的对象=="+calendarViews[currentMonth-j]);
			}
		}
		allMonth.add(calendarViews[currentMonth-1]);
		Log.i("Calender", "====日历的第====："+(currentMonth-1)+"月"+";的对象=="+calendarViews[currentMonth-1]);
		
		// 当月 以上的   
		if(currentMonth<12){
			int index = 12-currentMonth;
			for(int j=1 ;j<=index ;j++){
				for(int i=0;i<j;i++){
					calendarViews[currentMonth+j-1].rightSilde();
				}
				allMonth.add(calendarViews[currentMonth+j-1]);
				Log.i("Calender", "====日历的第====："+(currentMonth+j-1)+"月"+";的对象=="+calendarViews[currentMonth+j-1]);
			}
		}
		Log.i("Calender", "====新日历的个数====："+allMonth.size());
		return allMonth ;
	}

}
