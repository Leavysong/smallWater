package com.df.smallwater.smallwater;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.df.smallwater.smallwater.base.BaseActivity;
import com.df.smallwater.smallwater.utils.PhoneFormatCheckUtils;
import com.df.smallwater.smallwater.utils.PreferencesUtils;
import com.df.smallwater.smallwater.view.CountDownTextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

public class ForgetActivity extends BaseActivity {

    @Bind(R.id.et_username)
    EditText etUsername;
    @Bind(R.id.et_code)
    EditText etCode;
    @Bind(R.id.tv_code_time)
    CountDownTextView tvCodeTime;
    @Bind(R.id.btn_submit)
    Button btnSubmit;
    @Bind(R.id.linearLayout2)
    LinearLayout linearLayout2;
    @Bind(R.id.linearLayout3)
    LinearLayout linearLayout3;
    @Bind(R.id.tv_base_text)
    TextView tvBaseText;
    @Bind(R.id.iv_base_back)
    ImageView ivBaseBack;
    @Bind(R.id.relativeLayout2)
    RelativeLayout relativeLayout2;

    //0更改密码1忘记密码2注册密码
    private int change_type = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);
        ButterKnife.bind(this);
        SetStatusBarColor();
        tvBaseText.setText("更改密码");
        tvCodeTime.setCountDownMillis(60000);
        tvCodeTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((etUsername.getText().toString()+"").length()>6&& PhoneFormatCheckUtils.isPhoneLegal(etUsername.getText().toString()+"")){

                    // 触发操作
                    SMSSDK.getVerificationCode("86", etUsername.getText().toString()+"");
                    tvCodeTime.start();
                }else{
                    showLongToast("请填写正确手机号。");
                }

            }
        });

        Bundle extras = getIntent().getExtras();
        change_type = extras.getInt("change_type");
        if(change_type == 0) {
            etUsername.setText(PreferencesUtils.getString(this,"username"));
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if(change_type == 0){
                this.finish();
            }else{
                readyGoThenKill(LoginActivity.class, null);
            }
        }
        return true;
    }

    @OnClick({ R.id.btn_submit, R.id.iv_base_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.btn_submit:


                if(change_type == 0){


                    if((etCode.getText().toString()+"").length()>=4&& PhoneFormatCheckUtils.isPhoneLegal(etUsername.getText().toString()+"")){


                        // 注册一个事件回调，用于处理提交验证码操作的结果
                        SMSSDK.registerEventHandler(new EventHandler() {
                            public void afterEvent(int event, int result, Object data) {
                                if (result == SMSSDK.RESULT_COMPLETE) {
                                    // TODO 处理验证成功的结果
                                    Bundle bundle = new Bundle();
                                    bundle.putInt("change_type",change_type);
                                    bundle.putString("username",etUsername.getText().toString()+"");
                                    readyGoThenKill(ChangePwdActivity.class,bundle);
                                } else{
                                    // TODO 处理错误的结果
                                    showLongToast("验证错误，请重新验证。");
                                }

                            }
                        });
                        // 触发操作
                        SMSSDK.submitVerificationCode("86", etUsername.getText().toString()+"", etCode.getText().toString());
                    }
                    else{
                        showLongToast("请填写正确手机号。");
                    }



                }else {
                    Bundle bundle = new Bundle();
                    bundle.putInt("change_type",2);
                    readyGoThenKill(LoginActivity.class,bundle);
                }


                break;
            case R.id.iv_base_back:
                this.finish();
                break;
        }
    }
}
