package com.jmasters.android.app;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;


public class ResponseReceiver extends BroadcastReceiver
 {

     private static String TAG = ResponseReceiver.class.getName();

//    // Prevents instantiation
//    private ResponseReceiver() {
//    }

    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive");
    }

}