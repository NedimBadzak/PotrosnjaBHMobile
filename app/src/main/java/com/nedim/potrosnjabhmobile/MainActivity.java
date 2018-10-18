package com.nedim.potrosnjabhmobile;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView ultraKredit, extraKredit, extraMinute;
    int STEP_ONE_COMPLETE = 0;
    int STEP_TWO_COMPLETE = 0;
    int STEP_THREE_COMPLETE = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ultraKredit = (TextView) findViewById(R.id.textView1);
        extraKredit = (TextView) findViewById(R.id.textView2);
        extraMinute = (TextView) findViewById(R.id.textView3);
        requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 0);
        try {
            minuteData("*100#", ultraKredit, 42, 49); // zadnju
            Thread.sleep(600);
            if (STEP_ONE_COMPLETE == 1) {
                extraKreditData("*106#", extraKredit, 25, 33); // zadnj
            }
            Thread.sleep(600);
            if (STEP_TWO_COMPLETE == 1) {
                extraMinuteData("*102#", extraMinute, 27, 35); //zadnjih 9
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

        public void minuteData (String code,final TextView textViewID, final int stripStart,
        final int stripEnd){
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
            STEP_ONE_COMPLETE = 1;
        }

    public void extraKreditData (String code,final TextView textViewID, final int stripStart,
                            final int stripEnd){
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
    STEP_TWO_COMPLETE = 1;
    }

    public void extraMinuteData (String code,final TextView textViewID, final int stripStart,
                            final int stripEnd){
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