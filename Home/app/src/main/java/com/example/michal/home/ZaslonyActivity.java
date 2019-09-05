package com.example.michal.home;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

public class ZaslonyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zaslony);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
    public void otwieranie(View view) throws InterruptedException {
        ProgressBar progres = (ProgressBar) findViewById(R.id.stan_salon);
        ProgressBar progres2 = (ProgressBar) findViewById(R.id.stan_kuchnia);
        switch (view.getId())
        {
            case R.id.zaslon_salon:
                if(progres.getProgress()!=100) {
                    for (int i = 0; i <= 100; i = i + 5) {
                        progres.setProgress(i);
                        Thread.sleep(20);
                    }
                }
                break;

            case R.id.odslon_salon:
                if(progres.getProgress()!=0) {
                    for (int i = 100; i >= 0; i = i - 5) {
                        progres.setProgress(i);
                        Thread.sleep(20);
                    }
                }
                break;
            case R.id.zaslon_kuchnia:
                if(progres2.getProgress()!=100) {
                    for (int i = 0; i <= 100; i = i + 5) {
                        progres2.setProgress(i);
                        Thread.sleep(20);
                    }
                }
                break;
            case R.id.odslon_kuchnia:
                if(progres2.getProgress()!=0) {
                    for (int i = 100; i >= 0; i = i - 5) {
                        progres2.setProgress(i);
                        Thread.sleep(20);
                    }
                }
                break;
        }
    }
}
