package com.utils;

public class VerificationCodeUtils {

	/**
     * 生成一个6位的验证码
     * @return
     */
    public static String getYzm_Six(){

        int a = (int)((Math.random()*9+1)*100000);
        return a+"";
    }
    /**
     * 四位的验证码
     * @return
     */
    public static String getYzm_Four(){

        int a = (int)((Math.random()*9+1)*1000);
        return a+"";
    }
}
