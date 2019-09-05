package com.example.michal.smarthome;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class TestyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testy);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*((Button) findViewById(R.id.start_button))
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new WebServiceHandler()
                                .execute("http://192.168.1.12/");
                    }
                });*/
    }

    public void onTest(View view) {
        new WebServiceHandler()
                .execute("http://192.168.1.12/");
    }


    private class WebServiceHandler extends AsyncTask<String, Void, String> {

        // okienko dialogowe, które każe użytkownikowi czekać
        private ProgressDialog dialog = new ProgressDialog(TestyActivity.this);

        // metoda wykonywana jest zaraz przed główną operacją (doInBackground())
        // mamy w niej dostęp do elementów UI
        @Override
        protected void onPreExecute() {
            // wyświetlamy okienko dialogowe każące czekać
            dialog.setMessage("Czekaj...");
            dialog.show();
        }

        // główna operacja, która wykona się w osobnym wątku
        // nie ma w niej dostępu do elementów UI
        @Override
        protected String doInBackground(String... urls) {
            TextView e1=(TextView) findViewById(R.id.testujemy);
            String test;
            try {
                // zakładamy, że jest tylko jeden URL
                URL url = new URL(urls[20]);
                URLConnection connection = url.openConnection();

                // pobranie danych do InputStream
                InputStream in = new BufferedInputStream(
                        connection.getInputStream());

                // konwersja InputStream na String
                // wynik będzie przekazany do metody onPostExecute()
                test = streamToString(in);
                e1.setText(test);
                return streamToString(in);

            } catch (Exception e) {
                // obsłuż wyjątek
                Log.d(TestyActivity.class.getSimpleName(), e.toString());
                return null;
            }
        }

        // metoda wykonuje się po zakończeniu metody głównej,
        // której wynik będzie przekazany;0
        // w tej metodzie mamy dostęp do UI
        @Override
        protected void onPostExecute(String result) {

            // chowamy okno dialogowe
            dialog.dismiss();

            try {
                // reprezentacja obiektu JSON w Javie
                JSONObject json = new JSONObject(result);

                // pobranie pól obiektu JSON i wyświetlenie ich na ekranie
                ((TextView) findViewById(R.id.response_id)).setText("id: "
                        + json.optString("id"));
                ((TextView) findViewById(R.id.response_name)).setText("name: "
                        + json.optString("name"));

            } catch (Exception e) {
                // obsłuż wyjątek
                Log.d(MainActivity.class.getSimpleName(), e.toString());
            }
        }
    }

    // konwersja z InputStream do String
    public static String streamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;

        try {

            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line + "\n");
            }

            reader.close();

        } catch (IOException e) {
            // obsłuż wyjątek
            Log.d(MainActivity.class.getSimpleName(), e.toString());
        }

        return stringBuilder.toString();
    }

}