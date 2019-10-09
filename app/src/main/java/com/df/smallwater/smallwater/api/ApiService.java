package com.df.smallwater.smallwater.api;


import com.df.smallwater.smallwater.bean.Code;
import com.df.smallwater.smallwater.bean.Gonggao;
import com.df.smallwater.smallwater.bean.Hanzi;
import com.df.smallwater.smallwater.bean.Homework;
import com.df.smallwater.smallwater.bean.Result;
import com.df.smallwater.smallwater.bean.Richang;
import com.df.smallwater.smallwater.bean.Sign;
import com.df.smallwater.smallwater.bean.User;
import com.df.smallwater.smallwater.bean.Version;
import com.df.smallwater.smallwater.bean.Word;
import com.df.smallwater.smallwater.bean.Xinwen;
import com.df.smallwater.smallwater.bean.Youeryuan;
import com.df.smallwater.smallwater.bean.Zhuangkuang;
import com.df.smallwater.smallwater.bean.Zidian;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;


/**
 * Created by boxu on 2017/4/4.
 */

public interface ApiService {


    /**
     * 修改密码
     *
     * @return
     */
    @FormUrlEncoded
    @POST("changepwd.action")
    Observable<Result> changepwd(@Field("username") String username
            , @Field("npassword") String npassword)
    ;

    /**
     * 修改用户
     *
     * @return
     */
    @FormUrlEncoded
    @POST("changeuser.action")
    Observable<Result> changeuser(@Field("username") String username
            , @Field("address") String address
            , @Field("birthday") String birthday
            , @Field("mothername") String mothername
            , @Field("name") String name
            , @Field("phone") int phone
            , @Field("sex") String sex
            , @Field("wechat") String wechat)
    ;


    /**
     * 验证码激活
     *
     * @return
     */
    @FormUrlEncoded
    @POST("usercode.action")
    Observable<Result> usercode(@Field("username") String username
            , @Field("code") String code)
    ;


    /**
     * 用户登录     *
     * @return
     */
    @FormUrlEncoded
    @POST("userlogin.action")
    Observable<User> userlogin(@Field("username") String username
            , @Field("type") int type
            , @Field("code") String code
            , @Field("zone") String zone
            , @Field("password") String password)
    ;

    /**
     * 注册
     *
     * @return
     */
    @FormUrlEncoded
    @POST("reguser.action")
    Observable<Result> reguser(@Field("username") String username
            , @Field("password") String password)
    ;

    /**
     * 获取状况
     *
     * @return
     */
    @FormUrlEncoded
    @POST("getzhuangkuang.action")
    Observable<Zhuangkuang> getzhuangkuang(@Field("username") String username
            , @Field("page") int page)
    ;

    /**
     * 获取幼儿园
     *
     * @return
     */
    @FormUrlEncoded
    @POST("getyoueryuan.action")
    Observable<Youeryuan> getyoueryuan(@Field("id") int id)
    ;

    /**
     * 获取新闻
     *
     * @return
     */
    @FormUrlEncoded
    @POST("getxinwen.action")
    Observable<Xinwen> getxinwen(@Field("id") int id
            , @Field("page") int page)
    ;

    /**
     * 获取日常
     *
     * @return
     */
    @FormUrlEncoded
    @POST("getrichang.action")
    Observable<Richang> getrichang(@Field("id") int banjiid
            , @Field("page") int page)
    ;

    /**
     * 获取作业
     *
     * @return
     */
    @FormUrlEncoded
    @POST("getHomework.action")
    Observable<Homework> getHomework(@Field("id") int banjiid)
    ;

    /**
     * 获取公告
     *
     * @return
     */
    @FormUrlEncoded
    @POST("getgonggao.action")
    Observable<Gonggao> getgonggao(@Field("id") int id
            , @Field("page") int page)
    ;

    /**
     * 忘记密码
     *
     * @return
     */
    @FormUrlEncoded
    @POST("forgetpwd.action")
    Observable<Result> forgetpwd(@Field("username") String username
            , @Field("password") String password)
    ;


    /**
     * 获取验证码
     *
     * @return
     */
    @FormUrlEncoded
    @POST("getcode.action")
    Observable<Code> getcode(@Field("code") String code)
    ;

    /**
     * 请假
     *
     * @return
     */
    @FormUrlEncoded
    @POST("qingjia.action")
    Observable<Result> qingjia(@Field("username") String username,
                             @Field("content") String content,
                             @Field("banjiid") int banjiid,
                             @Field("name") String name)
    ;


    /**
     * 获取请假
     *
     * @return
     */
    @FormUrlEncoded
    @POST("myqingjia.action")
    Observable<Sign> getqingjia(@Field("username") String username)
    ;


    /**
     * 获取汉字
     *
     * @return
     */
    @FormUrlEncoded
    @POST("gethanzi.action")
    Observable<Hanzi> gethanzi(@Field("page") int page)
    ;


    /**
     * 获取单词
     *
     * @return
     */
    @FormUrlEncoded
    @POST("getword.action")
    Observable<Word> getword(@Field("page") int page)
    ;

    /**
     * 获取一课单词
     *
     * @return
     */
    @FormUrlEncoded
    @POST("getoneword.action")
    Observable<Word> getoneword(@Field("page") int page)
    ;

    /**
     * 获取一课汉字
     *
     * @return
     */
    @FormUrlEncoded
    @POST("getonehanzi.action")
    Observable<Hanzi> getonehanzi(@Field("page") int page)
    ;

    /**
     * 获取版本号
     *
     * @return
     */
    @FormUrlEncoded
    @POST("getversion.action")
    Observable<Version> getversion(@Field("type") int type)
    ;


    /**
     * 获取语文作业具体内容
     *
     * @return
     */
    @FormUrlEncoded
    @POST("findzi.action")
    Observable<Zidian> getzidian(@Field("text") String text)
    ;


    /**
     * 获取用户，主要是验证码登录之后自动验证
     *
     * @return
     */
    @FormUrlEncoded
    @POST("getuser.action")
    Observable<User> getuser(@Field("username") String username)
    ;

}
