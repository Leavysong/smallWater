package com.df.smallwater.smallwater;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.df.smallwater.smallwater.adapter.NewsAdapter;
import com.df.smallwater.smallwater.api.Api;
import com.df.smallwater.smallwater.base.BaseActivity;
import com.df.smallwater.smallwater.baserx.RxSchedulers;
import com.df.smallwater.smallwater.bean.Xinwen;
import com.df.smallwater.smallwater.utils.PreferencesUtils;
import com.google.gson.Gson;
import com.yalantis.phoenix.PullToRefreshView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;

public class NewsListActivity extends BaseActivity implements BaseQuickAdapter.OnItemClickListener {

    @Bind(R.id.tv_base_text)
    TextView tvBaseText;
    @Bind(R.id.iv_base_back)
    ImageView ivBaseBack;
    @Bind(R.id.rlv_news)
    RecyclerView rlvNews;
    @Bind(R.id.pull_to_refresh)
    PullToRefreshView pullToRefresh;
    NewsAdapter newsAdapter;


    Message msg;
    List<Xinwen.XinwenlistBean> list;
    int allcount, page, pagenum;

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            newsAdapter = new NewsAdapter(R.layout.item_news_list, list);
            //设置recyclevie布局
            LinearLayoutManager mManager = new LinearLayoutManager(NewsListActivity.this, LinearLayoutManager.VERTICAL, false);
            rlvNews.setLayoutManager(mManager);
            rlvNews.setAdapter(newsAdapter);
            pullToRefresh.setRefreshing(false);
            newsAdapter.setOnItemClickListener(NewsListActivity.this);


            if (allcount>0) {
            } else {
                View v = View.inflate(NewsListActivity.this,R.layout.layout_head,null);
                newsAdapter.addHeaderView(v);
                tvCount = v.findViewById(R.id.tv_count);
                tvCount.setText("暂无新闻");
            }

        }
    };
    TextView tvCount;
    RelativeLayout relativeLayout11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list);
        ButterKnife.bind(this);
        SetStatusBarColor();
        tvBaseText.setText("新闻中心");


        pullToRefresh.setOnRefreshListener(new PullToRefreshView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                rlvNews.post(new Runnable() {
                    @Override
                    public void run() {
                        Api.getDefault().getxinwen(PreferencesUtils.getInt(NewsListActivity.this, "youeryuanid"), 1).compose(RxSchedulers.<Xinwen>io_main()).subscribe(new Subscriber<Xinwen>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onNext(Xinwen Xinwen) {
                                list = Xinwen.getXinwenlist();
                                allcount = Xinwen.getAllcount();
                                page = Xinwen.getPage();
                                pagenum = Xinwen.getPagenum();
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
        Api.getDefault().getxinwen(PreferencesUtils.getInt(NewsListActivity.this, "youeryuanid"), 1).compose(RxSchedulers.<Xinwen>io_main()).subscribe(new Subscriber<Xinwen>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Xinwen Xinwen) {
                list = Xinwen.getXinwenlist();
                allcount = Xinwen.getAllcount();
                page = Xinwen.getPage();
                pagenum = Xinwen.getPagenum();
                msg = Message.obtain();
                handler.sendMessage(msg);
                pullToRefresh.setRefreshing(false);
            }
        });

    }

    @OnClick(R.id.iv_base_back)
    public void onViewClicked() {
        this.finish();
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Bundle bundle = new Bundle();
        Gson g = new Gson();
        String json = g.toJson(list.get(position));
        bundle.putString("json", json);
        readyGo(NewsActivity.class, bundle);
    }


}
