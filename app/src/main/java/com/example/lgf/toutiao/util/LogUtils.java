package com.example.lgf.toutiao.util;

import android.util.Log;

/**
 * Created by Administrator on 2018/1/25.
 */
public class LogUtils {
    private static final String TAG = "TouTiaoDemo";

    public static void debug(String message) {
        Log.d(TAG, message);
    }

    public static void info(String message) {
        Log.i(TAG, message);
    }

    public static void warn(String message) {
        Log.w(TAG, message);
    }

    public static void error(String message) {
        Log.e(TAG, message);
    }
}
