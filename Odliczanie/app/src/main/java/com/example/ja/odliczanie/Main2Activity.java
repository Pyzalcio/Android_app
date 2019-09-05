package com.example.ja.odliczanie;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {


    public class Czasek {
        long cale;
        long bierzace;
        long d = 0;
        long h = 0;
        long m = 0;
        long s = 0;
    }

    String time_left="";
    Czasek pozostalo = new Czasek();
    private int counter=15;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        aktu_time();

        final TextView text = findViewById(R.id.textView_pozostalo);
        final Button button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button.setVisibility(View.INVISIBLE);

                new CountDownTimer(counter, 1_000) {
                    @Override
                    public void onTick(long l)
                    {
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
                        licze_czas();
                    }

                    @Override
                    public void onFinish() {
                        text.setText("Koniec !");
                    }
                }.start();
            }
        });

    }

    public void aktu_time()
    {
        pozostalo.h = 174;
        pozostalo.m = 30;
        pozostalo.s = 20;
        long x = 561307;
        long y = 2788830;
        long final_time = x * y;

        long bierz_czas = System.currentTimeMillis();
        pozostalo.bierzace=bierz_czas;

        long dzien = 86400000;

        long pozost_czas = final_time - bierz_czas;
        counter= Math.toIntExact(pozost_czas);

        long czas_bez_dni = pozost_czas % dzien;
        pozostalo.h = pozost_czas / 3600000;
        long reszta = pozost_czas % 3600000;
        pozostalo.m = reszta / 60000;
        reszta = reszta % 60000;
        pozostalo.s = reszta / 1000;


        String roznica = Long.toString(pozost_czas);
    }

    public void licze_czas()
    {
        if(pozostalo.s>0)
            pozostalo.s--;
        else
        {
            pozostalo.s=59;
            if(pozostalo.m<0)
                pozostalo.m--;
            else
                pozostalo.h--;
        }
    }

}
