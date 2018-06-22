package com.example.dani.app12;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by dani on 06/06/18.
 */
public class MainActivity extends AppCompatActivity {


    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        session = new SessionManager(getApplicationContext());

        if(session.isLoggedIn()){
            Intent secondActivity = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(secondActivity);
        }else{
            Intent secondActivity = new Intent(MainActivity.this, BemVindoActivity.class);
            startActivity(secondActivity);
        }
    }
}
