package com.df.smallwater.smallwater;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.df.smallwater.smallwater.base.BaseActivity;
import com.df.smallwater.smallwater.utils.SayUtil;

import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SMath3Activity extends BaseActivity {

    @Bind(R.id.tv_base_text)
    TextView tvBaseText;
    @Bind(R.id.iv_base_back)
    ImageView ivBaseBack;
    @Bind(R.id.tv_question)
    TextView tvQuestion;
    int index ;

    MediaPlayer mMediaPlayer;
    int result;
    Random random = new Random();
    boolean stopThread = true;
    int a, b;
    Message msg;
    int type = 0;
    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (stopThread) {
                SayUtil.say(getQuestion(), "0","5");

            }
        }
    };

    int page;
    @Bind(R.id.tv_left)
    TextView tvLeft;
    @Bind(R.id.rl_left)
    RelativeLayout rlLeft;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.rl_right)
    RelativeLayout rlRight;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smath3);
        ButterKnife.bind(this);
        SetStatusBarColor();
        Bundle extras = getIntent().getExtras();
        page = extras.getInt("type");

        if (page == 3) {
            tvBaseText.setText("10以内加法测试");
        } else if (page == 4) {
            tvBaseText.setText("20以内加法测试");
        } else if (page == 6) {
            tvBaseText.setText("10以内减法测试");
        } else if (page == 7) {
            tvBaseText.setText("20以内减法测试");
        } else if (page == 8) {
            tvBaseText.setText("10以内加减法混合测试");
        } else if (page == 9) {
            tvBaseText.setText("20以内加减法混合测试");
        }
        msg = Message.obtain();
        handler.sendMessage(msg);

    }

    @OnClick({R.id.iv_base_back,R.id.rl_left,R.id.rl_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_base_back:
                this.finish();
                break;
            case R.id.rl_left:


                doright(0);

                break;
            case R.id.rl_right:
                doright(1);
                break;
        }
    }

    private void doright(int x) {

        if(index==x){
            SayUtil.stop();
            showLongToast("你真棒^-^");
            mMediaPlayer = MediaPlayer.create(SMath3Activity.this, R.raw.yes);
            mMediaPlayer.start();
            mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    mediaPlayer.release();
                    msg = Message.obtain();
                    handler.sendMessage(msg);
                }
            });


        }
       else{
            SayUtil.stop();
            showLongToast("加油再想想^-^");
            mMediaPlayer = MediaPlayer.create(SMath3Activity.this, R.raw.wrong);
            mMediaPlayer.start();
            mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    mediaPlayer.release();
                }
            });
        }



    }

    public String getQuestion() {
        int x;
        String result;
        switch (page) {
            case 3:
                type = 0;
                a = random.nextInt(9);
                b = random.nextInt(9);
                break;
            case 4:
                type = 0;
                a = random.nextInt(19);
                x = 20 - 19;
                b = random.nextInt(x);
                break;
            case 6:
                type = 1;
                a = random.nextInt(9) + 1;
                b = random.nextInt(a) + 1;
                break;
            case 7:
                type = 1;
                a = random.nextInt(19) + 1;
                b = random.nextInt(a) + 1;
                break;
            case 8:
                type = random.nextInt(2);
                //0加法1减法
                if (type == 0) {
                    a = random.nextInt(9);
                    b = random.nextInt(9);
                } else {
                    a = random.nextInt(9) + 1;
                    b = random.nextInt(a) + 1;
                }

                break;
            case 9:
                type = random.nextInt(2);
                //0加法1减法
                if (type == 0) {
                    a = random.nextInt(19);
                    x = 20 - 19;
                    b = random.nextInt(x);
                } else {
                    a = random.nextInt(19) + 1;
                    b = random.nextInt(a) + 1;
                }
                break;

        }

        if (type == 0) {
            result = "小朋友请问" + a + "加上" + b + "等于多少啊？";
            tvQuestion.setText(result);
            setanwser(a+b);
            return result;
        } else {
            result = "小朋友请问" + a + "减去" + b + "等于多少啊？";
            tvQuestion.setText(result);
            setanwser(a-b);
            return result;
        }

    }

    private void setanwser(int anwswer){

        Random rand = new Random();
        index = 0 ;
        int flag ;
        index = rand.nextInt(2);
        if(index==0){
            tvLeft.setText(anwswer+"");
            if(type==4||type==7||type==9){

                flag = rand.nextInt(9);
                while(anwswer==flag){
                    flag = rand.nextInt(9);
                }
                tvRight.setText(flag+"");
            }else{
                flag = rand.nextInt(19);
                while(anwswer==flag){
                    flag = rand.nextInt(19);
                }
                tvRight.setText(flag+"");
            }

        }else{
            tvRight.setText(anwswer+"");
            if(type==5||type==7||type==9){
                flag = rand.nextInt(9);
                while(anwswer==flag){
                    flag = rand.nextInt(9);
                }
                tvLeft.setText(flag+"");

            }else{
                flag = rand.nextInt(19);
                while(anwswer==flag){
                    flag = rand.nextInt(19);
                }
                tvLeft.setText(flag+"");

            }
        }

    }

    @Override
    protected void onDestroy() {
        SayUtil.stop();
        super.onDestroy();
    }
}
