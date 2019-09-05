package com.example.michal.home;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class WstepActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wstep);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        Intent intent;
        if (id == R.id.action_userOption) {
            intent = new Intent(WstepActivity.this,  UserActivity.class);
            startActivity(intent);
        }
        if (id == R.id.action_logout){
            intent = new Intent(WstepActivity.this,  MainActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
    public void przycisk(View view) {
        Intent intent;
        switch(view.getId())
        {
            case R.id.swiatlo:
                intent = new Intent(WstepActivity.this, Main2Activity.class);
                startActivity(intent);
                break;
            case R.id.zaslony:
                intent = new Intent(WstepActivity.this, ZaslonyActivity.class);
                startActivity(intent);
                break;
            case R.id.temp:
                intent = new Intent(WstepActivity.this, TempActivity.class);
                startActivity(intent);
                break;
            case R.id.went:
                intent = new Intent(WstepActivity.this, WentylacjaActivity.class);
                startActivity(intent);
                break;
        }
    }
}
