package com.example.ja.zakupy;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.NumberFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ZarzadcaBazy zb = new ZarzadcaBazy(this);
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    ArrayList<ExampleItem> list = new ArrayList<>();

    String numb, name, cost, expense_id;
    float prize;
    Boolean good=false;
    public float total_cost;
    public static final String final_cost = "";
    public static final String SHARED_PREFS = "sharedPrefs";

    private static final NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(getIntent().hasExtra("EXIT")) {
            finish();
        }

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new ExampleAdapter(list);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        TextView text_total = (TextView)findViewById(R.id.textView_total);
        EditText text_del = (EditText)findViewById(R.id.editText_del);
        Button button_del = (Button)findViewById(R.id.button_ok_del);
        text_del.setVisibility(View.INVISIBLE);
        button_del.setVisibility(View.INVISIBLE);
        text_total.setVisibility(View.VISIBLE);

        numb = getIntent().getStringExtra("numb");
        name = getIntent().getStringExtra("name");
        cost = getIntent().getStringExtra("cost");
        prize = getIntent().getFloatExtra("money", 0);
        good = getIntent().getBooleanExtra("good", false);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                /*Snackbar.make(view, "@String/new_expanse", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/

                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });

        if(good==true)
        {
            good=false;
            add_new();
        }

        getDataBase();

        //String temp_cost=Float.toString(total_cost);
        double cost_on_view = (double) total_cost;
        TextView total_p = (TextView)findViewById(R.id.textView_prize);
        total_p.setVisibility(View.VISIBLE);
        total_p.setText(currencyFormat.format(cost_on_view));
    }

    private static final int TIME_INTERVAL = 2000; // # milliseconds, desired time passed between two back presses.
    private long mBackPressed;

    @Override
    public void onBackPressed()
    {
        if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis())
        {
            super.onBackPressed();
            Intent fActivity = new Intent(getApplicationContext(), MainActivity.class);
            fActivity.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            fActivity.putExtra("EXIT", true);
            startActivity(fActivity);
            System.exit(0);
        }
        else
        {
            String tmp_help = getResources().getString(R.string.double_back);
            Toast.makeText(getBaseContext(), tmp_help, Toast.LENGTH_SHORT).show();
        }

        mBackPressed = System.currentTimeMillis();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void getDataBase()
    {
        String name_tmp, date_tmp, cost_tmp;

        total_cost=0;
        Cursor k = zb.getAll();
        while(k.moveToNext())
        {
            int nr = k.getInt(0);
            expense_id = Integer.toString(nr);
            expense_id +=".";
            name_tmp = k.getString(1);
            date_tmp = k.getString(2);
            cost_tmp = k.getString(3);
            total_cost+=Float.parseFloat(cost_tmp);
            list.add(new ExampleItem(name_tmp, date_tmp, cost_tmp, expense_id));
        }
    }


    public void add_new()
    {
        zb.addExpense(name, numb, cost);

        /*TextView cos1 = (TextView) findViewById(R.id.textView1);
        cos1.setText(name);
        TextView cos2 = (TextView) findViewById(R.id.textView2);
        cos2.setText(date);
        TextView cos3 = (TextView) findViewById(R.id.textView3);
        cos3.setText(cost);*/

        total_cost+=prize;
    }

    public void Clear(MenuItem item)
    {
        total_cost = 0;
        /*for(int i=1; i<list.size()+1; i++)
        {
            zb.delExpense(i);
        }*/

        list.clear();
        zb.del_all();

        Intent intent = new Intent(MainActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void remove_one(MenuItem item)
    {
        TextView text_total = (TextView)findViewById(R.id.textView_total);
        TextView text_prize = (TextView)findViewById(R.id.textView_prize);
        EditText text_del = (EditText)findViewById(R.id.editText_del);
        Button button_del = (Button)findViewById(R.id.button_ok_del);

        text_total.setVisibility(View.INVISIBLE);
        text_prize.setVisibility(View.INVISIBLE);
        text_del.setVisibility(View.VISIBLE);
        button_del.setVisibility(View.VISIBLE);
    }

    public void del_one_expense(View view)
    {
        EditText text_del = (EditText)findViewById(R.id.editText_del);

        int number_id=0;
        String temp_id = text_del.getText().toString();
        if(temp_id.equals(""))
            number_id=0;
        else
            number_id = Integer.parseInt(text_del.getText().toString());

        Cursor k = zb.getAll();
        int nr=0;
        Boolean find=false;
        while(k.moveToNext() && nr!=number_id)
        {
            nr = k.getInt(0);
            if(nr==number_id)   find=true;
        }
        if(find==true)
        {
            zb.delExpense(number_id);
        }
        else if(find==false)
        {
            String temp_info_exist = getResources().getString(R.string.temp_info_exist);
            Toast.makeText(getApplication().getBaseContext(), temp_info_exist, Toast.LENGTH_LONG).show();
        }
        Intent intent = new Intent(MainActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
