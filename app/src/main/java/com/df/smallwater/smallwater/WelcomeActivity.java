package com.df.smallwater.smallwater;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.df.smallwater.smallwater.api.Api;
import com.df.smallwater.smallwater.base.BaseActivity;
import com.df.smallwater.smallwater.baserx.RxSchedulers;
import com.df.smallwater.smallwater.baserx.RxSubscriber;
import com.df.smallwater.smallwater.bean.Gonggao;
import com.df.smallwater.smallwater.bean.User;
import com.df.smallwater.smallwater.bean.Xinwen;
import com.df.smallwater.smallwater.bean.Youeryuan;
import com.df.smallwater.smallwater.utils.PinYinUtils;
import com.df.smallwater.smallwater.utils.PreferencesUtils;
import com.df.smallwater.smallwater.utils.ToastUtils;
import com.df.smallwater.smallwater.utils.WordUtil;
import com.google.gson.Gson;

import java.util.List;

public class WelcomeActivity extends BaseActivity {


    private Message msg ;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            goLogin();
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        SetStatusBarColor();
        PinYinUtils p = PinYinUtils.getInstance();
        WordUtil w = WordUtil.getInstance();
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    Thread.sleep(1000);
                    msg = Message.obtain();
                    handler.sendMessage(msg);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void goLogin() {

        final Bundle bundle = new Bundle();
        final String username = PreferencesUtils.getString(this,"username");
        String password = PreferencesUtils.getString(this,"password");



        if(username!=null&&!username.equals("")){
            Api.getDefault().getyoueryuan(PreferencesUtils.getInt(WelcomeActivity.this, "youeryuanid")).compose(RxSchedulers.<Youeryuan>io_main()).subscribe(new RxSubscriber<Youeryuan>(this, false) {
                @Override
                protected void _onNext(Youeryuan youeryuan) {
                    Youeryuan.YoueryuanBean y = youeryuan.getYoueryuan();
                    PreferencesUtils.putString(WelcomeActivity.this, "yname", y.getName());
                    PreferencesUtils.putString(WelcomeActivity.this, "yaddress", y.getAddress());
                    PreferencesUtils.putString(WelcomeActivity.this, "ycontent", y.getContent());
                    PreferencesUtils.putString(WelcomeActivity.this, "ypersonal", y.getPersonal());
                    PreferencesUtils.putString(WelcomeActivity.this, "yphone", y.getPhone());
                    List<Youeryuan.YoueryuanpicBean> l = youeryuan.getYoueryuanpic();
                    Gson gson = new Gson();
                    String json = gson.toJson(l);
                    PreferencesUtils.putString(WelcomeActivity.this, "piclist", json);

                }

                @Override
                protected void _onError(String message) {

                }
            });


            Api.getDefault().getxinwen(PreferencesUtils.getInt(WelcomeActivity.this, "youeryuanid"),1).compose(RxSchedulers.<Xinwen>io_main()).subscribe(new RxSubscriber<Xinwen>(this, false) {
                @Override
                protected void _onNext(Xinwen o) {
                    PreferencesUtils.putInt(WelcomeActivity.this, "xallcount", o.getAllcount());
                    PreferencesUtils.putInt(WelcomeActivity.this, "xpage", o.getPage());
                    PreferencesUtils.putInt(WelcomeActivity.this, "xpagenum", o.getPagenum());
                    List<Xinwen.XinwenlistBean> l = o.getXinwenlist();
                    Gson gson = new Gson();
                    String json = gson.toJson(l);
                    PreferencesUtils.putString(WelcomeActivity.this, "xlist", json);
                }

                @Override
                protected void _onError(String message) {

                }
            });


            Api.getDefault().getgonggao(PreferencesUtils.getInt(WelcomeActivity.this, "youeryuanid"),1).compose(RxSchedulers.<Gonggao>io_main()).subscribe(new RxSubscriber<Gonggao>(this, false) {
                @Override
                protected void _onNext(Gonggao o) {
                    PreferencesUtils.putInt(WelcomeActivity.this, "gallcount", o.getAllcount());
                    PreferencesUtils.putInt(WelcomeActivity.this, "gpage", o.getPage());
                    PreferencesUtils.putInt(WelcomeActivity.this, "gpagenum", o.getPagenum());
                    List<Gonggao.GonggaolistBean> l = o.getGonggaolist();
                    Gson gson = new Gson();
                    String json = gson.toJson(l);
                    PreferencesUtils.putString(WelcomeActivity.this, "glist", json);
                }

                @Override
                protected void _onError(String message) {

                }
            });

            Api.getDefault().userlogin(username , 1, "",  "86", password).compose(RxSchedulers.io_main()).subscribe(new RxSubscriber(this, false) {


                @Override
                protected void _onNext(Object o) {
                    User user = (User) o;
                    String msg = user.getMsg();
                    int result = user.getResult();
                    User.ResultBeanBean resultBean = user.getResultBean();

                    if (result == 0) {
                        if (resultBean.getCode().equals("0")) {
                            bundle.putString("username", username);
                            showLongToast("请先激活账号");
                            readyGoThenKill(CodeActivity.class,bundle);
                        } else {
                            if (resultBean.getBanjiid() == -1) {
                                bundle.putInt("flag", 1);
                                bundle.putString("username", username);
                                bundle.putString("password", "password");
                                readyGo(CodeActivity.class,bundle);
                            } else {
                                ToastUtils.showLongToast(WelcomeActivity.this, msg);
                                PreferencesUtils.putString(WelcomeActivity.this, "username", resultBean.getUsername());
                                PreferencesUtils.putString(WelcomeActivity.this, "password", resultBean.getPassword());
                                PreferencesUtils.putString(WelcomeActivity.this, "birthday", resultBean.getBirthday());
                                PreferencesUtils.putString(WelcomeActivity.this, "address", resultBean.getAddress());
                                PreferencesUtils.putString(WelcomeActivity.this, "code", resultBean.getCode());
                                PreferencesUtils.putString(WelcomeActivity.this, "mothername", resultBean.getMothername());
                                PreferencesUtils.putString(WelcomeActivity.this, "phone", resultBean.getPhone());
                                PreferencesUtils.putString(WelcomeActivity.this, "name", resultBean.getName());
                                PreferencesUtils.putString(WelcomeActivity.this, "sex", resultBean.getSex());
                                PreferencesUtils.putString(WelcomeActivity.this, "wechat", resultBean.getWechat());
                                PreferencesUtils.putInt(WelcomeActivity.this, "banjiid", resultBean.getBanjiid());
                                PreferencesUtils.putInt(WelcomeActivity.this, "youeryuanid", resultBean.getYoueryuanid());



                                readyGoThenKill(MainActivity.class,null);



                            }

                        }
                    } else {

                        ToastUtils.showLongToast(WelcomeActivity.this, msg);
                    }


                }

                @Override
                protected void _onError(String message) {

                }
            });
        }else{
            readyGoThenKill(LoginActivity.class,null);
        }



    }



    public void initViewList(){

    }

}
