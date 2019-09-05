package com.example.michal.strefa_kursow;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void klik(View view) {

        TextView tw;
        Button button1;
        tw = (TextView) findViewById(R.id.napis);
        button1 = (Button) findViewById(R.id.przycisk);

        ArrayList<Integer> list = new ArrayList<Integer>();
        for(int i=1; i<50; i++)
        {
            list.add(i);
        }

        Collections.shuffle(list);

        ArrayList<Integer> wybrane = new ArrayList<Integer>();
        for(int i=0; i<6; i++)
        {
            wybrane.add(list.get(i));
        }
        Collections.sort(wybrane);

        String nowy_napis = wybrane.get(0).toString();
        for(int i=1; i<6; i++)
        {
            nowy_napis += ", ";
            nowy_napis += wybrane.get(i).toString();
        }

        tw.setText(nowy_napis);
    }
}
