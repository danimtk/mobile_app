package com.example.dani.app12;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dani.app12.connection.Connection;
import com.example.dani.app12.model.MyFile;
import com.example.dani.app12.persistence.FileController;
import com.example.dani.app12.slidingtab.ImageResource;
import com.example.dani.app12.task.GetAllSize;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by dani on 06/06/18.
 */
public class AsyncUpload extends AsyncTask<Object, Void, Boolean> {

    private Activity context;

    public TextView allsize;

    public Button localsize;

    SessionManager session;

    public AsyncUpload(Activity context, TextView allsize, Button localsize) {
        this.context = context;
        this.allsize = allsize;
        this.localsize = localsize;
    }

    @Override
    protected Boolean doInBackground(Object... params)
    {
        Log.i("Thread upload", ":");

        try
        {
            if (params.length < 1) {
                Log.d("Erro:", " menos de 1 parametro");
                return false;
            }

            if (!(params[0] instanceof Activity)) {
                Log.d("Erro:", " parametro 0 não é activity");
                return false;
            }

            context = (Activity)params[0];

            session = new SessionManager(context);

            // get user data from session
            HashMap<String, String> user = session.getUserDetails();

            // token_acess
            String user_key = user.get(SessionManager.KEY_ACESS);

            File[] files = ImageResource.getImages();

            ImageResource.prepareToUploadImages(files, user_key, context);

            return true;
        }
        catch (Exception e) {
            Log.d("Exception:", e.getMessage().toString());
            return false;
        }
    }


    @Override
    protected void onPostExecute(Boolean b)
    {
        super.onPostExecute(b);

        new GetAllSize(context, allsize).execute(context);
        new LocalSize(context, localsize).execute(context);

    }
}

class DeleteFile extends AsyncTask<Object, Void, Boolean> {

    FileController fc;

    TextView chatbox;

    Button sizelocal;

    SessionManager session;
    private Activity context;

    public DeleteFile(Button sizelocal) {
        this.sizelocal = sizelocal;
    }

    //  private List<Citacao> citacoes = new ArrayList<Citacao>();

    @Override
    protected Boolean doInBackground(Object... params)
    {
        Log.i("Thread delete", ":");

        try
        {
            if(params.length < 1) {
                Log.d("Erro:", " menos de 1 parametro");
                return false;
            }

            if(!(params[0] instanceof Activity)) {
                Log.d("Erro:", " parametro 0 não é activity");
                return false;
            }

            context = (Activity)params[0];

            fc = new FileController(context);

            List<MyFile> mf = fc.getAllFiles();

            for (MyFile m : mf) {

                File f = new File(Environment.getExternalStorageDirectory() + "/DCIM/Camera/"+m.getName());

                if (f.exists()) {
                    System.out.println("myfile: " + m.getName());
                    if (f.delete()) {
                        fc.deleteFile(m.getId());
                    }
                }
            }

            return true;
        }
        catch (Exception e) {
            Log.d("Exception:", e.getMessage().toString());
            return false;
        }
    }


    @Override
    protected void onPostExecute(Boolean b)
    {
        super.onPostExecute(b);


        Toast.makeText(context, "Espaço liberado", Toast.LENGTH_SHORT).show();
        sizelocal.setVisibility(View.INVISIBLE);
    }
}


