package com.df.smallwater.smallwater;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.df.smallwater.smallwater.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserActivity extends BaseActivity {

    @Bind(R.id.rl_changepwd)
    RelativeLayout rlChangepwd;
    @Bind(R.id.rl_jihuotime)
    RelativeLayout rlJihuotime;
    @Bind(R.id.rl_changeyuanfang)
    RelativeLayout rlChangeyuanfang;
    @Bind(R.id.tv_base_text)
    TextView tvBaseText;
    @Bind(R.id.iv_base_back)
    ImageView ivBaseBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        ButterKnife.bind(this);
        tvBaseText.setText("用户中心");
        SetStatusBarColor();
    }

    @OnClick({R.id.rl_changepwd, R.id.rl_jihuotime, R.id.rl_changeyuanfang , R.id.iv_base_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_changepwd:
                Bundle bundle = new Bundle();
                bundle.putInt("change_type",0);
                readyGo(ForgetActivity.class,bundle);

                break;
            case R.id.rl_jihuotime:
                readyGo(JihuoActivity.class);

                break;
            case R.id.rl_changeyuanfang:

                readyGo(ChangeyuanfangActivity.class);
                break;
            case R.id.iv_base_back:
                this.finish();
                break;
        }
    }





}
