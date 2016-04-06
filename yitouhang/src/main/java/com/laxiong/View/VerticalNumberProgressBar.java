package com.laxiong.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.View;

public class VerticalNumberProgressBar extends View {
	/****
	 *vertical progress Bar
	 */
	private float progress = 15.0f;
	
	private float max = 100.0f ;
	
	private Paint paint_bar ;		// color paint
	private Paint paint_default ;		// default paint
	
	private float stopX ;
	private float stopY ;

	public VerticalNumberProgressBar(Context context) {
		super(context);
		initData();
	}
	
	public VerticalNumberProgressBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		initData();
	}

	public VerticalNumberProgressBar(Context context, AttributeSet attrs ,int defStyleAttr) {
		super(context, attrs , 0);
		initData();
	}
	
	public float getProgress() {
		return progress;
	}

	public void setProgress(float progress) {
		if(progress<0){
			 throw new IllegalArgumentException("进度数据不能小于0");  
		}
		if(progress>max){
			progress = max ;
		}
		if(progress<=max){
			this.progress = progress;
			this.postInvalidate();
		}
	}

	public float getMax() {
		return max;
	}

	public void setMax(float max) {
		if(max<0){
			 throw new IllegalArgumentException("最大进度数值大于0");  
		}
		this.max = max;
	}
	
	private void initData(){
		paint_bar = new Paint();
		paint_bar.setColor(Color.parseColor("#EE4E42"));
		paint_bar.setStyle(Style.FILL);
		
		paint_default = new Paint();
		paint_default.setColor(Color.parseColor("#FF0000"));
		paint_default.setStyle(Style.FILL);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		int width = this.getWidth();
		int height = this.getHeight();
		canvas.drawColor(Color.parseColor("#EDEBEA")); // 灰色背景
		paint_bar.setStrokeWidth(height);
		stopY = ((max- progress) / max)*height ;
		canvas.drawLine(width, height, width, stopY, paint_bar);
		
	}
}
