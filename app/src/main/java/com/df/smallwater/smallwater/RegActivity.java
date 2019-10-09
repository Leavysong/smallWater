package com.df.smallwater.smallwater;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.df.smallwater.smallwater.base.BaseActivity;
import com.df.smallwater.smallwater.utils.PhoneFormatCheckUtils;
import com.df.smallwater.smallwater.view.CountDownTextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

public class RegActivity extends BaseActivity {

    @Bind(R.id.et_username)
    EditText etUsername;
    @Bind(R.id.et_code)
    EditText etCode;
    @Bind(R.id.tv_code_time)
    CountDownTextView tvCodeTime;
    @Bind(R.id.btn_submit)
    Button btnSubmit;
    @Bind(R.id.tv_base_text)
    TextView tvBaseText;
    @Bind(R.id.iv_base_back)
    ImageView ivBaseBack;

    Message msg ;

    @SuppressLint("HandlerLeak")
    Handler h = new Handler(){
        @Override
        public void handleMessage(Message msg) {

            if(msg.arg1==4){
                showLongToast("验证失败请确认验证码正确性");
            }else{
                tvCodeTime.setCountDownMillis(60000);
                tvCodeTime.start();
            }

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        ButterKnife.bind(this);
        SetStatusBarColor();
        tvBaseText.setText("注册");

        tvCodeTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = etUsername.getText().toString()+"";
                if(s.length()>6&& PhoneFormatCheckUtils.isPhoneLegal(s)){


                    // 触发操作
                    SMSSDK.getVerificationCode("86", etUsername.getText().toString()+"");
                    Message msg = Message.obtain();
             h.sendMessage(msg);
                }else{
                    showLongToast("前填写正确手机号。");
                }

            }
        });

    }

    @OnClick({R.id.btn_submit, R.id.iv_base_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_submit:


if((etUsername.getText().toString()+"").length()>6&& PhoneFormatCheckUtils.isPhoneLegal(etUsername.getText().toString()+"")){

    if(etCode.getText().toString().length()>1){
// 注册一个事件回调，用于处理提交验证码操作的结果
        SMSSDK.registerEventHandler(new EventHandler() {
            public void afterEvent(int event, int result, Object data) {
                if (result == SMSSDK.RESULT_COMPLETE) {
                    // TODO 处理验证成功的结果
                    Bundle bundle = new Bundle();
                    bundle.putInt("change_type",2);
                    bundle.putString("username",etUsername.getText().toString()+"");
                    readyGoThenKill(ChangePwdActivity.class,bundle);
                } else{
                    // TODO 处理错误的结果


                    msg = Message.obtain();
                    msg.arg1 = 4 ;
                    h.sendMessage(msg);
                }

            }
        });
        // 触发操作
        SMSSDK.submitVerificationCode("86", etUsername.getText().toString()+"", etCode.getText().toString());
    }else{
        showLongToast("请确认验证码正确性");
    }


}
else{
    showLongToast("请填写正确手机号。");
}

                break;
            case R.id.iv_base_back:
                this.finish();
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
                readyGoThenKill(LoginActivity.class, null);


        }
        return true;
    }
}
