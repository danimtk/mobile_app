package com.example.dani.app12.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.dani.app12.model.MyFile;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by dani on 06/06/18.
 */
public class FileController {

    private SQLiteDatabase db;
    private DatabaseHelper banco;

    public FileController(Context context) {
        banco = new DatabaseHelper(context);
    }

    public boolean insertFile(MyFile file)
    {
        try
        {
            db = banco.getWritableDatabase();

            ContentValues values = new ContentValues();

            values.put(DatabaseHelper.NAME, file.getName());

            long result = db.insert("file", null, values);
            db.close();

            if (result == -1) {
                Log.d("Banco Error: ", "Erro ao inserir no banco");
                return false;
            } else {
                Log.d("Banco", "inseriu: "+file.toString()+"");
                return true;
            }
        }catch(Exception e){
            Log.d("Erro", e.toString());
            return false;
        }
    }


    /*
 * getting all
 * */
    public List<MyFile> getAllFiles() {

        List<MyFile> files = new ArrayList<>();

        String selectQuery = "SELECT * FROM file";

        db = banco.getReadableDatabase();

        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                MyFile f = new MyFile();
                f.setId(c.getInt(c.getColumnIndex(DatabaseHelper.ID)));
                f.setName(c.getString(c.getColumnIndex(DatabaseHelper.NAME)));

                files.add(f);
            } while (c.moveToNext());
        }

        return files;
    }

    public boolean deleteFile(int id) {

        String where = DatabaseHelper.ID+"="+id;

        db = banco.getReadableDatabase();
        long result = db.delete("file", where, null);
        db.close();

        if(result ==-1) {
            Log.d("Banco Error: ", "Erro ao alterar banco");
            return false;
        }else{
            Log.d("Banco: ", "Excluiu");
            return true;
        }
    }
}
