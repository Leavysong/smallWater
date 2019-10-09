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

import com.df.smallwater.smallwater.adapter.zhuangkuangAdapter;
import com.df.smallwater.smallwater.api.Api;
import com.df.smallwater.smallwater.base.BaseActivity;
import com.df.smallwater.smallwater.baserx.RxSchedulers;
import com.df.smallwater.smallwater.bean.Zhuangkuang;
import com.df.smallwater.smallwater.utils.PreferencesUtils;
import com.yalantis.phoenix.PullToRefreshView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;

public class ZhuangkuangActivity extends BaseActivity {

    @Bind(R.id.tv_base_text)
    TextView tvBaseText;
    @Bind(R.id.iv_base_back)
    ImageView ivBaseBack;
    @Bind(R.id.relativeLayout2)
    RelativeLayout relativeLayout2;
    @Bind(R.id.rlv_zhuangkuang)
    RecyclerView rlvZhuangkuang;
    @Bind(R.id.pull_to_refresh)
    PullToRefreshView pullToRefresh;


    zhuangkuangAdapter zhuangkuangAdapter;

    Message msg;
    List<Zhuangkuang.ZhuangkuanglistBean> list;
    int allcount, page, pagenum;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            zhuangkuangAdapter = new zhuangkuangAdapter(R.layout.item_zhuangkuang_list, list);
            if (allcount>0) {
            } else {
                View v = View.inflate(ZhuangkuangActivity.this,R.layout.layout_head,null);
                zhuangkuangAdapter.addHeaderView(v);
                tvCount = v.findViewById(R.id.tv_count);
                tvCount.setText("您的宝贝状况很好^_^");
            }

            //设置recyclevie布局
            LinearLayoutManager mManager = new LinearLayoutManager(ZhuangkuangActivity.this, LinearLayoutManager.VERTICAL, false);
            rlvZhuangkuang.setLayoutManager(mManager);
            rlvZhuangkuang.setAdapter(zhuangkuangAdapter);
            pullToRefresh.setRefreshing(false);


        }
    };
    TextView tvCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhuangkuang);
        ButterKnife.bind(this);
        SetStatusBarColor();
        tvBaseText.setText("宝贝状况");


        pullToRefresh.setOnRefreshListener(new PullToRefreshView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                rlvZhuangkuang.post(new Runnable() {
                    @Override
                    public void run() {
                        Api.getDefault().getzhuangkuang(PreferencesUtils.getString(ZhuangkuangActivity.this, "username"), 1).compose(RxSchedulers.<Zhuangkuang>io_main()).subscribe(new Subscriber<Zhuangkuang>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onNext(Zhuangkuang Zhuangkuang) {
                                list = Zhuangkuang.getZhuangkuanglist();
                                allcount = Zhuangkuang.getAllcount();
                                page = Zhuangkuang.getPage();
                                pagenum = Zhuangkuang.getPagenum();
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
        Api.getDefault().getzhuangkuang(PreferencesUtils.getString(ZhuangkuangActivity.this, "username"), 1).compose(RxSchedulers.<Zhuangkuang>io_main()).subscribe(new Subscriber<Zhuangkuang>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Zhuangkuang Zhuangkuang) {
                list = Zhuangkuang.getZhuangkuanglist();
                allcount = Zhuangkuang.getAllcount();
                page = Zhuangkuang.getPage();
                pagenum = Zhuangkuang.getPagenum();
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
}
