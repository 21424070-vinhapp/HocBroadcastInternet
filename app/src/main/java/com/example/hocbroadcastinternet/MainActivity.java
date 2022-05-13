package com.example.hocbroadcastinternet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    BroadcastReceiver internetReceiver=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            //tao ra doi tuong connectiManager
            ConnectivityManager connectivityManager= (ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE);
            //kiem tra co internet hay khong
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if(networkInfo!=null)
            {
                Toast.makeText(MainActivity.this, "Co internet", Toast.LENGTH_SHORT).show();
            }
            else
            {

                Toast.makeText(MainActivity.this, "Khong co internet", Toast.LENGTH_SHORT).show();
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //lang nghe su thay doi cua internet
        IntentFilter filter=new IntentFilter(ConnectivityManager.EXTRA_NO_CONNECTIVITY);
        registerReceiver(internetReceiver,filter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(internetReceiver);
    }
}