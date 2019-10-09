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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShuxueActivity extends BaseActivity {


    List<String> list;
    @Bind(R.id.tv_base_text)
    TextView tvBaseText;
    @Bind(R.id.iv_base_back)
    ImageView ivBaseBack;
    @Bind(R.id.tv_question)
    TextView tvQuestion;
    @Bind(R.id.tv_left)
    TextView tvLeft;
    @Bind(R.id.rl_left)
    RelativeLayout rlLeft;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.rl_right)
    RelativeLayout rlRight;
    boolean stopThread = true;
    int index , a , b ;
    String c ;
    boolean reduce ;
    MediaPlayer mMediaPlayer;
    Message msg;
    int flag2 ;
    boolean finish = false ;
    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (stopThread) {
                SayUtil.say(getQuestion(), "0","5");


            }

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shuxue);
        ButterKnife.bind(this);
        SetStatusBarColor();
        tvBaseText.setText("数学作业");
        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        String shuxue = bundle.getString("shuxue");


        shuxue = "_" + shuxue;
        int slenth = shuxue.length();
        int flenth = shuxue.replaceAll("_", "").length();

        list = new ArrayList<>();

        int index1 = 0;
        if (shuxue.length() == 1) {

        } else {

            index1 = (slenth - flenth);

        }
        for (int i = 0; i < index1; i++) {
            shuxue = shuxue.substring(1, shuxue.length());
            if (i == (index1 - 1)) {
                list.add(shuxue.substring(0, shuxue.length()));
            } else {
                list.add(shuxue.substring(0, shuxue.indexOf("_")));
                shuxue = shuxue.substring(shuxue.indexOf("_"), shuxue.length());
            }


        }
        msg = Message.obtain();
        handler.sendMessage(msg);


    }


    private void doright(int x) {

        if(flag2==x){
            SayUtil.stop();
            showLongToast("你真棒^-^");
            mMediaPlayer = MediaPlayer.create(ShuxueActivity.this, R.raw.yes);
            mMediaPlayer.start();
            mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    mediaPlayer.release();
                }
            });
            if(finish){
                SayUtil.say("已完成今日作业，可以重复练习哦","0","5");
                showLongToast("已完成今日作业，可以重复练习哦");

                finish=false ;
            }
            msg = Message.obtain();
            handler.sendMessage(msg);
        }
        else{
            SayUtil.stop();
            showLongToast("加油再想想^-^");
            mMediaPlayer = MediaPlayer.create(ShuxueActivity.this, R.raw.wrong);
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



        String flag = list.get(index);
        String result ;


        if(flag.contains("+")){
            c = "+" ;

        }else {
            c = "-" ;
        }

        if(c.equals("+")){
            reduce = false ;
            a = Integer.valueOf(flag.substring(0,flag.indexOf("+")));
            b = Integer.valueOf(flag.substring(flag.indexOf("+")+1,flag.length()));
            result = "小朋友请问" + a+"加上"+b + "等于多少啊？";
        }else{ reduce = true ;
            a = Integer.valueOf(flag.substring(0,flag.indexOf("-")));
            b = Integer.valueOf(flag.substring(flag.indexOf("-")+1,flag.length()));
            result = "小朋友请问" + a+"减去"+b + "等于多少啊？";
        }
        index++;
        if(index>=list.size()){
if(finish){
    finish = false ;
}else{
    finish = true ;

}


            index = 0;

        }

        tvQuestion.setText(result);
        if(reduce){
            setanwser(a-b);
        }else{
            setanwser(a+b);
        }

            return result;


    }

    private void setanwser(int anwser) {

        Random rand = new Random();
        flag2 = 0;
        int  flag1 = 0;
        flag2 = rand.nextInt(2);
        if (flag2 == 0) {
            if (reduce) {
                tvLeft.setText(anwser+ "");
                flag1 = rand.nextInt(19);
                while (anwser== flag1) {
                    flag1 = rand.nextInt(19);
                }
                tvRight.setText(flag1 + "");
            } else {
                tvLeft.setText(anwser + "");

                flag1 = rand.nextInt(19);
                while (anwser== flag1) {
                    flag1 = rand.nextInt(19);
                }
                tvRight.setText(flag1 + "");

            }

        }else{
            if (reduce) {
                tvRight.setText(anwser+ "");
                flag1 = rand.nextInt(19);
                while (anwser== flag1) {
                    flag1 = rand.nextInt(19);
                }
                tvLeft.setText(flag1 + "");
            } else {
                tvRight.setText(anwser + "");

                flag1 = rand.nextInt(19);
                while (anwser== flag1) {
                    flag1 = rand.nextInt(19);
                }
                tvLeft.setText(flag1 + "");

            }
        }

    }
    @OnClick({R.id.iv_base_back, R.id.rl_left, R.id.rl_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_base_back:
                this.finish();;
                break;
            case R.id.rl_left:
                doright(0);
                break;
            case R.id.rl_right:
                doright(1);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        SayUtil.stop();
        super.onDestroy();
    }
}
