package com.laxiong.Fragment;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.laxiong.Activity.AssetActivity;
import com.laxiong.Activity.AvailableBalanceActivity;
import com.laxiong.Activity.RechargeActivity;
import com.laxiong.Activity.WithdrawCashActivity;
import com.laxiong.Activity.WithdrawCashDetailsActivity;
import com.laxiong.yitouhang.R;

@SuppressLint("NewApi") 
public class MySelfFragment extends Fragment implements OnClickListener{
	/****
	 *我的碎片
	 */
	View view ;
	private TextView totleMoney ;
	private RelativeLayout AvailableBalance ,WithdrawCash , Recharge;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		view = inflater.inflate(R.layout.myself_layout, null);
		initView();
		initData();
		return view;
	}

	private void initData() {
		totleMoney.setOnClickListener(this);
		AvailableBalance.setOnClickListener(this);
		WithdrawCash.setOnClickListener(this);
		Recharge.setOnClickListener(this);
	}

	private void initView() {
		totleMoney = (TextView)view.findViewById(R.id.togetche_tv);
		AvailableBalance = (RelativeLayout)view.findViewById(R.id.availablebalance);
		WithdrawCash = (RelativeLayout)view.findViewById(R.id.withdrawcash);
		Recharge = (RelativeLayout)view.findViewById(R.id.recharge);
	}

	@Override
	public void onClick(View V) {
		switch(V.getId()){
		case R.id.togetche_tv:  // 总资产
			startActivity(new Intent(getActivity(),
					AssetActivity.class));
			break;
		case R.id.availablebalance:  // 可用余额
			startActivity(new Intent(getActivity(),
					AvailableBalanceActivity.class));
			break;
		case R.id.withdrawcash:  // 提现
			startActivity(new Intent(getActivity(),
					WithdrawCashActivity.class));
			break;
		case R.id.recharge:  // 充值
			startActivity(new Intent(getActivity(),
					RechargeActivity.class));
			break;
		}
	}
}
