package com.df.smallwater.smallwater.utils;

/**
 * Created by DF on 2018/5/31.
 */

public class loginUtil {


    public static boolean login(String username , String password , String code){

        if(username.length()>=6&&password.length()>=6&&code.length()>=4){
            return true ;
        }else{
            return false ;
        }
    }
}
