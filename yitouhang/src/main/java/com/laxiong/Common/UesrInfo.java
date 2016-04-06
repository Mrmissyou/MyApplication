package com.laxiong.Common;

public class UesrInfo {
	/****
	 * 用户的信息UserInfo
	 */
	private static UesrInfo instance ;
	public UesrInfo(){}
	public synchronized static UesrInfo getInstance(){
		if(instance==null){
			instance = new UesrInfo();
		}
		return instance ;
	}
	/****
	 * 所需的用户信息   等等...
	 */
	private String realname ;
	private String named ;
	private String place ;
	private String name ;
	private String address ;
	private String phones ;
	private String phone ;
	

}
