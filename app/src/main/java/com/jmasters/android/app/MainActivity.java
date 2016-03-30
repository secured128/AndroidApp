package com.jmasters.android.app;

import com.jmasters.android.app.R;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IntentFilter mStatusIntentFilter = new IntentFilter(Constants.BROADCAST_ACTION);
        // Instantiates a new DownloadStateReceiver
        ResponseReceiver mDownloadStateReceiver = new ResponseReceiver();
        // Registers the DownloadStateReceiver and its intent filters
        LocalBroadcastManager.getInstance(this).registerReceiver(mDownloadStateReceiver,mStatusIntentFilter);
    }

    public void myButtonPressed1(View v)
    {
        Intent intent = new Intent(this, JcryptService.class);
        intent.putExtra("foo", "bar");
        startService(intent);
    }

}
