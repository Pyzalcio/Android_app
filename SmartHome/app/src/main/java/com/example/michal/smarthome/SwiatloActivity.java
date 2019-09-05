package com.example.michal.smarthome;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.net.ssl.HttpsURLConnection;

public class SwiatloActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swiatlo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    ToggleButton togiel;
    boolean jeden, dwa;

    Boolean blad=true;
    //TextView tekst_test=(TextView) findViewById(R.id.okno_testowe);
    //String test=tekst_test.getText().toString();
    public void czytaj_swiatlo(String url)
    {
        //final ProgressBar pasek = (ProgressBar) findViewById(R.id.stan_salon_swiatlo);
        final TextView mTextView = (TextView) findViewById(R.id.info_swiatlo);
        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        mTextView.setText("Response is: "+ response.substring(0,500));
                        //mTextView.setVisibility(View.INVISIBLE);
                        //pasek.setVisibility(View.VISIBLE);
                        blad=true;
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mTextView.setText("Błąd połączenia");
                //mTextView.setVisibility(View.VISIBLE);
                Toast.makeText(getApplicationContext(), "Błąd połączenia", Toast.LENGTH_LONG).show();
                //pasek.setVisibility(View.INVISIBLE);
                blad=false;
            }
        });
        queue.add(stringRequest);
    }

    public void wlacz_swiatlo(String url)
    {
        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        //mTextView.setText("Response is: "+ response.substring(0,500));
                        //mTextView.setVisibility(View.INVISIBLE);
                        //pasek.setVisibility(View.VISIBLE);
                        blad=true;
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //mTextView.setText("Błąd połączenia");
                //mTextView.setVisibility(View.VISIBLE);
                Toast.makeText(getApplicationContext(), "Błąd połączenia", Toast.LENGTH_LONG).show();
                //pasek.setVisibility(View.INVISIBLE);
                blad=false;
            }
        });
        queue.add(stringRequest);
    }
    public void swiatlo_click(View view) {
        ToggleButton przycisk;
        String url="http://192.168.1.12";
        czytaj_swiatlo(url);
        ProgressBar pasek = (ProgressBar) findViewById(R.id.stan_salon_swiatlo);
        switch(view.getId())
        {
            case R.id.toggleButton:
                przycisk=(ToggleButton) findViewById(R.id.toggleButton);
                if(przycisk.isChecked()) {
                    url = url+"/6/on";
                    wlacz_swiatlo(url);
                    if (blad == false) {
                        przycisk.setChecked(false);
                        pasek.setVisibility(View.INVISIBLE);
                    }
                    else
                    {
                        pasek.setVisibility(View.VISIBLE);
                        pasek.setProgress(100);
                    }
                }
                else {
                    url = url+"/6/off";
                    wlacz_swiatlo(url);
                    if(blad==false) {
                        przycisk.setChecked(true);
                        pasek.setVisibility(View.INVISIBLE);
                    }
                    else
                    {
                        pasek.setVisibility(View.VISIBLE);
                        pasek.setProgress(0);
                    }
                }
            break;
        }
    }

    public void swiatlo_przed_click(View view) {
        ToggleButton przycisk;
        String url="http://192.168.1.12";
        czytaj_swiatlo(url);
        ProgressBar pasek = (ProgressBar) findViewById(R.id.stan_salon_swiatlo);
        switch(view.getId())
        {
            case R.id.button_swiatlo_przed:
                przycisk=(ToggleButton) findViewById(R.id.button_swiatlo_przed);
                if(przycisk.isChecked()) {
                    url = url+"/7/on";
                    wlacz_swiatlo(url);
                }
                else {
                    url = url+"/7/off";
                    wlacz_swiatlo(url);
                }
                break;
        }
    }

    public void swiatlo_lazienka_click(View view) {
        ToggleButton przycisk;
        String url="http://192.168.1.12";
        czytaj_swiatlo(url);
        ProgressBar pasek = (ProgressBar) findViewById(R.id.stan_salon_swiatlo);
        switch(view.getId())
        {
            case R.id.button_swiatlo_lazienka:
                przycisk=(ToggleButton) findViewById(R.id.button_swiatlo_lazienka);
                if(przycisk.isChecked()) {
                    url = url+"/8/on";
                    wlacz_swiatlo(url);
                }
                else {
                    url = url+"/8/off";
                    wlacz_swiatlo(url);
                }
                break;
        }
    }

    public void swiatlo_sypialnia_click(View view) {
        ToggleButton przycisk;
        String url="http://192.168.1.12";
        czytaj_swiatlo(url);
        ProgressBar pasek = (ProgressBar) findViewById(R.id.stan_salon_swiatlo);
        switch(view.getId())
        {
            case R.id.button_swiatlo_sypialnia:
                przycisk=(ToggleButton) findViewById(R.id.button_swiatlo_sypialnia);
                if(przycisk.isChecked()) {
                    url = url+"/0/on";
                    wlacz_swiatlo(url);
                }
                else {
                    url = url+"/0/off";
                    wlacz_swiatlo(url);
                }
                break;
        }
    }

    public void ustaw_swiatlo_click(View view) {
        ProgressBar pasek = (ProgressBar) findViewById(R.id.stan_salon_swiatlo);
        EditText et = (EditText) findViewById(R.id.ustaw_jasnosc);
        int y=pasek.getProgress();
        String progres;
        int wartosc=0;
        String x;
        String url="http://192.168.1.12";
        switch (view.getId()) {
            case R.id.zmien_jasnosc:
                x=et.getText().toString();
                wartosc = Integer.parseInt(x);
                if (wartosc < 0 || wartosc > 100)
                    Toast.makeText(getApplicationContext(), "Wartość należy ustawić w procentach od 0 do 100", Toast.LENGTH_LONG).show();
                else if (wartosc >= 0 && wartosc <= 100) {
                    pasek.setProgress(wartosc);
                    y=wartosc;
                }
                else if(x.equals(""))
                    Toast.makeText(getApplicationContext(), "Nie podano wartości", Toast.LENGTH_LONG).show();
                et.setText("");
                break;
            case R.id.ciemno_button:
                if(y==0)
                {
                    Toast.makeText(getApplicationContext(), "Janość nie może być poniżej 0%", Toast.LENGTH_LONG).show();
                }
                else
                {
                    y=y-10;
                    pasek.setProgress(y);
                }
                break;
            case R.id.jasno_button:
                if(y==100)
                {
                    Toast.makeText(getApplicationContext(), "Janość nie może być powyżej 100%", Toast.LENGTH_LONG).show();
                }
                else
                {
                    y=y+10;
                    pasek.setProgress(y);
                }
                break;
        }
        if(y>=50)
        {
            url = url+"/6/on";
            wlacz_swiatlo(url);
        }
        else if(y<50)
        {
            url = url+"/6/off";
            wlacz_swiatlo(url);
        }

    }

    @Override
    protected void onStart() {
        Log.d ("aktywność", "onStart_swiatlo");
        super.onStart ();
    }

    @Override
    protected void onResume() {
        Log.d ("aktywność", "onResume_swiatlo");
        super.onResume ();
    }

    @Override
    protected void onPause() {
        Log.d ("aktywność", "onPause_swiatlo");
        //text=et.getText ().toString ();
        super.onPause ();
    }

    @Override
    protected void onRestart() {
        Log.d ("aktywność", "onRestart_swiatlo");
        super.onRestart ();
    }

    @Override
    protected void onDestroy() {
        Log.d ("aktywność", "onDestroy_swiatlo");
        super.onDestroy ();
    }
}
