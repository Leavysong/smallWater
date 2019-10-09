package com.df.smallwater.smallwater.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.df.smallwater.smallwater.CallActivity;
import com.df.smallwater.smallwater.DayListActivity;
import com.df.smallwater.smallwater.GonggaoActivity;
import com.df.smallwater.smallwater.LoginActivity;
import com.df.smallwater.smallwater.NewsListActivity;
import com.df.smallwater.smallwater.QingjiaListActivity;
import com.df.smallwater.smallwater.R;
import com.df.smallwater.smallwater.UserActivity;
import com.df.smallwater.smallwater.ZhuangkuangActivity;
import com.df.smallwater.smallwater.api.Api;
import com.df.smallwater.smallwater.baserx.RxSchedulers;
import com.df.smallwater.smallwater.baserx.RxSubscriber;
import com.df.smallwater.smallwater.bean.Version;
import com.df.smallwater.smallwater.utils.PreferencesUtils;
import com.df.smallwater.smallwater.utils.versionUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MineFragment extends Fragment {


    @Bind(R.id.gonggo)
    RelativeLayout gonggo;
    @Bind(R.id.qingkuang)
    RelativeLayout qingkuang;
    @Bind(R.id.richang)
    RelativeLayout richang;
    @Bind(R.id.xinwen)
    RelativeLayout xinwen;
    @Bind(R.id.zhanghao)
    RelativeLayout zhanghao;
    @Bind(R.id.zhuxiao)
    RelativeLayout zhuxiao;
    @Bind(R.id.suggestion)
    RelativeLayout suggestion;
    @Bind(R.id.vup)
    RelativeLayout vup;
    @Bind(R.id.qingjia)
    RelativeLayout qingjia;
    @Bind(R.id.tv_version)
    TextView tvVersion;


    private final versionUtil versionUtil = new versionUtil();

    private Message msg ;
    @SuppressLint("HandlerLeak")
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            Version version = (Version) msg.obj;
            try {
                versionUtil.checkVersion(null,version);



            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    };


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        ButterKnife.bind(this, view);
        tvVersion.setText(PreferencesUtils.getString(getActivity(),"versioncode"));
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.gonggo, R.id.qingkuang, R.id.richang, R.id.xinwen, R.id.zhanghao, R.id.zhuxiao, R.id.suggestion, R.id.vup, R.id.qingjia})
    public void onViewClicked(View view) {
        Intent i;
        switch (view.getId()) {
            case R.id.gonggo:
                i = new Intent(getActivity(), GonggaoActivity.class);
                getActivity().startActivity(i);
                break;
            case R.id.qingkuang:
                i = new Intent(getActivity(), ZhuangkuangActivity.class);
                getActivity().startActivity(i);
                break;
            case R.id.richang:
                i = new Intent(getActivity(), DayListActivity.class);
                getActivity().startActivity(i);
                break;
            case R.id.xinwen:

                i = new Intent(getActivity(), NewsListActivity.class);
                getActivity().startActivity(i);
                break;
            case R.id.zhanghao:
                i = new Intent(getActivity(), UserActivity.class);
                getActivity().startActivity(i);
                break;
            case R.id.zhuxiao:
                PreferencesUtils.clear(getActivity(), "username");
                PreferencesUtils.clear(getActivity(), "password");
                PreferencesUtils.clear(getActivity(), "birthday");
                PreferencesUtils.clear(getActivity(), "address");
                PreferencesUtils.clear(getActivity(), "code");
                PreferencesUtils.clear(getActivity(), "mothername");
                PreferencesUtils.clear(getActivity(), "phone");
                PreferencesUtils.clear(getActivity(), "name");
                PreferencesUtils.clear(getActivity(), "sex");
                PreferencesUtils.clear(getActivity(), "wechat");
                PreferencesUtils.clear(getActivity(), "banjiid");
                PreferencesUtils.clear(getActivity(), "youeryuanid");
                i = new Intent(getActivity(), LoginActivity.class);
                getActivity().startActivity(i);
                getActivity().finish();
                break;
            case R.id.suggestion:
                i = new Intent(getActivity(), CallActivity.class);
                getActivity().startActivity(i);
                break;
            case R.id.vup:
                versionUtil.setContext(getActivity());
                Api.getDefault().getversion(0).compose(RxSchedulers.<Version>io_main()).subscribe(new RxSubscriber<Version>(getActivity(),false) {
                    @Override
                    protected void _onNext(Version version) {
                        msg = Message.obtain();
                        msg.obj = version;
                        handler.sendMessage(msg);


                    }

                    @Override
                    protected void _onError(String message) {

                    }
                });

                break;
            case R.id.qingjia:
                i = new Intent(getActivity(), QingjiaListActivity.class);
                getActivity().startActivity(i);
                break;
        }
    }

}
