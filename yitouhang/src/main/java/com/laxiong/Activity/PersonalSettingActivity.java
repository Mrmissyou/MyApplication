package com.laxiong.Activity;

import java.io.File;

import android.app.ActionBar.LayoutParams;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.laxiong.yitouhang.R;

public class PersonalSettingActivity extends BaseActivity implements OnClickListener{
	
	/****
	 * 个人设置
	 */
	private TextView backBtn;
	private RelativeLayout personIcon,nameSetting,addressSetting,trueName,phoneBind ;
	private FrameLayout mBack ;
	private ImageView mUseFace ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_personal_setting);
		initView();
		initData();
	}
	private void initData() {
		
		backBtn.setOnClickListener(this);
		personIcon.setOnClickListener(this);
		nameSetting.setOnClickListener(this);
		addressSetting.setOnClickListener(this);
		trueName.setOnClickListener(this);
		phoneBind.setOnClickListener(this);
		mBack.setOnClickListener(this);
		
	}
	private void initView() {
		
		backBtn = (TextView)findViewById(R.id.backbtn);
		personIcon =(RelativeLayout)findViewById(R.id.personIcon);
		nameSetting =(RelativeLayout)findViewById(R.id.nameSetting);
		addressSetting =(RelativeLayout)findViewById(R.id.addressSetting);
		trueName =(RelativeLayout)findViewById(R.id.true_name);
		phoneBind =(RelativeLayout)findViewById(R.id.phoneBind);
		mBack = (FrameLayout)findViewById(R.id.back_layout);
		mUseFace = (ImageView)findViewById(R.id.use_face);
		
		TextView mText = (TextView)findViewById(R.id.title);
		mText.setText("个人设置");
	}
	
	private PopupWindow mPopWindows ;  
	private View photoView ;
	private TextView camearBtn ;//拍照
	private TextView galleryBtn ;// 相册
	
	/* 头像名称 */
	private static final String PHOTO_FILE_NAME = "temp_photo.jpg";
	
	private static final int PHOTO_REQUEST_CAMERA = 1;// 拍照
	private static final int PHOTO_REQUEST_GALLERY = 2;// 从相册中选择
	private static final int PHOTO_REQUEST_CUT = 3;// 结果
	private File tempFile;
	private Bitmap bitmap;
	
	private void showPhotoIconTpye(){
		
		photoView = LayoutInflater.from(this).inflate(R.layout.personicon_popwindow, null);
		camearBtn = (TextView)photoView.findViewById(R.id.takephoto);
		galleryBtn = (TextView)photoView.findViewById(R.id.picphoto);
		TextView concelBtn = (TextView)photoView.findViewById(R.id.concel);
		
		//拍照
		camearBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Toast.makeText(PersonalSettingActivity.this, "拍照", 3).show();
				cameraImages();
			}
		});
		// 相册
		galleryBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Toast.makeText(PersonalSettingActivity.this, "从相册中取", 3).show();
				galleryImages();
			}
		});
		
		concelBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if(mPopWindows!=null&&mPopWindows.isShowing()){
					mPopWindows.dismiss();
					mPopWindows = null ;
				}
			}
		});
		
		 mPopWindows = new PopupWindow(photoView,LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT,true);
		 mPopWindows.setTouchable(true);
		 mPopWindows.setOutsideTouchable(true);
		 // 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
	    // 我觉得这里是API的一个bug
		 mPopWindows.setBackgroundDrawable(getResources().getDrawable(R.drawable.kefu_bg)); //设置半透明
		 mPopWindows.showAtLocation(photoView, Gravity.BOTTOM, 0, 0);
	}
	// Camera
	private void cameraImages(){
		Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
		// 判断存储卡是否可以用，可用进行存储
		if(hasSdcard()){
			intent.putExtra(MediaStore.EXTRA_OUTPUT,
			Uri.fromFile(new File(Environment
					.getExternalStorageDirectory(), PHOTO_FILE_NAME)));
		}
		startActivityForResult(intent, PHOTO_REQUEST_CAMERA);		
	}
	
	// Gallery
	private void galleryImages(){
		// 激活系统图库，选择一张图片
		Intent intent = new Intent(Intent.ACTION_PICK);
		intent.setType("image/*");
		startActivityForResult(intent, PHOTO_REQUEST_GALLERY);
		
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		if(requestCode==PHOTO_REQUEST_GALLERY){  // 相册的
			if(data!=null){
				//获取图片的路径
				Uri uri = data.getData();
				cropImages(uri);
			}
		}else if(requestCode==PHOTO_REQUEST_CAMERA){  // 照相的
			if(hasSdcard()){
				tempFile = new File(Environment.getExternalStorageDirectory(),
						PHOTO_FILE_NAME);
				cropImages(Uri.fromFile(tempFile));
			}else{
				Toast.makeText(PersonalSettingActivity.this, "未找到存储卡，无法存储照片", 3).show();
			}
		}else if(requestCode == PHOTO_REQUEST_CUT){
			
			try{
				bitmap = data.getParcelableExtra("data");
				
				this.mUseFace.setImageBitmap(bitmap);
				/**
				 * 获取了图片开始加入缓存--LurCache
				 * 如果没有就加图片,有就清空缓存 再重新加  （主要就是只有一张截图的图片）
				 */
//				if(SaveImagesToNative.getInstance(this).getBitmapFromMemCache(key)==null){
//					SaveImagesToNative.getInstance(this).addBitmapToMemoryCache(key, bitmap);
//				}else{
//					SaveImagesToNative.getInstance(this).removeImageCache(key);
//					SaveImagesToNative.getInstance(this).addBitmapToMemoryCache(key, bitmap);
//				}
				// 如果加载好图片后,PopuoWindow还在就移除他
				if(mPopWindows!=null&&mPopWindows.isShowing()){
					mPopWindows.dismiss();
					mPopWindows = null ;
				}
				boolean delete = tempFile.delete();
				System.out.println("delete = " + delete);
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
	
	private void cropImages(Uri uri){
		// 裁剪图片意图
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(uri, "image/*");
		intent.putExtra("crop", "true");
		// 裁剪框的比例，1：1
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
		// 裁剪后输出图片的尺寸大小
		intent.putExtra("outputX", 250);
		intent.putExtra("outputY", 250);
		// 图片格式
		intent.putExtra("outputFormat", "JPEG");
		intent.putExtra("noFaceDetection", true);// 取消人脸识别
		intent.putExtra("return-data", true);// true:不返回uri，false：返回uri
		startActivityForResult(intent, PHOTO_REQUEST_CUT);		
		
	}
	
	private boolean hasSdcard() {
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			return true;
		} else {
			return false;
		}
	}
	
	// 上传图片(在工程upload_img里可仔细参考)
	private void loadupImages(){
			
			
			
	}
	
	/***
	 * 退出,显示的PopupWindows
	 */
	private PopupWindow backWindow ;
	private View backView ;
	private void showBackOutMethod(){
		
		backView = LayoutInflater.from(this).inflate(R.layout.backout_popwindow, null);
		TextView sureBtn = (TextView)backView.findViewById(R.id.back_sure);
		TextView concelBtn = (TextView)backView.findViewById(R.id.back_concel);
		
		sureBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Toast.makeText(PersonalSettingActivity.this, "退出登录", 3).show();
			}
		});
		
		concelBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if(backWindow!=null&&backWindow.isShowing()){
					backWindow.dismiss();
					backWindow = null ;
				}
			}
		});
		
		backWindow = new PopupWindow(backView, LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT,true);
		backWindow.setTouchable(true);
		backWindow.setOutsideTouchable(true);
		 // 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
	    // 我觉得这里是API的一个bug
		backWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.kefu_bg)); //设置半透明
		backWindow.showAtLocation(backView, Gravity.BOTTOM, 0, 0);
		
	}
	
	private View bindView ;
	private PopupWindow bindphoneWindows ;
	private void showBindphone(){
		
		bindView = LayoutInflater.from(this).inflate(R.layout.bindphone_popwindow, null);
		TextView bindphone = (TextView)bindView.findViewById(R.id.changebindphone);
		TextView concel = (TextView)bindView.findViewById(R.id.concel);
		
		bindphone.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				
				startActivity(new Intent(PersonalSettingActivity.this,
						ChangeBindPhoneActivity1.class));
				
				if(bindphoneWindows!=null&&bindphoneWindows.isShowing()){
					bindphoneWindows.dismiss();
					bindphoneWindows = null ;
				}
			}
		});
		
		concel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if(bindphoneWindows!=null&&bindphoneWindows.isShowing()){
					bindphoneWindows.dismiss();
					bindphoneWindows = null ;
				}
			}
		});
		
		bindphoneWindows = new PopupWindow(bindView, LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT,true);
		bindphoneWindows.setTouchable(true);
		bindphoneWindows.setOutsideTouchable(true);
		 // 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
	    // 我觉得这里是API的一个bug
		bindphoneWindows.setBackgroundDrawable(getResources().getDrawable(R.drawable.kefu_bg)); //设置半透明
		bindphoneWindows.showAtLocation(bindView, Gravity.BOTTOM, 0, 0);
		
	}
	@Override
	public void onClick(View v) {
		switch(v.getId()){
			case R.id.backbtn:				/**退出按钮**/
				showBackOutMethod();
				break;
			case R.id.personIcon:			/**头像设置,时显示的PopupWindow**/	
				showPhotoIconTpye();
				break;
			case R.id.addressSetting:   	/**地址设置**/
				startActivity(new Intent(PersonalSettingActivity.this,
						AddressSettingActivity.class));
				break;
			case R.id.true_name:			/**实名认证**/
				startActivity(new Intent(PersonalSettingActivity.this,
						TrueNameActivity1.class));
				break;
			case R.id.phoneBind:           /**绑手机的**/
				showBindphone();
				break;
			case R.id.nameSetting:  		/**设置昵称**/
				startActivity(new Intent(PersonalSettingActivity.this,
						NameSettingActivity.class));
				break;
				
			case R.id.back_layout:
				this.finish();
				break ;
		}
		
	}
	
	
	
}
