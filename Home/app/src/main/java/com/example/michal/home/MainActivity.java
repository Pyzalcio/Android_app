package com.example.michal.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    Users[] users = new Users[10];

    public void uzytkownicy()
    {
        users[0]=new Users("usr", "usr");
        for(int i=1; i<10; i++)
            users[i]=new Users();
    }

    public void dodaj_uzytkownika(String name, String pass)
    {
        int i;
        for(i=0; i<10; i++) {
            if (users[i].get_nick_name() == "admin")
                break;
        }
        users[i].set_nick_name(name);
        users[i].set_newpass(pass);
    }

    public void logowanie(View view) {
        uzytkownicy();
        //for(int i=0; i<3; i++)
           // Toast.makeText ( getApplicationContext (), users[i].get_nick_name (), Toast.LENGTH_SHORT ).show ();
        Intent intent= new Intent(MainActivity.this, WstepActivity.class);
        EditText et1, et2;
        TextView co;
        co = (TextView) findViewById(R.id.textView);
        et1 = (EditText) findViewById(R.id.login);
        et2 = (EditText) findViewById(R.id.haslo);
        String log = et1.getText().toString();
        String pass = et2.getText().toString();
        for(int i=0; i<10; i++) {
            if (log.equals(users[i].get_nick_name()) && pass.equals(users[i].get_newpass())) {
                startActivity(intent);
                co.setText("Zapraszamy !");
                et1.setText("");
                et2.setText("");
                Toast.makeText(getApplicationContext(), "Zalogowano poprawnie", Toast.LENGTH_SHORT).show();
                finish();
            } else if (log.equals("") && pass.equals(""))
                co.setText("Proszę podać login i hasło, aby się zalogować.");
            else if (log.equals("") && !pass.equals(""))
                co.setText("Nie podałeś loginu.");
            else if (pass.equals("") && !log.equals(""))
                co.setText("Nie podałeś hasła.");
            else if (!log.equals("usr") && !pass.equals(""))
                co.setText("Podany użytkownik nie istnieje.");
            else if (!pass.equals("usr") && log.equals("usr"))
                co.setText("Podane hasło jest nieprawidłowe.");
        }
    }

    public class Users {
        private String nick_name;
        private String newpass;

        public Users()
        {
            nick_name="admin";
            newpass="admin";
        }
        public Users(String nick_name, String newpass)
        {
            this.nick_name=nick_name;
            this.newpass=newpass;
        }
        public String get_nick_name()
        {
            return nick_name;
        }
        public String get_newpass()
        {
            return newpass;
        }
        public void set_nick_name(String nick_name)
        {
            this.nick_name=nick_name;
        }
        public void set_newpass(String newpass)
        {
            this.newpass=newpass;
        }
    }
}
