package com.example.dani.app12.log;

import android.os.Build;

import com.example.dani.app12.RestClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;


/**
 * Created by dani on 06/06/18.
 */
public class Logs {



   public static boolean sendLog(String log) {

       String currentversion = Build.VERSION.RELEASE;

        RequestParams parametros = new RequestParams();
        parametros.put("msg", log);
        parametros.put("version", currentversion);
        parametros.put("phone", getDeviceName());

        RestClient.post("logs.php", parametros, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject
                    responseString) {
                android.util.Log.d("Retorno: ", responseString.toString());

                try {
                    String retorno = responseString.getString("ret");
                    android.util.Log.d("Retorno: ", retorno.toString());

                    if (retorno.equals("true")) {
                        String hid = responseString.getString("hid");


                    } else {
                        android.util.Log.d("não", "não logou");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        return true;
    }

    public static String getDeviceName() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.startsWith(manufacturer)) {
            return capitalize(model);
        } else {
            return capitalize(manufacturer) + " " + model;
        }
    }


    private static String capitalize(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        char first = s.charAt(0);
        if (Character.isUpperCase(first)) {
            return s;
        } else {
            return Character.toUpperCase(first) + s.substring(1);
        }
    }
}

