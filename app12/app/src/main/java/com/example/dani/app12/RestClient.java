package com.example.dani.app12;

import android.os.Looper;
import android.util.Log;

import com.example.dani.app12.log.Logs;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.SyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;

import cz.msebera.android.httpclient.Header;

/**
 * Created by dani on 06/06/18.
 */
public class RestClient {

    private static final

    String BASE_URL = "http://192.168.1.104/phoup/";


    public static boolean ret;


    private static AsyncHttpClient client = new AsyncHttpClient();
    // A SyncHttpClient is an AsyncHttpClient
    public static AsyncHttpClient syncHttpClient= new SyncHttpClient();


    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        getClient().get(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        getClient().post(getAbsoluteUrl(url), params, responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }


    /**
     * @return an async client when calling from the main thread, otherwise a sync client.
     */
    private static AsyncHttpClient getClient()
    {
        // Return the synchronous HTTP client when the thread is not prepared
        if (Looper.myLooper() == null)
            return syncHttpClient;
        return client;
    }

    public static boolean postImage(String ImageLink, String user_key, Long size_file) {
        RequestParams params = new RequestParams();

        params.put("user_key", user_key);

        params.put("size", size_file);

        try {

            if (ImageLink.contains("jpeg") || ImageLink.contains("jpg")) {
                params.put("uploadedfile", new File(ImageLink), "image/jpeg");
            } else if (ImageLink.contains("png")){
                params.put("uploadedfile", new File(ImageLink), "image/png");
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Logs.sendLog(e.toString());
        }


        RestClient client = new RestClient();

        client.post("upload.php", params, new JsonHttpResponseHandler() {

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.d("teste:", responseString.toString());
                RestClient.ret = false;
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject responseString) {

                try {
                    
                    boolean retorno = Boolean.parseBoolean(responseString.getString("ret"));

                    RestClient.ret = retorno;

                } catch (Exception e) { }
            }
        });

        return RestClient.ret;
    }
}
