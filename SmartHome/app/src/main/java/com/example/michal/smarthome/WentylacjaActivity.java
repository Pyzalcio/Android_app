package com.example.michal.smarthome;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class WentylacjaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wentylacja);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    Boolean blad=true;
    public void wlacz_wentylator(String url)
    {
        final TextView mTextView = (TextView) findViewById(R.id.text);
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        //mTextView.setText("Response is: "+ response.substring(0,500));
                        blad=true;
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //mTextView.setText("That didn't work!");
                blad=false;
                Toast.makeText(getApplicationContext(), "Błąd połączenia", Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(stringRequest);
    }

    public void went_click(View view) {
        String url="http://192.168.1.12";
        ToggleButton przycisk, przycisk2;
        TextView e1;
        przycisk=(ToggleButton) findViewById(R.id.wentyl_tryb);
        przycisk2=(ToggleButton) findViewById(R.id.wentyl_on_off);
        e1=(TextView) findViewById(R.id.wentylator_text);
        switch(view.getId())
        {
            case R.id.wentyl_on_off:
                if (przycisk2.isChecked()) {
                    url = url+"/1/on";
                    wlacz_wentylator(url);
                    if (blad == false)
                        przycisk2.setChecked(false);
                }
                else {
                    url = url+"/1/off";
                    wlacz_wentylator(url);
                    if(blad==false)
                        przycisk2.setChecked(true);
                }
                break;
            case R.id.wentyl_tryb:
                if(przycisk.isChecked())
                {
                    przycisk2.setVisibility(View.INVISIBLE);
                    e1.setVisibility(View.INVISIBLE);
                    url = url+"/3/off";
                    wlacz_wentylator(url);
                    if(blad==false)
                        przycisk2.setChecked(true);
                }
                else
                {
                    przycisk2.setVisibility(View.VISIBLE);
                    e1.setVisibility(View.VISIBLE);
                    url = url+"/3/on";
                    wlacz_wentylator(url);
                    if (blad == false)
                        przycisk2.setChecked(false);
                }
                break;
        }
    }
}
