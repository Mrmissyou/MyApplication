package com.laxiong.View;

import android.content.Context;
import android.graphics.PointF;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;


//图片的适配器
public class ChildViewPager extends ViewPager {

	PointF downP = new PointF();
	PointF curP = new PointF();
	OnSingleTouchListener onSingleTouchListener;
	OnBtnClickListener onBtnClickListener;
	Context mContext;
	
	/**
	 * 屏幕宽度
	 */
	public static int DISPLAY_WIDTH;
	
	public ChildViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
	}

	public ChildViewPager(Context context) {
		super(context);
		mContext = context;
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent arg0) {
		// 当拦截触摸事件到达此位置的时候，返回true，
		// 说明将onTouch拦截在此控件，进而执行此控件的onTouchEvent
		return true;
	}

	@Override
	public boolean onTouchEvent(MotionEvent arg0) {
		// 每次进行onTouch事件都记录当前的按下的坐标
		curP.x = arg0.getX();
		curP.y = arg0.getY();

		if (arg0.getAction() == MotionEvent.ACTION_DOWN) {

			// 记录按下时候的坐标
			// 切记不可用 downP = curP ，这样在改变curP的时候，downP也会改变
			downP.x = arg0.getX();
			downP.y = arg0.getY();
		}

		if (arg0.getAction() == MotionEvent.ACTION_MOVE) {
			int x = (int) (downP.x - curP.x);
			int y = (int) (downP.y - curP.y);
			// 左右滑动
			if (Math.abs(x) > Math.abs(y)) {
				getParent().requestDisallowInterceptTouchEvent(true);
			}
		}

		if (arg0.getAction() == MotionEvent.ACTION_UP) {

			// 在up时判断是否按下和松手的坐标为一个点
			// 如果是一个点，将执行点击事件，这是我自己写的点击事件，而不是onClick
			int x = (int) (downP.x - curP.x);
			int y = (int) (downP.y - curP.y);

			if (Math.sqrt(x * x + y * y) <= 10) {

				if (curP.x > DISPLAY_WIDTH - dp2px( 50)
						&& curP.y < dp2px(45))
					onBtnClick();
				else {
					onSingleTouch();
				}
				return true;
			}
		}
		return super.onTouchEvent(arg0);

	}

	public void onSingleTouch() {
		if (onSingleTouchListener != null) {
			onSingleTouchListener.onSingleTouch();
		}
	}

	public interface OnSingleTouchListener {
		public void onSingleTouch();
	}

	public void setOnSingleTouchListener(OnSingleTouchListener onSingleTouchListener) {
		this.onSingleTouchListener = onSingleTouchListener;
	}
	
	public void onBtnClick() {
		if (onBtnClickListener != null) {
			onBtnClickListener.onBtnClick();
		}
	}

	public interface OnBtnClickListener {
		public void onBtnClick();
	}

	public void setOnBtnClickListener(OnBtnClickListener onBtnClickListener) {
		this.onBtnClickListener = onBtnClickListener;
	}
	
	/**
	 *  dp-->px
	 */
    private  int dp2px( float dpValue) {
        float density = mContext.getResources().getDisplayMetrics().density;
        return (int) (dpValue * density + 0.5f);
    }
    /**
	 *  px-->dp
	 */
    @SuppressWarnings("unused")
	private  int px2dp( float pxValue) {
        float density = mContext.getResources().getDisplayMetrics().density;
        return (int) (pxValue / density + 0.5f);
    }
	
}