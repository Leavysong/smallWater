package com.df.smallwater.smallwater;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.tts.client.SpeechError;
import com.baidu.tts.client.SpeechSynthesizer;
import com.baidu.tts.client.SpeechSynthesizerListener;
import com.df.smallwater.smallwater.base.BaseActivity;
import com.df.smallwater.smallwater.utils.SayUtil;
import com.youdao.sdk.app.Language;
import com.youdao.sdk.app.LanguageUtils;
import com.youdao.sdk.app.YouDaoApplication;
import com.youdao.sdk.ydonlinetranslate.Translator;
import com.youdao.sdk.ydtranslate.Translate;
import com.youdao.sdk.ydtranslate.TranslateErrorCode;
import com.youdao.sdk.ydtranslate.TranslateListener;
import com.youdao.sdk.ydtranslate.TranslateParameters;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class YingyuActivity extends BaseActivity {


    @Bind(R.id.tv_base_text)
    TextView tvBaseText;
    @Bind(R.id.iv_base_back)
    ImageView ivBaseBack;
    @Bind(R.id.tv_word)
    TextView tvWord;
    @Bind(R.id.tv_yinbiao)
    TextView tvYinbiao;
    @Bind(R.id.tv_mean)
    TextView tvMean;
    @Bind(R.id.tv_part)
    TextView tvPart;
    @Bind(R.id.tv_mix)
    TextView tvMix;

    MediaPlayer mMediaPlayer;

    SpeechSynthesizer mSpeechSynthesizer = SpeechSynthesizer.getInstance();

    List<String> list;

    Message msg;

    int index1;
    boolean flag = true;

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(final Message msg) {
            if (msg.what == -1) {
                showDialogFinish();
            } else if (msg.what == 2) {
                domsg(index1);
            } else {
                if (flag) {
                    Translate translate = (Translate) msg.obj;
                    tvWord.setText(translate.getQuery());
                    tvYinbiao.setText("/" + translate.getUkPhonetic() + "/");
                    String Explains = "";
                    for (int i = 0; i < translate.getExplains().size(); i++) {
                        Explains = Explains + translate.getExplains().get(i) + "、";
                    }
                    tvMean.setText(Explains);
                    tvPart.setText(translate.getQuery().replace("","  "));
                    tvMix.setText(translate.getQuery());
                    if (flag) {
                        sayWord(translate);
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yingyu);
        ButterKnife.bind(this);
        SayUtil.init(this);
        tvBaseText.setText("英语作业");

        initUI();

    }

    private void initUI() {

        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        String word = bundle.getString("word");
        word = "_" + word;
        int slenth = word.length();
        int flenth = word.replaceAll("_", "").length();

        list = new ArrayList<>();

        int index = 0;
        if (word.length() == 1) {

        } else {

            index = (slenth - flenth);

        }
        for (int i = 0; i < index; i++) {
            word = word.substring(1, word.length());
            if (i == (index - 1)) {
                list.add(word.substring(0, word.length()));
            } else {
                list.add(word.substring(0, word.indexOf("_")));
                word = word.substring(word.indexOf("_"), word.length());
            }


        }


        msg = Message.obtain();
        msg.what = 2;
        handler.sendMessage(msg);


    }


    private void sayWord(Translate translate) {
        if (flag) {
            String result = translate.getQuery().replace("", "、");
            result = result.substring(1);
            mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_SPEED, "2");
            mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_SPEAKER, "0");
            mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_PITCH, "4");
            mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_VOLUME, "15");
            mSpeechSynthesizer.speak(translate.getQuery() + "、、、、、" + translate.getQuery() + "、" + translate.getQuery()
                    + "、" + "由字母" + result + "拼写而成,小朋友跟我一起读" + translate.getQuery());
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
                    index1++;
                    if (index1 == 5) {
                        msg = Message.obtain();
                        msg.what = -1;
                        handler.sendMessage(msg);

                    } else {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    Thread.sleep(2000);
                                    if (flag) {
                                        showLongToast("你真棒^-^");
                                        mMediaPlayer = MediaPlayer.create(YingyuActivity.this, R.raw.yes);
                                        mMediaPlayer.start();
                                        mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                            @Override
                                            public void onCompletion(MediaPlayer mediaPlayer) {
                                                mediaPlayer.release();
                                                domsg(index1);

                                            }
                                        });
                                    }

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

    private void domsg(int id) {
        //注册应用ID ，建议在应用启动时，初始化，所有功能的使用都需要该初始化，调用一次即可，demo中在TranslateActivity类中
        YouDaoApplication.init(this, "18175d27093b4b09");
//查词对象初始化，请设置source参数为app对应的名称（英文字符串）
        Language langFrom = LanguageUtils.getLangByName("英文");
//若设置为自动，则查询自动识别源语言，自动识别不能保证完全正确，最好传源语言类型
//Language langFrom = LanguageUtils.getLangByName("自动");
        Language langTo = LanguageUtils.getLangByName("中文");
        TranslateParameters tps = new TranslateParameters.Builder()
                .source("ydtranslate-demo")
                .from(langTo).to(langFrom).build();
        Translator translator = Translator.getInstance(tps);
//查询，返回两种情况，一种是成功，相关结果存储在result参数中，另外一种是失败，失败信息放在TranslateErrorCode中，TranslateErrorCode是一个枚举类，整个查询是异步的，为了简化操作，回调都是在主线程发生。

        translator.lookup(list.get(id), "requestId", new TranslateListener() {

            @Override
            public void onError(TranslateErrorCode translateErrorCode, String s) {
                //查询失败

                index1++;
                if (index1 == 5) {
                    msg = Message.obtain();
                    msg.what = -1;
                    handler.sendMessage(msg);

                } else {
                    domsg(index1);
                }


            }

            @Override
            public void onResult(Translate translate, String s, String s1) {
//查询成功


                msg = Message.obtain();
                msg.obj = translate;
                handler.sendMessage(msg);
            }

            @Override
            public void onResult(List<Translate> list, List<String> list1, List<TranslateErrorCode> list2, String s) {
                msg = Message.obtain();
                msg.obj = list.get(0);
                handler.sendMessage(msg);
            }


        });


    }


    @OnClick(R.id.iv_base_back)
    public void onViewClicked() {
        this.finish();
    }


    /**
     * 提示
     */
    private void showDialogFinish() {
        // 这里的属性可以一直设置，因为每次设置后返回的是一个builder对象
        AlertDialog.Builder builder = new AlertDialog.Builder(YingyuActivity.this
        );

        //设置点击对话框外部区域不关闭对话框
        builder.setCancelable(false);
        // 设置提示框的标题
        builder.setTitle("作业完成√").
                // 设置提示框的图标
                        setIcon(R.mipmap.logo).
                // 设置要显示的信息
                        setMessage("英语作业已经完成了，是否再次学习？").
                // 设置确定按钮
                        setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Toast.makeText(MainActivity.this, "选择确定哦", 0).show();
                        index1 = 0;
                    }
                }).


                // 设置取消按钮,null是什么都不做，并关闭对话框
                        setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        YingyuActivity.this.finish();
                        ;


                    }
                });

        // 生产对话框
        AlertDialog alertDialog = builder.create();
        // 显示对话框
        alertDialog.show();


    }

}
