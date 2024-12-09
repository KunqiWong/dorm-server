package com.kaiyu.utils;


import com.kaiyu.domain.vo.UserInfo;



public class UserHolder {

    public static final ThreadLocal<UserInfo> USER_THREAD_LOCAL = new ThreadLocal<>();

    public static void set(UserInfo user){
        USER_THREAD_LOCAL.set(user);
    }

    public static UserInfo getLoginUser(){
        return USER_THREAD_LOCAL.get();
    }

    public static void remove(){
        USER_THREAD_LOCAL.remove();
    }

}
