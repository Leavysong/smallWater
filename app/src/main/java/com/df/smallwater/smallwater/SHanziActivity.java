package com.df.smallwater.smallwater;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.df.smallwater.smallwater.adapter.HZPagerAdapter;
import com.df.smallwater.smallwater.api.Api;
import com.df.smallwater.smallwater.base.BaseActivity;
import com.df.smallwater.smallwater.baserx.RxSchedulers;
import com.df.smallwater.smallwater.baserx.RxSubscriber;
import com.df.smallwater.smallwater.bean.Hanzi;
import com.df.smallwater.smallwater.utils.SayUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SHanziActivity extends BaseActivity {

    @Bind(R.id.hanzi_tableLayout)
    TabLayout hanziTableLayout;
    @Bind(R.id.viewpager_hanzi)
    ViewPager viewpagerHanzi;
    @Bind(R.id.tv_base_text)
    TextView tvBaseText;
    @Bind(R.id.iv_base_back)
    ImageView ivBaseBack;
    HZPagerAdapter HZPagerAdapter ;

    float DownX = 0;
    float DownY = 0;


    List<Hanzi.HanzilistBean> hanzilist =  new ArrayList<>();
    List<String> titlelist =  new ArrayList<>();
    int allcount;

    TextView tv_bushou , tv_pinyin , tv_zi , tv_cizu;

    Message msg;

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @SuppressLint({"ClickableViewAccessibility", "SetTextI18n"})
        @Override
        public void handleMessage(Message msg) {

            List<View> list = new ArrayList<>();
            View v;
            for (int i = 0; i < hanzilist.size(); i++) {
                hanziTableLayout.addTab(hanziTableLayout.newTab());
                v = View.inflate(SHanziActivity.this, R.layout.hanzi_viewpager, null);
                tv_zi = v.findViewById(R.id.tv_zi);
                tv_bushou = v.findViewById(R.id.tv_bushou);
                tv_pinyin = v.findViewById(R.id.tv_pinyin);
                tv_cizu = v.findViewById(R.id.tv_cizu);
                tv_zi.setText(hanzilist.get(i).getZi());
                tv_bushou.setText("部首：  "+hanzilist.get(i).getBushou()+"   笔画：  "+hanzilist.get(i).getBihua());
                tv_pinyin.setText("拼音：  "+hanzilist.get(i).getPinyin());

                tv_cizu.setText(hanzilist.get(i).getCizu());

                ImageView iv_hanzi = v.findViewById(R.id.iv_hanzi);
                Glide.with(SHanziActivity.this).load(hanzilist.get(i).getPic()).into(iv_hanzi);


                titlelist.add(hanzilist.get(i).getZi());
                list.add(v);

            }


            HZPagerAdapter = new HZPagerAdapter(list, titlelist, SHanziActivity.this);
            viewpagerHanzi.setAdapter(HZPagerAdapter);
            //MODE_SCROLLABLE可滑动的展示
            //MODE_FIXED固定展示
            hanziTableLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
            hanziTableLayout.setupWithViewPager(viewpagerHanzi);

            viewpagerHanzi.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    DownX = 0;
                    DownY = 0;
                }

                @Override
                public void onPageScrollStateChanged(int state) {
                    DownX = 0;
                    DownY = 0;
                }
            });

            viewpagerHanzi.setOnTouchListener(new View.OnTouchListener() {
                int flage = 0;

                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {

                    switch (motionEvent.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            SayUtil.stop();
                            DownX = motionEvent.getX();//float DownX
                            DownY = motionEvent.getY();//float DownY
                            flage = 0;
                            break;
                        case MotionEvent.ACTION_MOVE:

                            float moveX = motionEvent.getX() - DownX;//X轴距离
                            float moveY = motionEvent.getY() - DownY;//y轴距离

                            if (moveX < 1 || moveY < 1) {
                                flage = 0;
                            } else {
                                flage = 1;
                            }

                            break;
                        case MotionEvent.ACTION_UP:
                            if (flage == 0) {
                                final int item = viewpagerHanzi.getCurrentItem();
                                new Thread(
                                        new Runnable() {
                                            @Override
                                            public void run() {
                                                String query = hanzilist.get(item).getZi();
                                                SayUtil.say(query,"0","1");


                                            }

                                        }

                                ).start();

                            }
                            break;


                    }
                    return false;
                }
            });

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shanzi);
        ButterKnife.bind(this);
        SetStatusBarColor();
        tvBaseText.setText("识字");
       Bundle extras = getIntent().getExtras();
       int page = extras.getInt("page");

        Api.getDefault().getonehanzi(page).compose(RxSchedulers.<Hanzi>io_main()).subscribe(new RxSubscriber<Hanzi>(this,false) {
            @Override
            protected void _onNext(Hanzi hanzi) {
                hanzilist = hanzi.getHanzilist();
                allcount = hanzi.getAllcount();
                msg = Message.obtain();
                handler.sendMessage(msg);
            }

            @Override
            protected void _onError(String message) {

            }
        });
    }


    @Override
    protected void onDestroy() {
        SayUtil.stop();
        super.onDestroy();
    }

    @OnClick(R.id.iv_base_back)
    public void onViewClicked() {
        this.finish();
    }
}
