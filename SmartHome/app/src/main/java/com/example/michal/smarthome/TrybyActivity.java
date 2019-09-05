package com.example.michal.smarthome;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.text.NumberFormat;

public class TrybyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tryby);
    }

    private TextView percentTextView;
    private SeekBar percentSeekBar;

    private static final NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
    private static final NumberFormat percentFormat = NumberFormat.getPercentInstance();

    double zaslonaProcent = 0;


    //zmienne do połączenia
    private int mProgressStatus=0;
    private Handler mHandler = new Handler();
    String adres="http://192.168.1.12";
    String url;
    boolean przerwanie=true;

    public void zaslony(String url)
    {
        //final TextView mTextView = (TextView) findViewById(R.id.text);
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //mTextView.setText("Response is: "+ response.substring(0,500));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //mTextView.setText("That didn't work!");
                Toast.makeText(getApplicationContext(), "Błąd połączenia", Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(stringRequest);
    }

    public void zaslona_zamknij()
    {
        new AsyncTask<Void, Void, Void>()
        {
            @Override
            protected Void doInBackground(final Void... params)
            {
                while (mProgressStatus < 100 && przerwanie==true)
                {
                    try {Thread.sleep(200);}
                    catch (InterruptedException e) {e.printStackTrace();}
                    mProgressStatus += 1;
                }
                url = adres+"/2/off";
                zaslony(url);
                return null;
            }

        }.execute();
    }

    public void zaslona_otworz()
    {
        new AsyncTask<Void, Void, Void>()
        {
            @Override
            protected Void doInBackground(final Void... params)
            {
                while (mProgressStatus > 0 && przerwanie==true)
                {
                    try {Thread.sleep(200);}
                    catch (InterruptedException e) {e.printStackTrace();}
                    mProgressStatus -= 1;
                }
                url = adres+"/6/off";
                zaslony(url);
                return null;
            }

        }.execute();
    }

    Boolean blad=true;
    public void wlacz_swiatlo(String url)
    {
        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        blad=true;
                    }
                }, new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {

                    Toast.makeText(getApplicationContext(), "Błąd połączenia", Toast.LENGTH_LONG).show();

                    blad=false;
                    }
                });
        queue.add(stringRequest);
    }

    public void dzien(View view)
    {
        String url;
        url="http://192.168.1.12/1/off";
        wlacz_swiatlo(url);
        url="http://192.168.1.12/7/off";
        wlacz_swiatlo(url);
        url="http://192.168.1.12/8/off";
        wlacz_swiatlo(url);
        url="http://192.168.1.12/0/off";
        wlacz_swiatlo(url);

        if(mProgressStatus!=0)
        {
            przerwanie=true;
            url = "http://192.168.1.12/5/on";
            zaslony(url);
            zaslona_otworz();
        }
    }
    public void wieczor(View view)
    {
        String url;
        url="http://192.168.1.12/6/on";
        wlacz_swiatlo(url);
        url="http://192.168.1.12/7/on";
        wlacz_swiatlo(url);
        url="http://192.168.1.12/8/on";
        wlacz_swiatlo(url);
        url="http://192.168.1.12/0/on";
        wlacz_swiatlo(url);

        if(mProgressStatus!=100)
        {
            przerwanie=true;
            url = "http://192.168.1.12/2/on";
            zaslony(url);
            zaslona_zamknij();
        }
    }
    public void noc(View view)
    {
        String url;
        url="http://192.168.1.12/6/off";
        wlacz_swiatlo(url);
        url="http://192.168.1.12/7/off";
        wlacz_swiatlo(url);
        url="http://192.168.1.12/8/off";
        wlacz_swiatlo(url);
        url="http://192.168.1.12/0/off";
        wlacz_swiatlo(url);

        if(mProgressStatus!=100)
        {
            przerwanie=true;
            url = "http://192.168.1.12/2/on";
            zaslony(url);
            zaslona_zamknij();
        }
    }
    public void wylacz_wszystko(View view)
    {
        przerwanie=false;
        url="http://192.168.1.12/9/off";
        wlacz_swiatlo(url);
        przerwanie=true;
    }

}
