package com.kaiyu.utils;



public class TokenHolder {

    public static final ThreadLocal<String> TOKEN_THREAD_LOCAL = new ThreadLocal<>();

    public static void set(String token){
        TOKEN_THREAD_LOCAL.set(token);
    }

    public static String getToken(){
        return TOKEN_THREAD_LOCAL.get();
    }

    public static void remove(){
        TOKEN_THREAD_LOCAL.remove();
    }

}
