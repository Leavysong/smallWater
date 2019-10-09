package com.df.smallwater.smallwater;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.df.smallwater.smallwater.api.Api;
import com.df.smallwater.smallwater.base.BaseActivity;
import com.df.smallwater.smallwater.baserx.RxSchedulers;
import com.df.smallwater.smallwater.baserx.RxSubscriber;
import com.df.smallwater.smallwater.bean.Version;
import com.df.smallwater.smallwater.fragment.HomeWorkFragment;
import com.df.smallwater.smallwater.fragment.MainFragment;
import com.df.smallwater.smallwater.fragment.MineFragment;
import com.df.smallwater.smallwater.fragment.PlayFragment;
import com.df.smallwater.smallwater.utils.SayUtil;
import com.df.smallwater.smallwater.utils.versionUtil;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {


    @Bind(R.id.iv_bottom_learn)
    ImageView ivBottomLearn;
    @Bind(R.id.tv_bottom_learn)
    TextView tvBottomLearn;
    @Bind(R.id.iv_bottom_homework)
    ImageView ivBottomHomework;
    @Bind(R.id.tv_bottom_homework)
    TextView tvBottomHomework;
    @Bind(R.id.iv_bottom_play)
    ImageView ivBottomPlay;
    @Bind(R.id.tv_bottom_play)
    TextView tvBottomPlay;
    @Bind(R.id.iv_bottom_user)
    ImageView ivBottomUser;
    @Bind(R.id.tv_bottom_user)
    TextView tvBottomUser;
    @Bind(R.id.linearLayout_main)
    LinearLayout linearLayoutMain;
    @Bind(R.id.frameLayout_main)
    FrameLayout frameLayoutMain;
    @Bind(R.id.relativeLayout_bottom_learn)
    RelativeLayout relativeLayoutBottomLearn;
    @Bind(R.id.relativeLayout_bottom_homework)
    RelativeLayout relativeLayoutBottomHomework;
    @Bind(R.id.relativeLayout_bottom_play)
    RelativeLayout relativeLayoutBottomPlay;
    @Bind(R.id.relativeLayout_bottom_user)
    RelativeLayout relativeLayoutBottomUser;


    FragmentManager fragmentManager;
    FragmentTransaction beginTransaction;
    MainFragment mainFragment;
    HomeWorkFragment homeWorkFragment;
    MineFragment mineFragment;
    PlayFragment playFragment;


    boolean isExit ;

    private final versionUtil versionUtil = new versionUtil();

    private Message msg ;

    private static final int REQUEST_CODE_WRITE_STORAGE = 1002;
    private static final int REQUEST_CODE_UNKNOWN_APP = 2001;

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
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        SayUtil.init(this);
        super.onCreate(savedInstanceState);

        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        SetStatusBarColor();

        versionUtil.setContext(this);
        //安装应用的逻辑
        Api.getDefault().getversion(0).compose(RxSchedulers.<Version>io_main()).subscribe(new RxSubscriber<Version>(this,false) {
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


        initBottom();
        ivBottomLearn.setImageResource(R.mipmap.learn);
        tvBottomLearn.setTextColor(getResources().getColor(R.color.base_text_color));
        fragmentManager = getSupportFragmentManager();
        mainFragment = new MainFragment();
        beginTransaction = fragmentManager.beginTransaction();
        beginTransaction.replace(R.id.frameLayout_main, mainFragment);
        beginTransaction.commit();
    }


    private void initBottom() {
        ivBottomLearn.setImageResource(R.mipmap.learn1);
        ivBottomHomework.setImageResource(R.mipmap.homework1);
        ivBottomPlay.setImageResource(R.mipmap.play1);
        ivBottomUser.setImageResource(R.mipmap.user1);
        tvBottomLearn.setTextColor(getResources().getColor(R.color.text_color));
        tvBottomHomework.setTextColor(getResources().getColor(R.color.text_color));
        tvBottomPlay.setTextColor(getResources().getColor(R.color.text_color));
        tvBottomUser.setTextColor(getResources().getColor(R.color.text_color));
    }


    @OnClick({R.id.relativeLayout_bottom_learn, R.id.relativeLayout_bottom_homework, R.id.relativeLayout_bottom_play, R.id.relativeLayout_bottom_user})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.relativeLayout_bottom_learn:
                initBottom();
                mainFragment = new MainFragment();
                ivBottomLearn.setImageResource(R.mipmap.learn);
                tvBottomLearn.setTextColor(getResources().getColor(R.color.base_text_color));
                beginTransaction = fragmentManager.beginTransaction();
                beginTransaction.replace(R.id.frameLayout_main, mainFragment);
                beginTransaction.commit();
                break;
            case R.id.relativeLayout_bottom_homework:
                initBottom();
                homeWorkFragment = new HomeWorkFragment();
                ivBottomHomework.setImageResource(R.mipmap.homework);
                tvBottomHomework.setTextColor(getResources().getColor(R.color.base_text_color));
                beginTransaction = fragmentManager.beginTransaction();
                beginTransaction.replace(R.id.frameLayout_main, homeWorkFragment);
                beginTransaction.commit();
                break;
            case R.id.relativeLayout_bottom_play:
                initBottom();
                playFragment = new PlayFragment();
                ivBottomPlay.setImageResource(R.mipmap.play);
                tvBottomPlay.setTextColor(getResources().getColor(R.color.base_text_color));
                beginTransaction = fragmentManager.beginTransaction();
                beginTransaction.replace(R.id.frameLayout_main, playFragment);
                beginTransaction.commit();
                break;
            case R.id.relativeLayout_bottom_user:
                initBottom();
                mineFragment = new MineFragment();
                ivBottomUser.setImageResource(R.mipmap.user);
                tvBottomUser.setTextColor(getResources().getColor(R.color.base_text_color));
                beginTransaction = fragmentManager.beginTransaction();
                beginTransaction.replace(R.id.frameLayout_main, mineFragment);
                beginTransaction.commit();
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {


            exitByDoubleClick();
        }
        return true;
    }

    private void exitByDoubleClick() {
        Timer tExit=null;
        if(!isExit){
            isExit=true;
            Toast.makeText(this,"再按一次退出程序",Toast.LENGTH_SHORT).show();
            tExit=new Timer();
            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit=false;//取消退出
                }
            },2000);// 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务
        }else{
            finish();
            System.exit(0);
        }
    }


}
