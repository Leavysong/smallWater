package com.df.smallwater.smallwater;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.df.smallwater.smallwater.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CallActivity extends BaseActivity {

    @Bind(R.id.textView14)
    TextView textView14;
    @Bind(R.id.tv_base_text)
    TextView tvBaseText;
    @Bind(R.id.iv_base_back)
    ImageView ivBaseBack;
    @Bind(R.id.relativeLayout2)
    RelativeLayout relativeLayout2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);
        ButterKnife.bind(this);
        SetStatusBarColor();
        tvBaseText.setText("联系我们");
    }

    @OnClick(R.id.iv_base_back)
    public void onViewClicked() {
        this.finish();
    }
}
