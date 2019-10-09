package com.df.smallwater.smallwater.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.df.smallwater.smallwater.R;
import com.youdao.sdk.ydtranslate.WebExplain;

import java.util.List;

/**
 * Created by DF on 2018/4/10.
 */

public class webExplainsAdapter extends BaseQuickAdapter<WebExplain, BaseViewHolder> {




    public webExplainsAdapter(int layoutResId, @Nullable List<WebExplain> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, WebExplain item) {


        helper.setText(R.id.tv_explain_word,item.getKey());

        String Explains = "";
        for (int i = 0; i < item.getMeans().size(); i++) {
            Explains = Explains + item.getMeans().get(i) + "、" ;
        }
        helper.setText(R.id.tv_explain_mean,Explains);

    }
}
