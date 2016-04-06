package com.laxiong.Adapter;

import java.util.ArrayList;


public class Bean {
	/****
	 * 解析获取的数据可以暂时的加载在此
	 */
	public static final int ITEM = 0;
	public static final int SECTION = 1;

	public final int type;
	public final String text;
	public final String imgsUrl ;   //这是图片的URL

	public int sectionPosition;
	public int listPosition;

	public int getSectionPosition() {
		return sectionPosition;
	}

	public void setSectionPosition(int sectionPosition) {
		this.sectionPosition = sectionPosition;
	}

	public int getListPosition() {
		return listPosition;
	}

	public void setListPosition(int listPosition) {
		this.listPosition = listPosition;
	}

	public Bean(int type, String text,String imgsUrl) {
		this.type = type;
		this.text = text;
		this.imgsUrl = imgsUrl ;
	}


	public Bean(int type, String text,String imgsUrl, int sectionPosition, int listPosition) {
		super();
		this.type = type;
		this.text = text;
		this.imgsUrl = imgsUrl ;
		this.sectionPosition = sectionPosition;
		this.listPosition = listPosition;
	}

	@Override public String toString() {
		return text;
	}
	/***
	 * 区分是时息通的  还是固息宝的  看看他们的参数类型
	 * 时息通：time
	 * 固息宝：gu
	 * 余额宝：last
	 */
	public static ArrayList<Bean> getData(){
		ArrayList<Bean>  list=new ArrayList<Bean>();
		list.add(new Bean(SECTION, "固息宝"," "));
		list.add(new Bean(ITEM, "2","gu"));
		list.add(new Bean(ITEM, "7","gu"));
		list.add(new Bean(ITEM, "7","gu"));
		list.add(new Bean(ITEM, "6","gu"));
		list.add(new Bean(ITEM, "6.6","gu"));
		list.add(new Bean(ITEM, "8.6","gu"));
		list.add(new Bean(ITEM, "9.9","gu"));
		list.add(new Bean(ITEM, "11.3","gu"));
		list.add(new Bean(ITEM, "12.4","gu"));
		
		list.add(new Bean(SECTION, "时息通"," "));
		list.add(new Bean(ITEM, "6.5","time"));
		list.add(new Bean(ITEM, "4.3","time"));
		list.add(new Bean(ITEM, "8.9","time"));
		list.add(new Bean(ITEM, "6.6","time"));
		list.add(new Bean(ITEM, "8.6","time"));
		list.add(new Bean(ITEM, "9.9","time"));
		list.add(new Bean(ITEM, "11.3","time"));
		list.add(new Bean(ITEM, "12.4","time"));
		
		list.add(new Bean(SECTION, "余额宝"," "));
		list.add(new Bean(ITEM, "8.8","last"));
		list.add(new Bean(ITEM, "6.6","last"));
		list.add(new Bean(ITEM, "8.6","last"));
		list.add(new Bean(ITEM, "9.9","last"));
		list.add(new Bean(ITEM, "11.3","last"));
		list.add(new Bean(ITEM, "12.4","last"));
		
		
		return list;
	}
}
