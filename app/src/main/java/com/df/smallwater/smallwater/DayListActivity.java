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
import com.df.smallwater.smallwater.adapter.richangAdapter;
import com.df.smallwater.smallwater.api.Api;
import com.df.smallwater.smallwater.base.BaseActivity;
import com.df.smallwater.smallwater.baserx.RxSchedulers;
import com.df.smallwater.smallwater.bean.Richang;
import com.df.smallwater.smallwater.utils.PreferencesUtils;
import com.google.gson.Gson;
import com.yalantis.phoenix.PullToRefreshView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;

public class DayListActivity extends BaseActivity implements BaseQuickAdapter.OnItemClickListener {


    @Bind(R.id.tv_base_text)
    TextView tvBaseText;
    @Bind(R.id.iv_base_back)
    ImageView ivBaseBack;
    @Bind(R.id.rlv_richange)
    RecyclerView rlvRichange;
    @Bind(R.id.pull_to_refresh)
    PullToRefreshView pullToRefresh;
    richangAdapter richangAdapter;


    List<Richang.RichanglistBean> list;
    int allcount, page, pagenum;
    Message msg;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            richangAdapter = new richangAdapter(R.layout.item_richang_list, list);
            //设置recyclevie布局
            LinearLayoutManager mManager = new LinearLayoutManager(DayListActivity.this, LinearLayoutManager.VERTICAL, false);
            rlvRichange.setLayoutManager(mManager);
            rlvRichange.setAdapter(richangAdapter);
            pullToRefresh.setRefreshing(false);
            richangAdapter.setOnItemClickListener(DayListActivity.this);
            if (allcount>0) {
            } else {
                View v = View.inflate(DayListActivity.this,R.layout.layout_head,null);
                richangAdapter.addHeaderView(v);
                tvCount = v.findViewById(R.id.tv_count);
                tvCount.setText("暂无日常");
            }


        }
    };
    TextView tvCount;
    RelativeLayout relativeLayout11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_list);
        ButterKnife.bind(this);
        SetStatusBarColor();
        tvBaseText.setText("院内日常");
        //设置刷新事件


        pullToRefresh.setOnRefreshListener(new PullToRefreshView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                rlvRichange.post(new Runnable() {
                    @Override
                    public void run() {
                        Api.getDefault().getrichang(PreferencesUtils.getInt(DayListActivity.this, "banjiid"), 1).compose(RxSchedulers.<Richang>io_main()).subscribe(new Subscriber<Richang>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onNext(Richang Richang) {
                                list = Richang.getRichanglist();
                                allcount = Richang.getAllcount();
                                page = Richang.getPage();
                                pagenum = Richang.getPagenum();
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
        Api.getDefault().getrichang(PreferencesUtils.getInt(DayListActivity.this, "banjiid"), 1).compose(RxSchedulers.<Richang>io_main()).subscribe(new Subscriber<Richang>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Richang Richang) {
                list = Richang.getRichanglist();
                allcount = Richang.getAllcount();
                page = Richang.getPage();
                pagenum = Richang.getPagenum();
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
        readyGo(DayActivity.class, bundle);
    }


}
