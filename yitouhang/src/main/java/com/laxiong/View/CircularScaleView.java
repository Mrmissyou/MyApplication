package com.laxiong.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.View;

public class CircularScaleView extends View{
	/***
	 * 自定义的  圆环比例图
	 */
	private int maxProgress = 100;
	private float progressFrist = 15;    //  第一段的progress
	
	private float progressSecond = 55;    //  第二段的progress
	
	private float progressThird = 30;    //  第三段的progress
	private int progressStrokeWidth = 40;
	private int marxArcStorkeWidth = 40;
	
	private String totalMoney = "263,758";
	
	// 画圆所在的距形区域
	RectF oval;
	Paint paint;
	
	
	public CircularScaleView(Context context, AttributeSet attrs) {
		super(context, attrs);
		oval = new RectF();
		paint = new Paint();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		int width = this.getWidth();
		int height = this.getHeight();
		
		width = (width > height) ? height : width;
		height = (width > height) ? height : width;
		
		paint.setAntiAlias(true);  // 设置画笔为抗锯齿
		paint.setColor(Color.parseColor("#D8D8D8")); // 设置画笔颜色
		canvas.drawColor(Color.TRANSPARENT); // 白色背景
		paint.setStrokeWidth(progressStrokeWidth); // 线宽
		paint.setStyle(Style.STROKE);
		
		oval.left = marxArcStorkeWidth / 2; // 左上角x
		oval.top = marxArcStorkeWidth / 2;  // 左上角y
		oval.right = width - marxArcStorkeWidth / 2; // 左下角x
		oval.bottom = height - marxArcStorkeWidth / 2; // 右下角y
		
		canvas.drawArc(oval, -90, 360, false, paint); // 绘制白色圆圈
		
		paint.setColor(Color.parseColor("#A6A6A6"));
		paint.setStrokeWidth(marxArcStorkeWidth);
		canvas.drawArc(oval, -90, ((float) progressFrist / maxProgress) * 360,
				false, paint);
		
		paint.setColor(Color.parseColor("#BDBDBD"));
		paint.setStrokeWidth(marxArcStorkeWidth);
		canvas.drawArc(oval, -90+((float) progressFrist / maxProgress) * 360,((float) progressSecond / maxProgress) * 360,
				false, paint);
		
		paint.setColor(Color.parseColor("#D8D8D8"));
		paint.setStrokeWidth(marxArcStorkeWidth);
		canvas.drawArc(oval, -90+((float) progressFrist / maxProgress) * 360+((float) progressSecond / maxProgress) * 360,((float) progressThird / maxProgress) * 360,
				false, paint);
		
		
		paint.setColor(Color.GRAY);
		paint.setStrokeWidth(1);
		String text = "帐户资产(元)";
		int textHeight = height / 16;
		paint.setTextSize(textHeight);
		int textWidth = (int) paint.measureText(text, 0, text.length());
		paint.setStyle(Style.FILL);
		canvas.drawText(text, width / 2 - textWidth / 2, height / 2
				+ textHeight / 14, paint);
		
		paint.setColor(Color.BLACK);
		paint.setStrokeWidth(5);
		int MoneyWidth = (int) paint.measureText(totalMoney, 0, text.length());
		paint.setStyle(Style.FILL);
		canvas.drawText(totalMoney, width / 2 - MoneyWidth / 2, height / 2
				+ textHeight , paint);
		
	}

	public float getProgressFrist() {
		return progressFrist;
	}

	public void setProgressFrist(float progressFrist) {
		this.progressFrist = progressFrist;
	}

	public float getProgressSecond() {
		return progressSecond;
	}

	public void setProgressSecond(float progressSecond) {
		this.progressSecond = progressSecond;
	}

	public float getProgressThird() {
		return progressThird;
	}

	public void setProgressThird(float progressThird) {
		this.progressThird = progressThird;
	}

	public String getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(String totalMoney) {
		this.totalMoney = totalMoney;
	}
	
}
