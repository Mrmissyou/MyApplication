package com.laxiong.Utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.view.WindowManager;

/**
 * Created by xiejin on 2016/4/7.
 * Types DialogUtils.java
 */
public class DialogUtils {
    public static void  bgalpha(Activity context, float alpha) {
        WindowManager.LayoutParams params = context.getWindow().getAttributes();
        params.alpha = alpha;
        context.getWindow().setAttributes(params);
    }
}
