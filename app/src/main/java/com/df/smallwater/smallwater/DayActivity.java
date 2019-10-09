package com.df.smallwater.smallwater;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.method.LinkMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.df.smallwater.smallwater.base.BaseActivity;
import com.df.smallwater.smallwater.bean.Richang;
import com.df.smallwater.smallwater.bean.Xinwen;
import com.df.smallwater.smallwater.utils.PreferencesUtils;
import com.google.gson.Gson;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DayActivity extends BaseActivity {

    @Bind(R.id.toolbar_image)
    ImageView toolbarImage;
    @Bind(R.id.toolbar_text)
    TextView toolbarText;
    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.collapsing_layout)
    CollapsingToolbarLayout collapsingLayout;
    @Bind(R.id.day_detail_text)
    TextView dayDetailText;
    @Bind(R.id.tv_time)
    TextView tvTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day);
        ButterKnife.bind(this);
        SetStatusBarColor();

        toolbarText.setText(PreferencesUtils.getString(this, "yname"));
        Bundle extras = getIntent().getExtras();
        String json = extras.getString("json");
        Gson g = new Gson();
        Richang.RichanglistBean r = g.fromJson(json, Richang.RichanglistBean.class);

        Glide.with(this).load(r.getPic()).into(toolbarImage);
        dayDetailText.setText(r.getContent());
        collapsingLayout.setTitle(r.getTitle());

        tvTime.setText(r.getTime());
        //设置还没收缩时状态下字体颜色
        collapsingLayout.setExpandedTitleColor(Color.WHITE);
        //设置收缩后Toolbar上字体的颜色
        collapsingLayout.setCollapsedTitleTextColor(Color.WHITE);
        dayDetailText.setMovementMethod(LinkMovementMethod.getInstance());






    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        this.finish();
        ;
    }
}
