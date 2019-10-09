package com.df.smallwater.smallwater;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.df.smallwater.smallwater.adapter.gonggaoAdapter;
import com.df.smallwater.smallwater.api.Api;
import com.df.smallwater.smallwater.base.BaseActivity;
import com.df.smallwater.smallwater.baserx.RxSchedulers;
import com.df.smallwater.smallwater.baserx.RxSubscriber;
import com.df.smallwater.smallwater.bean.Result;
import com.df.smallwater.smallwater.bean.User;
import com.df.smallwater.smallwater.utils.PreferencesUtils;
import com.df.smallwater.smallwater.utils.ToastUtils;
import com.yalantis.phoenix.PullToRefreshView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CodeActivity extends BaseActivity {


    @Bind(R.id.et_code)
    EditText etCode;
    @Bind(R.id.linearLayout2)
    LinearLayout linearLayout2;
    @Bind(R.id.btn_code_sure)
    Button btnCodeSure;
    @Bind(R.id.tv_base_text)
    TextView tvBaseText;
    @Bind(R.id.iv_base_back)
    ImageView ivBaseBack;
    @Bind(R.id.relativeLayout2)
    RelativeLayout relativeLayout2;

    String username , password;
    TextView tvHint;
    @Bind(R.id.pull_to_refresh)
    PullToRefreshView pullToRefresh;
    @Bind(R.id.relativeLayout10)
    RelativeLayout relativeLayout10;

    gonggaoAdapter gonggaoAdapter ;

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            relativeLayout10.setVisibility(View.VISIBLE);
            etCode.setVisibility(View.GONE);
            btnCodeSure.setVisibility(View.GONE);
            gonggaoAdapter = new gonggaoAdapter(R.layout.item_word_list,null);
            View v = View.inflate(CodeActivity.this,R.layout.code_head,null);
            tvHint = v.findViewById(R.id.tv_hint);
            tvHint.setText("激活成功，请等待教师完成信息录入。");
            gonggaoAdapter.addHeaderView(v);



            LinearLayoutManager mManager = new LinearLayoutManager(CodeActivity.this, LinearLayoutManager.VERTICAL, false);
            rlvCode.setLayoutManager(mManager);
            rlvCode.setAdapter(gonggaoAdapter);

            pullToRefresh.setRefreshing(false);


        }
    };
    @Bind(R.id.rlv_code)
    RecyclerView rlvCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code);
        ButterKnife.bind(this);
        tvBaseText.setText("激活");
        Bundle extras = getIntent().getExtras();
        username = extras.getString("username");
        password = extras.getString("password");
        int flag = extras.getInt("flag");
        if (flag == 1) {
            relativeLayout10.setVisibility(View.VISIBLE);

            etCode.setVisibility(View.GONE);
            btnCodeSure.setVisibility(View.GONE);
            gonggaoAdapter = new gonggaoAdapter(R.layout.item_word_list,null);
            View v = View.inflate(CodeActivity.this,R.layout.code_head,null);
            tvHint = v.findViewById(R.id.tv_hint);
            tvHint.setText("激活成功，请等待教师完成信息录入。");
            gonggaoAdapter.addHeaderView(v);
            LinearLayoutManager mManager = new LinearLayoutManager(CodeActivity.this, LinearLayoutManager.VERTICAL, false);
            rlvCode.setLayoutManager(mManager);
            rlvCode.setAdapter(gonggaoAdapter);
        }


        pullToRefresh.setOnRefreshListener(new PullToRefreshView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                rlvCode.post(new Runnable() {
                    @Override
                    public void run() {



                        Api.getDefault().userlogin(username,1,"","86",password).compose(RxSchedulers.<User>io_main()).subscribe(new RxSubscriber<User>(CodeActivity.this, false) {

                            @Override
                            protected void _onNext(User user) {


                                String msg = user.getMsg();
                                int result = user.getResult();
                                User.ResultBeanBean resultBean = user.getResultBean();

                                if (result == 0) {
                                    if (resultBean.getBanjiid() == -1) {
                                        Message msg1 = Message.obtain();
                                        handler.sendMessage(msg1);
                                    } else {
                                        ToastUtils.showLongToast(CodeActivity.this, msg);
                                        PreferencesUtils.putString(CodeActivity.this, "username", resultBean.getUsername());
                                        PreferencesUtils.putString(CodeActivity.this, "password", resultBean.getPassword());
                                        PreferencesUtils.putString(CodeActivity.this, "birthday", resultBean.getBirthday());
                                        PreferencesUtils.putString(CodeActivity.this, "address", resultBean.getAddress());
                                        PreferencesUtils.putString(CodeActivity.this, "code", resultBean.getCode());
                                        PreferencesUtils.putString(CodeActivity.this, "mothername", resultBean.getMothername());
                                        PreferencesUtils.putString(CodeActivity.this, "phone", resultBean.getPhone());
                                        PreferencesUtils.putString(CodeActivity.this, "name", resultBean.getName());
                                        PreferencesUtils.putString(CodeActivity.this, "sex", resultBean.getSex());
                                        PreferencesUtils.putString(CodeActivity.this, "wechat", resultBean.getWechat());
                                        PreferencesUtils.putInt(CodeActivity.this, "banjiid", resultBean.getBanjiid());
                                        PreferencesUtils.putInt(CodeActivity.this, "youeryuanid", resultBean.getYoueryuanid());
                                        readyGoThenKill(MainActivity.class,null);

                                    }
                                } else {
                                    ToastUtils.showLongToast(CodeActivity.this, msg);
                                }



                                pullToRefresh.setRefreshing(false);


                            }

                            @Override
                            protected void _onError(String message) {

                            }
                        });


                    }
                });
            }
        });

    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {


        if (keyCode == KeyEvent.KEYCODE_BACK) {
            readyGoThenKill(LoginActivity.class, null);

        }
        return true;
    }

    @OnClick({R.id.btn_code_sure, R.id.iv_base_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_code_sure:
                if ((etCode.getText().toString() + "").length() > 4) {
                    Api.getDefault().usercode(username, etCode.getText().toString() + "").compose(RxSchedulers.<Result>io_main()).subscribe(new RxSubscriber<Result>(CodeActivity.this, false) {
                        @Override
                        protected void _onNext(Result result) {
                            if (result.getResult() == 0) {

                                Message msg = Message.obtain();
                                handler.sendMessage(msg);


                            } else if (result.getResult() == 1) {


                                readyGoThenKill(LoginActivity.class, null);


                            }
                            showLongToast(result.getMsg());


                        }

                        @Override
                        protected void _onError(String message) {

                        }
                    });
                } else {
                    showLongToast("请检查验证码正确性");
                }


                break;
            case R.id.iv_base_back:
                this.finish();
                break;
        }
    }
}
