package com.example.ja.zakupy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ZarzadcaBazy extends SQLiteOpenHelper{

    public ZarzadcaBazy(Context context) {
        super(context, "kontakty.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(
                "create table expense(" +
                        "nr integer primary key autoincrement," +
                        "name text," +
                        "date text," +
                        "cost text);" +
                        "");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }


    public void addExpense(String name, String date, String cost){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues wartosci = new ContentValues();
        wartosci.put("name", name);
        wartosci.put("date", date);
        wartosci.put("cost", cost);
        db.insertOrThrow("expense",null, wartosci);
    }

    public Cursor getAll(){
        String[] kolumny={"nr","name","date","cost"};
        SQLiteDatabase db = getReadableDatabase();
        Cursor kursor =db.query("expense",kolumny,null,null,null,null,null);
        return kursor;
    }

    public void delExpense(int id)
    {
        SQLiteDatabase db =  getWritableDatabase();
        String[] arguments={""+id};
        db.delete("expense", "nr=?", arguments);
    }

    public void del_all()
    {
        SQLiteDatabase db =  getWritableDatabase();
        db.delete("expense", null, null);
    }

}
