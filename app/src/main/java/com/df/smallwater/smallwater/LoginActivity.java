package com.df.smallwater.smallwater;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.df.smallwater.smallwater.api.Api;
import com.df.smallwater.smallwater.base.BaseActivity;
import com.df.smallwater.smallwater.baserx.RxSchedulers;
import com.df.smallwater.smallwater.baserx.RxSubscriber;
import com.df.smallwater.smallwater.bean.Gonggao;
import com.df.smallwater.smallwater.bean.User;
import com.df.smallwater.smallwater.bean.Xinwen;
import com.df.smallwater.smallwater.bean.Youeryuan;
import com.df.smallwater.smallwater.utils.PhoneFormatCheckUtils;
import com.df.smallwater.smallwater.utils.PreferencesUtils;
import com.df.smallwater.smallwater.utils.ToastUtils;
import com.df.smallwater.smallwater.utils.loginUtil;
import com.df.smallwater.smallwater.view.CountDownTextView;
import com.google.gson.Gson;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.smssdk.SMSSDK;

public class LoginActivity extends BaseActivity {

    @Bind(R.id.btn_login)
    Button btnLogin;
    @Bind(R.id.btn_reg)
    Button btnReg;

    @Bind(R.id.et_username_code)
    EditText etUsernameCode;
    @Bind(R.id.et_password_code)
    EditText etPasswordCode;

    @Bind(R.id.tv_code_time)
    CountDownTextView tvCodeTime;
    @Bind(R.id.layout_code)
    RelativeLayout layoutCode;

    @Bind(R.id.et_username)
    EditText etUsername;
    @Bind(R.id.et_password)
    EditText etPassword;

    @Bind(R.id.layout_username)
    RelativeLayout layoutUsername;
    @Bind(R.id.login_username)
    RelativeLayout loginUsername;
    @Bind(R.id.login_code)
    RelativeLayout loginCode;
    @Bind(R.id.login_forget)
    RelativeLayout loginForget;

    int type = 1;

    boolean isExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        layoutUsername.setVisibility(View.VISIBLE);
        layoutCode.setVisibility(View.GONE);



