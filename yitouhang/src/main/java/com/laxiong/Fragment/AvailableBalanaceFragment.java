package com.laxiong.Fragment;

import com.laxiong.yitouhang.R;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

@SuppressLint("NewApi") 
public class AvailableBalanaceFragment extends Fragment {
	/***
	 * 可用余额的碎片
	 */
	private View availableView ;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		availableView = inflater.inflate(R.layout.availablebalanace_frag, null);
		
		return availableView;
	}

}
