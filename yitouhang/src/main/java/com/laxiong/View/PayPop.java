package com.laxiong.View;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.laxiong.Utils.DialogUtils;
import com.laxiong.yitouhang.R;

/**
 * Created by xiejin on 2016/4/6.
 * Types PayPop.java
 */
public class PayPop extends PopupWindow {
    private View pop;

    public PayPop(final Activity context,int yuan,View.OnClickListener listener) {
        super(context);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_inputpass, null);
        TextView et_pass = (TextView) view.findViewById(R.id.et_pass);
        TextView tv_cancel = (TextView) view.findViewById(R.id.tv_cancel);
        TextView tv_confirm = (TextView) view.findViewById(R.id.tv_confirm);
        TextView tv_input_tip= (TextView) view.findViewById(R.id.tv_input_tip);
        TextView tv_input_num= (TextView) view.findViewById(R.id.tv_input_num);
        tv_input_tip.setText("从1T商城-兑换"+yuan+"元红包");
        tv_input_num.setText(yuan*100+"壹币");
        DisplayMetrics metric = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(metric);
        int width = Math.round(3 * (float) metric.widthPixels / 4);
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                DialogUtils.bgalpha(context, 1.0f);
            }
        });
        tv_confirm.setOnClickListener(listener);
        this.setContentView(view);
        this.setWidth(width);
        this.setHeight(height);
        this.setFocusable(true);
        this.setBackgroundDrawable(null);
        this.setOutsideTouchable(false);
    }
}
