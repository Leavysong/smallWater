package com.df.smallwater.smallwater;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baidu.tts.client.SpeechError;
import com.baidu.tts.client.SpeechSynthesizer;
import com.baidu.tts.client.SpeechSynthesizerListener;
import com.df.smallwater.smallwater.api.Api;
import com.df.smallwater.smallwater.base.BaseActivity;
import com.df.smallwater.smallwater.baserx.RxSchedulers;
import com.df.smallwater.smallwater.bean.Zidian;
import com.df.smallwater.smallwater.utils.SayUtil;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Subscriber;

public class YuwenActivity extends BaseActivity {

    @Bind(R.id.tv_base_text)
    TextView tvBaseText;
    @Bind(R.id.iv_base_back)
    ImageView ivBaseBack;
    @Bind(R.id.relativeLayout2)
    RelativeLayout relativeLayout2;
    @Bind(R.id.tv_hanzi)
    TextView tvHanzi;
    @Bind(R.id.tv_pinyin)
    TextView tvPinyin;
    @Bind(R.id.tv_boshou)
    TextView tvBoshou;
    @Bind(R.id.tv_bihua)
    TextView tvBihua;
    @Bind(R.id.tv_hanzi_big)
    TextView tvHanziBig;
    @Bind(R.id.linearLayout)
    LinearLayout linearLayout;

    int index;
    boolean flag = true;

    String text = "";
    Message msg;
    List<Zidian.ZidianBean> list;

    MediaPlayer mMediaPlayer;

    SpeechSynthesizer mSpeechSynthesizer = SpeechSynthesizer.getInstance();

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == -1) {
                showDialogFinish();
            } else if (msg.what == 1) {
                if (flag) {
                    showLongToast("你真棒^-^");
                    mMediaPlayer = MediaPlayer.create(YuwenActivity.this, R.raw.yes);
                    mMediaPlayer.start();
                    mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            mediaPlayer.release();
                            Zidian.ZidianBean zidian = list.get(index);
                            tvHanzi.setText(zidian.getZi());
                            tvPinyin.setText(zidian.getPinyin());
                            tvBoshou.setText("部首： " + zidian.getBushou());
                            tvBihua.setText("笔画： " + zidian.getBihua());
                            tvHanziBig.setText(zidian.getZi());
                            if (flag) {
                                sayWord(zidian);
                            }

                        }
                    });
                }

            } else {
                if (flag) {
                    Zidian.ZidianBean zidian = list.get(index);
                    tvHanzi.setText(zidian.getZi());
                    tvPinyin.setText(zidian.getPinyin());
                    tvBoshou.setText("部首： " + zidian.getBushou());
                    tvBihua.setText("笔画： " + zidian.getBihua());
                    tvHanziBig.setText(zidian.getZi());
                    if (flag) {
                        sayWord(zidian);
                    }

                }
            }

        }
    };

    @Override
    protected void onDestroy() {
        SayUtil.stop();
        mSpeechSynthesizer.stop();
        flag = false;
        super.onDestroy();
    }

    private void sayWord(Zidian.ZidianBean zidian) {
        if (flag) {
            mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_SPEED, "1");
            mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_SPEAKER, "4");
            mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_PITCH, "4");
            mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_VOLUME, "15");
            mSpeechSynthesizer.speak(zidian.getZi() + "、" + zidian.getZi() + "、" + zidian.getZi() + "、" + "小朋友跟我一起读、" + zidian.getZi());
            mSpeechSynthesizer.setSpeechSynthesizerListener(new SpeechSynthesizerListener() {
                @Override
                public void onSynthesizeStart(String s) {

                }

                @Override
                public void onSynthesizeDataArrived(String s, byte[] bytes, int i) {

                }

                @Override
                public void onSynthesizeFinish(String s) {

                }

                @Override
                public void onSpeechStart(String s) {

                }

                @Override
                public void onSpeechProgressChanged(String s, int i) {

                }

                @Override
                public void onSpeechFinish(String s) {
                    index++;
                    if (index == 5) {
                        msg = Message.obtain();
                        msg.what = -1;
                        handler.sendMessage(msg);

                    } else {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    Thread.sleep(2000);
                                    msg = Message.obtain();
                                    msg.what = 1;
                                    handler.sendMessage(msg);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }).start();
                    }

                }

                @Override
                public void onError(String s, SpeechError speechError) {

                }
            });
        }


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yuwen);
        ButterKnife.bind(this);

        SetStatusBarColor();
        SayUtil.init(this);
        tvBaseText.setText("语文作业");

        Bundle bundle = getIntent().getExtras();
        text = bundle.getString("text");


        Api.getDefault().getzidian(text).compose(RxSchedulers.<Zidian>io_main()).subscribe(new Subscriber<Zidian>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Zidian zidian) {
                list = zidian.getZidian();
                msg = Message.obtain();
                handler.sendMessage(msg);
            }
        });

    }

    /**
     * 提示作业完成
     */
    private void showDialogFinish() {
        // 这里的属性可以一直设置，因为每次设置后返回的是一个builder对象
        AlertDialog.Builder builder = new AlertDialog.Builder(YuwenActivity.this
        );

        //设置点击对话框外部区域不关闭对话框
        builder.setCancelable(false);
        // 设置提示框的标题
        builder.setTitle("作业完成√").
                // 设置提示框的图标
                        setIcon(R.mipmap.logo).
                // 设置要显示的信息
                        setMessage("语文作业已经完成了，是否再次学习？").
                // 设置确定按钮
                        setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Toast.makeText(MainActivity.this, "选择确定哦", 0).show();
                        index = 0;
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    Thread.sleep(2000);
                                    msg = Message.obtain();
                                    msg.what = 1;
                                    handler.sendMessage(msg);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }).start();
                    }
                }).


                // 设置取消按钮,null是什么都不做，并关闭对话框
                        setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        YuwenActivity.this.finish();
                        ;


                    }
                });

        // 生产对话框
        AlertDialog alertDialog = builder.create();
        // 显示对话框
        alertDialog.show();


    }


}
