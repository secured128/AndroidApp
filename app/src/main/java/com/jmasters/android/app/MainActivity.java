package com.jmasters.android.app;

import android.com.alexb.androidapp.R;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }

    public void myButtonPressed1(View v)
    {
        Intent intent = new Intent(this, JcryptService.class);
        startService(intent);
    }
//    @Override
//    protected () {
//
//    }
}
