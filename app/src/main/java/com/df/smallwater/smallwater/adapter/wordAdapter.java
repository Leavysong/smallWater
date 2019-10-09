package com.df.smallwater.smallwater.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.df.smallwater.smallwater.R;
import com.df.smallwater.smallwater.bean.Word;

import java.util.List;

/**
 * Created by DF on 2018/4/10.
 */

public class wordAdapter extends BaseQuickAdapter<Word.WordlistBean, BaseViewHolder> {




    public wordAdapter(int layoutResId, @Nullable List<Word.WordlistBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Word.WordlistBean item) {



        helper.setText(R.id.tv_word,"词汇： "+item.getWord());
        helper.setText(R.id.tv_mean,"词义： "+item.getMean());
        helper.setText(R.id.tv_us,"音标： /"+item.getPronunciation_us()+"/");
    }
}
