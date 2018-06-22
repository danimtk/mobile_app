package com.example.dani.app12;

import android.content.Context;
import android.content.Intent;

/**
 * Created by dani on 06/06/18.
 */
public class Share {

    public static void share(Context context, int id)
    {
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
    /*
        CitacaoController cc = new CitacaoController(context);

         Citacao ci = cc.getQuote(id);

        String shareBody = ci.getCitacao();
        //sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject Here");
        sharingIntent.putExtra("com.facebook.platform.extra.APPLICATION_ID", "1018347338258548");
        // intent.putExtra(EXTRA_APP_ID, YOUR_APP_ID);
        sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
        context.startActivity(Intent.createChooser(sharingIntent, "Compartilhar com"));
    */
    }

    public static void share(Context context, String texto)
    {
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");


        String shareBody = texto;
        //sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject Here");
        sharingIntent.putExtra("com.facebook.platform.extra.APPLICATION_ID", "1018347338258548");
        // intent.putExtra(EXTRA_APP_ID, YOUR_APP_ID);
        sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
        context.startActivity(Intent.createChooser(sharingIntent, "Compartilhar com"));
    }
}
