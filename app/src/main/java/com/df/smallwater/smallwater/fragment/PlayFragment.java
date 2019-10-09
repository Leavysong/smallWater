package com.df.smallwater.smallwater.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.df.smallwater.smallwater.R;
import com.df.smallwater.smallwater.adapter.GameAdapter;
import com.df.smallwater.smallwater.bean.Game;
import com.df.smallwater.smallwater.game.activity.ChessActivity;
import com.df.smallwater.smallwater.game.bigsmall.BigsActivity;
import com.df.smallwater.smallwater.game.link.LinkGameActivity;
import com.df.smallwater.smallwater.game.run.RunActivity;
import com.df.smallwater.smallwater.game.tuixiangzi.TuixiangziActivity;
import com.df.smallwater.smallwater.game.tuxing.TuxingActivity;
import com.df.smallwater.smallwater.game.tzfe.TzfeActivity;
import com.yalantis.phoenix.PullToRefreshView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class PlayFragment extends Fragment implements BaseQuickAdapter.OnItemClickListener {


    @Bind(R.id.list_view)
    RecyclerView listView;
    @Bind(R.id.pull_to_refresh)
    PullToRefreshView pullToRefresh;

    GameAdapter gameAdapter = null ;

    List<Game> list;

    Game game = new Game();

    boolean stopThread = true;



    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_play, container, false);
        ButterKnife.bind(this, view);
        list = new ArrayList<>();


        game = new Game();
        game.setName("动物连连看");
        game.setContent("点击一样的动物，消除全部小动物。");
        game.setImg(R.mipmap.link_logo);
        list.add(game);

        game = new Game();
        game.setName("恐龙快跑");
        game.setContent("加油向前奔跑吧。");
        game.setImg(R.mipmap.run_logo);
        list.add(game);

        game = new Game();
        game.setName("推箱子");
        game.setContent("控制方向键，将箱子推到指定位置。");
        game.setImg(R.mipmap.tuixiangzilogo);
        list.add(game);

        game = new Game();
        game.setName("2048");
        game.setContent("滑动界面. 2 + 2 = 4。 完成 2048。");
        game.setImg(R.mipmap.logo_2048);
        list.add(game);

        game = new Game();
        game.setName("比大小");
        game.setContent("比较水果大小。");
        game.setImg(R.mipmap.bigs);
        list.add(game);

        game = new Game();
        game.setName("五子棋大战");
        game.setContent("两个人一起玩五子棋吧！");
        game.setImg(R.mipmap.wuziqi);
        list.add(game);

        game = new Game();
        game.setName("识图形");
        game.setContent("找出正确的形状。");
        game.setImg(R.mipmap.tuxing);
        list.add(game);




        gameAdapter = new GameAdapter(R.layout.layout_game, list);
        //设置recyclevie布局
        LinearLayoutManager mManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        if (stopThread) {
            listView.setLayoutManager(mManager);
            listView.setAdapter(gameAdapter);
            pullToRefresh.setRefreshing(false);

            gameAdapter.setOnItemClickListener(PlayFragment.this);
        }



        pullToRefresh.setOnRefreshListener(new PullToRefreshView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                listView.post(new Runnable() {
                    @Override
                    public void run() {

                        pullToRefresh.setRefreshing(false);
                    }
                });
            }
        });
        return view;
    }


    @Override
    public void onDestroyView() {
        stopThread = false;
        super.onDestroyView();

        ButterKnife.unbind(this);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Intent i = null ;


                switch(position){

                    case 0:
                        i = new Intent(getActivity(), LinkGameActivity.class);


                        break;
                    case 1:
                        i = new Intent(getActivity(), RunActivity.class);


                        break;
                    case 2:

                        i = new Intent(getActivity(), TuixiangziActivity.class);
                        break;
                    case 3:
                        i = new Intent(getActivity(), TzfeActivity.class);

                        break;
                    case 4:

                        i = new Intent(getActivity(), BigsActivity.class);
                        break;
                    case 5:

                        i = new Intent(getActivity(), ChessActivity.class);
                        break;
                    case 6:

                        i = new Intent(getActivity(), TuxingActivity.class);
                        break;

                }
                getActivity().startActivity(i);

    }
}
