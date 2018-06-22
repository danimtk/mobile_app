package com.example.dani.app12;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.dani.app12.model.MyFile;
import com.example.dani.app12.model.MyGambi;
import com.example.dani.app12.persistence.FileController;

import java.io.File;
import java.util.List;

/**
 * Created by dani on 22/06/18.
 */

    public class LocalSize extends AsyncTask<Object, Button, MyGambi> {

        private Activity context;
        Button btsize;
        FileController fc;

        public LocalSize(Activity context, Button btsize) {
            this.context = context;
            this.btsize = btsize;
        }


        @Override
        protected MyGambi doInBackground(Object... params)
        {
            Log.i("Thread get citacao", ":");
            try
            {
                fc = new FileController(context);

                List<MyFile> files = fc.getAllFiles();

                long size_local = 0;

                for (MyFile file : files) {

                    System.out.println("file: "+file.getName());
                    File f =new File(Environment.getExternalStorageDirectory() + "/DCIM/Camera/"+file.getName());

                    if (f.exists()) {

                        size_local += f.length();
                    }
                }

                String final_size_local;

                double size_local_double;

                double fileSizeLocalInKB = size_local / 1024;
                size_local_double = fileSizeLocalInKB;
                        final_size_local = String.format( "%.2f", fileSizeLocalInKB) + " KB para liberar";

                if (fileSizeLocalInKB > 1000) {

                    double fileSizeLocalInMB = fileSizeLocalInKB / 1024;
                    size_local_double = fileSizeLocalInMB;
                    final_size_local = String.format( "%.2f", fileSizeLocalInMB) + " MB para liberar";

                    if (fileSizeLocalInMB > 1000) {

                        double fileSizeLocalInGB = fileSizeLocalInMB / 1024;
                        size_local_double = fileSizeLocalInGB;
                        final_size_local = String.format( "%.2f", fileSizeLocalInGB) + " GB para liberar";
                    }
                }

                return new MyGambi(size_local_double, final_size_local);
            }
            catch (Exception e) {
                Log.d("Exception:", e.getMessage().toString());
                return null;
            }
        }


        @Override
        protected void onPostExecute(MyGambi b)
        {

            super.onPostExecute(b);
            // gambiarra mestre -> tenha compaixão
            if (b.getSize_S().contains("0,00 KB") || b.getSize_S().contains("0,00 MB") || b.getSize_S().contains("0,00 GB")) {
                btsize.setVisibility(View.INVISIBLE);
            } else {
                btsize.setText(b.getSize_S());
                btsize.setVisibility(View.VISIBLE);
            }

        }
}
