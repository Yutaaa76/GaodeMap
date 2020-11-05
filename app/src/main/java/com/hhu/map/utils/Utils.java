package com.hhu.map.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.hhu.map.model.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    private  static final long MIN_CLICK_TIME = 1000;

    private static long lastClickTime = 0;

    public static boolean   registerLoginPhone(String str){
        Pattern pattern = Pattern.compile("^[1][3|4|5|7|8]{1}[0-9]{9}$");
        Matcher matcher = pattern.matcher(str);
        if(matcher.matches()) {
            return true;
        }
        else {
            return false;
        }
    }
    public static boolean isEmpty(String str){
        if(str.isEmpty()){
            return true;
        }
        else{
            return false;
        }
    }

    //存储
    public static void save(Context context, User user){
        SharedPreferences sp = context.getSharedPreferences("register",Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString("phone",user.getPhone());
        edit.putString("password",user.getPassword());
        edit.putString("sex",user.getSex());
        edit.commit();
    }

    //获取
    public static String get(Context context, String str){
        SharedPreferences sp = context.getSharedPreferences("register",Context.MODE_PRIVATE);
        return sp.getString(str,"");
    }


    //判断是否快速点击
    public static boolean prevent() {
        boolean flag = false;
        long currClickTime = System.currentTimeMillis();        //获取当前系统时间
        if(currClickTime - lastClickTime < MIN_CLICK_TIME)
            flag = true;
        lastClickTime = System.currentTimeMillis();
        return flag;
    }


}