        SetStatusBarColor();
        tvCodeTime.setCountDownMillis(60000);
        tvCodeTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Bundle bundle = new Bundle();
                String s = etUsernameCode.getText().toString()+"";
                                if(s.length()>6&& PhoneFormatCheckUtils.isPhoneLegal(s)){
                                    tvCodeTime.start();
                // 触发操作
                SMSSDK.getVerificationCode("86", etUsernameCode.getText().toString()+"");

                }else{
                    showLongToast("请填写正确手机号。");
                }



            }
        });

    }

    @OnClick({R.id.btn_login, R.id.btn_reg, R.id.login_username, R.id.login_code, R.id.login_forget})
    public void onViewClicked(View view) {
        final Bundle bundle = new Bundle();
        switch (view.getId()) {

            case R.id.btn_login:
                String cusername = etUsernameCode.getText().toString()+"";
                String cpassword = etPasswordCode.getText().toString()+"";
                String password = etPassword.getText().toString()+"";
                String username = etUsername.getText().toString()+"";
                if(loginUtil.login(cusername, "1111111", cpassword)&& PhoneFormatCheckUtils.isPhoneLegal(cusername)){
                    if (type == 0) {
                        Api.getDefault().userlogin(cusername, type, cpassword, "86", password).compose(RxSchedulers.io_main()).subscribe(new RxSubscriber(this, false) {


                            @Override
                            protected void _onNext(Object o) {
                                User user = (User) o;
                                String msg = user.getMsg();
                                int result = user.getResult();
                                User.ResultBeanBean resultBean = user.getResultBean();

                                if (result == 200) {
                                    if (resultBean.getCode().equals("0")) {
                                        bundle.putString("username", etUsernameCode.getText().toString()+"");
                                showLongToast("请先激活账号");
                                        readyGoThenKill(CodeActivity.class,bundle);
                                    } else {
                                        if (resultBean.getBanjiid() == -1) {
                                            bundle.putInt("flag", 1);
                                            bundle.putString("username", resultBean.getUsername());
                                            bundle.putString("password", resultBean.getPassword());
                                            readyGoThenKill(CodeActivity.class,bundle);
                                        } else {
                                            ToastUtils.showLongToast(LoginActivity.this, msg);
                                            PreferencesUtils.putString(LoginActivity.this, "username", resultBean.getUsername());
                                            PreferencesUtils.putString(LoginActivity.this, "password", resultBean.getPassword());
                                            PreferencesUtils.putString(LoginActivity.this, "birthday", resultBean.getBirthday());
                                            PreferencesUtils.putString(LoginActivity.this, "address", resultBean.getAddress());
                                            PreferencesUtils.putString(LoginActivity.this, "code", resultBean.getCode());
                                            PreferencesUtils.putString(LoginActivity.this, "mothername", resultBean.getMothername());
                                            PreferencesUtils.putString(LoginActivity.this, "phone", resultBean.getPhone());
                                            PreferencesUtils.putString(LoginActivity.this, "name", resultBean.getName());
                                            PreferencesUtils.putString(LoginActivity.this, "sex", resultBean.getSex());
                                            PreferencesUtils.putString(LoginActivity.this, "wechat", resultBean.getWechat());
                                            PreferencesUtils.putInt(LoginActivity.this, "banjiid", resultBean.getBanjiid());
                                            PreferencesUtils.putInt(LoginActivity.this, "youeryuanid", resultBean.getYoueryuanid());


                                            Api.getDefault().getxinwen(PreferencesUtils.getInt(LoginActivity.this, "youeryuanid"),1).compose(RxSchedulers.<Xinwen>io_main()).subscribe(new RxSubscriber<Xinwen>(LoginActivity.this, false) {
                                                @Override
                                                protected void _onNext(Xinwen o) {
                                                    PreferencesUtils.putInt(LoginActivity.this, "xallcount", o.getAllcount());
                                                    PreferencesUtils.putInt(LoginActivity.this, "xpage", o.getPage());
                                                    PreferencesUtils.putInt(LoginActivity.this, "xpagenum", o.getPagenum());
                                                    List<Xinwen.XinwenlistBean> l = o.getXinwenlist();
                                                    Gson gson = new Gson();
                                                    String json = gson.toJson(l);
                                                    PreferencesUtils.putString(LoginActivity.this, "xlist", json);
                                                }

                                                @Override
                                                protected void _onError(String message) {

                                                }
                                            });


                                            Api.getDefault().getgonggao(PreferencesUtils.getInt(LoginActivity.this, "youeryuanid"),1).compose(RxSchedulers.<Gonggao>io_main()).subscribe(new RxSubscriber<Gonggao>(LoginActivity.this, false) {
                                                @Override
                                                protected void _onNext(Gonggao o) {
                                                    PreferencesUtils.putInt(LoginActivity.this, "gallcount", o.getAllcount());
                                                    PreferencesUtils.putInt(LoginActivity.this, "gpage", o.getPage());
                                                    PreferencesUtils.putInt(LoginActivity.this, "gpagenum", o.getPagenum());
                                                    List<Gonggao.GonggaolistBean> l = o.getGonggaolist();
                                                    Gson gson = new Gson();
                                                    String json = gson.toJson(l);
                                                    PreferencesUtils.putString(LoginActivity.this, "glist", json);
                                                }

                                                @Override
                                                protected void _onError(String message) {

                                                }
                                            });

                                            Api.getDefault().getyoueryuan(PreferencesUtils.getInt(LoginActivity.this, "youeryuanid")).compose(RxSchedulers.<Youeryuan>io_main()).subscribe(new RxSubscriber<Youeryuan>(LoginActivity.this, false) {
                                                @Override
                                                protected void _onNext(Youeryuan o) {
                                                    Youeryuan  youeryuan = (Youeryuan) o;
                                                    Youeryuan.YoueryuanBean y = youeryuan.getYoueryuan();
                                                    PreferencesUtils.putString(LoginActivity.this, "yname", y.getName());
                                                    PreferencesUtils.putString(LoginActivity.this, "yaddress", y.getAddress());
                                                    PreferencesUtils.putString(LoginActivity.this, "ycontent", y.getContent());
                                                    PreferencesUtils.putString(LoginActivity.this, "ypersonal", y.getPersonal());
                                                    PreferencesUtils.putString(LoginActivity.this, "yphone", y.getPhone());
                                                    List<Youeryuan.YoueryuanpicBean> l = youeryuan.getYoueryuanpic();
                                                    Gson gson = new Gson();
                                                    String json = gson.toJson(l);
                                                    PreferencesUtils.putString(LoginActivity.this, "piclist", json);
                                                    readyGoThenKill(MainActivity.class,null);
                                                }

                                                @Override
                                                protected void _onError(String message) {

                                                }
                                            });

                                        }

                                    }
                                } else {
                                    ToastUtils.showLongToast(LoginActivity.this, msg);
                                }


                            }

                            @Override
                            protected void _onError(String message) {

                            }
                        });
                    }else{
                        showLongToast("前填写全部正确信息。");
                    }
                    } else {
                    if(loginUtil.login(username,password, "11111111")&& PhoneFormatCheckUtils.isPhoneLegal(username)){
                        Api.getDefault().userlogin(username, type, cpassword,  "86", password).compose(RxSchedulers.io_main()).subscribe(new RxSubscriber(this, false) {


                            @Override
                            protected void _onNext(Object o) {
                                User user = (User) o;
                                String msg = user.getMsg();
                                int result = user.getResult();
                                User.ResultBeanBean resultBean = user.getResultBean();

                                if (result == 0) {
                                    if (resultBean.getCode().equals("0")) {
                                        showLongToast("请先激活账号");
                                        bundle.putString("username", etUsername.getText().toString()+"");
                                        readyGoThenKill(CodeActivity.class,bundle);
                                    } else {
                                        if (resultBean.getBanjiid() == -1) {
                                            bundle.putString("username", resultBean.getUsername());
                                            bundle.putString("password", resultBean.getPassword());
                                            bundle.putInt("flag", 1);
                                            readyGoThenKill(CodeActivity.class,bundle);
                                        } else {
                                            ToastUtils.showLongToast(LoginActivity.this, msg);
                                            PreferencesUtils.putString(LoginActivity.this, "username", resultBean.getUsername());
                                            PreferencesUtils.putString(LoginActivity.this, "password", resultBean.getPassword());
                                            PreferencesUtils.putString(LoginActivity.this, "birthday", resultBean.getBirthday());
                                            PreferencesUtils.putString(LoginActivity.this, "address", resultBean.getAddress());
                                            PreferencesUtils.putString(LoginActivity.this, "code", resultBean.getCode());
                                            PreferencesUtils.putString(LoginActivity.this, "mothername", resultBean.getMothername());
                                            PreferencesUtils.putString(LoginActivity.this, "phone", resultBean.getPhone());
                                            PreferencesUtils.putString(LoginActivity.this, "name", resultBean.getName());
                                            PreferencesUtils.putString(LoginActivity.this, "sex", resultBean.getSex());
                                            PreferencesUtils.putString(LoginActivity.this, "wechat", resultBean.getWechat());
                                            PreferencesUtils.putInt(LoginActivity.this, "banjiid", resultBean.getBanjiid());
                                            PreferencesUtils.putInt(LoginActivity.this, "youeryuanid", resultBean.getYoueryuanid());
                                            Api.getDefault().getxinwen(PreferencesUtils.getInt(LoginActivity.this, "youeryuanid"),1).compose(RxSchedulers.<Xinwen>io_main()).subscribe(new RxSubscriber<Xinwen>(LoginActivity.this, false) {
                                                @Override
                                                protected void _onNext(Xinwen o) {
                                                    PreferencesUtils.putInt(LoginActivity.this, "xallcount", o.getAllcount());
                                                    PreferencesUtils.putInt(LoginActivity.this, "xpage", o.getPage());
                                                    PreferencesUtils.putInt(LoginActivity.this, "xpagenum", o.getPagenum());
                                                    List<Xinwen.XinwenlistBean> l = o.getXinwenlist();
                                                    Gson gson = new Gson();
                                                    String json = gson.toJson(l);
                                                    PreferencesUtils.putString(LoginActivity.this, "xlist", json);
                                                }

                                                @Override
                                                protected void _onError(String message) {

                                                }
                                            });


                                            Api.getDefault().getgonggao(PreferencesUtils.getInt(LoginActivity.this, "youeryuanid"),1).compose(RxSchedulers.<Gonggao>io_main()).subscribe(new RxSubscriber<Gonggao>(LoginActivity.this, false) {
                                                @Override
                                                protected void _onNext(Gonggao o) {
                                                    PreferencesUtils.putInt(LoginActivity.this, "gallcount", o.getAllcount());
                                                    PreferencesUtils.putInt(LoginActivity.this, "gpage", o.getPage());
                                                    PreferencesUtils.putInt(LoginActivity.this, "gpagenum", o.getPagenum());
                                                    List<Gonggao.GonggaolistBean> l = o.getGonggaolist();
                                                    Gson gson = new Gson();
                                                    String json = gson.toJson(l);
                                                    PreferencesUtils.putString(LoginActivity.this, "glist", json);
                                                }

                                                @Override
                                                protected void _onError(String message) {

                                                }
                                            });

                                            Api.getDefault().getyoueryuan(PreferencesUtils.getInt(LoginActivity.this, "youeryuanid")).compose(RxSchedulers.<Youeryuan>io_main()).subscribe(new RxSubscriber<Youeryuan>(LoginActivity.this, false) {
                                                @Override
                                                protected void _onNext(Youeryuan o) {
                                                    Youeryuan  youeryuan = (Youeryuan) o;
                                                    Youeryuan.YoueryuanBean y = youeryuan.getYoueryuan();
                                                    PreferencesUtils.putString(LoginActivity.this, "yname", y.getName());
                                                    PreferencesUtils.putString(LoginActivity.this, "yaddress", y.getAddress());
                                                    PreferencesUtils.putString(LoginActivity.this, "ycontent", y.getContent());
                                                    PreferencesUtils.putString(LoginActivity.this, "ypersonal", y.getPersonal());
                                                    PreferencesUtils.putString(LoginActivity.this, "yphone", y.getPhone());
                                                    List<Youeryuan.YoueryuanpicBean> l = youeryuan.getYoueryuanpic();
                                                    Gson gson = new Gson();
                                                    String json = gson.toJson(l);
                                                    PreferencesUtils.putString(LoginActivity.this, "piclist", json);
                                                    readyGoThenKill(MainActivity.class,null);
                                                }

                                                @Override
                                                protected void _onError(String message) {

                                                }
                                            });

                                        }

                                    }
                                } else {
                                    ToastUtils.showLongToast(LoginActivity.this, msg);
                                }


                            }

                            @Override
                            protected void _onError(String message) {

                            }
                        });
                }else{
                showLongToast("请填写全部正确信息。");
            }
                    }

                break;
            case R.id.btn_reg:
                readyGoThenKill(RegActivity.class, bundle);
                break;




            case R.id.login_username:
                layoutUsername.setVisibility(View.VISIBLE);
                layoutCode.setVisibility(View.GONE);
                type = 1 ;
                break;
            case R.id.login_code:
                layoutUsername.setVisibility(View.GONE);
                layoutCode.setVisibility(View.VISIBLE);
                type = 0 ;
                break;
            case R.id.login_forget:
                bundle.putInt("change_type", 1);
                readyGoThenKill(ForgetActivity.class, bundle);
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {


            exitByDoubleClick();
        }
        return true;
    }


    private void exitByDoubleClick() {
        Timer tExit = null;
        if (!isExit) {
            isExit = true;
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            tExit = new Timer();
            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false;//取消退出
                }
            }, 2000);// 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务
        } else {
            finish();
            System.exit(0);
        }
    }


}
