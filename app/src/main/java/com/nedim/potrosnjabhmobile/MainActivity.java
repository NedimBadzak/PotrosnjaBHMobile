package com.nedim.potrosnjabhmobile;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import android.app.ProgressDialog;
import android.os.AsyncTask;

public class MainActivity extends AppCompatActivity {
    TextView ultraKredit, extraKredit, extraMinute;
    Integer time = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ultraKredit = (TextView) findViewById(R.id.textView1);
        extraKredit = (TextView) findViewById(R.id.textView2);
        extraMinute = (TextView) findViewById(R.id.textView3);
        requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 0);


        AsyncTaskRunner runner = new AsyncTaskRunner();
        String sleepTime = time.toString();
        runner.execute(sleepTime);
    }
    private class AsyncTaskRunner extends AsyncTask<String, String, String> {

        private String resp;
        ProgressDialog progressDialog;

        @Override
        protected String doInBackground(String... params) {
            publishProgress("Sleeping..."); // Calls onProgressUpdate()
            try {
                int time = Integer.parseInt(params[0])*1000;
                minuteData("*100#", ultraKredit, 42, 49); // zadnju
                Thread.sleep(3000);
                minuteData("*106#", extraKredit, 25, 33); // zadnj
                Thread.sleep(3000);
                minuteData("*102#", extraMinute, 27, 35); //zadnjih 9
                Thread.sleep(time);
                resp = "Slept for " + params[0] + " seconds";
            } catch (InterruptedException e) {
                e.printStackTrace();
                resp = e.getMessage();
            } catch (Exception e) {
                e.printStackTrace();
                resp = e.getMessage();
            }
            return resp;
        }


        @Override
        protected void onPostExecute(String result) {
            // execution of result of Long time consuming operation
            progressDialog.dismiss();

        }


        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(MainActivity.this,
                    "ProgressDialog",
                    "Wait for "+time.toString()+ " seconds");


        }


        @Override
        protected void onProgressUpdate(String... text) {

        }
    }
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
}