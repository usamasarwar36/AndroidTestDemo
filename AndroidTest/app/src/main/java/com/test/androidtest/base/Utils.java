package com.test.androidtest.base;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Usama Sarwar on 4/1/2017.
 */

public class Utils {
    public static boolean isNetworkAvailable(Context context) {

        boolean connected = false;
        ConnectivityManager connectivityManager = null;
        connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();
        if (netInfo != null) {
            connected = netInfo.isConnected();
        }
        return connected;
    }

}
