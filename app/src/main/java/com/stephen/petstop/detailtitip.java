package com.stephen.petstop;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class detailtitip extends AppCompatActivity {
    protected Cursor cursor;
    DataHelper dbHelper;
    Button kembali;
    TextView tvid, tvjenis, tvnama, tvdurasi, tvperlakuan, tvbiaya, header;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailtitip);
        getSupportActionBar().hide();

        dbHelper = new DataHelper(this);
        header = findViewById(R.id.hdr);
        Typeface head = Typeface.createFromAsset(getAssets(), "fonts/HappySundayRegular.ttf");
        header.setTypeface(head);
        tvid = findViewById(R.id.id_hewan);
        tvjenis = findViewById(R.id.jenis_hewan);
        tvnama = findViewById(R.id.nama_hewan);
        tvdurasi = findViewById(R.id.durasi_penitipan);
        tvperlakuan = findViewById(R.id.perlakuan_khusus);
        tvbiaya = findViewById(R.id.biaya_penitipan);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM titip WHERE nama = '" +getIntent().getStringExtra("nama") + "'",null);
        cursor.moveToFirst();
        if (cursor.getCount()>0){
            cursor.moveToPosition(0);
            tvid.setText(cursor.getString(0).toString());
            tvjenis.setText(cursor.getString(1).toString());
            tvnama.setText(cursor.getString(2).toString());
            tvdurasi.setText(cursor.getString(3).toString());
            tvperlakuan.setText(cursor.getString(4).toString());
            tvbiaya.setText("Rp."+cursor.getString(5).toString()+",-");
        }
        kembali = findViewById(R.id.btn_kembali);
        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}