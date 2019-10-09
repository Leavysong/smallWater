package com.df.smallwater.smallwater.game.bigsmall;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.df.smallwater.smallwater.R;
import com.df.smallwater.smallwater.utils.ToastUtils;

import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BigsActivity extends AppCompatActivity {

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

    Random random = new Random();

    Integer[] tuxingimg = {R.mipmap.bs0, R.mipmap.bs1, R.mipmap.bs2, R.mipmap.bs3, R.mipmap.bs4, R.mipmap.bs5, R.mipmap.bs6};

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            tvQuestion.setText("小朋友请问哪个水果更大些呢");
            int big = random.nextInt(50)+350;
            int small = random.nextInt(50)+200;
                if (anwser == 0) {
                iv1.setBackgroundResource(tuxingimg[question]);
                ViewGroup.LayoutParams params = iv1.getLayoutParams();
                params.height=big;
                params.width =big;
                iv1.setLayoutParams(params);

                Random random = new Random();
                iv2.setBackgroundResource(tuxingimg[question2]);
                ViewGroup.LayoutParams params1 = iv2.getLayoutParams();
                params1.height=small;
                params1.width =small;
                iv2.setLayoutParams(params1);

            } else {
                iv1.setBackgroundResource(tuxingimg[question2]);
                ViewGroup.LayoutParams params = iv1.getLayoutParams();
                params.height=small;
                params.width =small;
                iv1.setLayoutParams(params);

                Random random = new Random();
                iv2.setBackgroundResource(tuxingimg[question]);
                ViewGroup.LayoutParams params1 = iv2.getLayoutParams();
                params1.height=big;
                params1.width =big;
                iv2.setLayoutParams(params1);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bigs);
        ButterKnife.bind(this);

        initUI();
    }

    private void initUI() {

        doquestion();

    }

    private void doquestion() {





        question = random.nextInt(tuxingimg.length);

        question2 = random.nextInt(tuxingimg.length);

        while (question2 == question) {
            question2 = random.nextInt(tuxingimg.length);
        }

        anwser = random.nextInt(2);

        msg = Message.obtain();

        handler.sendMessage(msg);

    }

    @OnClick({R.id.iv_1, R.id.iv_2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_1:
                if(anwser==0){
                    ToastUtils.showLongToast(BigsActivity.this,"你真棒");
                    mMediaPlayer = MediaPlayer.create(BigsActivity.this, R.raw.yes);
                    mMediaPlayer.start();
                    mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            mediaPlayer.release();
                            doquestion();

                        }
                    });
                }else{
                    ToastUtils.showLongToast(BigsActivity.this,"再想想");
                    mMediaPlayer = MediaPlayer.create(BigsActivity.this, R.raw.wrong);
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
                    ToastUtils.showLongToast(BigsActivity.this,"再想想");
                    mMediaPlayer = MediaPlayer.create(BigsActivity.this, R.raw.wrong);
                    mMediaPlayer.start();
                    mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            mediaPlayer.release();


                        }
                    });
                }else{
                    ToastUtils.showLongToast(BigsActivity.this,"你真棒");
                    mMediaPlayer = MediaPlayer.create(BigsActivity.this, R.raw.yes);
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
