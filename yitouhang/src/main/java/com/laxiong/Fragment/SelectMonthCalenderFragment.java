package com.laxiong.Fragment;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.laxiong.Adapter.CalenderBean;
import com.laxiong.Adapter.SelectCalenderAdapter;
import com.laxiong.Calender.CalendarView;
import com.laxiong.Calender.CalendarView.CallBack;
import com.laxiong.Calender.CalendarViewBuilder;
import com.laxiong.Calender.CustomDate;
import com.laxiong.Calender.ListViewCalenderUtil;
import com.laxiong.View.PinnedSectionListView;
import com.laxiong.yitouhang.R;
@SuppressLint("NewApi") 
public class SelectMonthCalenderFragment extends Fragment implements CallBack{
	 /****
	  * ListView的Calender
	  */
	private PinnedSectionListView mSelectCalender ;
	private View CalenderView ;
	private CalendarViewBuilder builder = new CalendarViewBuilder();
	private static CalendarView[] views;
	
	private CustomDate date ;
	private ArrayList<CalendarView> allMonth ;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		CalenderView = inflater.inflate(R.layout.select_month_calender_frag, null);
		initView();
		initData();
		return CalenderView;
	}

	private void initData() {
		mSelectCalender.setAdapter(new SelectCalenderAdapter(getActivity(),CalenderBean.getData(getActivity(),allMonth)));
		
	}

	private void initView() {
		mSelectCalender = (PinnedSectionListView)CalenderView.findViewById(R.id.sectionListviewCalender);
		views = builder.createMassCalendarViews(getActivity(), 12, this);
		
		allMonth = ListViewCalenderUtil.getInstance().allMonthCalenderView(date, views);
		
	}

	@Override
	public void clickDate(CustomDate date) {
		
		
	}

	@Override
	public void onMesureCellHeight(int cellSpace) {
		
		
	}
	@Override
	public void changeDate(CustomDate date) {
		this.date = date;
		date.getMonth();
		date.getYear();
	}
	
	
	
}
