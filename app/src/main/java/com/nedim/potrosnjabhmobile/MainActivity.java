package com.nedim.potrosnjabhmobile;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import dmax.dialog.SpotsDialog;


public class MainActivity extends AppCompatActivity {
    TextView ultraKredit, extraKredit, extraMinute, extraInternet;
    Integer time = 10;
    Button button1, button2, button3, button4;
    Boolean checker = Boolean.TRUE;
    android.os.Handler customHandler;
    ProgressBar progressBar;
    AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ultraKredit = (TextView) findViewById(R.id.textView1);
        extraKredit = (TextView) findViewById(R.id.textView2);
        extraMinute = (TextView) findViewById(R.id.textView3);
        extraInternet = (TextView) findViewById(R.id.textView5);
        progressBar = findViewById(R.id.progressBar1);
        progressBar.setProgress(0);
        requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                minuteData("*100#", ultraKredit, 42, 49); // zadnju
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                minuteData("*101*2#", extraKredit, 24, 33); // zadnj
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                minuteData("*101*4#", extraMinute, 21, 24); //zadnjih 9

            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                minuteData("*101*3#", extraInternet, 37, 47); //zadnjih 9

            }
        });
        customHandler = new android.os.Handler();
        customHandler.postDelayed(updateAnotherAnotherAnotherTimer, 0);
        customHandler.postDelayed(updateAnotherTimer, 3000);
        customHandler.postDelayed(updateAnotherAnotherTimer, 7500);
        customHandler.postDelayed(updateTimerThread, 10000);
        //customHandler.postDelayed(progressBarTimer,10000);
    }

    private Runnable updateAnotherAnotherAnotherTimer = new Runnable()
    {
        public void run()
        {
            minuteData("*100#", ultraKredit, 42, 49); //zadnjih 9
            progressBar.setProgress(25);
        }
    };
    private Runnable updateAnotherTimer = new Runnable()
    {
        public void run()
        {
            minuteData("*101*2#", extraKredit, 24, 33); //zadnjih 9
            progressBar.setProgress(50);
        }
    };
    private Runnable updateTimerThread = new Runnable()
    {
        public void run()
        {
            minuteData("*101*3#", extraInternet, 37, 47); // zadnj
            progressBar.setProgress(100);
        }
    };

    private Runnable updateAnotherAnotherTimer = new Runnable()
    {
        public void run()
        {
            minuteData("*101*4#", extraMinute, 21, 24); //zadnjih 9
            progressBar.setProgress(75);
        }
    };
    private Runnable progressBarTimer = new Runnable()
    {
        public void run()
        {
            progressBar.setProgress(100);
            customHandler.postDelayed(progressBarTimer, 1000);
            progressBar.setVisibility(View.INVISIBLE);
            findViewById(R.id.textView4).setVisibility(View.INVISIBLE);
        }
    };


    public void minuteData(String code, final TextView textViewID, final int stripStart,
                           final int stripEnd) {
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        Handler handler = new Handler();

        TelephonyManager.UssdResponseCallback responseCallback = new TelephonyManager.UssdResponseCallback() {
            @Override
            public void onReceiveUssdResponse(TelephonyManager telephonyManager, String request, CharSequence response) {
                super.onReceiveUssdResponse(telephonyManager, request, response);

                //Toast.makeText(MainActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
                String ultra = response.toString().substring(stripStart, stripEnd);
                textViewID.setText(ultra);
                Log.d("TAGIC", response.toString());
            }

            @Override
            public void onReceiveUssdResponseFailed(TelephonyManager telephonyManager, String request, int failureCode) {
                super.onReceiveUssdResponseFailed(telephonyManager, request, failureCode);

                Toast.makeText(MainActivity.this, String.valueOf(failureCode), Toast.LENGTH_SHORT).show();
                Log.d("TAGIC", String.valueOf(failureCode));
            }
        };

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        telephonyManager.sendUssdRequest(code, responseCallback, handler);
    }

    public void extraKredit(String code, final TextView textViewID, final int stripStart,
                           final int stripEnd) {
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        Handler handler = new Handler();

        TelephonyManager.UssdResponseCallback responseCallback = new TelephonyManager.UssdResponseCallback() {
            @Override
            public void onReceiveUssdResponse(TelephonyManager telephonyManager, String request, CharSequence response) {
                super.onReceiveUssdResponse(telephonyManager, request, response);

                //Toast.makeText(MainActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
                String ultra = response.toString().substring(stripStart, stripEnd);
                textViewID.setText(ultra);
                Log.d("TAGIC", response.toString());
            }

            @Override
            public void onReceiveUssdResponseFailed(TelephonyManager telephonyManager, String request, int failureCode) {
                super.onReceiveUssdResponseFailed(telephonyManager, request, failureCode);

                Toast.makeText(MainActivity.this, String.valueOf(failureCode), Toast.LENGTH_SHORT).show();
                Log.d("TAGIC", String.valueOf(failureCode));
            }
        };

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        telephonyManager.sendUssdRequest(code, responseCallback, handler);
    }
}