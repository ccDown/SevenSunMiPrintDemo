package com.seven.sevensunmiprintdemo;

import android.app.Application;

import com.seven.sevensunmiprintdemo.utils.AidlUtil;

/**
 * @author kuan
 * Created on 2018/4/9.
 * @description
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        AidlUtil.getInstance().connectPrinterService(this);
    }
}
