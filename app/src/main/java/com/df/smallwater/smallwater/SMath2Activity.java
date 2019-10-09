package com.df.smallwater.smallwater;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SMath2Activity extends AppCompatActivity {


    int page;
    @Bind(R.id.tv_base_text)
    TextView tvBaseText;
    @Bind(R.id.iv_base_back)
    ImageView ivBaseBack;
    @Bind(R.id.img_math)
    ImageView imgMath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smath2);
        ButterKnife.bind(this);
        Bundle extras = getIntent().getExtras();
        page = extras.getInt("type");

        if(page==2){
            imgMath.setImageResource(R.mipmap.imgadd);
        }else{
            imgMath.setImageResource(R.mipmap.imgreduce);
        }
    }

    @OnClick(R.id.iv_base_back)
    public void onViewClicked() {
        this.finish();
    }
}
