package com.example.dani.app12;

import android.Manifest;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Vibrator;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import android.graphics.Bitmap;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.dani.app12.connection.Connection;
import com.example.dani.app12.slidingtab.ImageResource;
import com.example.dani.app12.slidingtab.SlidingTabLayout;
import com.example.dani.app12.slidingtab.ViewPagerAdapter;
import com.github.clans.fab.FloatingActionButton;


public class HomeActivity extends AppCompatActivity {

    public static final int PERMISSAO_CAMERA = 2;
    public static final int PERMISSAO_ARMA_EXTERNO = 1;

    public static final int REQUEST_CAMERA = 1;
    public static final int REQUEST_CROP = 2;

    public static final String TAG = "HomeActivity";

    SessionManager session;
    private Bitmap bmp;
    URI imageUri;

    private PrefManager prefManager;
    private DrawerLayout mDrawerLayout;

    ImageView imgview;

    public static String user_key;

    // Declaring Your View and Variables
    Toolbar toolbar;
    ViewPager pager;
    ViewPagerAdapter adapter;
    SlidingTabLayout tabs;



    CharSequence Titles[]={"1", "2"};

     CharSequence BTitles[]={"Detalhes", "Configurações"};

    //    int tabIcons[] = {R.drawable.ic_quote, R.drawable.ic_action_name};

    int Numboftabs = 2;

    @Override
    public void onBackPressed() {
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_main);


        session = new SessionManager(getApplicationContext());

        HashMap<String, String> user = session.getUserDetails();

        user_key = user.get(SessionManager.KEY_ACESS);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setElevation(0);
        toolbar.setTitleTextColor(Color.WHITE);

        changeStatusBarColor();



        if (Build.VERSION.SDK_INT >= 23) {

            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                Log.e(TAG, "Permission Storage is granted");
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSAO_ARMA_EXTERNO);
                Log.e(TAG, "Permission Storage is revoked");
            }

            if (checkSelfPermission(Manifest.permission_group.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                Log.e(TAG, "Permission Camera is granted");
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission_group.CAMERA}, PERMISSAO_CAMERA);
                Log.e(TAG, "Permission Camera is revoked");
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            Log.e(TAG, "Permission is already granted");
        }

        FloatingActionButton fab = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.menu_item);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                prefManager = new PrefManager(getApplicationContext());

                Timestamp timestamp = new Timestamp(System.currentTimeMillis());

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                File file = new File(Environment.getExternalStorageDirectory(), "/DCIM/Camera/"+timestamp+".jpg");
                Uri outputFileUri = Uri.fromFile(file);

                intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);

                Log.d(TAG+" Cheguei: ", outputFileUri.toString());

                startActivityForResult(intent, REQUEST_CAMERA);
            }
        });

        final com.github.clans.fab.FloatingActionButton fab2 = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.menu_item2);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              /*
                Intent secondActivity = new Intent(getApplicationContext(), QuoteActivity.class);
                startActivity(secondActivity);
              */
            }
        });

        ActionBar ab = getSupportActionBar();
        ab.setTitle(BTitles[0]);

        // Creating The ViewPagerAdapter and Passing Fragment Manager, Titles fot the Tabs and Number Of Tabs.
        adapter =  new ViewPagerAdapter(getSupportFragmentManager(),Titles,Numboftabs);

        // Assigning ViewPager View and setting the adapter
        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter);

        // Assiging the Sliding Tab Layout View
        tabs = (SlidingTabLayout) findViewById(R.id.tabs);

        tabs.setDistributeEvenly(true); // To make the Tabs Fixed set this true, This makes the tabs Space Evenly in Available width

        // Setting Custom Color for the Scroll bar indicator of the Tab View
        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.branco);
            }
        });


        tabs.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                android.support.v7.app.ActionBar ab = getSupportActionBar();
                ab.setTitle(BTitles[position]);

                if(position == 0) {

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        // Setting the ViewPager For the SlidingTabsLayout
        tabs.setViewPager(pager);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSAO_ARMA_EXTERNO:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d("permissão", " aham");
                    Toast.makeText(getApplicationContext(), "Já tem permissão", Toast.LENGTH_LONG).show();
                } else {
                    Log.d("permissão", "necas");
                    Toast.makeText(getApplicationContext(), "Não tem permissão", Toast.LENGTH_LONG).show();
                }
                break;

            case PERMISSAO_CAMERA:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d("permissão", " aham");
                    Toast.makeText(getApplicationContext(), "Já tem permissão", Toast.LENGTH_LONG).show();

                } else {
                    Log.d("permissão", "necas");
                    Toast.makeText(getApplicationContext(), "Não tem permissão", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            pager.setCurrentItem(1);
            return true;
        }

        if(id == R.id.action_exit) {
            session.logoutUser();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Making notification bar transparent
     */
    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.colorb));
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK)
        {
            //user is returning from capturing an image using the camera
            if (requestCode == REQUEST_CAMERA) {
                //get the
                // Uri for the captured image
            }
            //user is returning from cropping the image
            if (requestCode == REQUEST_CROP)
            {

            }

            if(requestCode == 3) {

            //    File file = new File(Environment.getExternalStorageDirectory(), "/tesseract/teste.jpg");
            //    Uri outputFileUri = Uri.fromFile(file);

            }
        }
    }

}