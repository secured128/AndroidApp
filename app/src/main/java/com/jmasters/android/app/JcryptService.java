package com.jmasters.android.app;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.jmasters.jcrypt.JcryptManager;
import com.jmasters.jcrypt.common.exceptions.JCRYPTDecryptionException;
import com.jmasters.jcrypt.common.exceptions.JCRYPTEncryptionException;

/**
 * Created by alexb on 16/03/2016.
 */
public class JcryptService extends IntentService {

    private static String TAG = "Jcrypt Service";

    public JcryptService() {
        // Used to name the worker thread
        // Important only for debugging
        //super("Jcrypt Service");
        super(JcryptService.class.getName());
        setIntentRedelivery(true);
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(TAG, "Intent served");
        TelephonyManager telephonyManager = ((TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE));
        Log.d(TAG, "getLine1Number : " + telephonyManager.getLine1Number());
        Intent localIntent = new Intent(Constants.BROADCAST_ACTION).putExtra(Constants.EXTENDED_DATA_STATUS, "OK");
        localIntent.putExtra("PHONE", telephonyManager.getLine1Number());
        if(intent.getExtras().containsKey(Constants.TEXT_TO_ENCRYPT)) {
            try {
                localIntent.putExtra(Constants.RESULT, JcryptManager.encrypt(0L, 0L, null, intent.getExtras().get(Constants.TEXT_TO_ENCRYPT).toString()));
            } catch (JCRYPTEncryptionException e) {
                e.printStackTrace();
                localIntent.putExtra(Constants.RESULT, e.getMessage());
            }
        }else if(intent.getExtras().containsKey(Constants.TEXT_TO_DECRYPT)) {
            try {
                localIntent.putExtra(Constants.RESULT, JcryptManager.decrypt(0L, intent.getExtras().get(Constants.TEXT_TO_DECRYPT).toString()));
            } catch (JCRYPTDecryptionException e) {
                e.printStackTrace();
                localIntent.putExtra(Constants.RESULT,e.getMessage());
            }
        }
        LocalBroadcastManager.getInstance(this).sendBroadcast(localIntent);
    }

}
