package com.laxiong.Activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.laxiong.yitouhang.R;

public class MessageActivity extends BaseActivity implements OnClickListener{
	/***
	 * 消息
	 */
	private FrameLayout mBack ;
	private TextView mTitle ;
	private ListView mListView ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_message_layout);
		initView();
		initData();
	}

	private void initData() {
		mBack.setOnClickListener(this);
		
		mListView.setAdapter(new mListAdapter());
	}

	private void initView() {
		mBack = (FrameLayout)findViewById(R.id.back_layout);
		
		mTitle = (TextView)findViewById(R.id.title);
		mTitle.setText("消息");
		
		mListView = (ListView)findViewById(R.id.info_listview);
	}

	@Override
	public void onClick(View V) {
		switch(V.getId()){
			case R.id.back_layout:
				this.finish();
				break;
		}
	}
	
	/**setAdapter**/
	class mListAdapter extends BaseAdapter{
		@Override
		public int getCount() {
			return 14;
		}
		@Override
		public Object getItem(int arg0) {
			return null;
		}
		@Override
		public long getItemId(int arg0) {
			return 0;
		}
		@Override
		public View getView(int position, View view, ViewGroup arg2) {
			if(view == null){
				view = LayoutInflater.from(MessageActivity.this).inflate(R.layout.message_listview_item, null);
			}
			return view;
		}
		
	}
	
}
