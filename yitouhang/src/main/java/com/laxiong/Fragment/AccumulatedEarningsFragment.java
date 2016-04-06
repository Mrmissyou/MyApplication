package com.laxiong.Fragment;

import com.laxiong.yitouhang.R;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

@SuppressLint("NewApi") 
public class AccumulatedEarningsFragment extends Fragment {
	/****
	 * 累计收益的碎片
	 */
	private View accimlatedView ;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		accimlatedView = inflater.inflate(R.layout.accumlatedearnings_frag, null);
		
		return accimlatedView;
	}
	

}
