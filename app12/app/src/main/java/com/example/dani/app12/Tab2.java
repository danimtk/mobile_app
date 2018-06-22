package com.example.dani.app12;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dani on 26/03/16.
 */
public class Tab2 extends Fragment {


    private SQLiteDatabase db;
    //private DatabaseHelper banco;

    //private CitacaoController cc;

    SwipeRefreshLayout swipeLayout;

  //  private CardArrayAdapterNoticia cardArrayAdapter;
    private ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.tab2,container,false);

    /*
        List<Noticia> noticias = new ArrayList<Noticia>();

        Noticia n1 = new Noticia();

        n1.setId(1);
        n1.setId_user(1);
        n1.setNome("Caroline A. Nascimento");
        n1.setImg("http://52.24.37.49/img/min/img-perfil/0b4399eb482f818224e3d3e3703eec44.jpg");
        n1.setFrase("Frase");
        n1.setAutor("Autor");
        n1.setLivro("Livro");
        noticias.add(n1);

        Noticia n2 = new Noticia();

        n2.setId(2);
        n2.setId_user(2);
        n2.setNome("Julio Sieg");
        n2.setImg("http://52.24.37.49/img/min/img-perfil/9418cf1abac00472321e1acfb2ac48f8.png");
        n2.setFrase("Frase");
        n2.setAutor("Autor");
        n2.setLivro("Livro");
        noticias.add(n2);

        Noticia n3 = new Noticia();

        n3.setId(3);
        n3.setId_user(3);
        n3.setNome("Edimar Manica");
        n3.setImg("http://52.24.37.49/img/min/img-perfil/2940d91390cc258526fb8f389e9455a0.jpg");
        n3.setFrase("Frase");
        n3.setAutor("Autor");
        n3.setLivro("Livro");
        noticias.add(n3);

        Noticia n4 = new Noticia();
        n4.setId(4);
        n4.setId_user(4);
        n4.setNome("Solange");
        n4.setImg("http://52.24.37.49/img/min/img-perfil/b1c54607d3d9816f944ea14f0a8e81ab.jpg");
        n4.setFrase("Frase da solange");
        n4.setAutor("Autor");
        n4.setLivro("Livro");
        noticias.add(n4);

        listView = (ListView) v.findViewById(R.id.card_list_news);

        cardArrayAdapter = new CardArrayAdapterNoticia(getContext(), R.layout.list_news);

        for (Noticia noticia : noticias) {
            Log.d("Autor", noticia.getNome());

            Noticia n = new Noticia(noticia.getId(), noticia.getId_user(), noticia.getNome(), noticia.getImg(), noticia.getFrase(), noticia.getAutor(), noticia.getLivro());
            cardArrayAdapter.add(n);

        }

        listView.setAdapter(cardArrayAdapter);
    */

        swipeLayout = (SwipeRefreshLayout) v.findViewById(R.id.swipe_tab2);


        swipeLayout.setColorSchemeResources(R.color.amarelo,
                android.R.color.holo_orange_light,
                android.R.color.holo_orange_dark,
                android.R.color.holo_red_light);

        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeLayout.setRefreshing(true);
                Log.d("oi", "oi");

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeLayout.setRefreshing(false);
                /*
                        Noticia n4 = new Noticia();

                        n4.setId(5);
                        n4.setId_user(5);
                        n4.setNome("Solange");
                        n4.setImg("http://52.24.37.49/img/min/img-perfil/b1c54607d3d9816f944ea14f0a8e81ab.jpg");
                        n4.setFrase("Simulação de recebimento de novidades do servidor. Frase da Solange.");
                        n4.setAutor("Clovis de Barros Filho");
                        n4.setLivro("Somos todos canalhas");

                        cardArrayAdapter.add(0, n4);

                        listView.setAdapter(cardArrayAdapter);
                */

                    }
                }, 2000);
            }
        });


        return v;
    }
}

