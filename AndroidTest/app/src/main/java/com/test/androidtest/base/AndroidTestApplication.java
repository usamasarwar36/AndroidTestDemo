package com.test.androidtest.base;

import android.app.Application;

import io.paperdb.Paper;

/**
 * Created by Usama Sarwar on 4/1/2017.
 */

public class AndroidTestApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Paper.init(this);
    }
}
