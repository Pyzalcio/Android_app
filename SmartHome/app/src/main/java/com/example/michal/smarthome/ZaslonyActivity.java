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

public class ZaslonyActivity extends AppCompatActivity {


    //Seekbar

    private TextView percentTextView;
    private SeekBar percentSeekBar;

    private static final NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
    private static final NumberFormat percentFormat = NumberFormat.getPercentInstance();

    double zaslonaProcent = 0;
    int calkowityProcent;

    //zmienne do połączenia
    private int mProgressStatus=0;
    private Handler mHandler = new Handler();
    String adres="http://192.168.1.12";
    String url;
    boolean przerwanie=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zaslony);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //inicjalizacja

        percentTextView = (TextView) findViewById(R.id.percentTextView);
        percentSeekBar = (SeekBar) findViewById(R.id.percentSeekBar);

        percentSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                zaslonaProcent=progress/100.0;
                zmienSeekBar();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

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

    public void zaslona_zamknij() {
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.stan_salon);
        progressBar.setProgress(mProgressStatus);

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(final Void... params) {
                while (mProgressStatus < 100 && przerwanie==true && mProgressStatus!=calkowityProcent) {
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    mProgressStatus += 1;
                    mHandler.post(new Runnable() {
                        public void run() {
                            progressBar.setProgress(mProgressStatus);
                        }
                    });
                }
                url = adres+"/2/off";
                zaslony(url);
                return null;
            }

        }.execute();
    }

    public void zaslona_otworz() {
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.stan_salon);
        progressBar.setProgress(mProgressStatus);

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(final Void... params) {
                while (mProgressStatus > 0 && przerwanie==true && mProgressStatus!=calkowityProcent) {
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    mProgressStatus -= 1;
                    mHandler.post(new Runnable() {
                        public void run() {
                            progressBar.setProgress(mProgressStatus);
                        }
                    });
                }
                url = adres+"/5/off";
                zaslony(url);
                return null;
            }

        }.execute();
    }


    public void otwieranie(View view) throws InterruptedException {
        ProgressBar progres = (ProgressBar) findViewById(R.id.stan_salon);
        Button przycisk_odslon = (Button) findViewById(R.id.odslon_salon);
        Button przycisk_zaslon = (Button) findViewById(R.id.zaslon_salon);
        switch (view.getId())
        {
            case R.id.zaslon_salon:
                if(progres.getProgress()!=100) {
                    calkowityProcent=100;
                    przerwanie=false;
                    Thread.sleep(200);
                    przerwanie=true;
                    url = adres+"/2/on";

                    zaslony(url);
                    Thread.sleep(200);

                    zaslona_zamknij();
                }
                break;
            case R.id.odslon_salon:
                if(progres.getProgress()!=0) {
                    calkowityProcent=0;
                    przerwanie=false;
                    Thread.sleep(200);
                    przerwanie=true;
                    url = adres+"/5/on";
                    zaslony(url);
                    zaslona_otworz();
                }
                break;
            case R.id.zaslony_stop:
                przerwanie=false;
                Thread.sleep(500);
                przerwanie=true;
                break;
        }
    }

    private void zmienSeekBar()
    {
        percentTextView.setText(percentFormat.format(zaslonaProcent));
        double x = zaslonaProcent*100.0;
        calkowityProcent=(int)x;

        przerwanie=false;
        SystemClock.sleep(200);
        przerwanie=true;
        if(mProgressStatus>calkowityProcent)
        {
            przerwanie=false;
            przerwanie=true;
            url = adres+"/5/on";
            zaslony(url);
            zaslona_otworz();
        }
        else if(mProgressStatus<calkowityProcent)
        {
            przerwanie=false;
            przerwanie=true;
            url = adres+"/2/on";
            zaslony(url);
            zaslona_zamknij();
        }
    }


}
