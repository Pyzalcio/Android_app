package com.example.michal.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddUserActivity extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText et1, et2, et3;
                et1=(EditText) findViewById(R.id.add_user_name);
                et2=(EditText) findViewById(R.id.add_user_pass);
                et3=(EditText) findViewById(R.id.add_user_repass);
                String x=et1.getText().toString();
                String y=et2.getText().toString();
                String z=et3.getText().toString();
                if(!x.equals("") && !y.equals("") && !z.equals(""))
                {
                    if(x.length()<5)
                        Toast.makeText ( getApplicationContext (), "Nazwa użytkownika musi mieć przynajmniej 5 znaków", Toast.LENGTH_SHORT ).show ();
                    else if(y.length()<5)
                        Toast.makeText ( getApplicationContext (), "Hasło musi mieć więcej niż 4 znaki", Toast.LENGTH_SHORT ).show ();
                    else {
                        if (y.equals ( z )) {
                            //Snackbar.make(view, "Dodaje użytkownika...", Snackbar.LENGTH_SHORT).show();
                            Toast.makeText ( getApplicationContext (), "Dodaje użytkownika...", Toast.LENGTH_SHORT ).show ();
                            Intent intent = new Intent(AddUserActivity.this, UserActivity.class);
                            startActivity (intent);
                            dodawanie_uzytkownika(x,y);
                            et1.setText("");
                            et2.setText("");
                            et3.setText("");
                            Toast.makeText ( getApplicationContext (), "Dodano użytkownika: "+x, Toast.LENGTH_SHORT ).show ();
                        }
                        else
                            Toast.makeText ( getApplicationContext (), "Podane hasła muszą się zgadzać!", Toast.LENGTH_SHORT ).show ();
                    }
                }
                else
                    Toast.makeText ( getApplicationContext (), "Proszę wypełnić wszystkie pola!", Toast.LENGTH_SHORT ).show ();
            }
        });
    }

    public void dodawanie_uzytkownika(String a, String b)
    {
        int i;
        for(i=0; i<10; i++) {
            if (users[i].get_nick_name() == "admin")
                break;
        }
        users[i].set_nick_name(a);
        users[i].set_newpass(b);
    }
}
