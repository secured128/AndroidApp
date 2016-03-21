package com.jmasters.android.app;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
/**
 * Created by alexb on 16/03/2016.
 */
public class JcryptService extends IntentService {

    private static String TAG = "Jcrypt Service";

    public JcryptService() {
        super("Jcrypt Service");
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(TAG, "Intent served");
    }

}
