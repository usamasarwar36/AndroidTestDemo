package com.futureuniverse.fgandroidtest.base.executor.implementation;

import android.os.Handler;
import android.os.Looper;

import com.futureuniverse.fgandroidtest.base.executor.interfaces.UIThread;

/**
 * Created by Usama Sarwar on 4/1/2017.
 */

public class UIThreadImpl implements UIThread {
    private static UIThread uiThread;
    private Handler handler;

    public static UIThread getInstance() {
        if(uiThread == null) {
            uiThread = new UIThreadImpl();
        }
        return uiThread;
    }

    private UIThreadImpl() {
        this.handler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void post(Runnable runnable) {
        handler.post(runnable);
    }
}
