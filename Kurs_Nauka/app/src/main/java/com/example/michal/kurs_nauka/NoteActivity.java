package com.example.michal.kurs_nauka;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import java.io.PrintWriter;

import org.w3c.dom.ProcessingInstruction;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class NoteActivity extends AppCompatActivity {

    EditText et;
    String text = "";
    private String path = Environment.getExternalStorageDirectory().toString()+"/Kenis_Toys";
    private final int memory_access=5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d ("aktywność", "onCreate");
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_note );
        Toolbar toolbar = (Toolbar) findViewById ( R.id.toolbar );
        setSupportActionBar( toolbar );
        getSupportActionBar().setDisplayHomeAsUpEnabled ( true );

        et = (EditText) findViewById ( R.id.editText );
        et.setText(text);

        if(ActivityCompat.shouldShowRequestPermissionRationale(NoteActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {}
        else
        {
            ActivityCompat.requestPermissions(NoteActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, memory_access);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode)
        {
            case memory_access:
                if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_DENIED)
                {
                    Toast.makeText ( getApplicationContext(), "Bez zgody dostępu do pamięci, zapis jest niemożliwy", Toast.LENGTH_SHORT ).show();
                }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate( R.menu.menu_note, menu );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId ();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_save) {
            create_dir();
            if(create_file())
                Toast.makeText ( getApplicationContext(), "Zapisano poprawnie", Toast.LENGTH_SHORT).show();
            //finish();
            return true;
        }
        return super.onOptionsItemSelected ( item );
    }

    @Override
    protected void onStart() {
        Log.d ("aktywność", "onStart");
        super.onStart ();
    }

    @Override
    protected void onResume() {
        Log.d ("aktywność", "onResume");
        super.onResume ();
    }

    @Override
    protected void onPause() {
        Log.d ("aktywność", "onPause");
        text=et.getText ().toString ();
        super.onPause ();
    }

    @Override
    protected void onRestart() {
        Log.d ("aktywność", "onRestart");
        super.onRestart ();
    }

    @Override
    protected void onDestroy() {
        Log.d ("aktywność", "onDestroy");
        super.onDestroy ();
    }

    public void create_dir()
    {
        File folder = new File(path);
        if(!folder.exists())
        {
            try
            {
                folder.mkdirs();
            }
            catch (Exception e)
            {
                Toast.makeText ( getApplicationContext(), e.toString(), Toast.LENGTH_LONG ).show();
            }
        }
    }
    public boolean create_file()
    {
        File file = new File(path+"/"+System.currentTimeMillis()+".txt");
        FileOutputStream fout;
        OutputStreamWriter out_writer;
        try {
            PrintWriter zapis = new PrintWriter(path+"/"+System.currentTimeMillis()+".txt");
            zapis.println("Ala ma kota, a kot ma Alę");
            zapis.close();
            return true;
        }
        catch (Exception e)
        {
            Toast.makeText ( getApplicationContext(), e.toString(), Toast.LENGTH_LONG ).show();
            return false;
        }
    }
}
