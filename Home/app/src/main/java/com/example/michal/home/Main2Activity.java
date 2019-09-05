package com.example.michal.home;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import java.lang.Object;
import java.net.*;
import android.view.View;
import android.widget.Toast;


public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void swiatlo_click(View view) {
        try
        {
            URL url = new URL("192.168.1.12/5/on");
        }
        catch(Exception e)
        {
            Toast.makeText ( getApplicationContext(), e.toString(), Toast.LENGTH_LONG ).show();
        }
    }

    private class HttpRequestTask extends AsyncTask<Void, Void, Greeting> {
        @Override
        protected Greeting doInBackground(Void... params) {
            try {
                final String url = "192.168.1.12/5/on";
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                Greeting greeting = restTemplate.getForObject(url, Greeting.class);
                return greeting;
            }
            catch (Exception e) {
                Toast.makeText ( getApplicationContext(), e.toString(), Toast.LENGTH_LONG ).show();
            }

            return null;
        }
}
