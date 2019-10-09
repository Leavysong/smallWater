package com.df.smallwater.smallwater;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.df.smallwater.smallwater.adapter.MathPagerAdapter;
import com.df.smallwater.smallwater.base.BaseActivity;
import com.df.smallwater.smallwater.utils.MathUtil;
import com.df.smallwater.smallwater.utils.SayUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SMath1Activity extends BaseActivity {


    List<String> titlelist = new ArrayList<>();

    List<String> datalist = new ArrayList<>();

    List<String> meanlist = new ArrayList<>();

    List<String> imglist = new ArrayList<>();


    @Bind(R.id.hanzi_tableLayout)
    TabLayout hanziTableLayout;
    @Bind(R.id.viewpager_hanzi)
    ViewPager viewpagerHanzi;
    @Bind(R.id.tv_base_text)
    TextView tvBaseText;
    @Bind(R.id.iv_base_back)
    ImageView ivBaseBack;

    MathUtil mathUtil = MathUtil.getInstance();

    float DownX = 0;
    float DownY = 0;

    ImageView iv_math ;

    private MediaPlayer mp;
    MathPagerAdapter MathPagerAdapter ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smath1);
        ButterKnife.bind(this);

        SetStatusBarColor();
        tvBaseText.setText("识数字");
        Bundle extras = getIntent().getExtras();
        int page = extras.getInt("type");

        if(page==0){
            for (int i = 0; i < 5; i++) {
                titlelist.add(mathUtil.getMathList().get(i));
                datalist.add(mathUtil.getMathList().get(i));
                meanlist.add(mathUtil.getMathMeanList().get(i));
                imglist.add(mathUtil.getMathImgList().get(i));
            }
        }else{
            for (int i = 5; i < mathUtil.getMathList().size(); i++) {

                titlelist.add(mathUtil.getMathList().get(i));
                datalist.add(mathUtil.getMathList().get(i));
                meanlist.add(mathUtil.getMathMeanList().get(i));
                imglist.add(mathUtil.getMathImgList().get(i));
            }
        }

        List<View> list = new ArrayList<>();
        View v;
        for (int i = 0; i < datalist.size(); i++) {
            hanziTableLayout.addTab(hanziTableLayout.newTab());
            v = View.inflate(SMath1Activity.this, R.layout.math_viewpager, null);
            iv_math = v.findViewById(R.id.iv_math);
            ImageView iv_hanzi = v.findViewById(R.id.iv_math);
            Glide.with(SMath1Activity.this).load(imglist.get(i)).into(iv_hanzi);


            titlelist.add(datalist.get(i));
            list.add(v);

        }


        MathPagerAdapter = new MathPagerAdapter(list, titlelist, SMath1Activity.this);
        viewpagerHanzi.setAdapter(MathPagerAdapter);
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
                                            String query = datalist.get(item);


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


        SayUtil.say("小朋友,点击图片跟着读哟","4","5");
    }

    @OnClick(R.id.iv_base_back)
    public void onViewClicked() {
        this.finish();;
    }

    @Override
    protected void onDestroy() {
        SayUtil.stop();
        super.onDestroy();
    }
}
