package com.example.dani.app12.task;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.example.dani.app12.HomeActivity;
import com.example.dani.app12.RestClient;
import com.example.dani.app12.model.MyFile;
import com.example.dani.app12.model.MyGambi;
import com.example.dani.app12.persistence.FileController;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.io.File;
import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by dani on 22/06/18.
 */

    public class GetAllSize extends AsyncTask<Object, TextView, String> {

        private Activity context;
        TextView tvsize;
        FileController fc;

        public static String final_size;


    public GetAllSize(Activity context, TextView tvsize) {
            this.context = context;
            this.tvsize = tvsize;
        }


        @Override
        protected String doInBackground(Object... params)
        {
            Log.i("Thread get citacao", ":");
            try {
                fc = new FileController(context);

                RequestParams params2 = new RequestParams();
                params2.put("user_key", HomeActivity.user_key);

                RestClient.post("getsizeall.php", params2, new JsonHttpResponseHandler() {
                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        System.out.println("deu erro: " + responseString);
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject responseString) {

                        try {
                            long size = Long.parseLong(responseString.getString("size"));


                            double fileSizeInKB = size / 1024;

                            final_size = String.format("%.2f", fileSizeInKB) + "KB";

                            if (fileSizeInKB > 1000) {
                                System.out.println("jaca a");

                                double fileSizeInMB = fileSizeInKB / 1024;


                                final_size = String.format("%.2f", fileSizeInMB) + "MB";

                                if (fileSizeInMB > 1000) {

                                    double fileSizeInGB = fileSizeInMB / 1024;

                                    final_size = String.format("%.2f", fileSizeInGB) + "GB";
                                }
                            }


                        } catch (Exception e) {

                        }
                    }
                });
                return final_size + " usados";
            } catch (Exception e) {
                return null;
            }
        }



        @Override
        protected void onPostExecute(String b)
        {
            super.onPostExecute(b);
            tvsize.setText(b+" usados");

        }
}
