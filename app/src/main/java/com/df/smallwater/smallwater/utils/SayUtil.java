package com.df.smallwater.smallwater.utils;

import android.content.Context;
import android.util.Log;

import com.baidu.tts.client.SpeechError;
import com.baidu.tts.client.SpeechSynthesizer;
import com.baidu.tts.client.SpeechSynthesizerListener;
import com.baidu.tts.client.TtsMode;

/**
 * Created by DF on 2018/6/11.
 */

public class SayUtil implements SpeechSynthesizerListener
{


    private static  String AppId = "11382078";
    private static String AppKey = "SSXSo15eMnlVWrhu0B5Ci6N5";
    private static String AppSecret = "qWQhlwpjkQ7vkh0tvIotbKX0tSYvcuWd";
    static SpeechSynthesizer mSpeechSynthesizer = SpeechSynthesizer.getInstance();
    private static SayUtil s ;

    public synchronized SayUtil getInstance(Context context){
        if(s==null){
            init(context);
            s = new SayUtil();
            return s ;
        }else{
            return s ;
        }
    }


    public static void init(Context context){
        mSpeechSynthesizer.setContext(context);
        mSpeechSynthesizer.setAppId(AppId);
        mSpeechSynthesizer.setApiKey(AppKey,   AppSecret);
        mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_SPEAKER, "4");
        mSpeechSynthesizer.setSpeechSynthesizerListener(s);
        mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_SPEED,"1");
        mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_PITCH,"4");
        mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_VOLUME,"15");
        mSpeechSynthesizer.initTts(TtsMode.ONLINE);
    }

    /**
     *
     * @param s 要说的话或字
     * @param type 语音模式 "0"（默认）普通女声 	"1"普通男声 "2"特别男声 "3"情感男声<度逍遥 >"4"情感儿童声<度丫丫>
     * @param speed
     */
    public static void say( String s,String type,String speed){

        mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_SPEAKER, type);
        mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_SPEED,speed);
        int result =  mSpeechSynthesizer.speak(s);
    }

    public static void stop( ){
        SpeechSynthesizer mSpeechSynthesizer = SpeechSynthesizer.getInstance();

        int result =  mSpeechSynthesizer.stop();
    }

    @Override
    public void onSynthesizeStart(String s) {

        Log.e("speakstart","开始");
    }

    @Override
    public void onSynthesizeDataArrived(String s, byte[] bytes, int i) {


    }

    @Override
    public void onSynthesizeFinish(String s) {

    }

    @Override
    public void onSpeechStart(String s) {
        Log.e("speakstart","开始");
    }

    @Override
    public void onSpeechProgressChanged(String s, int i) {

    }

    @Override
    public void onSpeechFinish(String s) {

    }

    @Override
    public void onError(String s, SpeechError speechError) {

    }

}
