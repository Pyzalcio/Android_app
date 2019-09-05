package com.example.ja.odliczanie;

import android.content.ClipData;
import android.content.Intent;
import android.icu.text.DateFormat;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import android.os.AsyncTask;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.sql.Date;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import pl.droidsonroids.gif.GifImageView;

public class MainActivity extends AppCompatActivity {

    public class Czas {
        long cale;
        long bierzace;
        long d = 0;
        long h = 0;
        long m = 0;
        long s = 0;
        long ms = 0;
    }

    String time_left="";
    Czas pozostalo = new Czas();
    private int counter=0;
    String help="";
    Boolean gone=false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final TextView text = findViewById(R.id.textView_pozostalo);
        final TextView dni=(TextView)findViewById(R.id.textView_dni);
        final GifImageView gif=(GifImageView)findViewById(R.id.jakis_gif);
        final Button button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button.setVisibility(View.INVISIBLE);
                gif.setVisibility(View.VISIBLE);
                a_time();
                gone=true;

                new CountDownTimer(counter, 1_000) {
                    @Override
                    public void onTick(long l)
                    {
                        help=Long.toString(pozostalo.d);
                        help+=" dni";
                        dni.setText(help);

                        if (pozostalo.h < 10)
                        {
                            time_left += "0";
                        }
                        time_left += Long.toString(pozostalo.h);
                        time_left += ":";
                        if (pozostalo.m < 10)
                        {
                            time_left += "0";
                        }
                        time_left += Long.toString(pozostalo.m);
                        time_left += ":";
                        if (pozostalo.s < 10)
                        {
                            time_left += "0";
                        }
                        time_left += Long.toString(pozostalo.s);

                        text.setText(time_left);
                        time_left="";
                        licz_czas();
                    }

                    @Override
                    public void onFinish() {
                        text.setText("I'm home !");
                    }
                }.start();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void pokaz_dni(MenuItem item)
    {
        /*Intent intent = new Intent(MainActivity.this, Main2Activity.class);
        startActivity(intent);*/
        TextView dni=(TextView)findViewById(R.id.textView_dni);
        //item opcja_menu=(TextView)findViewById(R.id.action_settings);
        if(gone==false)
        {
            Toast.makeText(getApplication().getBaseContext(), "Musisz najpierw uruchomić odliczanie !", Toast.LENGTH_LONG).show();
        }
        else
        {
            if (dni.getVisibility() == View.INVISIBLE)
            {
                dni.setVisibility(View.VISIBLE);
                pozostalo.h -= pozostalo.d * 24;

            } else if (dni.getVisibility() == View.VISIBLE)
            {
                dni.setVisibility(View.INVISIBLE);
                pozostalo.h += pozostalo.d * 24;
            }
        }
    }

    public void a_time()
    {
        long x = 561307;
        long y = 2788830;
        long final_time = x * y;
        final_time-=10800000;

        long bierz_czas = System.currentTimeMillis();
        pozostalo.bierzace=bierz_czas;

        long dzien = 86400000;

        long pozost_czas = final_time - bierz_czas;
        counter=Math.toIntExact(pozost_czas);

        long czas_bez_dni = pozost_czas % dzien;
        pozostalo.d = pozost_czas / 86400000;
        pozostalo.h = pozost_czas / 3600000;
        long reszta = pozost_czas % 3600000;
        pozostalo.m = reszta / 60000;
        reszta = reszta % 60000;
        pozostalo.s = reszta / 1000;
    }

    public void licz_czas()
    {
        if(pozostalo.s>0)
            pozostalo.s--;
        else
        {
            pozostalo.s=59;
            if(pozostalo.m>0)
                pozostalo.m--;
            else
                pozostalo.h--;
        }
    }
}
