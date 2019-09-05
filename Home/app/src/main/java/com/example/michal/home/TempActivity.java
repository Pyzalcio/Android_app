package com.example.michal.home;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class TempActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void temp_click(View view) {
        TextView temp;
        temp=(TextView) findViewById(R.id.temperatura);
        String x=temp.getText().toString();
        int liczba=Integer.parseInt(x);
        switch(view.getId())
        {
            case R.id.zw_temp:
                liczba++;
                if(liczba>10 && liczba<36)
                    x=Integer.toString(liczba);
                temp.setText(x);
                break;
            case R.id.zm_temp:
                liczba--;
                if(liczba>10 && liczba<36)
                    x=Integer.toString(liczba);
                temp.setText(x);
                break;
            case R.id.ustaw_przycisk:
                EditText wartosc=(EditText) findViewById(R.id.ustaw_temp);
                String y=wartosc.getText().toString();
                liczba=Integer.parseInt(y);
                if(liczba>10 && liczba<36)
                    temp.setText(y);
                wartosc.setText("");
                break;
        }
    }
}
