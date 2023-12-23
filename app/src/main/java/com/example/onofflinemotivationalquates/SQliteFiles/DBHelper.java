package com.example.onofflinemotivationalquates.SQliteFiles;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.onofflinemotivationalquates.Modals.QuoteModal;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    final  static String DBNAME= "onoffquates.db";
    final static  int VERSION= 2;
    int c=0;
    public DBHelper(@Nullable Context context)
    {
        super(context, DBNAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String creatTable = "create table quotes ( id integer primary key autoincrement, name text, quote text)";
        db.execSQL(creatTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("drop table if exists quotes");
            onCreate(db);
    }
    public boolean insertOrder(String name,String quote){
        SQLiteDatabase database = getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("quote",quote);
        long id = database.insert("quotes",null,contentValues);
        if(id<=0)
            return  false;
        else
            return true;
    }
    public ArrayList<QuoteModal> getQuotes(){
        ArrayList<QuoteModal> quoteModals = new ArrayList<>();
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("select * from quotes",null);
        if(cursor.moveToFirst()){
            while (cursor.moveToNext()){
                QuoteModal modal = new QuoteModal();
                modal.setId(cursor.getInt(0)+"");
                modal.setName(cursor.getString(1));
                modal.setQuote(cursor.getString(2));
                quoteModals.add(modal);
                c++;
            }
        }

        database.close();
        cursor.close();
        return quoteModals;
    }
    public int totalQuoate(){
//        SQLiteDatabase database = this.getWritableDatabase();
//        Cursor cursor = database.rawQuery("select count(*) from quotes",null);
//        cursor.getCount(); //returns integer how many records it has
        return  c;
    }
}
