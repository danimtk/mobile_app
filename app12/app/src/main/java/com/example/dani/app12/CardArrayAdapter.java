package com.example.dani.app12;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dani.app12.connection.Connection;

import java.util.ArrayList;
import java.util.List;

public class CardArrayAdapter  extends ArrayAdapter<Card> {
    private static final String TAG = "CardArrayAdapter";
    private static List<Card> cardList = new ArrayList<Card>();

    static class CardViewHolder {
        TextView line1, line2 , line3;
        Button  btshare;
        ImageButton btmenu;
    }

    public CardArrayAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }


    //@Override
    public void add(Card object) {
        cardList.add(object);
        super.add(object);
    }

   // @Override
    public void add(int index, Card object) {
        cardList.add(index, object);
        //add(index, object);
    }

    public void removeAll() {
        for(int i = 0; i<this.cardList.size(); i++){
            cardList.remove(i);
        }
    }

    @Override
    public int getCount() {
        return this.cardList.size();
    }

 //   @Override
    public Card getItem(int index) {
        return this.cardList.get(index);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        final CardViewHolder viewHolder;
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.list_item_card, parent, false);

            viewHolder = new CardViewHolder();
            viewHolder.line1 = (TextView) row.findViewById(R.id.frase);
            viewHolder.line2 = (TextView) row.findViewById(R.id.autor);
            viewHolder.line3 = (TextView) row.findViewById(R.id.livro);
            viewHolder.btshare = (Button) row.findViewById(R.id.btsharec);
            viewHolder.btmenu = (ImageButton) row.findViewById(R.id.quote_menu);

            row.setTag(viewHolder);
        } else {
            viewHolder = (CardViewHolder)row.getTag();
        }

        viewHolder.btshare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = (int) v.getTag();
                Log.d("tag: ", String.valueOf(id));
                Share sh = new Share();
                sh.share(getContext(), id);
            }
        });

        final Card mCartItem =  getItem(position);


        viewHolder.btmenu.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                final int cod = (int) v.getTag();
                Log.d("tag: ", String.valueOf(cod));
                //Creating the instance of PopupMenu
                PopupMenu popup = new PopupMenu(getContext(), viewHolder.btmenu);
                //Inflating the Popup using xml file
                popup.getMenuInflater().inflate(R.menu.menu_quote, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {

                        int id = item.getItemId();

                        //noinspection SimplifiableIfStatement
                        if (id == R.id.action_delete) {
                            //    Toast.makeText(getContext(), "Excluir " + cod, Toast.LENGTH_SHORT).show();

                            AlertDialog.Builder alert = new AlertDialog.Builder(getContext());

                            alert.setTitle(R.string.dialog_titulo_exclusao);
                            alert.setMessage(R.string.dialog_mensagem_exclusao);
                            alert.setPositiveButton(R.string.sim, new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                  /*
                                    CitacaoController cc = new CitacaoController(getContext());

                                    // seta citação como removida (status = 3) para sinalizar o servidor futuramente
                                    // quando houver conexão e passa um sinal para o cardlist para remover da lista

                                    if(cc.SetRemoved(cod)) {
                                        cardList.remove(mCartItem);

                                        Connection con = new Connection();
                                        // se há conexão com internet, inicia a thread de sincronização para deletar

                                        if (con.haveNetworkConnection(getContext())) {
                                            new SynDeleteCitacao().execute(getContext());
                                            Toast.makeText(getContext(),"Excluindo frase...", Toast.LENGTH_LONG).show();

                                        }else {
                                            Toast.makeText(getContext(),"Você não está conectado. Vamos sincronizar futuramente.", Toast.LENGTH_LONG).show();
                                        }
                                    }

                                    // queremos atualizar qualquer lista vista em anexo ao nosso MyAdapter
                                    // Isto irá atualizar o listview

                                    notifyDataSetChanged();

                                    dialog.dismiss();
                                    */
                                }
                            });
                            alert.setNegativeButton(R.string.nao, new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    dialog.dismiss();
                                }
                            });

                            alert.show();

                            return true;
                        }

                        if(id == R.id.action_edit) {
                            Toast.makeText(getContext(), "Editar " + cod, Toast.LENGTH_SHORT).show();
                            return true;
                        }
                        return false;
                    }
                });

                popup.show();//showing popup menu
            }
        });

        Card card = getItem(position);
        viewHolder.btshare.setTag(card.getId());
        viewHolder.btmenu.setTag(card.getId());
        viewHolder.line1.setText(card.getLine1());
        viewHolder.line2.setText(card.getLine2());
        viewHolder.line3.setText(card.getLine3());
        viewHolder.btshare.setText("Compartilhar");

        return row;
    }

    public Bitmap decodeToBitmap(byte[] decodedByte) {
        return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
    }
}
