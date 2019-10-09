package com.df.smallwater.smallwater.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.df.smallwater.smallwater.R;
import com.df.smallwater.smallwater.bean.Gonggao;
import com.df.smallwater.smallwater.bean.Sign;

import java.util.List;

/**
 * Created by DF on 2018/4/10.
 */

public class qingjiaAdapter extends BaseQuickAdapter<Sign.QingjialistBean, BaseViewHolder> {




    public qingjiaAdapter(int layoutResId, @Nullable List<Sign.QingjialistBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Sign.QingjialistBean item) {
        helper.setText(R.id.qingjia_title,item.getContent());
        helper.setText(R.id.qingjia_time,item.getTime());
    }
}
