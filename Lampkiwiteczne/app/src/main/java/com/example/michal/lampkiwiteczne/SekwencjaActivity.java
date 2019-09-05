package com.example.michal.lampkiwiteczne;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class SekwencjaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sekwencja);

    }
    Boolean stop = true;
    Boolean blad = true;
    String URL = "http://192.168.1.12";

    public void wlacz_swiatlo(String url) {
        RequestQueue queue = Volley.newRequestQueue(this);


        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        blad = true;
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Błąd połączenia", Toast.LENGTH_LONG).show();
                blad = false;
            }
        });
        queue.add(stringRequest);
    }

    public void mruganie(View view) {
        String url;
        url = URL + "/mruganie/";
        wlacz_swiatlo(url);
    }

    public void przemienne(View view) {
        String url;
        url = URL + "/przemienne/";
        wlacz_swiatlo(url);
    }

    public void przycisk_stop(View view) {
        String url;
        stop=false;
        url = URL + "/stop/";
        wlacz_swiatlo(url);
        stop=true;
    }
}