package com.example.dani.app12;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.dani.app12.connection.Connection;
import com.example.dani.app12.model.MyFile;
import com.example.dani.app12.persistence.FileController;
import com.example.dani.app12.task.GetAllSize;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by dani on 26/03/16.
 */
public class Tab1 extends Fragment {

    SwipeRefreshLayout swipeLayout;

    FileController fc;

    TextView allsize;
    Button localsize;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        fc = new FileController(getActivity());

        View v = inflater.inflate(R.layout.tab1, container, false);

        allsize = (TextView) v.findViewById(R.id.sizeall);

        localsize = (Button) v.findViewById(R.id.btshare);

        if (Connection.haveNetworkConnection(getActivity()) != null)
        {
            new AsyncUpload(getActivity(), allsize, localsize).execute(getActivity());
        }


        swipeLayout = (SwipeRefreshLayout) v.findViewById(R.id.swipe_tab1);

        swipeLayout.setColorSchemeResources(R.color.amarelo,
                android.R.color.holo_red_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_orange_dark);

        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeLayout.setRefreshing(true);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeLayout.setRefreshing(false);
                    }
                }, 2000);
            }
        });


        localsize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //atributo da classe.
                AlertDialog alerta;

                //Cria o gerador do AlertDialog
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                //define o titulo
                builder.setTitle("Exclusão");
                //define a mensagem
                builder.setMessage("Deseja excluir as fotos já sincronizadas?");
                //define um botão como positivo
                builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        new DeleteFile(localsize).execute(getActivity());
                    }
                });
                //define um botão como negativo.
                builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        // Toast.makeText(MainActivity.this, "negativo=" + arg1, Toast.LENGTH_SHORT).show();
                    }
                });
                //cria o AlertDialog
                alerta = builder.create();
                //Exibe
                alerta.show();
            }
        });

        swipeLayout = (SwipeRefreshLayout) v.findViewById(R.id.swipe_tab1);


        swipeLayout.setColorSchemeResources(R.color.amarelo,
                android.R.color.holo_orange_light,
                android.R.color.holo_orange_dark,
                android.R.color.holo_red_light);

        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeLayout.setRefreshing(true);

                new AsyncUpload(getActivity(), allsize, localsize).execute(getActivity());

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeLayout.setRefreshing(false);


                    }
                }, 2000);
            }
        });

        return v;
    }
}



