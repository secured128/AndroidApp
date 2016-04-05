package com.jmasters.android.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            MainActivity.this.receivedBroadcast(intent);
        }
    };

    // Incoming Intent
    private Intent mIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IntentFilter mStatusIntentFilter = new IntentFilter(Constants.BROADCAST_ACTION);
        // Instantiates a new DownloadStateReceiver
//        ResponseReceiver mDownloadStateReceiver = new ResponseReceiver();
        // Registers the DownloadStateReceiver and its intent filters
        LocalBroadcastManager.getInstance(this).registerReceiver(mBroadcastReceiver,mStatusIntentFilter);
        handleViewIntent();
    }

    /*
     * Called from onNewIntent() for a SINGLE_TOP Activity
     * or onCreate() for a new Activity. For onNewIntent(),
     * remember to call setIntent() to store the most
     * current Intent
     *
     */
    private void handleViewIntent() {
//        ...
//        // Get the Intent action
//        mIntent = getIntent();
//        String action = mIntent.getAction();
//        /*
//         * For ACTION_VIEW, the Activity is being asked to display data.
//         * Get the URI.
//         */
//        if (TextUtils.equals(action, Intent.ACTION_VIEW)) {
//            // Get the URI from the Intent
//            Uri beamUri = mIntent.getData();
//            /*
//             * Test for the type of URI, by getting its scheme value
//             */
//            if (TextUtils.equals(beamUri.getScheme(), "file")) {
//                try {
//                    FileInputStream inputStream = new FileInputStream(new File(beamUri.getPath()));
//                    Scanner s = new Scanner(inputStream).useDelimiter("\\A");
//                    ((TextView)findViewById(R.id.textView)).setText(s.hasNext() ? s.next() : "");
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                }
//            }
//

        if (getIntent() != null && TextUtils.equals(getIntent().getAction(), Intent.ACTION_VIEW)) {
            final Uri uri = getIntent().getData();
            InputStream inputStream = null;
            String str = "";
            StringBuffer buf = new StringBuffer();
            TextView txt = (TextView) findViewById(R.id.textView);
            try {
                inputStream = getContentResolver().openInputStream(uri);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            if (inputStream != null) {
                try {
                    while ((str = reader.readLine()) != null) {
                        buf.append(str + "\n");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                txt.setText(buf.toString());
            }
        }
    }

    public void encryptButtonPressed(View v)
    {
        ((Button)findViewById(R.id.encryptButton)).setClickable(false);
        ((Button)findViewById(R.id.decryptButton)).setClickable(false);

        Intent intent = new Intent(this, JcryptService.class);
        intent.putExtra(Constants.TEXT_TO_ENCRYPT,((TextView)findViewById(R.id.textView)).getText().toString());
        startService(intent);
    }

    public void decryptButtonPressed(View v)
    {
        ((Button)findViewById(R.id.encryptButton)).setClickable(false);
        ((Button)findViewById(R.id.decryptButton)).setClickable(false);

        Intent intent = new Intent(this, JcryptService.class);
        intent.putExtra(Constants.TEXT_TO_DECRYPT,((TextView)findViewById(R.id.textView)).getText().toString());
        startService(intent);
    }

    private void receivedBroadcast(Intent i) {
        ((TextView)findViewById(R.id.textView)).setText(i.getStringExtra(Constants.RESULT));
        ((TextView)findViewById(R.id.phoneLine)).setText("PHONE N : " + i.getStringExtra("PHONE"));
        ((Button)findViewById(R.id.encryptButton)).setClickable(true);
        ((Button)findViewById(R.id.decryptButton)).setClickable(true);
    }

}
