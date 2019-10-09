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

import com.df.smallwater.smallwater.adapter.qingjiaAdapter;
import com.df.smallwater.smallwater.api.Api;
import com.df.smallwater.smallwater.base.BaseActivity;
import com.df.smallwater.smallwater.baserx.RxSchedulers;
import com.df.smallwater.smallwater.bean.Sign;
import com.df.smallwater.smallwater.utils.PreferencesUtils;
import com.yalantis.phoenix.PullToRefreshView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;

public class QingjiaListActivity extends BaseActivity {

    @Bind(R.id.tv_base_text)
    TextView tvBaseText;
    @Bind(R.id.iv_base_back)
    ImageView ivBaseBack;
    @Bind(R.id.rlv_qingjia)
    RecyclerView rlvQingjia;
    @Bind(R.id.pull_to_refresh)
    PullToRefreshView pullToRefresh;

    qingjiaAdapter qingjiaAdapter;

    Message msg;
    List<Sign.QingjialistBean> list;
    int count;

    TextView tvCount;
    RelativeLayout relativeLayout11;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            qingjiaAdapter = new qingjiaAdapter(R.layout.item_qingjia_list, list);
            //设置recyclevie布局
            LinearLayoutManager mManager = new LinearLayoutManager(QingjiaListActivity.this, LinearLayoutManager.VERTICAL, false);
            rlvQingjia.setLayoutManager(mManager);
            rlvQingjia.setAdapter(qingjiaAdapter);
            pullToRefresh.setRefreshing(false);






            if (count>0) {
            } else {
                View v = View.inflate(QingjiaListActivity.this,R.layout.layout_head,null);
                qingjiaAdapter.addHeaderView(v);
                tvCount = v.findViewById(R.id.tv_count);
                tvCount.setText("宝贝还没有请假过呢");
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qingjia_list);
        SetStatusBarColor();
        ButterKnife.bind(this);
        tvBaseText.setText("请假记录");


        pullToRefresh.setOnRefreshListener(new PullToRefreshView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                rlvQingjia.post(new Runnable() {
                    @Override
                    public void run() {
                        Api.getDefault().getqingjia(PreferencesUtils.getString(QingjiaListActivity.this, "username")).compose(RxSchedulers.<Sign>io_main()).subscribe(new Subscriber<Sign>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onNext(Sign Sign) {
                                list = Sign.getQingjialist();
                                count = Sign.getCount();
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
        Api.getDefault().getqingjia(PreferencesUtils.getString(QingjiaListActivity.this, "username")).compose(RxSchedulers.<Sign>io_main()).subscribe(new Subscriber<Sign>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Sign Sign) {
                list = Sign.getQingjialist();
                count = Sign.getCount();
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
