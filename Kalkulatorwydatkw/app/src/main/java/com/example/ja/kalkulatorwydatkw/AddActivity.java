package com.example.ja.kalkulatorwydatkw;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.StringDef;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Formatter;

public class AddActivity extends AppCompatActivity {

    private static final NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        CalendarView calendar = (CalendarView)findViewById(R.id.calendar);

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2)
            {
                TextView cos = (TextView) findViewById(R.id.calendarInfo);
                String date = i2 + "/" + i1 + "/" + i;
                cos.setText(date);
            }
        });
    }


    public void add_new(View view)
    {
        String date = "", name_this = "", cost = "";
        Boolean good = false;
        float money = 0;

        EditText name = (EditText) findViewById(R.id.name_expenditure);
        EditText price = (EditText) findViewById(R.id.cost);
        TextView cos = (TextView) findViewById(R.id.calendarInfo);

        Intent intent = new Intent(AddActivity.this, MainActivity.class);

        if (price.length()!=0 && name.length()!=0)
        {
            date = cos.getText().toString();
            if(date.equals("Date of the expanse:") || date.equals("Data wydatku:"))
                date="";

            name_this = name.getText().toString();
            cost = price.getText().toString();

            money = Float.parseFloat(cost);
            good = true;

            intent.putExtra("date", date);
            intent.putExtra("name", name_this);
            intent.putExtra("cost", cost);
            intent.putExtra("good", good);
            intent.putExtra("money", money);

            String added = getResources().getString(R.string.added);
            String text_for = getResources().getString(R.string.text_for);
            String without_date = getResources().getString(R.string.without_date);
            String text_on = getResources().getString(R.string.text_on);

            double temp_cost = Double.valueOf(cost);
            String final_temp_cost = currencyFormat.format(temp_cost);

            if(date.equals(""))
            {
                Toast.makeText(getApplication().getBaseContext(), added+name_this+text_for+final_temp_cost+without_date, Toast.LENGTH_LONG).show();
            }
            else
            {
                Toast.makeText(getApplication().getBaseContext(), added+name_this+text_on+date+text_for+final_temp_cost, Toast.LENGTH_LONG).show();
            }
        }
        else
        {
            String addition_window = getResources().getString(R.string.addition_window);
            Toast.makeText(getApplication().getBaseContext(), addition_window+cost, Toast.LENGTH_LONG).show();
        }

        startActivity(intent);
    }

}
