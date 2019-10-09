package com.df.smallwater.smallwater;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
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
import com.df.smallwater.smallwater.bean.Word;
import com.df.smallwater.smallwater.utils.SayUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SWordActivity extends BaseActivity {

    @Bind(R.id.word_tableLayout)
    TabLayout wordTableLayout;
    @Bind(R.id.viewpager_word)
    ViewPager viewpagerWord;
    @Bind(R.id.tv_base_text)
    TextView tvBaseText;
    @Bind(R.id.iv_base_back)
    ImageView ivBaseBack;

    private MediaPlayer mp;
    HZPagerAdapter HZPagerAdapter ;

    float DownX = 0;
    float DownY = 0;

    List<Word.WordlistBean> wordlist =  new ArrayList<>();
    List<String> titlelist =  new ArrayList<>();

    ImageView  iv_word ;
    TextView tv_word , tv_yinbiao , tv_mean , tv_cizu ;

    int allcount ;

    Message msg;
    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @SuppressLint("ClickableViewAccessibility")
        @Override
        public void handleMessage(Message msg) {

            List<View> list = new ArrayList<>();
            View v;
            for (int i = 0; i < wordlist.size(); i++) {
                wordTableLayout.addTab(wordTableLayout.newTab());
                v = View.inflate(SWordActivity.this, R.layout.sword_viewpager, null);
                iv_word = v.findViewById(R.id.iv_word);
                tv_word = v.findViewById(R.id.tv_word);
                tv_yinbiao = v.findViewById(R.id.tv_yinbiao);
                tv_mean = v.findViewById(R.id.tv_mean);
                tv_cizu = v.findViewById(R.id.tv_cizu);
                tv_word.setText(wordlist.get(i).getWord());
                tv_yinbiao.setText(wordlist.get(i).getPronunciation_us());
                tv_mean.setText(wordlist.get(i).getMean());
                Glide.with(SWordActivity.this).load(wordlist.get(i).getPic()).into(iv_word);
                tv_cizu.setText(wordlist.get(i).getCizu());

                titlelist.add(wordlist.get(i).getWord());
                list.add(v);

            }


            HZPagerAdapter = new HZPagerAdapter(list, titlelist, SWordActivity.this);
            viewpagerWord.setAdapter(HZPagerAdapter);
            //MODE_SCROLLABLE可滑动的展示
            //MODE_FIXED固定展示
            wordTableLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
            wordTableLayout.setupWithViewPager(viewpagerWord);

            viewpagerWord.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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

            viewpagerWord.setOnTouchListener(new View.OnTouchListener() {
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
                                final int item = viewpagerWord.getCurrentItem();
                                new Thread(
                                        new Runnable() {
                                            @Override
                                            public void run() {
                                                String query = wordlist.get(item).getWord();
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
        setContentView(R.layout.activity_sword);
        ButterKnife.bind(this);
        SetStatusBarColor();
        tvBaseText.setText("识字");
        Bundle extras = getIntent().getExtras();
        int page = extras.getInt("page");
        Api.getDefault().getoneword(page).compose(RxSchedulers.<Word>io_main()).subscribe(new RxSubscriber<Word>(this,false) {
            @Override
            protected void _onNext(Word word) {
                wordlist = word.getWordlist();
                allcount = word.getAllcount();
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
