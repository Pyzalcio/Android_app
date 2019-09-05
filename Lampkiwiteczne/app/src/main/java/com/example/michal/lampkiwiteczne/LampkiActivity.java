package com.example.michal.lampkiwiteczne;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class LampkiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lampki);
    }
    ToggleButton togiel;
    boolean jeden, dwa;

    Boolean blad=true;

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
    public void swiatlo_jeden(View view) {
        ToggleButton przycisk;
        String url="http://192.168.1.12";
        switch(view.getId())
        {
            case R.id.button_lampki_1:
                przycisk=(ToggleButton) findViewById(R.id.button_lampki_1);
                if(przycisk.isChecked()) {
                    url = url+"/1/on";
                    wlacz_swiatlo(url);
                }
                else {
                    url = url+"/1/off";
                    wlacz_swiatlo(url);
                }
                break;
        }
    }

    public void swiatlo_dwa(View view) {
        ToggleButton przycisk;
        String url="http://192.168.1.12";
        switch(view.getId())
        {
            case R.id.button_lampki_2:
                przycisk=(ToggleButton) findViewById(R.id.button_lampki_2);
                if(przycisk.isChecked()) {
                    url = url+"/2/on";
                    wlacz_swiatlo(url);
                }
                else {
                    url = url+"/2/off";
                    wlacz_swiatlo(url);
                }
                break;
        }
    }

    public void swiatlo_trzy(View view) {
        ToggleButton przycisk;
        String url="http://192.168.1.12";
        switch(view.getId())
        {
            case R.id.button_lampki_3:
                przycisk=(ToggleButton) findViewById(R.id.button_lampki_3);
                if(przycisk.isChecked()) {
                    url = url+"/5/on";
                    wlacz_swiatlo(url);
                }
                else {
                    url = url+"/5/off";
                    wlacz_swiatlo(url);
                }
                break;
        }
    }

    public void swiatlo_cztery(View view) {
        ToggleButton przycisk;
        String url="http://192.168.1.12";
        switch(view.getId())
        {
            case R.id.button_lampki_4:
                przycisk=(ToggleButton) findViewById(R.id.button_lampki_4);
                if(przycisk.isChecked()) {
                    url = url+"/6/on";
                    wlacz_swiatlo(url);
                }
                else {
                    url = url+"/6/off";
                    wlacz_swiatlo(url);
                }
                break;
        }
    }

    public void wszystkie(View view) {
        ToggleButton przycisk;
        String url="http://192.168.1.12";
        switch(view.getId())
        {
            case R.id.button_all:
                przycisk=(ToggleButton) findViewById(R.id.button_all);
                if(przycisk.isChecked()) {
                    url = url+"/9/on";
                    wlacz_swiatlo(url);
                }
                else {
                    url = url+"/9/off";
                    wlacz_swiatlo(url);
                }
                break;
        }
    }

    public void sekwencja(View view) {
        Intent intent= new Intent(LampkiActivity.this, SekwencjaActivity.class);
        startActivity(intent);
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
