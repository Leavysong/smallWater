package com.df.smallwater.smallwater.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.df.smallwater.smallwater.R;
import com.df.smallwater.smallwater.ShuxueActivity;
import com.df.smallwater.smallwater.YingyuActivity;
import com.df.smallwater.smallwater.YuwenActivity;
import com.df.smallwater.smallwater.adapter.HomeWorkAdapter;
import com.df.smallwater.smallwater.api.Api;
import com.df.smallwater.smallwater.baserx.RxSchedulers;
import com.df.smallwater.smallwater.bean.Homework;
import com.df.smallwater.smallwater.utils.PreferencesUtils;
import com.yalantis.phoenix.PullToRefreshView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Subscriber;


public class HomeWorkFragment extends Fragment implements BaseQuickAdapter.OnItemChildClickListener{


    HomeWorkAdapter homeWorkAdapter;
    @Bind(R.id.list_view)
    RecyclerView listView;
    @Bind(R.id.pull_to_refresh)
    PullToRefreshView pullToRefresh;

    List<Homework.HomeworkBean> list;

    Message msg;

    int result = 0;

    TextView tv_yanem;
    boolean stopThread = true;

    Activity f ;

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {




                homeWorkAdapter = new HomeWorkAdapter(R.layout.item_homework_list, list);
                //设置recyclevie布局
                LinearLayoutManager mManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                if (stopThread) {
                    listView.setLayoutManager(mManager);
                    listView.setAdapter(homeWorkAdapter);
                    pullToRefresh.setRefreshing(false);

                    homeWorkAdapter.setOnItemChildClickListener(HomeWorkFragment.this);

                    View v = View.inflate(getActivity(), R.layout.bottom_yname, null);
                    homeWorkAdapter.addFooterView(v);
                    tv_yanem = v.findViewById(R.id.tv_youeryuan);
                    tv_yanem.setText(PreferencesUtils.getString(getActivity(), "yname"));
                    pullToRefresh.setRefreshing(false);
                    if (result==1) {
                        View v1 = View.inflate(getActivity(),R.layout.layout_head,null);

                        tvCount = v1.findViewById(R.id.tv_count);
                        tvCount.setText("老师还未布置作业");
                        homeWorkAdapter.addHeaderView(v1);
                    }
                }




        }
    };
    TextView tvCount;

    @Override
    public void onDestroy() {
        stopThread = false;
        super.onDestroy();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_work, container, false);
        ButterKnife.bind(this, view);

        pullToRefresh.setOnRefreshListener(new PullToRefreshView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                listView.post(new Runnable() {
                    @Override
                    public void run() {
                        Api.getDefault().getHomework(PreferencesUtils.getInt(getActivity(), "banjiid")).compose(RxSchedulers.<Homework>io_main()).subscribe(new Subscriber<Homework>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onNext(Homework homework) {
                                list = new ArrayList<>();
                                result = homework.getResult();
                                list = homework.getHomework();
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
        Api.getDefault().getHomework(PreferencesUtils.getInt(getActivity(), "banjiid")).compose(RxSchedulers.<Homework>io_main()).subscribe(new Subscriber<Homework>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Homework homework) {
                list = new ArrayList<>();
                list = homework.getHomework();
                result = homework.getResult();
                msg = Message.obtain();
                handler.sendMessage(msg);
                pullToRefresh.setRefreshing(false);
            }

        });


        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }



    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
Intent i ;
Bundle bundle ;
        switch(view.getId()){

            case R.id.rl_yuwen:
                i = new Intent(getActivity(), YuwenActivity.class);
                bundle=new Bundle();
                bundle.putString("text",list.get(position).getYuwen());
                i.putExtras(bundle);
                getActivity().startActivity(i);
                break;

            case R.id.rl_yingyu:
                i = new Intent(getActivity(), YingyuActivity.class);
                bundle=new Bundle();
                bundle.putString("word",list.get(position).getYingyu());
                i.putExtras(bundle);
                getActivity().startActivity(i);
                break;

            case R.id.rl_shuxue:
                i = new Intent(getActivity(), ShuxueActivity.class);
                bundle=new Bundle();
                bundle.putString("shuxue",list.get(position).getShuxue());
                i.putExtras(bundle);
                getActivity().startActivity(i);
                break;

        }
    }
}
