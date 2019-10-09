package com.df.smallwater.smallwater.game.tuxing;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.df.smallwater.smallwater.R;
import com.df.smallwater.smallwater.utils.ToastUtils;

import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TuxingActivity extends AppCompatActivity {

    @Bind(R.id.tv_question)
    TextView tvQuestion;
    @Bind(R.id.iv_1)
    ImageView iv1;
    @Bind(R.id.iv_2)
    ImageView iv2;

    int question = 0;

    int question2 = 0;

    int anwser = 0;

    Message msg ;

    MediaPlayer mMediaPlayer;

    String[] tuxing = {"圆形", "正方形", "三角形", "长方形", "五边形", "菱形", "梯形", "五角星"};

    Integer[] tuxingimg = {R.mipmap.yuanxing, R.mipmap.zhengfangxing, R.mipmap.sanjiaoxing, R.mipmap.juxing, R.mipmap.wubianxing, R.mipmap.lingxing, R.mipmap.tixing, R.mipmap.xingxing};

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            tvQuestion.setText("小朋友请问哪个是" + tuxing[question]);

            if (anwser == 0) {
                iv1.setImageResource(tuxingimg[question]);
                iv2.setImageResource(tuxingimg[question2]);
            } else {
                iv1.setImageResource(tuxingimg[question2]);
                iv2.setImageResource(tuxingimg[question]);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tuxing);
        ButterKnife.bind(this);

        initUI();
    }

    private void initUI() {

        doquestion();

    }

    private void doquestion() {


        Random random = new Random();

        question = random.nextInt(tuxing.length);

        question2 = random.nextInt(tuxing.length);

        while (question2 == question) {
            question2 = random.nextInt(tuxing.length);
        }

        anwser = random.nextInt(2);

        Log.e("随机数",anwser+"+++123");

        msg = Message.obtain();

        handler.sendMessage(msg);

    }

    @OnClick({R.id.iv_1, R.id.iv_2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_1:
                if(anwser==0){
                    ToastUtils.showLongToast(TuxingActivity.this,"你真棒");
                    mMediaPlayer = MediaPlayer.create(TuxingActivity.this, R.raw.yes);
                    mMediaPlayer.start();
                    mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            mediaPlayer.release();
                            doquestion();

                        }
                    });
                }else{
                    ToastUtils.showLongToast(TuxingActivity.this,"再想想");
                    mMediaPlayer = MediaPlayer.create(TuxingActivity.this, R.raw.wrong);
                    mMediaPlayer.start();
                    mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            mediaPlayer.release();


                        }
                    });
                }
                break;
            case R.id.iv_2:
                if(anwser==0){
                    ToastUtils.showLongToast(TuxingActivity.this,"再想想");
                    mMediaPlayer = MediaPlayer.create(TuxingActivity.this, R.raw.wrong);
                    mMediaPlayer.start();
                    mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            mediaPlayer.release();


                        }
                    });
                }else{
                    ToastUtils.showLongToast(TuxingActivity.this,"你真棒");
                    mMediaPlayer = MediaPlayer.create(TuxingActivity.this, R.raw.yes);
                    mMediaPlayer.start();
                    mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            mediaPlayer.release();
                            doquestion();

                        }
                    });
                }
                break;
        }
    }
}
