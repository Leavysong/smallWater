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

import com.df.smallwater.smallwater.api.Api;
import com.df.smallwater.smallwater.base.BaseActivity;
import com.df.smallwater.smallwater.baserx.RxSchedulers;
import com.df.smallwater.smallwater.baserx.RxSubscriber;
import com.df.smallwater.smallwater.bean.Result;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChangePwdActivity extends BaseActivity {

    @Bind(R.id.et_password)
    EditText etPassword;
    @Bind(R.id.btn_change_sure)
    Button btnChangeSure;
    @Bind(R.id.linearLayout2)
    LinearLayout linearLayout2;
    @Bind(R.id.tv_base_text)
    TextView tvBaseText;
    @Bind(R.id.iv_base_back)
    ImageView ivBaseBack;
    @Bind(R.id.relativeLayout2)
    RelativeLayout relativeLayout2;
    private int change_type = -1;
    private String username = "";
    /**
     * 0更改密码1忘记密码2注册密码
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pwd);
        ButterKnife.bind(this);
        SetStatusBarColor();
        Bundle extras = getIntent().getExtras();
        change_type = extras.getInt("change_type");
        username = extras.getString("username");
        if(change_type==0){
            tvBaseText.setText("更改密码");

        }

        if(change_type==1){
            tvBaseText.setText("输入密码");
        }

        if(change_type==2){
            tvBaseText.setText("输入密码");
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {

            if (change_type == 0) {
                this.finish();
            } else {
                readyGoThenKill(LoginActivity.class, null);
            }

        }
        return true;
    }

    @OnClick({R.id.btn_change_sure, R.id.iv_base_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_change_sure:

                if(etPassword.getText().toString().length()>=6) {

                    if (change_type == 0) {
                        Api.getDefault().changepwd(username, etPassword.getText().toString() + "").
                                compose(RxSchedulers.io_main()).subscribe(new RxSubscriber(this, false) {
                            @Override
                            protected void _onNext(Object o) {
                                Result Result = (com.df.smallwater.smallwater.bean.Result) o;
                                int rsl = Result.getResult();
                                String msg = Result.getMsg();
                                if (rsl == 0) {
                                    readyGoThenKill(LoginActivity.class, null);
                                    showLongToast(msg);
                                } else {
                                    showLongToast(msg);
                                }
                            }

                            @Override
                            protected void _onError(String message) {

                            }
                        });
                    }


                    if (change_type == 1) {
                        Api.getDefault().forgetpwd(username, etPassword.getText().toString() + "").
                                compose(RxSchedulers.io_main()).subscribe(new RxSubscriber(this, false) {
                            @Override
                            protected void _onNext(Object o) {
                                Result Result = (com.df.smallwater.smallwater.bean.Result) o;
                                int rsl = Result.getResult();
                                String msg = Result.getMsg();
                                if (rsl == 0) {
                                    readyGoThenKill(LoginActivity.class, null);
                                    showLongToast(msg);
                                } else {
                                    showLongToast(msg);
                                }

                            }

                            @Override
                            protected void _onError(String message) {

                            }
                        });
                    }


                    if (change_type == 2) {
                        Api.getDefault().reguser(username, etPassword.getText().toString() + "").
                                compose(RxSchedulers.io_main()).subscribe(new RxSubscriber(this, false) {
                            @Override
                            protected void _onNext(Object o) {
                                Result Result = (com.df.smallwater.smallwater.bean.Result) o;
                                int rsl = Result.getResult();
                                String msg = Result.getMsg();
                                if (rsl == 0) {
                                    readyGoThenKill(LoginActivity.class, null);
                                    showLongToast(msg);
                                } else {
                                    showLongToast(msg);
                                }

                            }

                            @Override
                            protected void _onError(String message) {

                            }
                        });
                    }


                }else{
                    showLongToast("密码不能少于6位");
                }



                break;
            case R.id.iv_base_back:
                this.finish();
                break;
        }
    }
}
