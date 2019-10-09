package com.df.smallwater.smallwater;

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
import com.df.smallwater.smallwater.adapter.gonggaoAdapter;
import com.df.smallwater.smallwater.api.Api;
import com.df.smallwater.smallwater.base.BaseActivity;
import com.df.smallwater.smallwater.baserx.RxSchedulers;
import com.df.smallwater.smallwater.bean.Gonggao;
import com.df.smallwater.smallwater.utils.PreferencesUtils;
import com.yalantis.phoenix.PullToRefreshView;
import com.youth.banner.listener.OnBannerListener;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;

public class GonggaoActivity extends BaseActivity implements OnBannerListener, BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.OnItemChildClickListener {

    @Bind(R.id.rlv_gonggao)
    RecyclerView rlvGonggao;
    @Bind(R.id.pull_to_refresh)
    PullToRefreshView pullToRefresh;
    @Bind(R.id.tv_base_text)
    TextView tvBaseText;
    @Bind(R.id.iv_base_back)
    ImageView ivBaseBack;

    gonggaoAdapter gonggaoAdapter;


    Message msg;
    List<Gonggao.GonggaolistBean> list;
    int allcount, page, pagenum;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            gonggaoAdapter = new gonggaoAdapter(R.layout.item_gonggao_list, list);
            //设置recyclevie布局
            LinearLayoutManager mManager = new LinearLayoutManager(GonggaoActivity.this, LinearLayoutManager.VERTICAL, false);
            rlvGonggao.setLayoutManager(mManager);
            rlvGonggao.setAdapter(gonggaoAdapter);
            pullToRefresh.setRefreshing(false);
            gonggaoAdapter.setOnItemClickListener(GonggaoActivity.this);

            if (allcount>0) {
            } else {
                View v = View.inflate(GonggaoActivity.this,R.layout.layout_head,null);
                gonggaoAdapter.addHeaderView(v);
                tvCount = v.findViewById(R.id.tv_count);
                tvCount.setText("暂无公告");
            }

        }
    };
    TextView tvCount;
    RelativeLayout relativeLayout11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gonggao);
        SetStatusBarColor();
        ButterKnife.bind(this);
        tvBaseText.setText("院内公告");


        pullToRefresh.setOnRefreshListener(new PullToRefreshView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                rlvGonggao.post(new Runnable() {
                    @Override
                    public void run() {
                        Api.getDefault().getgonggao(PreferencesUtils.getInt(GonggaoActivity.this, "youeryuanid"), 1).compose(RxSchedulers.<Gonggao>io_main()).subscribe(new Subscriber<Gonggao>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onNext(Gonggao Gonggao) {
                                list = Gonggao.getGonggaolist();
                                allcount = Gonggao.getAllcount();
                                page = Gonggao.getPage();
                                pagenum = Gonggao.getPagenum();
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
        Api.getDefault().getgonggao(PreferencesUtils.getInt(GonggaoActivity.this, "youeryuanid"), 1).compose(RxSchedulers.<Gonggao>io_main()).subscribe(new Subscriber<Gonggao>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Gonggao Gonggao) {
                list = Gonggao.getGonggaolist();
                allcount = Gonggao.getAllcount();
                page = Gonggao.getPage();
                pagenum = Gonggao.getPagenum();
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
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void OnBannerClick(int position) {

    }
}
