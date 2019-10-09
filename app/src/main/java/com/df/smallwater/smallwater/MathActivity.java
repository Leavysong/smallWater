package com.df.smallwater.smallwater;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.df.smallwater.smallwater.adapter.mathlistAdapter;
import com.df.smallwater.smallwater.base.BaseActivity;
import com.df.smallwater.smallwater.bean.Title;
import com.yalantis.phoenix.PullToRefreshView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MathActivity extends BaseActivity implements View.OnClickListener ,  BaseQuickAdapter.OnItemClickListener{

    @Bind(R.id.tv_base_text)
    TextView tvBaseText;
    @Bind(R.id.iv_base_back)
    ImageView ivBaseBack;

    List<Title> list;
    @Bind(R.id.rlv_math)
    RecyclerView rlvMath;
    @Bind(R.id.pull_to_refresh)
    PullToRefreshView pullToRefresh;

    mathlistAdapter hanzilistAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math);
        ButterKnife.bind(this);
        SetStatusBarColor();
        tvBaseText.setText("加减法");
        list = new ArrayList<>();
        Title title = new Title();
        title.setTitle("第1课   认数字 0~4");
        title.setPic("http://pacnmzg6f.bkt.clouddn.com/img0.png");
        list.add(title);
        title = new Title();
        title.setTitle("第2课   认数字 5~9");
        title.setPic("http://pacnmzg6f.bkt.clouddn.com/img5.png");
        list.add(title);
        title = new Title();
        title.setTitle("第3课   加法口诀");
        title.setPic("http://pacnmzg6f.bkt.clouddn.com/imgadd.jpg");
        list.add(title);
        title = new Title();
        title.setTitle("第4课   10以内加法测试");
        title.setPic("http://pacnmzg6f.bkt.clouddn.com/10add.jpg");
        list.add(title);
        title = new Title();
        title.setTitle("第5课   20以内加法测试");
        title.setPic("http://pacnmzg6f.bkt.clouddn.com/20add.jpg");
        list.add(title);
        title = new Title();
        title.setTitle("第6课   减法口诀");
        title.setPic("http://pacnmzg6f.bkt.clouddn.com/imgreduce.jpg");
        list.add(title);title = new Title();
        title.setTitle("第7课   10以内减法测试");
        title.setPic("http://pacnmzg6f.bkt.clouddn.com/10reduce.jpg");
        list.add(title);
        title = new Title();
        title.setTitle("第8课   20以内减法测试");
        title.setPic("http://pacnmzg6f.bkt.clouddn.com/20reduce.jpg");
        list.add(title);
        title = new Title();
        title.setTitle("第9课   10以内混合测试");
        title.setPic("http://pacnmzg6f.bkt.clouddn.com/10mix.jpg");
        list.add(title);
        title = new Title();
        title.setTitle("第10课   20以内混合测试");
        title.setPic("http://pacnmzg6f.bkt.clouddn.com/20mix.jpg");
        list.add(title);
        hanzilistAdapter = new mathlistAdapter(R.layout.item_hanzi_list, list,this);
        //设置recyclevie布局
        LinearLayoutManager mManager = new LinearLayoutManager(MathActivity.this, LinearLayoutManager.VERTICAL, false);
        rlvMath.setLayoutManager(mManager);
        rlvMath.setAdapter(hanzilistAdapter);
        hanzilistAdapter.setOnItemClickListener(MathActivity.this);
        pullToRefresh.setRefreshing(false);

        pullToRefresh.setOnRefreshListener(new PullToRefreshView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                rlvMath.post(new Runnable() {
                    @Override
                    public void run() {
                        pullToRefresh.setRefreshing(false);
                    }
                });
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

    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

        Bundle bundle = new Bundle();
        bundle.putInt("type",position);
if(position==0||position==1){
    readyGo(SMath1Activity.class,bundle);
}else if(position==2||position==5){
    switch (position){

        case 2:
            readyGo(SMath2Activity.class,bundle);
            break;
        case 5:
            readyGo(SMath2Activity.class,bundle);
            break;
    }

}
else{
    readyGo(SMath3Activity.class,bundle);
}


    }
}
