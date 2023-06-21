package com.stephen.petstop;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class list extends AppCompatActivity {
    String[] daftar;
    ListView listview1;
    Menu menu;
    protected Cursor cursor;
    DataHelper dbcenter;
    TextView header;
    public static list li;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        getSupportActionBar().hide();

        header = findViewById(R.id.hdr);
        Typeface head = Typeface.createFromAsset(getAssets(), "fonts/HappySundayRegular.ttf");
        header.setTypeface(head);
        li = this;
        dbcenter = new DataHelper(this);
        RefreshList();
    }
    public void RefreshList(){
        SQLiteDatabase db = dbcenter.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM titip",null);
        daftar = new String[cursor.getCount()];
        cursor.moveToFirst();
        for (int cc=0; cc<cursor.getCount(); cc++){
            cursor.moveToPosition(cc);
            daftar[cc] = cursor.getString(2).toString();
        }
        listview1 = findViewById(R.id.listview1);
        listview1.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, daftar));
        listview1.setSelected(true);
        listview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3) {
                final String selection = daftar[arg2];//.getItemAtPosition(arg2).toString;
                final CharSequence[] dialogitem = {"Lihat Detail Titip", "Ubah Data Titip", "Hapus Data Titip"};
                AlertDialog.Builder builder = new AlertDialog.Builder(list.this);
                builder.setTitle("Aksi");
                builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        switch (item){
                            case 0 :
                                Intent i = new Intent(list.this, detailtitip.class);
                                i.putExtra("nama", selection);
                                startActivity(i);
                                break;
                            case 1 :
                                Intent in = new Intent(list.this, updatetitip.class);
                                in.putExtra("nama", selection);
                                startActivity(in);
                                break;
                            case 2 :
                                AlertDialog alert = new AlertDialog.Builder(list.this).create();
                                alert.setTitle("Hapus Data");
                                alert.setMessage("Yakin hapus data titip "+selection+"?");
                                alert.setButton(AlertDialog.BUTTON_POSITIVE, "Ya, Hapus", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        SQLiteDatabase db = dbcenter.getWritableDatabase();
                                        db.execSQL("DELETE FROM titip WHERE nama = '"+ selection + "'");
                                        RefreshList();
                                    }
                                });
                                alert.setButton(AlertDialog.BUTTON_NEGATIVE, "Batal", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        alert.dismiss();
                                    }
                                });
                                alert.show();
                                break;
                        }
                    }
                });
                builder.create().show();
            }
        });
        ((ArrayAdapter)listview1.getAdapter()).notifyDataSetInvalidated();
    }
}