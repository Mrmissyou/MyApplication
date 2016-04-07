package com.laxiong.Basic;

import android.app.Activity;
import android.view.View;

/**
 * Created by win7 on 2016/4/6.
 * Types BackListener.java
 */
public class BackListener implements View.OnClickListener {
    private Activity activity;

    public BackListener(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onClick(View v) {
        this.activity.finish();
    }
}
