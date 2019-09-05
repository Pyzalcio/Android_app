package com.example.michal.smarthome;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
                if(liczba>15 && liczba<33)
                    x=Integer.toString(liczba);
                else
                {
                    if(liczba<=15)
                        Toast.makeText(getApplicationContext(), "Zbyt niska temperatura", Toast.LENGTH_SHORT).show();
                    else if(liczba>=33)
                        Toast.makeText(getApplicationContext(), "Zbyt wysoka temperatura", Toast.LENGTH_SHORT).show();
                }
                temp.setText(x);
                break;
            case R.id.zm_temp:
                liczba--;
                if(liczba>15 && liczba<33)
                    x=Integer.toString(liczba);
                else
                {
                    if(liczba<=15)
                        Toast.makeText(getApplicationContext(), "Zbyt niska temperatura", Toast.LENGTH_SHORT).show();
                    else if(liczba>=33)
                        Toast.makeText(getApplicationContext(), "Zbyt wysoka temperatura", Toast.LENGTH_SHORT).show();
                }
                temp.setText(x);
                break;
            case R.id.ustaw_przycisk:
                EditText wartosc=(EditText) findViewById(R.id.ustaw_temp);
                String y=wartosc.getText().toString();
                liczba=Integer.parseInt(y);
                if(liczba>15 && liczba<33)
                    temp.setText(y);
                else
                {
                    if(liczba<=15)
                        Toast.makeText(getApplicationContext(), "Zbyt niska temperatura", Toast.LENGTH_SHORT).show();
                    else if(liczba>=33)
                        Toast.makeText(getApplicationContext(), "Zbyt wysoka temperatura", Toast.LENGTH_SHORT).show();
                }
                wartosc.setText("");
                break;
        }
    }
}
