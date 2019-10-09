package com.df.smallwater.smallwater;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.df.smallwater.smallwater.adapter.wordlistAdapter;
import com.df.smallwater.smallwater.api.Api;
import com.df.smallwater.smallwater.base.BaseActivity;
import com.df.smallwater.smallwater.baserx.RxSchedulers;
import com.df.smallwater.smallwater.bean.Title;
import com.df.smallwater.smallwater.bean.Word;
import com.df.smallwater.smallwater.utils.PreferencesUtils;
import com.df.smallwater.smallwater.utils.cardviewUtil;
import com.df.smallwater.smallwater.utils.dateUtil;
import com.yalantis.phoenix.PullToRefreshView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;

public class WordActivity extends BaseActivity implements View.OnClickListener,BaseQuickAdapter.OnItemClickListener{


    @Bind(R.id.tv_base_text)
    TextView tvBaseText;
    @Bind(R.id.iv_base_back)
    ImageView ivBaseBack;
    @Bind(R.id.rlv_word)
    RecyclerView rlvWord;
    @Bind(R.id.pull_to_refresh)
    PullToRefreshView pullToRefresh;

    wordlistAdapter wordlistAdapter;
    Message msg;

    List<Title> list  = new ArrayList<>();
    int allcount, page;
    private MediaPlayer mp;
    RelativeLayout rl_word1 , rl_word2 , rl_word3 , rl_word4 , rl_word5 ;

    CardView cardview , cardview1 , cardview2 , cardview3 , cardview4 ;

    ImageView iv_hanzi_img , iv_hanzi_img1 , iv_hanzi_img2 , iv_hanzi_img3 , iv_hanzi_img4 ;

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {





            wordlistAdapter = new wordlistAdapter(R.layout.item_word_list, list,WordActivity.this);
            View v = View.inflate(WordActivity.this, R.layout.head_word, null);
            iv_hanzi_img = v.findViewById(R.id.iv_hanzi_img);
            iv_hanzi_img1 = v.findViewById(R.id.iv_hanzi_img1);
            iv_hanzi_img2 = v.findViewById(R.id.iv_hanzi_img2);
            iv_hanzi_img3 = v.findViewById(R.id.iv_hanzi_img3);
            iv_hanzi_img4 = v.findViewById(R.id.iv_hanzi_img4);
            Glide.with(WordActivity.this).load("http://pacnmzg6f.bkt.clouddn.com/worda.png").into(iv_hanzi_img);
            Glide.with(WordActivity.this).load("http://pacnmzg6f.bkt.clouddn.com/worda.png").into(iv_hanzi_img1);
            Glide.with(WordActivity.this).load("http://pacnmzg6f.bkt.clouddn.com/wordh.png").into(iv_hanzi_img2);
            Glide.with(WordActivity.this).load("http://pacnmzg6f.bkt.clouddn.com/wordo.png").into(iv_hanzi_img3);
            Glide.with(WordActivity.this).load("http://pacnmzg6f.bkt.clouddn.com/wordv.png").into(iv_hanzi_img4);
            cardview = v.findViewById(R.id.cardview);
            cardview1 = v.findViewById(R.id.cardview1);
            cardview2 = v.findViewById(R.id.cardview2);
            cardview3 = v.findViewById(R.id.cardview3);
            cardview4 = v.findViewById(R.id.cardview4);
            cardviewUtil.setCard(cardview);
            cardviewUtil.setCard(cardview1);
            cardviewUtil.setCard(cardview2);
            cardviewUtil.setCard(cardview3);
            cardviewUtil.setCard(cardview4);
            rl_word1 = v.findViewById(R.id.rl_word1);
            rl_word2 = v.findViewById(R.id.rl_word2);
            rl_word3 = v.findViewById(R.id.rl_word3);
            rl_word4 = v.findViewById(R.id.rl_word4);
            rl_word5 = v.findViewById(R.id.rl_word5);
            rl_word1.setOnClickListener(WordActivity.this);
            rl_word2.setOnClickListener(WordActivity.this);
            rl_word3.setOnClickListener(WordActivity.this);
            rl_word4.setOnClickListener(WordActivity.this);
            rl_word5.setOnClickListener(WordActivity.this);
            wordlistAdapter.addHeaderView(v);

            //设置recyclevie布局
            LinearLayoutManager mManager = new LinearLayoutManager(WordActivity.this, LinearLayoutManager.VERTICAL, false);
            rlvWord.setLayoutManager(mManager);
            rlvWord.setAdapter(wordlistAdapter);
            wordlistAdapter.setOnItemClickListener(WordActivity.this);
            pullToRefresh.setRefreshing(false);


        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word);
        ButterKnife.bind(this);
        SetStatusBarColor();
        tvBaseText.setText("英语学习");
        page = PreferencesUtils.getInt(this,"wpage");
        if(page==-1){

            page=0;
        }
        pullToRefresh.setOnRefreshListener(new PullToRefreshView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                rlvWord.post(new Runnable() {
                    @Override
                    public void run() {
                        Api.getDefault().getword(page).compose(RxSchedulers.<Word>io_main()).subscribe(new Subscriber<Word>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onNext(Word Word) {
                                list = dateUtil.getwordlist(Word.getWordlist());
                                allcount = Word.getAllcount();
                                msg = Message.obtain();
                                handler.sendMessage(msg);
                                pullToRefresh.setRefreshing(false);
                            }
                        });

                    }
                });
            }
        });
        pullToRefresh.setRefreshing(true);
        Api.getDefault().getword(page).compose(RxSchedulers.<Word>io_main()).subscribe(new Subscriber<Word>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Word Word) {
                list = dateUtil.getwordlist(Word.getWordlist());
                allcount = Word.getAllcount();
                msg = Message.obtain();
                handler.sendMessage(msg);
                pullToRefresh.setRefreshing(false);
            }
        });

    }


    @OnClick({R.id.iv_base_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_base_back:
                this.finish();
                break;
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

        int page = position ;
        Bundle bundle = new Bundle();
        bundle.putInt("page",page);
        readyGo(SWordActivity.class,bundle);


    }



    @Override
    public void onClick(View view) {
        Bundle bundle = new Bundle();
        switch(view.getId()){

            case R.id.rl_word1:
                bundle.putInt("wordtype",0);
                readyGo(SABCActivity.class,bundle);
                break;
            case R.id.rl_word2:

                bundle.putInt("wordtype",1);
                readyGo(SABCActivity.class,bundle);
                break;
            case R.id.rl_word3:
                bundle.putInt("wordtype",2);
                readyGo(SABCActivity.class,bundle);
                break;
            case R.id.rl_word4:

                bundle.putInt("wordtype",3);
                readyGo(SABCActivity.class,bundle);
                break;
            case R.id.rl_word5:

                bundle.putInt("wordtype",4);
                readyGo(SABCActivity.class,bundle);
                break;

        }
    }
}
