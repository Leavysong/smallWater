package com.df.smallwater.smallwater.adapter;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.df.smallwater.smallwater.MainActivity;
import com.df.smallwater.smallwater.R;

import java.util.List;

/**
 * Created by DF on 2018/4/17.
 */

public class PYPagerAdapter extends PagerAdapter {


    private List<View> datas;

    private List<String> titls;

    private Context context ;

    public PYPagerAdapter(List<View> list , List<String> list2 , Context con) {
        super();
        this.datas = list;
        this.titls = list2 ;
        this.context = con ;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }


    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        container.addView(datas.get(position));

        return datas.get(position);
    }



    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(datas.get(position));
    }

    //重写这个方法，将设置每个Tab的标题
    @Override
    public CharSequence getPageTitle(int position) {
        return "";
    }
}
