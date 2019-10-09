package com.df.smallwater.smallwater;

import android.annotation.SuppressLint;
import android.graphics.Typeface;
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
import com.df.smallwater.smallwater.adapter.ABCPagerAdapter;
import com.df.smallwater.smallwater.base.BaseActivity;
import com.df.smallwater.smallwater.utils.SayUtil;
import com.df.smallwater.smallwater.utils.WordUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SABCActivity extends BaseActivity {

    @Bind(R.id.abc_tableLayout)
    TabLayout abcTableLayout;
    @Bind(R.id.viewpager_abc)
    ViewPager viewpagerAbc;
    @Bind(R.id.tv_base_text)
    TextView tvBaseText;
    @Bind(R.id.iv_base_back)
    ImageView ivBaseBack;

    private int wordtype = -1;
    ABCPagerAdapter ABCPagerAdapter;
    WordUtil w = WordUtil.getInstance();
    MediaPlayer mMediaPlayer;
    Typeface typeface ;

    float DownX = 0;
    float DownY = 0;

    List<View> list = null;
    List<String> wordlist = null;
    List<Integer> iconlist = null;
    List<String> wordimglist = null;


    private MediaPlayer mp;


    View v;

    ImageView  iv_word_img ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sabc);
        ButterKnife.bind(this);

        initUI();
        SetStatusBarColor();
        typeface = Typeface.createFromAsset(getAssets(),"fonts/zz.ttf");
        tvBaseText.setText("字母学习");
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initUI() {

        wordlist = new ArrayList<>();
        iconlist = new ArrayList<>();
        wordimglist = new ArrayList<>();


        Bundle extras = getIntent().getExtras();
        wordtype = extras.getInt("wordtype");
        switch(wordtype){

            case 0 :
                wordlist = w.getWordList();
                iconlist = w.getWordIconList();
                wordimglist = w.getWordImgList();
                setlist();
                ABCPagerAdapter = new ABCPagerAdapter(list, w.getWordList(), this);

                break ;
            case 1 :

                for (int i = 0 ; i < 7 ; i ++){
                    wordlist.add(w.getWordList().get(i));
                    iconlist.add(w.getWordIconList().get(i));
                    wordimglist.add(w.getWordImgList().get(i));
                }
                setlist();
                ABCPagerAdapter = new ABCPagerAdapter(list, w.getWordList(), this);
                break ;
            case 2 :
                for (int i = 7 ; i < 14 ; i ++){
                    wordlist.add(w.getWordList().get(i));
                    iconlist.add(w.getWordIconList().get(i));
                    wordimglist.add(w.getWordImgList().get(i));
                }
                setlist();
                ABCPagerAdapter = new ABCPagerAdapter(list, w.getWordList(), this);
                break ;
            case 3 :
                for (int i = 14 ; i < 21 ; i ++){
                    wordlist.add(w.getWordList().get(i));
                    iconlist.add(w.getWordIconList().get(i));
                    wordimglist.add(w.getWordImgList().get(i));
                }
                setlist();
                ABCPagerAdapter = new ABCPagerAdapter(list, w.getWordList(), this);
                break ;
            case 4 :
                for (int i = 21 ; i < w.getWordList().size() ; i ++){
                    wordlist.add(w.getWordList().get(i));
                    iconlist.add(w.getWordIconList().get(i));
                    wordimglist.add(w.getWordImgList().get(i));
                }
                setlist();
                ABCPagerAdapter = new ABCPagerAdapter(list, w.getWordList(), this);
                break ;


        }


        viewpagerAbc.setAdapter(ABCPagerAdapter);
        //MODE_SCROLLABLE可滑动的展示
        //MODE_FIXED固定展示
        abcTableLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        abcTableLayout.setupWithViewPager(viewpagerAbc);
        setupTabIcons();


        viewpagerAbc.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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
        viewpagerAbc.setOnTouchListener(new View.OnTouchListener() {
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
                            final int item = viewpagerAbc.getCurrentItem();

                                new Thread(
                                        new Runnable() {
                                            @Override
                                            public void run() {
                                               
                                                String query = wordlist.get(item);
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


        SayUtil.say("小朋友,点击图片跟着读哟","0","5");
    }


    @Override
    protected void onDestroy() {
        SayUtil.stop();
        super.onDestroy();
    }

    //设置icon
    private void setupTabIcons() {


        switch(wordtype){

            case 0 :
                settab();
            break ;
            case 1 :
                settab();
                break ;
            case 2 :
                settab();
                break ;
            case 3 :
                settab();
                break ;
            case 4 :
                settab();
                break ;


        }
    }


//获取tabview并设置图片icon

    public View getTabView(int position) {

        View view = LayoutInflater.from(this).inflate(R.layout.pinyin_icon, null);
        ImageView img_title = view.findViewById(R.id.img_title);
        switch(wordtype){

            case 0 :
                img_title.setImageResource(iconlist.get(position));
                break;
            case 1 :
                img_title.setImageResource(iconlist.get(position));
                break;
            case 2 :
                img_title.setImageResource(iconlist.get(position));
                break;
            case 3 :
                img_title.setImageResource(iconlist.get(position));
                break;
            case 4 :
                img_title.setImageResource(iconlist.get(position));
                break;
        }
        return view;
    }



    @OnClick(R.id.iv_base_back)
    public void onViewClicked() {
        this.finish();;
    }

    private void settab() {
        for (int i = 0; i < wordlist.size(); i++) {
            abcTableLayout.getTabAt(i).setCustomView(getTabView(i));

        }
    }



    private void setlist() {

        list = new ArrayList<>();
        for (int i = 0; i < wordlist.size(); i++) {
            abcTableLayout.addTab(abcTableLayout.newTab().setText(wordlist.get(i)));
            v = View.inflate(this, R.layout.word_viewpager, null);
            iv_word_img = v.findViewById(R.id.iv_word_img);
            Glide.with(SABCActivity.this).load(wordimglist.get(i)).into(iv_word_img);
            list.add(v);

        }

    }
}
