package com.example.dani.app12.slidingtab;

import android.app.Activity;
import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.example.dani.app12.RestClient;
import com.example.dani.app12.model.MyFile;
import com.example.dani.app12.persistence.FileController;

import java.io.File;

/**
 * Created by dani on 21/06/18.
 */

public class ImageResource {

    public static File[] getImages() {

        File direct = new File(Environment.getExternalStorageDirectory() + "/DCIM/Camera");

        if (direct.exists()) {
            File[] listOfFiles = direct.listFiles();
            return listOfFiles;
        }
        return null;
    }

    public static void prepareToUploadImages(File[] listOfFiles, String user_key, Context contexto)
    {
        FileController fc = new FileController(contexto);

        for (File file : listOfFiles) {

            if (file.isFile() && (file.toString().contains("jpeg")
                        || file.toString().contains("jpg")
                        || file.toString().contains("png")))
            {
                if (RestClient.postImage(file.getAbsolutePath(), user_key, file.length())) {
                    System.out.println("confirma a confirmação");
                    fc.insertFile(new MyFile(file.getName()));
                }
            }
        }

        Log.d("Alerta> ", "Existe diretorio da câmera");
    }
}
