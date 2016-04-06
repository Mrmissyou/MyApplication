package com.laxiong.Common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.laxiong.yitouhang.R;

import android.widget.ImageView;

public class Common {

	public final static String sharedPrefName = "YITOUHANG";
	static boolean isChecked = false ;
	
	
	
	// 阅读协议的
	public static void isCheck(ImageView iv ){
		
		if(isChecked){ // 是阅读的
			iv.setImageResource(R.drawable.img_read);
			isChecked = false ;
		}else{  // 没有阅读
			iv.setImageResource(R.drawable.img_no_read);
			isChecked = true ;
		}
		
	}

	// 验证手机号码
	public static boolean isMobileNO(String mobiles){
		
		Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");  
		Matcher m = p.matcher(mobiles);
		return m.matches();
		
	}
	// 输入框的内容的非空判断
	public static boolean inputContentNotNull(String str){
		
		if(str!=null&&str.length()!=0&&!str.equals("")){
			return true ;
		}else{
			return false ;
		}
	}
	
	// 判断输入的密码是不是  至少是6位的
	public static boolean inputPswdCount(String pswd){

		Pattern pp = Pattern.compile("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,24}$");
		Matcher m = pp.matcher(pswd);
		return m.matches();
	}
	
	
}
