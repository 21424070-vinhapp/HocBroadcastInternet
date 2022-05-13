package com.example.hocbroadcastinternet;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.widget.Toast;


//phai extend moi xai duoc broadcast
public class MyBroadcastInternet extends BroadcastReceiver {

    //ham se lang nghe thay doi action cua he thong
    @Override
    public void onReceive(Context context, Intent intent) {
        //Neu da lang nghe duoc su thay doi cua network
        if(ConnectivityManager.EXTRA_NO_CONNECTIVITY.equals(intent.getAction()))
        {
            //neu co internet
            if(isNetWorkAvailable(context))
            {
                Toast.makeText(context, "Internet connected", Toast.LENGTH_SHORT).show();
            }
            //nguoc lai
            else
            {
                Toast.makeText(context, "Internet disconnected", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean isNetWorkAvailable(Context context) {
        //Tao ra doi tuong nhan biet su thay doi cua internet
        ConnectivityManager connectivityManager= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager==null)
        {
            return false;
        }
        //kiem tra internet version tren API 26
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            Network network=connectivityManager.getActiveNetwork();
            if(network==null)
            {
                return false;
            }
            NetworkCapabilities networkCapabilities=connectivityManager.getNetworkCapabilities(network);
            return networkCapabilities!=null && networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI);

        }
        //kiem tra internet version duoi API 26
        else
        {
            NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
            return networkInfo!=null && networkInfo.isConnected();
        }
    }
}
