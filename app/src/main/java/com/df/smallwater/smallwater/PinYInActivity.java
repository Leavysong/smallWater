package com.df.smallwater.smallwater;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.df.smallwater.smallwater.adapter.PYPagerAdapter;
import com.df.smallwater.smallwater.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PinYInActivity extends BaseActivity {


    @Bind(R.id.btn_shengmu)
    Button btnShengmu;
    @Bind(R.id.btn_yunmu)
    Button btnYunmu;
    private PYPagerAdapter shengmuAdapter;
    private PYPagerAdapter yunmuAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin_yin);
        ButterKnife.bind(this);
        SetStatusBarColor();

    }

    @OnClick({R.id.btn_shengmu, R.id.btn_yunmu})
    public void onViewClicked(View view) {
        Bundle bundle = new Bundle();
        switch (view.getId()) {
            case R.id.btn_shengmu:

                bundle.putInt("pingyintype",0);
                readyGo(SPinyinActivity.class,bundle);
                break;
            case R.id.btn_yunmu:
                bundle.putInt("pingyintype",1);
                readyGo(SPinyinActivity.class,bundle);
                break;
        }
    }
}
