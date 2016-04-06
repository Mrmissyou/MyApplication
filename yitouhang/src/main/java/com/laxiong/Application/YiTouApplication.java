package com.laxiong.Application;


import java.io.File;
import java.lang.reflect.Field;

import com.laxiong.Common.Settings;

import android.app.Application;
import android.os.Environment;

public class YiTouApplication extends Application{
	
	private static YiTouApplication instance = null ;
	/****
	 * 实现多种功能的开机自启的功能
	 */
	@Override
	public void onCreate() {
		super.onCreate();
		init();
		instance = this ;
	}
	
	/**程序启动时自动获取的数据**/
	private void init(){
		
		// 获得屏幕高度（像素）
		Settings.DISPLAY_HEIGHT = getResources().getDisplayMetrics().heightPixels;
		// 获得屏幕宽度（像素）
		Settings.DISPLAY_WIDTH = getResources().getDisplayMetrics().widthPixels;
		// 获得系统状态栏高度（像素）
		Settings.STATUS_BAR_HEIGHT = getStatusBarHeight();
		// 获得屏幕高度比例
		Settings.RATIO_HEIGHT = Settings.DISPLAY_HEIGHT / 1280.0f;
		// 获得屏幕宽度比例
	    Settings.RATIO_WIDTH= Settings.DISPLAY_WIDTH / 760.0f;
		
	    String parentPath = null;
		// 存在SDCARD的时候，路径设置到SDCARD
		if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
			parentPath = Environment.getExternalStorageDirectory().getPath() + File.separator + getPackageName();
			// 不存在SDCARD的时候，路径设置到ROM
		} else {
			parentPath = Environment.getDataDirectory().getPath() + "/data/" + getPackageName();
		}
		
		// 临时文件路径设置
		Settings.TEMP_PATH = parentPath + "/tmp";
		// 图片缓存路径设置
		Settings.PIC_PATH = parentPath + "/pic";
		// 更新APK路径设置
		Settings.APK_SAVE = parentPath + "/upd";		
		
		//TODO 创建各自的目录
		
	    
	    
	}
	
	/**
	 * 本身实例
	 */
	public static YiTouApplication getInstance() {
		return instance;
	}
	
	/**
	 * 获得系统状态栏高度
	 * @return 系统状态栏高度（像素）
	 */
	private int getStatusBarHeight() {
		try {
			Class<?> cls = Class.forName("com.android.internal.R$dimen");
			Object obj = cls.newInstance();
			Field field = cls.getField("status_bar_height");
			int x = Integer.parseInt(field.get(obj).toString());
			return getResources().getDimensionPixelSize(x);
		} catch (Exception e) {
		}
		return 0;
	}
	
	
}
