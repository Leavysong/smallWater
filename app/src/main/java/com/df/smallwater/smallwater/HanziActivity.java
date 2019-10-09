package com.df.smallwater.smallwater;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.df.smallwater.smallwater.adapter.hanzilistAdapter;
import com.df.smallwater.smallwater.api.Api;
import com.df.smallwater.smallwater.base.BaseActivity;
import com.df.smallwater.smallwater.baserx.RxSchedulers;
import com.df.smallwater.smallwater.bean.Hanzi;
import com.df.smallwater.smallwater.bean.Title;
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

public class HanziActivity extends BaseActivity implements View.OnClickListener ,  BaseQuickAdapter.OnItemClickListener{

    @Bind(R.id.tv_base_text)
    TextView tvBaseText;
    @Bind(R.id.iv_base_back)
    ImageView ivBaseBack;
    @Bind(R.id.rlv_hanzi)
    RecyclerView rlvHanzi;
    @Bind(R.id.pull_to_refresh)
    PullToRefreshView pullToRefresh;


    RelativeLayout rl_hanzi1 , rl_hanzi2 , rl_hanzi3 ;

    hanzilistAdapter hanzilistAdapter;
    Message msg;
    List<Title> list  = new ArrayList<>();
    int allcount, page, pagenum;

    CardView cardview , cardview1 , cardview2 ;

    ImageView iv_hanzi_img , iv_hanzi_img1 , iv_hanzi_img2 ;

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            hanzilistAdapter = new hanzilistAdapter(R.layout.item_hanzi_list, list,HanziActivity.this);
            View v = View.inflate(HanziActivity.this, R.layout.head_hanzi, null);

            iv_hanzi_img = v.findViewById(R.id.iv_hanzi_img);
            iv_hanzi_img1 = v.findViewById(R.id.iv_hanzi_img1);
            iv_hanzi_img2 = v.findViewById(R.id.iv_hanzi_img2);
            Glide.with(HanziActivity.this).load("http://pacnmzg6f.bkt.clouddn.com/acard.png").into(iv_hanzi_img);
            Glide.with(HanziActivity.this).load("http://pacnmzg6f.bkt.clouddn.com/acard.png").into(iv_hanzi_img1);
            Glide.with(HanziActivity.this).load("http://pacnmzg6f.bkt.clouddn.com/bcard.png").into(iv_hanzi_img2);
            cardview = v.findViewById(R.id.cardview);
            cardview1 = v.findViewById(R.id.cardview1);
            cardview2 = v.findViewById(R.id.cardview2);
            cardviewUtil.setCard(cardview);
            cardviewUtil.setCard(cardview1);
            cardviewUtil.setCard(cardview2);
            rl_hanzi1 = v.findViewById(R.id.rl_hanzi1);
            rl_hanzi2 = v.findViewById(R.id.rl_hanzi2);
            rl_hanzi3 = v.findViewById(R.id.rl_hanzi3);
            rl_hanzi1.setOnClickListener(HanziActivity.this);
            rl_hanzi2.setOnClickListener(HanziActivity.this);
            rl_hanzi3.setOnClickListener(HanziActivity.this);
            hanzilistAdapter.addHeaderView(v);


            //设置recyclevie布局
            LinearLayoutManager mManager = new LinearLayoutManager(HanziActivity.this, LinearLayoutManager.VERTICAL, false);
            rlvHanzi.setLayoutManager(mManager);
            rlvHanzi.setAdapter(hanzilistAdapter);
            hanzilistAdapter.setOnItemClickListener(HanziActivity.this);


        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hanzi);
        ButterKnife.bind(this);
        SetStatusBarColor();
        tvBaseText.setText("汉字学习");

        //设置Item增加、移除动画
        rlvHanzi.setItemAnimator(new DefaultItemAnimator());
        page = PreferencesUtils.getInt(this, "hpage");
        if (page == -1) {

            page = 0;
        }
        pullToRefresh.setOnRefreshListener(new PullToRefreshView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                rlvHanzi.post(new Runnable() {
                    @Override
                    public void run() {
                        Api.getDefault().gethanzi(page).compose(RxSchedulers.<Hanzi>io_main()).subscribe(new Subscriber<Hanzi>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onNext(Hanzi Hanzi) {

                                list = dateUtil.gethanzilist(Hanzi.getHanzilist());
                                allcount = Hanzi.getAllcount();
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
        Api.getDefault().gethanzi(page).compose(RxSchedulers.<Hanzi>io_main()).subscribe(new Subscriber<Hanzi>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Hanzi Hanzi) {
                list = dateUtil.gethanzilist(Hanzi.getHanzilist());
                allcount = Hanzi.getAllcount();
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
    public void onClick(View view) {
        Bundle bundle = new Bundle();
        switch(view.getId()){

            case R.id.rl_hanzi1:
                bundle.putInt("pingyintype",2);
                readyGo(SPinyinActivity.class,bundle);
                break;
            case R.id.rl_hanzi2:

                bundle.putInt("pingyintype",1);
                readyGo(SPinyinActivity.class,bundle);
                break;
            case R.id.rl_hanzi3:


                bundle.putInt("pingyintype",0);
                readyGo(SPinyinActivity.class,bundle);
                break;

        }

    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        int page = position ;
        Bundle bundle = new Bundle();
        bundle.putInt("page",page);
        readyGo(SHanziActivity.class,bundle);
    }
}
