package com.example.lgf.toutiao;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.facebook.stetho.Stetho;

/**
 * 如果minSdkVersion 设置为 20 或更低值，要使应用进行 Dalvik 可执行文件分包，需继承MultiDexApplication(需添加依赖：com.android.support:multidex:1.0.2)
 */
public class InitApp extends MultiDexApplication {
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        if (BuildConfig.DEBUG) {
            // Chrome中输入-->chrome://inspect，然后点击inspect
            Stetho.initializeWithDefaults(this);
        }
    }
}
