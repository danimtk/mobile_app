package com.example.dani.app12.connection;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by dani on 06/06/18.
 */
public class Connection {

    public static final Integer WIFI_MODE = 1;
    public static final Integer MOBILE_MODE = 2;

    public static Integer haveNetworkConnection(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    return WIFI_MODE;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    return MOBILE_MODE;
        }
        return null;
    }
}
