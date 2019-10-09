package com.df.smallwater.smallwater.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.df.smallwater.smallwater.R;
import com.youdao.sdk.ydtranslate.Translate;

import java.util.List;

/**
 * Created by DF on 2018/4/10.
 */

public class YingyuAdapter extends BaseQuickAdapter<Translate, BaseViewHolder> {


private Context context ;

    public YingyuAdapter(int layoutResId, @Nullable List<Translate> data , Context c) {
        super(layoutResId, data);
        context = c ;
    }

    @Override
    protected void convert(BaseViewHolder helper, final Translate item) {


        helper.setText(R.id.tv_word,item.getQuery());
        helper.setText(R.id.tv_us,"/"+item.getUkPhonetic()+"/");
        String Explains = "";
        for (int i = 0; i < item.getExplains().size(); i++) {
            Explains = Explains + item.getExplains().get(i) + "、" ;
        }
        helper.setText(R.id.tv_mean,Explains);

        webExplainsAdapter webExplainsAdapter = new webExplainsAdapter(R.layout.item_explain_list_content,item.getWebExplains());
        RecyclerView RecyclerView = new RecyclerView(context);
        RecyclerView = helper.getView(R.id.rlv_word);
        //设置recyclevie布局
        LinearLayoutManager mManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        RecyclerView.setLayoutManager(mManager);
        RecyclerView.setAdapter(webExplainsAdapter);


    }
}
