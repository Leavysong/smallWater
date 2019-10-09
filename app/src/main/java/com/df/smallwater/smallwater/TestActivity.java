package com.df.smallwater.smallwater;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.df.smallwater.smallwater.adapter.zhuangkuangAdapter;
import com.df.smallwater.smallwater.base.BaseActivity;

import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TestActivity extends BaseActivity {

    @Bind(R.id.tv_base_text)
    TextView tvBaseText;
    @Bind(R.id.iv_base_back)
    ImageView ivBaseBack;
    @Bind(R.id.tv_test)
            //暂时不用了2期开放
    TextView tvTest;
    @Bind(R.id.tv_question)
    TextView tvQuestion;
    @Bind(R.id.ed_answer)
    EditText edAnswer;
    @Bind(R.id.btn_submit)
    Button btnSubmit;
    MediaPlayer mMediaPlayer;

    int high = 0 ;
    Random random = new Random();
    boolean stopThread=true;
    int a , b ;
    Message msg;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if(stopThread) {
                tvQuestion.setText(getQuestion());
                edAnswer.setText("");
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);
        SetStatusBarColor();
        tvBaseText.setText("加减练习");

        tvTest.setText("初级");

        tvQuestion.setText(getQuestion());


    }

    @Override
    protected void onDestroy() {
        stopThread=false;
        super.onDestroy();
    }

    @OnClick({R.id.iv_base_back, R.id.tv_test, R.id.btn_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_base_back:

                this.finish();;
                break;
            case R.id.tv_test:
               switch (high)
               {
                   case 0:
                        tvTest.setText("中级");
                       high = 1 ;
                       msg = Message.obtain();
                       handler.sendMessage(msg);

                       break;
                   case 1:
                       tvTest.setText("高级");
                       high = 2 ;
                       msg = Message.obtain();
                       handler.sendMessage(msg);

                       break;
                   case 2:
                       tvTest.setText("初级");
                       high = 0 ;
                       msg = Message.obtain();
                       handler.sendMessage(msg);

                       break;

               }

                break;
            case R.id.btn_submit:


                if(edAnswer.getText().toString().length()>0&&Integer.valueOf(edAnswer.getText().toString())==a+b){

                    showLongToast("你真棒^-^");
                    mMediaPlayer = MediaPlayer.create(TestActivity.this, R.raw.yes);
                    mMediaPlayer.start();
                    mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            mediaPlayer.release();
                        }
                    });

                    msg = Message.obtain();
                    handler.sendMessage(msg);

                }else{
                    showLongToast("加油再想想^-^");
                    mMediaPlayer = MediaPlayer.create(TestActivity.this, R.raw.wrong);
                    mMediaPlayer.start();
                    mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            mediaPlayer.release();
                        }
                    });
                }
                break;
        }
    }

    public String getQuestion() {
        int x ;
        switch (high)
        {
            case 0:
                a = random.nextInt(9)+1;
                x = 9-a;
                b =  random.nextInt(x)+1;
                break;
            case 1:
                a = random.nextInt(19)+1;
                x = 19-a;
                b =  random.nextInt(x)+1;
                break;
            case 2:
                a = random.nextInt(99)+1;
                x = 99-a;
                b =  random.nextInt(x)+1;
                break;

        }

        return "小朋友请问"+a+"+"+b+"等于多少啊？";
    }
}
