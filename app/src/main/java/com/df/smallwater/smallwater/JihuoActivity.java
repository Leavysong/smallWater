package com.df.smallwater.smallwater;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.df.smallwater.smallwater.api.Api;
import com.df.smallwater.smallwater.base.BaseActivity;
import com.df.smallwater.smallwater.baserx.RxSchedulers;
import com.df.smallwater.smallwater.baserx.RxSubscriber;
import com.df.smallwater.smallwater.bean.Code;
import com.df.smallwater.smallwater.utils.PreferencesUtils;
import com.df.smallwater.smallwater.utils.TimeUtils;

import java.text.ParseException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class JihuoActivity extends BaseActivity {

    @Bind(R.id.tv_username)
    TextView tvUsername;
    @Bind(R.id.tv_starttime)
    TextView tvStarttime;
    @Bind(R.id.tv_finishtime)
    TextView tvFinishtime;
    @Bind(R.id.tv_address)
    TextView tvAddress;
    @Bind(R.id.tv_base_text)
    TextView tvBaseText;
    @Bind(R.id.iv_base_back)
    ImageView ivBaseBack;
    @Bind(R.id.relativeLayout2)
    RelativeLayout relativeLayout2;

   Code.CodeBean code ;


    Handler h = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            tvUsername.setText(code.getUsername());  ;
            tvStarttime.setText(code.getStime().substring(0,code.getStime().indexOf(" ")));;

            try {
                tvFinishtime.setText(TimeUtils.getdateaddyear(code.getStime()).substring(0,TimeUtils.getdateaddyear(code.getStime()).indexOf(" ")));
            } catch (ParseException e) {
                e.printStackTrace();
            };




            tvAddress.setText(PreferencesUtils.getString(JihuoActivity.this,"yname"));

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jihuo);
        ButterKnife.bind(this);
        tvBaseText.setText("账号状态");
        SetStatusBarColor();


        Api.getDefault().getcode(PreferencesUtils.getString(JihuoActivity.this,"code")).compose(RxSchedulers.<Code>io_main()).subscribe(new RxSubscriber<Code>(JihuoActivity.this,false) {
            @Override
            protected void _onNext(Code c) {
                code=c.getCode();
                Message msg = Message.obtain();
                h.sendMessage(msg);
            }

            @Override
            protected void _onError(String message) {

            }
        });
    }

    @OnClick(R.id.iv_base_back)
    public void onViewClicked() {
        this.finish();

    }
}
