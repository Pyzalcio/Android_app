package com.example.michal.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

//AppCompatActivity
public class UserActivity extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void user_click(View view) {
        Intent intent;
        switch(view.getId())
        {
            case R.id.add_user:
                intent=new Intent(UserActivity.this, AddUserActivity.class);
                startActivity(intent);
                break;
            case R.id.edit_user:
                intent=new Intent(UserActivity.this, EditUserActivity.class);
                startActivity(intent);
                break;
            case R.id.remove_user:
                intent=new Intent(UserActivity.this, RemoveUserActivity.class);
                startActivity(intent);
                break;
        }
    }
}
