package com.df.smallwater.smallwater;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.df.smallwater.smallwater.adapter.PYPagerAdapter;
import com.df.smallwater.smallwater.base.BaseActivity;
import com.df.smallwater.smallwater.utils.PinYinUtils;
import com.df.smallwater.smallwater.utils.SayUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SPinyinActivity extends BaseActivity {


    @Bind(R.id.viewpager_pinyin)
    ViewPager viewpagerPinyin;
    @Bind(R.id.pinyin_tableLayout)
    TabLayout pinyinTableLayout;
    @Bind(R.id.tv_base_text)
    TextView tvBaseText;
    @Bind(R.id.iv_base_back)
    ImageView ivBaseBack;
    private int pinyin_type = -1;
    PYPagerAdapter pyPagerAdapter;
    PinYinUtils p = PinYinUtils.getInstance();
    MediaPlayer mMediaPlayer = new MediaPlayer();


    float DownX = 0;
    float DownY = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinyin);
        ButterKnife.bind(this);
        initUI();
        SetStatusBarColor();
        tvBaseText.setText("拼音学习");
    }

    @Override
    protected void onDestroy() {
        SayUtil.stop();
        super.onDestroy();

    }

    @SuppressLint("ClickableViewAccessibility")
    private void initUI() {
        List<View> list = null;
        View v;
        Bundle extras = getIntent().getExtras();
        pinyin_type = extras.getInt("pingyintype");


        if (pinyin_type == 1) {
            list = new ArrayList<>();
            for (int i = 0; i < p.getYUNMU_LIST().size(); i++) {
                pinyinTableLayout.addTab(pinyinTableLayout.newTab().setIcon(p.getPINYIN_LIST().get(p.getYUNMU_LIST().get(i))));

                //上面两句放在下面这个循环的前面,图标才
//                }能正常显示
//                for (int i = 0; i < MAIN_FUN_TITLES.length; i++) {
//                    TabLayout.Tab tab = tabMain.newTab();
//                    tab.setCustomView(MAIN_FUN_VIEWS[i]);//设置自定义的View
//                    tabMain.addTab(tab, i);

                v = View.inflate(this, R.layout.pinyin_viewpager, null);
                ImageView ibv = v.findViewById(R.id.iv_pinyincard);
                Glide.with(SPinyinActivity.this).load(p.getPINYIN_CARD_LIST().get(p.getYUNMU_LIST().get(i))).into(ibv);
                list.add(v);

            }

        } else if(pinyin_type == 0){
            list = new ArrayList<>();
            for (int i = 0; i < p.getSHENGMU_LIST().size(); i++) {
                pinyinTableLayout.addTab(pinyinTableLayout.newTab().setText(p.getSHENGMU_LIST().get(i)));
                v = View.inflate(this, R.layout.pinyin_viewpager, null);
                ImageView ibv = v.findViewById(R.id.iv_pinyincard);
                Glide.with(SPinyinActivity.this).load(p.getPINYIN_CARD_LIST().get(p.getSHENGMU_LIST().get(i))).into(ibv);
                list.add(v);
            }

        }else if(pinyin_type == 2){
            list = new ArrayList<>();

            for (int i = 0; i < p.getAll_PINYIN_LIST().size(); i++) {
                pinyinTableLayout.addTab(pinyinTableLayout.newTab().setText(p.getAll_PINYIN_LIST().get(i)));
                v = View.inflate(this, R.layout.pinyin_viewpager, null);
                ImageView ibv = v.findViewById(R.id.iv_pinyincard);
                Glide.with(SPinyinActivity.this).load(p.getPINYIN_CARD_LIST().get(p.getAll_PINYIN_LIST().get(i))).into(ibv);


                list.add(v);
            }



        }

        if (pinyin_type == 1) {
            pyPagerAdapter = new PYPagerAdapter(list, p.getYUNMU_LIST(), this);
        } else if(pinyin_type == 0){

            pyPagerAdapter = new PYPagerAdapter(list, p.getSHENGMU_LIST(), this);
        } else if(pinyin_type == 2){


                List<String> iconlist = new ArrayList<>();


            for (int i = 0 ; i < p.getSHENGMU_LIST().size() ; i ++){
                iconlist.add(p.getSHENGMU_LIST().get(i));
            }
            for (int i = 0 ; i < p.getYUNMU_LIST().size() ; i ++){
                iconlist.add(p.getYUNMU_LIST().get(i));
            }



            pyPagerAdapter = new PYPagerAdapter(list, iconlist, this);
        }
        viewpagerPinyin.setAdapter(pyPagerAdapter);
        //MODE_SCROLLABLE可滑动的展示
        //MODE_FIXED固定展示
        pinyinTableLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        pinyinTableLayout.setupWithViewPager(viewpagerPinyin);
        setupTabIcons();

        viewpagerPinyin.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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

        viewpagerPinyin.setOnTouchListener(new View.OnTouchListener() {
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
                            final int item = viewpagerPinyin.getCurrentItem();
                            if (pinyin_type == 1) {
                                new Thread(
                                        new Runnable() {
                                            @Override
                                            public void run() {
                                                mMediaPlayer = MediaPlayer.create(SPinyinActivity.this, p.getPINYIN_TALK_LIST().get(p.getYUNMU_LIST().get(item)));
                                                mMediaPlayer.start();
                                                mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                                    @Override
                                                    public void onCompletion(MediaPlayer mediaPlayer) {
                                                        mediaPlayer.release();
                                                    }
                                                });

                                            }
                                        }

                                ).start();


                            } else if(pinyin_type == 0){
                                new Thread(
                                        new Runnable() {
                                            @Override
                                            public void run() {
                                                mMediaPlayer = MediaPlayer.create(SPinyinActivity.this, p.getPINYIN_TALK_LIST().get(p.getSHENGMU_LIST().get(item)));
                                                mMediaPlayer.start();
                                                mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                                    @Override
                                                    public void onCompletion(MediaPlayer mediaPlayer) {
                                                        mediaPlayer.release();
                                                    }
                                                });

                                            }
                                        }

                                ).start();

                            }else{
                                new Thread(
                                        new Runnable() {
                                            @Override
                                            public void run() {
                                                mMediaPlayer = MediaPlayer.create(SPinyinActivity.this,  p.getPINYIN_TALK_LIST().get(p.getAll_PINYIN_LIST().get(item)));
                                                mMediaPlayer.start();
                                                mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                                    @Override
                                                    public void onCompletion(MediaPlayer mediaPlayer) {
                                                        mediaPlayer.release();
                                                    }
                                                });

                                            }
                                        }

                                ).start();
                            }
                        }
                        break;


                }
                return false;
            }
        });

        SayUtil.say("小朋友,点击图片跟着读哟","0","5");
    }


    //设置icon
    private void setupTabIcons() {
        if (pinyin_type == 1) {
            for (int i = 0; i < p.getYUNMU_LIST().size(); i++) {
                pinyinTableLayout.getTabAt(i).setCustomView(getTabView(i));

            }
        } else if(pinyin_type == 0) {
            for (int i = 0; i < p.getSHENGMU_LIST().size(); i++) {
                pinyinTableLayout.getTabAt(i).setCustomView(getTabView(i));

            }
        }
        else{
            for (int i = 0; i < p.getAll_PINYIN_LIST().size(); i++) {
                pinyinTableLayout.getTabAt(i).setCustomView(getTabView(i));

            }

        }


    }

//获取tabview并设置图片icon

    public View getTabView(int position) {

        if (pinyin_type == 1) {
            View view = LayoutInflater.from(this).inflate(R.layout.pinyin_icon, null);
            ImageView img_title = view.findViewById(R.id.img_title);
            img_title.setImageResource(p.getPINYIN_LIST().get(p.getYUNMU_LIST().get(position)));
            return view;
        } else if(pinyin_type == 0){
            View view = LayoutInflater.from(this).inflate(R.layout.pinyin_icon, null);
            ImageView img_title = view.findViewById(R.id.img_title);

            img_title.setImageResource(p.getPINYIN_LIST().get(p.getSHENGMU_LIST().get(position)));
            return view;
        }else{
            View view = LayoutInflater.from(this).inflate(R.layout.pinyin_icon, null);
            ImageView img_title = view.findViewById(R.id.img_title);
            img_title.setImageResource(p.getPINYIN_LIST().get(p.getAll_PINYIN_LIST().get(position)));
            return view;
        }


    }

    @OnClick(R.id.iv_base_back)
    public void onViewClicked() {
        this.finish();
    }
}
