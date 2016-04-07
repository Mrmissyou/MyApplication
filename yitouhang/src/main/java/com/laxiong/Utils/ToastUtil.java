package com.laxiong.Utils;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.laxiong.yitouhang.R;

/**
 * Created by xiejin on 2016/4/7.
 * Types ToastUtil.java
 */
public class ToastUtil {
    private static Toast toast;

    public static void customAlert(Context context, String msg) {
        if (toast == null) {
            toast = new Toast(context);
        }
        View view = LayoutInflater.from(context).inflate(R.layout.layout_toast, null);
        TextView tv_msg = (TextView) view.findViewById(R.id.tv_msg);
        tv_msg.setText(msg);
        toast.setView(view);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
    }
}
