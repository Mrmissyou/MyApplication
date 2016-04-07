package com.laxiong.Activity;

import android.app.ActionBar;
import android.app.Dialog;
import android.content.Context;
import android.net.nsd.NsdManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.laxiong.Basic.BackListener;
import com.laxiong.Utils.DialogUtils;
import com.laxiong.Utils.ToastUtil;
import com.laxiong.View.CommonActionBar;
import com.laxiong.View.PayPop;
import com.laxiong.yitouhang.R;

/**
 * @params tv_ecnum - +之间的数字
 * @params tv_value 1元 兑换的红包
 * @params tv_total 所需要的壹币数量
 */
public class ExChangeActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "ExChangeActivity";
    private int yinum = 100;//壹币的数量
    private int yuan = 1;//要兑换的人民币的数量
    private PayPop dialog;
    private TextView tv_exchange, tv_ecnum, tv_value, tv_total, tv_deduct, tv_plus, tv_jiesuannum;
    private CommonActionBar actionbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_ex_change);
        init();
    }

    private void init() {
        tv_ecnum = (TextView) findViewById(R.id.tv_ecnum);
        tv_total = (TextView) findViewById(R.id.tv_total);
        tv_exchange = (TextView) findViewById(R.id.tv_exchange);
        tv_value = (TextView) findViewById(R.id.tv_value);
        actionbar = (CommonActionBar) findViewById(R.id.actionbar);
        tv_deduct = (TextView) findViewById(R.id.tv_deduct);
        tv_plus = (TextView) findViewById(R.id.tv_plus);
        tv_jiesuannum = (TextView) findViewById(R.id.tv_jiesuannum);
        yuan = Integer.parseInt(tv_ecnum.getText().toString());
        initValue();
        initListener();
    }

    private void initValue() {
        boolean flag = yuan * 100 <= yinum;
        tv_value.setText(yuan + "元");
        tv_total.setText(yuan * 100 + "");
        tv_jiesuannum.setText(yuan * 100 + "");
        tv_exchange.setEnabled(flag ? true : false);
        tv_exchange.setBackgroundResource(flag ? R.drawable.shape_redbtn_border : R.drawable.shape_greybtn_border);
        tv_exchange.setText(flag ? R.string.exchange_btn_im : R.string.exchange_btn_noenum);
    }

    private void initListener() {
        actionbar.setBackListener(this);
        tv_exchange.setOnClickListener(this);
        tv_deduct.setOnClickListener(this);
        tv_plus.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_exchange:
                DialogUtils.bgalpha(ExChangeActivity.this, 0.3f);
                dialog = new PayPop(ExChangeActivity.this, yuan, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DialogUtils.bgalpha(ExChangeActivity.this, 1.0f);
                        ToastUtil.customAlert(ExChangeActivity.this,"兑换成功");
                        dialog.dismiss();
                    }
                });
                dialog.showAtLocation(v, Gravity.CENTER, 0, 0);
                break;
            case R.id.tv_deduct:
                tv_ecnum.setText((yuan > 1 ? (--yuan) : (yuan = 1)) + "");
                tv_deduct.setTextColor(getResources().getColor(yuan == 1 ? R.color.shen_grey : R.color.maintext_grey));
                initValue();
                break;
            case R.id.tv_plus:
                tv_ecnum.setText(++yuan + "");
                initValue();
                break;
        }
    }
}
