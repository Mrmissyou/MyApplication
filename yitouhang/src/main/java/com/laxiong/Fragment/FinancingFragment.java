package com.laxiong.Fragment;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.laxiong.Adapter.Bean;
import com.laxiong.Adapter.PinnedListViewAdapter;
import com.laxiong.View.PinnedSectionListView;
import com.laxiong.View.PinnedSectionListView.OnRefreshListener;
import com.laxiong.yitouhang.R;

@SuppressLint("NewApi") 
public class FinancingFragment extends Fragment implements OnClickListener{
	/****
	 * 理财的碎片
	 */
	
	private View view ;
	private ImageView mConcel_img ;
	private LinearLayout mFinancelMessage ; // 提示消息
	
	private PinnedSectionListView mPinnedSectionListView ;
	private PinnedListViewAdapter adapter ;
	
	private Handler handler = new Handler() {
	      @Override
	      public void handleMessage(Message msg) {
	           super.handleMessage(msg);
	           switch (msg.what){
	               case 1:
	            	    Toast.makeText(getActivity(), "完成刷新", 2).show();
	            	    mPinnedSectionListView.completeRefresh();
	                    break;
	               case 2:
	            	    Toast.makeText(getActivity(), "完成加载更多", 2).show();
	            	    mPinnedSectionListView.completeRefresh();
	                    break;
	           }
	       }
	  };
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		view = inflater.inflate(R.layout.financing_layout, null);
		initView();
		return view;
	}

	private void initView() {
		//TODO 做PinnedSection的操作....
		mPinnedSectionListView = (PinnedSectionListView)view.findViewById(R.id.sectionListview);
		
		mConcel_img = (ImageView)view.findViewById(R.id.concel_img);
		mFinancelMessage = (LinearLayout)view.findViewById(R.id.finance_message);
		mConcel_img.setOnClickListener(this);
		
		adapter = new PinnedListViewAdapter(getActivity(),Bean.getData());
		mPinnedSectionListView.setAdapter(adapter);
		
		mPinnedSectionListView.setOnRefreshListener(new OnRefreshListener() {
			@Override
			public void onPullRefresh() {
				//请求数据，更新数据
				requestDataFromServer(true);
				Toast.makeText(getActivity(), "正在刷新", 2).show();
				
			}

			@Override
			public void onLoadingMore() {
				requestDataFromServer(false);
				Toast.makeText(getActivity(), "正在加载更多", 2).show();
			}
		});		
		
	}
	
	private void requestDataFromServer(final boolean isLoading){
		new Thread(){
			public void run() {
				SystemClock.sleep(3000);
				Message msg = Message.obtain();
				if(isLoading){
					msg.what = 1;
				}else{
					msg.what = 2;
				}
				//更新UI
				handler.sendMessage(msg);
			};
		}.start();
		
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
			case R.id.concel_img:
				if(mFinancelMessage!=null)
					mFinancelMessage.setVisibility(View.GONE);
				break;
		}
	}
	
}
