package com.df.smallwater.smallwater.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.df.smallwater.smallwater.GonggaoActivity;
import com.df.smallwater.smallwater.HanziActivity;
import com.df.smallwater.smallwater.MathActivity;
import com.df.smallwater.smallwater.NewsActivity;
import com.df.smallwater.smallwater.NewsListActivity;
import com.df.smallwater.smallwater.PinYInActivity;
import com.df.smallwater.smallwater.QingjiaActivity;
import com.df.smallwater.smallwater.R;
import com.df.smallwater.smallwater.WordActivity;
import com.df.smallwater.smallwater.adapter.NewsAdapter;
import com.df.smallwater.smallwater.bean.Gonggao;
import com.df.smallwater.smallwater.bean.Xinwen;
import com.df.smallwater.smallwater.bean.Youeryuan;
import com.df.smallwater.smallwater.utils.JsonUtil;
import com.df.smallwater.smallwater.utils.PreferencesUtils;
import com.google.gson.Gson;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainFragment extends Fragment implements OnBannerListener, BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.OnItemChildClickListener {



    @Bind(R.id.btn_hanzi)
    Button btnHanzi;
    @Bind(R.id.btn_math)
    Button btnMath;
    @Bind(R.id.btn_english)
    Button btnEnglish;
    @Bind(R.id.banner)
    Banner banner;
    @Bind(R.id.tv_gonggao)
    TextView tvGonggao;
    @Bind(R.id.recyclerView_toutiao)
    RecyclerView recyclerViewToutiao;
    @Bind(R.id.rl_gonggao)
    RelativeLayout rl_gonggao;
    @Bind(R.id.tv_top)
    TextView tvTop;
    @Bind(R.id.rl_xinwen)
    RelativeLayout rlXinwen;

    List<Xinwen.XinwenlistBean> xlist;
    @Bind(R.id.tv_qingjia)
    TextView tvQingjia;
    private ArrayList<String> list_path;
    private ArrayList<String> list_title;

    NewsAdapter newsAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        try {
            initUI();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return view;
    }

    private void initUI() throws JSONException {
        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(),"fonts/zz.ttf");
        tvTop.setTypeface(typeface);
        tvTop.setText(PreferencesUtils.getString(getActivity(), "yname"));
        //放图片地址的集合
        list_path = new ArrayList<>();
        //放标题的集合
        list_title = new ArrayList<>();

        String s = PreferencesUtils.getString(getActivity(), "piclist");

        List<Youeryuan.YoueryuanpicBean> list = JsonUtil.Json2Youeryuanpic(s);

        for (int i = 0; i < list.size(); i++) {
            list_path.add(list.get(i).getPic());
            list_title.add(list.get(i).getTitle());
        }

        String x = PreferencesUtils.getString(getActivity(), "xlist");

        String g = PreferencesUtils.getString(getActivity(), "glist");

        xlist = JsonUtil.Json2xinwen(x);

        List<Gonggao.GonggaolistBean> glist = JsonUtil.Json2gonggao(g);

        if (glist.size() > 0) {
            tvGonggao.setText(glist.get(0).getContent());
        } else {
            tvGonggao.setText("暂无公告");
        }


        //设置内置样式，共有六种可以点入方法内逐一体验使用。
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        //设置图片加载器，图片加载器在下方
        banner.setImageLoader(new MyLoader());
        //设置图片网址或地址的集合
        banner.setImages(list_path);
        //设置轮播的动画效果，内含多种特效，可点入方法内查找后内逐一体验
        banner.setBannerAnimation(Transformer.Default);
        //设置轮播图的标题集合
        banner.setBannerTitles(list_title);
        //设置轮播间隔时间
        banner.setDelayTime(3000);
        //设置是否为自动轮播，默认是“是”。
        banner.isAutoPlay(true);
        //设置指示器的位置，小点点，左中右。
        banner.setIndicatorGravity(BannerConfig.CENTER)
                //以上内容都可写成链式布局，这是轮播图的监听。比较重要。方法在下面。
                .setOnBannerListener(this)
                //必须最后调用的方法，启动轮播图。
                .start();


        List<Xinwen.XinwenlistBean> dataList = new ArrayList<>();
        //初始化数据和适配器
        dataList.addAll(xlist);

        newsAdapter = new NewsAdapter(R.layout.item_news_list, dataList);
        //设置recyclevie布局
        LinearLayoutManager mManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerViewToutiao.setLayoutManager(mManager);
        recyclerViewToutiao.setAdapter(newsAdapter);

        newsAdapter.setOnItemChildClickListener(this);
        newsAdapter.setOnItemClickListener(this);


    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

        Bundle bundle = new Bundle();
        Gson g = new Gson();
        String json = g.toJson(xlist.get(position));
        bundle.putString("json", json);
        Intent i = new Intent(getActivity(), NewsActivity.class);
        i.putExtra("json", json);
        getActivity().startActivity(i);
    }


    //轮播图的监听方法
    @Override
    public void OnBannerClick(int position) {
        Log.i("tag", "你点了第" + position + "张轮播图");
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }


    //自定义的图片加载器
    private class MyLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load((String) path).into(imageView);
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.btn_hanzi, R.id.btn_math, R.id.btn_english, R.id.tv_top, R.id.banner, R.id.rl_gonggao, R.id.rl_xinwen,R.id.tv_qingjia})
    public void onViewClicked(View view) {
        Intent i ;
        switch (view.getId()) {

//            case R.id.btn_pinyin:
//                Intent i = new Intent(getActivity(), PinYInActivity.class);
//                getActivity().startActivity(i);
//                break;


            case R.id.btn_hanzi:
                i = new Intent(getActivity(), HanziActivity.class);
                getActivity().startActivity(i);
                break;
            case R.id.btn_math:
                i = new Intent(getActivity(), MathActivity.class);
                getActivity().startActivity(i);

                break;
            case R.id.btn_english:
                i = new Intent(getActivity(), WordActivity.class);
                getActivity().startActivity(i);
                break;
            case R.id.tv_top:
                break;
            case R.id.tv_qingjia:
                i = new Intent(getActivity(), QingjiaActivity.class);
                getActivity().startActivity(i);
                break;
            case R.id.banner:
                break;
            case R.id.rl_gonggao:
                i = new Intent(getActivity(), GonggaoActivity.class);
                getActivity().startActivity(i);
                break;
            case R.id.rl_xinwen:
                i = new Intent(getActivity(), NewsListActivity.class);
                getActivity().startActivity(i);
                break;

        }
    }


}
