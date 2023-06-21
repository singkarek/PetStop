package com.stephen.petstop;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "titip.db";
    private static final int DATABASE_VERSION = 1;
    public DataHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //TODO Auto-generated construction stub
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        //TODO Auto-generated method stub
        String sql = "create table titip(id integer primary key autoincrement, jenis text null, nama text null, durasi integer null, perlakuan text null, biaya integer null);";
        Log.d("Data", "onCreate: " + sql);
        db.execSQL(sql);
        sql = "INSERT INTO titip (id, jenis, nama, durasi, perlakuan, biaya) VALUES ('1', 'Kucing', 'Calico', '4', 'Dilepas malam hari', '200.000');";
        db.execSQL(sql);
    }
    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2){
        //TODO Auto-generated method stub
    }
}
